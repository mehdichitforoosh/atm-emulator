pipeline {
    agent any
    environment {
            COMPOSE_PROJECT_NAME="atm-emulator"
    }
    stages {
        stage('Checkout') {
            steps {
                parallel (
                    "ATM Emulator Repository": {
                        checkout([
                            $class: 'GitSCM',
                            branches: [[name: '*/master']],
                            doGenerateSubmoduleConfigurations: false,
                            extensions: [[$class: 'CleanBeforeCheckout'], [$class: 'RelativeTargetDirectory', relativeTargetDir: '/atm-emulator']],
                            submoduleCfg: [],
                            userRemoteConfigs: [[url: 'https://github.com/mehdichitforoosh/atm-emulator.git']]
                        ])
                    },
                )
            }
        }
        stage('Deploy Development Environment') {
            steps {
                echo 'Deploying....'
                sh 'ls'
                sh 'docker-compose --version'
                sh 'docker-compose build'
                sh 'docker-compose up -d --force-recreate --remove-orphans'
                sh 'docker-compose ps --services'
            }
        }
    }
    post {
        always {
            // Always cleanup after the build.
            cleanWs()
            deleteDir()
        }
        failure {
            mail bcc: '', body: 'This is a failure.:-(', cc: '', from: '', replyTo: '', subject: 'Jenkins Deploy Pipeline Failure', to: 'mehdichitforoosh@gmail.com'
        }
    }
}