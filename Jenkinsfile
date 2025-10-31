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
        
        EUREKA_SERVER_PORT = '8761'
        DB_USERNAME = 'mongodb'
        DB_NAME = 'buy01'
        DB_HOSTNAME = 'mongodb'
        DB_PORT = '27017'
        DB_AUTH_DB = 'admin'
        KEY_ALIAS = 'buy0x'
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
                echo 'Compilation des tests jUnit '
                sh 'mvn clean package '
                junit '**/target/surefire-reports/*.xml'
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
                echo 'Les fichiers JAR ont été créés :'

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
                withCredentials([
                    string(credentialsId: 'db-password', variable: 'DB_PASS'),
                    string(credentialsId: 'jwt-secret', variable: 'JWT_SECRET'),
                    string(credentialsId: 'aws-access-key-id', variable: 'AWS_ACCESS_KEY_ID'),
                    string(credentialsId: 'aws-secret-access-key', variable: 'AWS_SECRET_ACCESS_KEY'),
                    string(credentialsId: 'keystore-password', variable: 'KEY_STORE_PASSWORD')
                ]) {
                    sh 'docker compose build'
                }
            }
        }

        stage('🚀 Deploy Local'){
            steps{
                script {
                    echo 'DÉPLOIEMENT LOCAL EN COURS...'
                    withCredentials([
                        string(credentialsId: 'db-password', variable: 'DB_PASS'),
                        string(credentialsId: 'jwt-secret', variable: 'JWT_SECRET'),
                        string(credentialsId: 'aws-access-key-id', variable: 'AWS_ACCESS_KEY_ID'),
                        string(credentialsId: 'aws-secret-access-key', variable: 'AWS_SECRET_ACCESS_KEY'),
                        string(credentialsId: 'keystore-password', variable: 'KEY_STORE_PASSWORD')
                    ]) {

                       sh '''
                       echo "Sauvegarde des images actuelles..."
                       docker images --format "{{.Repository}:{{.Tag}}" | grep buy-01| while read image;
                       do docker tag $image ${image}-backup || true
                       done
                       '''

                       sh 'docker compose down || true'

                       sh 'docker compose up -d'

                       echo 'Attente du démarrage des services (60 secondes)...'

                       sleep (time: 60, unit:'SECONDS')

                       sh '''
                           echo "Vérification de l\'état des services..."

                           docker compose ps

                           RUNNING=$(docker compose ps --filter "status=running" -q | wc -l )
                           echo "Conteneurs actifs : $RUNNING / 10"

                           if [ "$RUNNING" -lt 8 ]; then
                               echo "ÉCHEC : Seulement $RUNNING conteneurs actifs (minimum requis: 8)"
                               exit 1
                           fi
                           echo "Déploiement réussi ! Tous les services sont opérationnels."
                       '''

                    }
                }
            }
            post {
                failure {
                    script {
                        echo 'RollBack en cours'
                        withCredentials([
                            string(credentialsId: 'db-password', variable: 'DB_PASS'),
                            string(credentialsId: 'jwt-secret', variable: 'JWT_SECRET'),
                            string(credentialsId: 'aws-access-key-id', variable: 'AWS_ACCESS_KEY_ID'),
                            string(credentialsId: 'aws-secret-access-key', variable: 'AWS_SECRET_ACCESS_KEY'),
                            string(credentialsId: 'keystore-password', variable: 'KEY_STORE_PASSWORD')
                        ]) {
                            sh 'docker compose down'
                            sh '''
                                    echo "Restauration des images précédentes..."
                                    docker images --format "{{.Repository}}:{{.Tag}}" | grep backup | while read image; do
                                        original=$(echo $image | sed 's/-backup//')
                                        docker tag $image $original
                                    done
                                '''
                            sh 'docker compose up -d'
                            echo 'Rollback terminé : ancienne version restaurée'
                        }
                    }

                }
                success {
                        echo '✅ ════════════════════════════════════════'
                        echo '🎉 DÉPLOIEMENT LOCAL RÉUSSI !'
                        echo '📍 Application accessible sur :'
                        echo '   - Frontend : http://localhost:4200'
                        echo '   - API Gateway : https://localhost:8080'
                        echo '   - Eureka : http://localhost:8761'
                        echo '✅ ════════════════════════════════════════'
                }
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
    }
}