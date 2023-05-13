pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps {
        git 'https://github.com/mohamedidmansour/ci-cd-project-ov.git'
      }
    }

    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }

    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Deploy') {
      steps {
        sh 'java -jar target/democicd-0.0.1-SNAPSHOT.jar'
      }
    }
  }

  post {
    always {
      deleteDir()
    }
  }
}
