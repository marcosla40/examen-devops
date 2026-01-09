pipeline {
    agent any

    tools {
        maven 'Maven3.9.11'
    }

    environment {
        VERSION = '1.3.0'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Move jar') {
            steps {
                echo "Eliminando directorio versiones...."
                script {
                    if (fileExists('versiones')) {
                        bat 'rd /s /q versiones'
                    }
                }
            }
            post {
                success {
                    echo "Se crea el directorio versiones con la última versión de la api"
                    bat 'mkdir versiones'
                    bat "copy target\\examen-${VERSION}.jar versiones\\"
                }
            }
        }
    }
}