//  pipeline
pipeline {
    agent any


    stages {
        stage('Demo for chrome') {
            steps {
                echo 'chrome'
                bat "mvn -Dtest=Chrome test"
            }
        }
        stage('Demo for firefox') {
            steps {
                echo ' firefox'
                bat"mvn -Dtest=Firefox test  "
            }
        }
        stage('Demo for edge') {
            steps {
                echo 'edge'
                bat "mvn -Dtest=Edge test"
            }
        }
    }
}
