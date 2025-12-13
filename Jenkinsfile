pipeline {
  agent {
    docker {
      image 'maven:3.9.6-eclipse-temurin-17'
      args '-v $WORKSPACE/.m2:/root/.m2'
    }
  }
  options {
    timestamps()
    ansiColor('xterm')
    buildDiscarder(logRotator(numToKeepStr: '10'))
  }
  triggers {
    pollSCM('H/5 * * * *')
  }
  stages {
    stage('Checkout') {
      steps {
        checkout([$class: 'GitSCM',
          branches: [[name: '*/main']],
          userRemoteConfigs: [[url: 'https://github.com/SandhyaD1023/devops-course-task.git']]
        ])
      }
    }
    stage('Build') {
      steps {
        sh 'mvn -v'
        sh 'mvn -B -e clean compile'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn -B -e test'
      }
      post {
        always {
          junit 'target/surefire-reports/*.xml'
        }
      }
    }
    stage('Package') {
      steps {
        sh 'mvn -B -e package'
      }
      post {
        success {
          archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
      }
    }
    stage('Deploy (placeholder)') {
      when { branch 'main' }
      steps {
        sh 'echo "Deploying artifact... (placeholder step for CD)"'
      }
    }
  }
  post {
    success { echo 'Pipeline completed successfully.' }
    failure { echo 'Pipeline failed. Check the logs for details.' }
  }
}
