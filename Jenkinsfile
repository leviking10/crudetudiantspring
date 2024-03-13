pipeline {
    agent {
        docker {
            image 'mysql:8.0'
            args '--health-cmd "mysqladmin ping" --health-interval 10s --health-timeout 5s --health-retries 5 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=crudetudiantdb -e MYSQL_USER=user -e MYSQL_PASSWORD=user -p 3306:3306'
        }
    }
    environment {
        DB_HOST = '127.0.0.1'
        DB_NAME = 'crudetudiantdb'
        DB_USERNAME = 'user'
        DB_PASSWORD = 'user'
        MAVEN_HOME = '/usr/share/maven'
        JAVA_HOME = '/usr/lib/jvm/java-17-openjdk'
        // Remplacez par vos propres identifiants
        DOCKER_HUB_USERNAME = credentials('DOCKER_HUB_USERNAME')
        DOCKER_HUB_ACCESS_TOKEN = credentials('DOCKER_HUB_ACCESS_TOKEN')
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Set up JDK 17') {
            steps {
                sh 'sudo apt-get update && sudo apt-get install -y openjdk-17-jdk'
                sh 'java -version'
            }
        }
        stage('Build et Test') {
            steps {
                sh 'mvn -B clean package --file pom.xml'
            }
        }
        stage('Generate timestamp') {
            steps {
                script {
                    env.TIMESTAMP = sh(returnStdout: true, script: "date +'%Y%m%d%H%M%S'").trim()
                }
            }
        }
        stage('Login to Docker Hub') {
            steps {
                sh 'echo $DOCKER_HUB_ACCESS_TOKEN | docker login --username $DOCKER_HUB_USERNAME --password-stdin'
            }
        }
        stage('Build and Push Docker Image') {
            steps {
                sh "docker build -t $DOCKER_HUB_USERNAME/crudetudiantspring:$TIMESTAMP ."
                sh "docker push $DOCKER_HUB_USERNAME/crudetudiantspring:$TIMESTAMP"
            }
        }
    }
}
