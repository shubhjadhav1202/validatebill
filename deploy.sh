#!/bin/bash
# ------------------------------------------------------------
# Spring Boot deploy script (JAR + WAR)
# Usage: ./deploy.sh <artifact-path> [app-name]
# Example: ./deploy.sh /tmp/myapp-1.0.0.jar myapp
#          ./deploy.sh /tmp/myapp-1.0.0.war myapp
# ------------------------------------------------------------
set -euo pipefail

ARTIFACT="${1:?Artifact path dya (jar/war)}"
APP_NAME="${2:-myapp}"

# ---- Config (garaj pramane badla) ----
APP_DIR="${APP_DIR:-/opt/${APP_NAME}}"            # JAR deploy directory
BACKUP_DIR="${APP_DIR}/backup"
LOG_DIR="${APP_DIR}/logs"
TOMCAT_HOME="${TOMCAT_HOME:-/opt/tomcat}"
APP_PORT="${APP_PORT:-8080}"
HEALTH_URL="${HEALTH_URL:-http://localhost:${APP_PORT}/actuator/health}"
JAVA_OPTS="${JAVA_OPTS:--Xms256m -Xmx512m}"
SPRING_PROFILE="${SPRING_PROFILE:-prod}"
HEALTH_RETRIES=20
# --------------------------------------

log() { echo "[$(date '+%F %T')] $*"; }

[ -f "$ARTIFACT" ] || { log "ERROR: $ARTIFACT file sapadli nahi"; exit 1; }

EXT="${ARTIFACT##*.}"
TS=$(date +%Y%m%d%H%M%S)

health_check() {
  log "Health check suru..."
  for i in $(seq 1 $HEALTH_RETRIES); do
    if curl -sf "$HEALTH_URL" > /dev/null; then
      log "Application UP ahe ✔"
      return 0
    fi
    sleep 3
  done
  return 1
}

deploy_jar() {
  mkdir -p "$APP_DIR" "$BACKUP_DIR" "$LOG_DIR"
  local PID_FILE="${APP_DIR}/${APP_NAME}.pid"
  local CURRENT="${APP_DIR}/${APP_NAME}.jar"

  # Stop old process
  if [ -f "$PID_FILE" ] && kill -0 "$(cat "$PID_FILE")" 2>/dev/null; then
    log "Junna process band karto (PID $(cat "$PID_FILE"))"
    kill "$(cat "$PID_FILE")"
    for i in {1..15}; do
      kill -0 "$(cat "$PID_FILE")" 2>/dev/null || break
      sleep 1
    done
    kill -9 "$(cat "$PID_FILE")" 2>/dev/null || true
  fi

  # Backup
  [ -f "$CURRENT" ] && cp "$CURRENT" "${BACKUP_DIR}/${APP_NAME}-${TS}.jar"

  # Deploy
  cp "$ARTIFACT" "$CURRENT"

  log "Navin JAR start karto..."
  nohup java $JAVA_OPTS -jar "$CURRENT" \
      --spring.profiles.active="$SPRING_PROFILE" \
      > "${LOG_DIR}/${APP_NAME}.log" 2>&1 &
  echo $! > "$PID_FILE"

  if ! health_check; then
    log "Health check fail! Rollback karto..."
    kill "$(cat "$PID_FILE")" 2>/dev/null || true
    local LAST_BACKUP
    LAST_BACKUP=$(ls -t ${BACKUP_DIR}/${APP_NAME}-*.jar 2>/dev/null | head -1 || true)
    if [ -n "$LAST_BACKUP" ]; then
      cp "$LAST_BACKUP" "$CURRENT"
      nohup java $JAVA_OPTS -jar "$CURRENT" --spring.profiles.active="$SPRING_PROFILE" \
        > "${LOG_DIR}/${APP_NAME}.log" 2>&1 &
      echo $! > "$PID_FILE"
      log "Rollback zhala: $LAST_BACKUP"
    fi
    exit 1
  fi

  # Fakt last 5 backups theva
  ls -t ${BACKUP_DIR}/${APP_NAME}-*.jar 2>/dev/null | tail -n +6 | xargs -r rm -f
}

deploy_war() {
  local WEBAPPS="${TOMCAT_HOME}/webapps"
  mkdir -p "$BACKUP_DIR"

  # Backup
  [ -f "${WEBAPPS}/${APP_NAME}.war" ] && cp "${WEBAPPS}/${APP_NAME}.war" "${BACKUP_DIR}/${APP_NAME}-${TS}.war"

  log "Tomcat stop karto..."
  "${TOMCAT_HOME}/bin/shutdown.sh" || true
  sleep 5

  # Junna exploded folder + war kadha
  rm -rf "${WEBAPPS}/${APP_NAME}" "${WEBAPPS}/${APP_NAME}.war"
  cp "$ARTIFACT" "${WEBAPPS}/${APP_NAME}.war"

  log "Tomcat start karto..."
  "${TOMCAT_HOME}/bin/startup.sh"

  HEALTH_URL="http://localhost:${APP_PORT}/${APP_NAME}/actuator/health"
  health_check || { log "WAR deploy fail zhala"; exit 1; }

  ls -t ${BACKUP_DIR}/${APP_NAME}-*.war 2>/dev/null | tail -n +6 | xargs -r rm -f
}

log "Deploy suru: $ARTIFACT (type: $EXT)"
case "$EXT" in
  jar) deploy_jar ;;
  war) deploy_war ;;
  *)   log "ERROR: fakt .jar kinva .war support ahe"; exit 1 ;;
esac
log "Deploy purna zhala ✔"