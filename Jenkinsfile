pipeline {
    agent any

    tools {
        maven 'Maven3.9.11'
        jdk 'jdk-21'
    }

    environment {
        VERSION = '1.3.0-SNAPSHOT'
        JAR_NAME = 'examenMocMLA'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Move jar') {
            steps {
                echo "Eliminando directorio versiones...."
                script {
                    if (fileExists('versiones')) {
                        sh 'rm -rf versiones'
                    }
                }
            }
            post {
                success {
                    echo "Se crea el directorio versiones con la última versión de la api"
                    sh 'mkdir versiones'
                    sh "cp target/examen-${VERSION}.jar versiones/"
                }
            }
        }
    }
}