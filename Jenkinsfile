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
                sh 'mvn -DskipTests  clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test -Pprod sonar:sonar \
                    -Dsonar.projectKey=integ \
                    -Dsonar.organization=integ \
                    -Dsonar.host.url=https://sonarcloud.io \
                    -Dsonar.login=e7a9f87ecbc37f8459da7b3e493a18f4d017452a'
            }
        }
    }
}
