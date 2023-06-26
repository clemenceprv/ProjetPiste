# ProjetPiste

## Base de données
Voici le lien pour télécharger la base de données projetpermis1 : https://we.tl/t-vmk21NUiXu

## Configuration de l'application
Si l'application ne se lance pas, il faut verifier que le fichier
[Application.properties](./src/main/resources/application.properties) est bien configuré.
Verifier que le port soit bien :
spring.datasource.url=jdbc:mysql://<strong>localhost:3306</strong>/projetpermis1

## Informations 
<ul>
<li>Le design n'est pas géré dans le projetPiste
<li>Nous avons concu cette app pour tester les apprenants lors d'un examen officiel de 
piste / permis blanc</li>
<li>Pour pouvoir jouer à un jeu, il faut s'affecter ou affecter quelqu'un à une mission. L'apprenant sera donc 
automatiquement affecté aux actions liées</li>
<li>L'évaluation se comporte comme un QCM. Le score est calculé selon les valeurs valueIfCheck et valueIfNotCheck</li>
<li>Le score sera affiché et stocké à la fin</li>


</ul>
