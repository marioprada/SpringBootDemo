pipeline {  
  agent any
  stages {
    stage('Preparation') {
      steps {
        echo 'Init Preparation stage'
        echo "PATH = ${env.PATH}"
        echo "JAVA_HOME = ${env.JAVA_HOME}"
        echo "JRE_HOME = ${env.JRE_HOME}"
      }
    }
    stage('Build in Master') {
      when {
        branch 'master'
      }
      steps {
        echo 'Init Build stage'
        script {
          if (isUnix()) {
            sh "'mvn' -Dmaven.test.failure.ignore clean verify"
          } else {
            bat(/"mvn" -Dmaven.test.failure.ignore clean package/)
          }
        }

      }
    }
    stage('Test') {
      steps {
        echo 'Init Test stage'
      }
    }
    stage('Deploy in Master') {
      when {
        branch 'master'
      }
      steps {
        echo 'Init Deploy stage'
        script {
          currentBuild.result = 'SUCCESS'
        }

      }
    }
  }
  tools {
    maven 'Maven'
    jdk 'JDK'
  }
  environment {
    JAVA_HOME = "${tool 'JDK'}"
    JRE_HOME = "${tool 'JDK'}/jre"
    PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
  }
  post {
    failure {
      script {
        currentBuild.result = 'FAILURE'
      }


    }

    always {
      echo "Finished '${JOB_NAME}' (${BUILD_NUMBER}) ${currentBuild.result}"

    }

  }
}
