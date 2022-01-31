
# TP1   Comment nommer les commits selon leur type/section/grosseur/etc.    
Nous allons nommer nos commits selon les issues que nous devons réaliser. Les commits auront comme premier mot la ou les actions qui ont été effectuées à l'intérieur du développement de ce commit. L'objectif étant de voir rapidement à chaque commit quelle action générale a été posée sur la partie du projet. Les messages des commits doivent inclure la ou les sections affectées par les changements. Dans le cadre de notre projet, les noms des commits correspondent à des classes bien définies dans le projet, ce qui permet de se retrouver facilement.  


# Quoi et quand commiter?
Il est pertinent de commit lorsqu'un ajout dans un projet fonctionne et ne brise rien dans le fonctionnement du reste du programme. Les actions nécessitant un commit peuvent être par exemple: un ```refactoring```, un ajout de ```feature```, un ```bugfix```, un ```merge``` et des ajouts de méthodes et classes considérés comme significatifs. En résumé, faire des commits fréquents, mais pertinents selon le fil du développement.  La grosseur des commits, ou encore l'ampleur, doit correspondre à des changements significatifs et importants dans le développement. La fréquence des commits ne devrait pas être trop élevée pour faciliter la tâche de suivi des versions dans l'éventualité que l'on voudrait revenir en arrière, mais pas trop gros non plus pour garder le suivi régulier et pertinent, toujours en considérant que l'on pourrait vouloir revenir en arrière dans le développement.



# Quelles sont les branches de base (qui sont communes et qui existeront toujours) et quels sont leurs rôles (chacune)?  
Les branches de base, dans notre projet, sont ```dev``` et ```main```. La branche dev est la branche principale de développement où tous les nouveaux ```features``` seront ajoutés (ou ```merge```) de leurs branches respectives. Une fois les ```features``` ajoutés et testés, ces modifications pourront être ```merge``` dans le main pour s'ajouter à la version officielle du projet.




# Quelle branche est LA branche principale (contenant le code officiellement intégré et pouvant être remis)? 
La branche principale avec le code officiel pouvant être remis est la branche ```master``` (main). Le code de cette branche est préalablement révisé et testé dans d'autres branches du projet avant d'être annexé pour assurer que normalement tout fonctionne et que le code est bien écrit selon les standards établis.



# Quand créer une nouvelle branche?   
Une nouvelle branche devrait être créée lorsque du travail de développement est effectué et qu'il y a un risque d'affecter le reste du fonctionnement du programme. Une nouvelle branche permet de développer et tester dans un environnement isolé du projet et donne la possibilité d'abandonner facilement une partie du développement sans affecter la branche ```dev``` et par conséquent la branche ```main```.



# Quand faire une demande de changement / d'intégration (pull request / merge request) et sur quelle branche la faire?
Le développement est effectué sur une branche enfant de la branche ```dev```. Une fois que ces changements ont été testés et que tout semble fonctionner sans altérer négativement le reste du programme, le développeur fait une demande de changement (```pull request```) de la branche ```dev``` pour que ces changements puissent être vérifiés par d'autres personnes dans le projet. Les coéquipiers valident le travail et une fois qu'ils jugent que tout fonctionne correctement, ils font une demande d'intégration (```merge request```) pour que la nouvelle partie du code soit intégrée à la branche cible.

Project:
![Project](https://user-images.githubusercontent.com/73801331/151677316-8d65f752-904e-439d-a61c-16107a61e3d6.PNG)

Milestone:
![Milestone](https://user-images.githubusercontent.com/73801331/151677321-903db925-d76b-45c6-9616-c06c57df838f.PNG)

Issues:
![Save seller issue](https://user-images.githubusercontent.com/73801331/151677335-eba46291-12fd-4e61-8a3d-3fb7152b9b6e.PNG)
![seller get issue](https://user-images.githubusercontent.com/73801331/151677339-c07c3478-3d6a-48ee-9226-76c36ae4ca96.PNG)
![sellers exception issue](https://user-images.githubusercontent.com/73801331/151677342-14c6baf8-35ef-43cb-b135-bcdb10c8f1ec.PNG)

PR:
![PR1](https://user-images.githubusercontent.com/73801331/151677302-2bb115d9-444c-4f81-b2fe-7604fabcbb41.PNG)
![PR2](https://user-images.githubusercontent.com/73801331/151677348-3f3601fb-d3e5-41ca-a698-4cf37c5f0199.PNG)
![PR3](https://user-images.githubusercontent.com/73801331/151677351-90e9fa0d-d881-40bc-aa4c-f8f0daf8be7e.PNG)


