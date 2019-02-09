# Intégration Continue

**Le projet**

Nous avons développé une application Java SPRING avec un CRUD classic. Ce projet comporte Docker avec un conteneur Jenkins, SonarQube afin de lancer des analyses de code et des tests unitaires.

**L'équipe**

Notre groupe est composé des personnes suivantes :
- Christopher LUKOMBO
- Mickael MOREIRA
- Guillaume TAKO

URL sonar : 
https://sonarcloud.io/organizations/integ/projects


Pour démarrer l'analyse sonar lancer la commande :

mvn sonar:sonar \
  -Dsonar.projectKey=integ \
  -Dsonar.organization=integ \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=e7a9f87ecbc37f8459da7b3e493a18f4d017452a
  
L'option -Pprod permet de lancer le profile prod en exécutant les tests unitaires coté front également :
  
mvn clean package -Pprod sonar:sonar \
  -Dsonar.projectKey=integ \
  -Dsonar.organization=integ \
  -Dsonar.host.url=https://sonarcloud.io \
  -Dsonar.login=e7a9f87ecbc37f8459da7b3e493a18f4d017452a