pipeline {
    agent any
    tools { 
        // initialize jdk and mvnHome based on Jenkins Global Tools
        maven 'Maven' 
        jdk 'JDK' 
    }
    environment { 
        JAVA_HOME="${tool 'JDK'}"
        JRE_HOME="${tool 'JDK'}/jre"
        PATH="${env.JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Preparation') { // for display purposes
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
                  // Run the maven build
                script{
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
                currentBuild.result = 'SUCCESS'
            }
        }
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

