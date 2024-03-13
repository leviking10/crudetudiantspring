pipeline {
    agent any

    environment {
        // Définir les variables d'environnement nécessaires
        MYSQL_ROOT_PASSWORD = 'root'
        MYSQL_DATABASE = 'crudetudiantdb'
        MYSQL_USER = 'user'
        MYSQL_PASSWORD = 'user'
        DOCKER_IMAGE = 'mleviking/crudetudiantspring' // Remplacer par votre nom d'utilisateur Docker réel
        DOCKER_CREDENTIALS_ID = 'dockerhub-credentials' // ID des credentials Docker dans Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Set up JDK') {
            steps {
                script {
                    // Utiliser le plugin 'Tool Environment Plugin' pour configurer JDK
                    env.JAVA_HOME="${tool 'JDK17'}"
                    env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Démarrer le service MySQL via Docker
                    sh 'docker run --name mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_PASSWORD} -e MYSQL_DATABASE=${MYSQL_DATABASE} -e MYSQL_USER=${MYSQL_USER} -e MYSQL_PASSWORD=${MYSQL_PASSWORD} -d -p 3306:3306 mysql:8.0'
                    // Attendre que MySQL soit prêt
                    sh 'until docker exec mysql mysqladmin ping --silent; do sleep 1; done'
                    // Exécuter les tests
                    sh 'mvn test'
                }
            }
        }

        stage('Build and Push Docker Image') {
            steps {
                script {
                    def timestamp = new Date().format("yyyyMMddHHMSS", TimeZone.getTimeZone('UTC'))
                    def dockerImageName = "${DOCKER_IMAGE}:${timestamp}"
                    sh "docker build -t ${dockerImageName} ."
                    sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD" // Utiliser avec prudence, préférer les credentials
                    sh "docker push ${dockerImageName}"
                }
            }
        }
    }

    post {
        always {
            // Nettoyer après les builds
            sh 'docker rm -f mysql'
            sh 'docker image prune -af'
        }
    }
}
