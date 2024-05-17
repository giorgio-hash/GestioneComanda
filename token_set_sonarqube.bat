@echo off
set /p a=Nome progetto: 
set /p b=token: 

echo .\mvnw clean verify site spotbugs:spotbugs sonar:sonar -Dsonar.projectKey=%a% -Dsonar.projectName='%a%' -Dsonar.host.url=http://localhost:9000 -Dsonar.token=%b% -Dsonar.java.pmd.reportPaths=/target/pmd.xml -Dsonar.java.checkstyle.reportPaths=/target/checkstyle-result.xml -Dsonar.java.spotbugs.reportPaths=/target/spotbugsXml.xml > scan.bat

echo Created scan.bat with the provided values.
PAUSE
