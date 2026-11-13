pipeline {
    agent any

    // Manage Jenkins > Tools madhe ya navane configure kara
    tools {
        maven 'Maven3'
        jdk   'JDK17'     // pom.xml madhil <java.version> shi match vhava
    }

    parameters {
        booleanParam(name: 'SKIP_TESTS', defaultValue: false, description: 'Tests skip karaychya?')
    }

    environment {
        APP_NAME   = 'validatebill'
        APP_DIR    = '/var/jenkins_home/apps/validatebill'
        APP_PORT   = '8081'   // Jenkins 8080 vaparto; tumchya app cha port ithe dya
        // Actuator nasel tar app cha kontahi working URL dya
        HEALTH_URL = 'http://localhost:8081/actuator/health'
    }

    stages {
        stage('Checkout') {
            steps { checkout scm }
        }

        stage('Build') {
            steps {
                script {
                    def skip = params.SKIP_TESTS ? '-DskipTests' : ''
                    sh "mvn -B clean package ${skip}"
                }
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/validatebill-*.jar', excludes: 'target/*.original', fingerprint: true
            }
        }

        stage('Deploy') {
            steps {
                // dontKillMe nasel tar build sampla ki Jenkins app process kill karto
                withEnv(['JENKINS_NODE_COOKIE=dontKillMe']) {
                    sh '''
                        JAR=$(ls target/validatebill-*.jar | grep -v original | head -1)
                        echo "Deploying: $JAR"
                        chmod +x deploy.sh
                        ./deploy.sh "$JAR" "$APP_NAME"
                    '''
                }
            }
        }
    }

    post {
        success { echo 'Build + Deploy successful ✔' }
        failure { echo 'Pipeline failed ✘ - Console Output tapasa' }
    }
}