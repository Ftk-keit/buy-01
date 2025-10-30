pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'JDK17'
        nodejs 'NodeJS 20'
    }

    environment {
        APP_NAME = 'buy-01'
        SPRING_PROFILES_ACTIVE = 'dev'
        MAVEN_OPTS = '-Dspring.profiles.active=dev'

    }

    stages {
        stage('Tests back') {
            steps {
                echo "Coucou Ftk "
                echo 'Démarrage des tests du back'
            }
        }
        stage('📋 Info') {
            steps {
                echo "════════════════════════════════════════"
                echo "🚀 Démarrage du build #${env.BUILD_NUMBER}"
                echo "📦 Application: ${APP_NAME}"
                echo "🌿 Branche: ${env.GIT_BRANCH}"
                echo "════════════════════════════════════════"

                sh 'java -version'
                sh 'mvn -version'
            }
        }

        stage('🔨 Build & 🧪 Tests') {
            steps {
                echo 'Compilation des tests jUnit'
                sh 'mvn clean package '
            }
            post {
                success {
                    echo 'Tests passés avec success ftk'
                    echo 'Bien joué'
                }
                unsuccessful {
                    echo 'Mhum tima il y a une erreur'
                }
            }
        }

        stage('📦 Résultat') {
            steps {
                echo '✨ Les fichiers JAR ont été créés :'

                sh 'find . -name "*.jar" -path "*/target/*" ! -name "*-original.jar" -exec ls -lh {} \\;'

                archiveArtifacts artifacts: '**/target/*.jar',
                                                fingerprint: true,
                                                excludes: '**/*-original.jar',
                                                allowEmptyArchive: true
            }
        }
        stage('Finition') {
            steps {
                echo 'Alors là ma go t\'as assuré'
            }
        }
        stage('Tests front') {
            steps {
                dir('frontend') {
                    echo 'Démarrage de l\'installation des dépendances'
                    sh 'npm install'
                }
            }
        }
        stage('Build front') {
            steps {
                dir('frontend') {
                    echo 'Démarrage du build'
                    sh 'npm run build'
                }
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker-compose build'
            }
        }
    }

    post {
        success {
            echo '🎉 ════════════════════════════════════════'
            echo '✅ BUILD RÉUSSI !'
            echo '🎉 ════════════════════════════════════════'
        }
        always {
                 junit '**/target/surefire-reports/*.xml'
        }
        failure {
            echo '❌ ════════════════════════════════════════'
            echo '💥 BUILD ÉCHOUÉ !'
            echo '❌ ════════════════════════════════════════'
        }
    }
}