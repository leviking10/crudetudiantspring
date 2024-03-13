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
                    agent {
                        docker {
                            image 'maven:3.8.4-openjdk-17' // Utilisez une image Docker Maven
                            args '-v /var/jenkins_home/.m2:/root/.m2' // Montez le cache local Maven pour éviter de retélécharger les dépendances
                        }
                    }
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
    }
}
