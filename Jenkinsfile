//  pipeline
pipeline {
    agent any


    stages {
        stage('Demo for chrome') {
            steps {
                echo 'chrome'
                bat "mvn -Dtest=Main test"
            }
        }
        stage('Demo for firefox') {
            steps {
                echo ' firefox'
                bat"mvn -Dtest=Main test  "
            }
        }
        stage('Demo for edge') {
            steps {
                echo 'edge'
                bat "mvn -Dtest=Main test"
            }
        }
    }
}