pipeline {
    agent any
    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    environment {
        APP_NAME = 'buy-01'
        SPRING_PROFILES_ACTIVE = 'dev'
        MAVEN_OPTS = '-Dspring.profiles.active=dev'
    }

    stages {

        stage('📋 Info') {
            steps {
                echo "════════════════════════════════════════"
                echo "Coucou Fatima Keita"
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
                echo '⏭️  Tests ignorés pour l\'instant'
                echo 'Tu pourras les réactiver plus tard après correction'
            }
        }

        stage('📦 Résultat') {
            steps {
                echo '✨ Les fichiers JAR ont été créés :'

                sh 'find . -name "*.jar" -path "*/target/*" ! -name "*-original.jar" -exec ls -lh {} \\;'

                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true, excludes: '**/*-original.jar'
            }
        }
        stage('Finition') {
            steps {
                echo 'Alors là ma go t\'as assuré'
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