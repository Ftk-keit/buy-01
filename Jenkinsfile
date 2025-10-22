pipeline {
    agent any

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    environment {
        APP_NAME = 'buy-01'
    }

    stages {

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

        stage('🔨 Build') {
            steps {
                echo '⚙️  Compilation du projet Spring Boot...'

                sh 'mvn clean package -DskipTests'

                echo '✅ Compilation terminée !'
            }
        }

        stage('🧪 Tests') {
            steps {
                echo '🔬 Exécution des tests unitaires...'

                sh 'mvn test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }

        stage('📦 Résultat') {
            steps {
                echo '✨ Le fichier JAR a été créé :'

                sh 'ls -lh target/*.jar'

                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '🎉 ════════════════════════════════════════'
            echo '✅ BUILD RÉUSSI !'
            echo '🎉 ════════════════════════════════════════'
        }
        failure {
            echo '❌ ════════════════════════════════════════'
            echo '💥 BUILD ÉCHOUÉ !'
            echo '❌ ════════════════════════════════════════'
        }
        always {
            echo '🧹 Nettoyage terminé'
        }
    }
}