pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-u root -v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Pprod'
            }
            post {
                always {
                    karma 'target/test-results/karma/*.xml'
                    jacoco 'target/test-results/coverage/jacoco/*.xml'
                }
            }
        }
    }
}
