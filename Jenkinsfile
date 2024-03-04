pipeline {
    agent any

    environment {
        // Définir les variables d'environnement nécessaires
        DOCKER_IMAGE = "mleviking/crudetudiantspring"
        // Assurez-vous de configurer ces identifiants dans Jenkins
        DOCKER_CREDENTIALS_ID = "dockerhub-credentials"
        KUBECONFIG_CREDENTIALS_ID = "kubeconfig-credentials"
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

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}:${env.BUILD_ID}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', DOCKER_CREDENTIALS_ID) {
                        docker.image("${DOCKER_IMAGE}:${env.BUILD_ID}").push()
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    kubernetesDeploy(
                        kubeconfigId: KUBECONFIG_CREDENTIALS_ID,
                        configs: 'k8s.yml',
                        enableConfigSubstitution: true
                    )
                }
            }
        }
    }
}
