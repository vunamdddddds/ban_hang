pipeline {
    agent any

    stages {
        stage('Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/vunamdddddds/ban_hang.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                bat 'xcopy target\\*.jar C:\\deploy\\springapp\\ /Y'
                bat 'for /f "tokens=2" %%i in (\'tasklist ^| findstr java\') do taskkill /F /PID %%i'
                bat 'start "" java -jar C:\\deploy\\springapp\\demo2-0.0.1-SNAPSHOT.jar'
            }
        }
    }
}
