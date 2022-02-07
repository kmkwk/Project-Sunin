pipeline {
    agent none
    options { skipDefaultCheckout(true) }
    stages {
        stage('Checkout repository') {
            agent any
            steps {
                checkout scm
            }
        }
        stage('Build and test') {
            agent {
                docker {
                    image 'gradle:6.6.1-jdk11-openj9'
                    args '-v /root/.m2:/root/.m2'
                }
            }
            steps {
                sh 'gradle clean build -x test'
            }
        }
        stage('Docker build') {
            agent any
            steps {
                sh 'docker build -t backend:latest .'
            }
        }
        stage('Docker run') {
            agent any
            steps {
                sh 'docker ps -f name=backend -q | xargs --no-run-if-empty docker container stop'
                sh 'docker container ls -a -fname=sbor_dev -q | xargs -r docker container rm'
                sh 'docker images --no-trunc --all --quiet --filter="dangling=true" | xargs --no-run-if-empty docker rmi'
                sh 'docker run -d --name backend -p 8080:8080 backend:latest'
            }
        }
    }
}