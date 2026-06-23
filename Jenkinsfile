pipeline {
    agent any

    environment {
        AWS_REGION = 'us-east-1'
        ECR_REPO = '095279701594.dkr.ecr.us-east-1.amazonaws.com/fheloops-cloud-manager'
        IMAGE_TAG = "${BUILD_NUMBER}"
    }

    stages {

        stage('Checkout Code') {
            steps {
                git branch: 'main',
                url: 'https://github.com/Fhelobanty/fheloops-cloud-manager.git'
            }
        }

        stage('Build Application') {
            steps {
                sh './gradlew clean build -x test'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                docker build -t fheloops-cloud-manager:${IMAGE_TAG} .
                '''
            }
        }

stage('Login to ECR') {
    steps {
        sh '''
            whoami
            echo HOME=$HOME

            ls -la $HOME
            ls -la $HOME/.aws || true

            cat $HOME/.aws/config || true
            cat $HOME/.aws/credentials || true

            aws configure list || true

            aws sts get-caller-identity --no-cli-pager || true

            aws ecr get-login-password \
            --region ${AWS_REGION} \
            --no-cli-pager | docker login \
            --username AWS \
            --password-stdin 095279701594.dkr.ecr.us-east-1.amazonaws.com
        '''
    }
}

        stage('Tag Image') {
            steps {
                sh '''
                docker tag fheloops-cloud-manager:${IMAGE_TAG} \
                ${ECR_REPO}:${IMAGE_TAG}
                '''
            }
        }

        stage('Push Image') {
            steps {
                sh '''
                docker push ${ECR_REPO}:${IMAGE_TAG}
                '''
            }
        }
    }
}