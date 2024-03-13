pipeline {
    agent any

    environment {
        // Variables d'environnement
        DOCKER_IMAGE = "mleviking/crudetudiantspring"
        DOCKER_CREDENTIALS_ID = "dockerhub-credentials"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git 'https://github.com/leviking10/crudetudiantspring.git'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    // Utilisation de BUILD_ID pour l'unicité
                    def dockerImage = docker.build("${DOCKER_IMAGE}:${env.BUILD_ID}")
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        dockerImage.push("${env.BUILD_ID}")
                        dockerImage.push("latest")
                    }
                }
            }
        }
        post {
            always {
                script {
                    // Nettoyer les images Docker inutilisées
                    sh "docker image prune -af"
                    // Nettoyer les conteneurs Docker arrêtés
                    sh "docker container prune -f"
                    // Supprimer les volumes non utilisés si nécessaire
                    sh "docker volume prune -f"
                }
            }
        }
    }
}
