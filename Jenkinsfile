pipeline {
    agent none
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dh_cred')
    }
    stages {
        stage('Checkout'){
            agent any
            steps{
                checkout scm
            }
        }

        stage('Init'){
            agent any
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }

        stage('Build Backend'){
            agent any
            //when {
              //changeset "**/backend/*.*"
            //beforeAgent true
           //}
            steps {
                dir('springboot-backend'){
                    sh 'docker build -t $DOCKERHUB_CREDENTIALS_USR/backend-springboot:$BUILD_ID .'
                    sh 'docker push $DOCKERHUB_CREDENTIALS_USR/backend-springboot:$BUILD_ID'
                    sh 'docker rmi $DOCKERHUB_CREDENTIALS_USR/backend-springboot:$BUILD_ID'
                }
            }
        }

        stage('Build Frontend'){
            agent any
            //when {
               //changeset "**/frontend/*.*"
             //beforeAgent true
            //}
            steps {
                dir('angular-frontend'){
                    sh 'docker build -t $DOCKERHUB_CREDENTIALS_USR/frontend-angular:$BUILD_ID .'
                    sh 'docker push $DOCKERHUB_CREDENTIALS_USR/frontend-angular:$BUILD_ID'
                    sh 'docker rmi $DOCKERHUB_CREDENTIALS_USR/frontend-angular:$BUILD_ID'
                }
            }
        }

        stage('logout'){
            agent any
            steps {
                sh 'docker logout'
            }
        }

    }
}
