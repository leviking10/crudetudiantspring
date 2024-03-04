# Utiliser une image de base officielle Java avec JDK 17
FROM openjdk:17-jdk

WORKDIR /crudetudiantspring
# Copier les fichiers exécutables .jar dans le conteneur
COPY target/crudetudiant.jar /crudetudiantspring/crudetudiant.jar
EXPOSE 8080
# Exécuter l'application
CMD java -jar crudetudiant.jar

