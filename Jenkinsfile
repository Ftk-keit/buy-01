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
        SPRING_PROFILES_ACTIVE=dev


    }

    stages {
        stage('📋 Info') {
            steps {
                echo "════════════════════════════════════════"
                echo "Coucou Fatima Keita "
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
                echo 'Compilation des tests jUnit'
                sh 'mvn clean test -DskipTests=false'
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

                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true, excludes: '**/*-original.jar'
            }
        }
        stage('Package') {
                    steps {
                        echo '📦 Packaging du projet...'
                        sh 'mvn clean package -DskipTests'
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