# Utiliser une image de base officielle Java avec JDK 17
FROM openjdk:17-oracle

# Copier les fichiers exécutables .jar dans le conteneur
COPY target/crudetudiant.jar crudetudiant.jar

# Exécuter l'application
ENTRYPOINT ["java","-jar","/crudetudiant.jar"]
