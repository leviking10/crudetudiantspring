pipeline {
    agent any

    environment {
        // Définir les variables d'environnement nécessaires
        DOCKER_IMAGE = "mleviking/crudetudiantspring" // Votre image Docker
        DOCKER_CREDENTIALS_ID = "dockerhub-credentials" // ID des credentials Docker dans Jenkins
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/leviking10/crudetudiantspring.git'
            }
        }
        stage('Build and Push Docker Image') {
            steps {
                script {
                    def timestamp = new Date().format("yyyyMMddHHmmss", TimeZone.getTimeZone('UTC'))
                    def dockerImageName = "${DOCKER_IMAGE}:${timestamp}"

                    // Construire l'image Docker
                    sh "docker build -t ${dockerImageName} ."

                    // Se connecter au Docker Hub (ou autre registre) pour pousser l'image
                    // en utilisant les credentials stockés dans Jenkins
                    withCredentials([usernamePassword(credentialsId: DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                        sh "docker push ${dockerImageName}"
                    }
                }
            }
        }
    }
}
