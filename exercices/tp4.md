# TP4
## Retrospective finale
1. Décrivez 2 problématiques que possèdent votre processus et développer 2 plans distincts afin de les résoudres. Soyez constructifs dans vos critiques et évitez de mettre la faute sur une ou un groupe de personne en particulier.

La première problématique de notre processus se trouverait au niveau de la diffusion des informations du projet dans certaines portions du développement. Puisque nous sommes 5 dans l'équipe, il a été logique de diviser la charge du développement en plusieurs sous-groupes quelques fois dans le projet. Ainsi, il y a des personnes qui ne touchent qu'à certaines parties du développement ce qui cause le problème que les autres membres n'ont pas connaissance de comment les technologies ont été intégrées ou simplement comment les utiliser dans le logiciel. Une solution à ce problème serait de réserver un petit moment à chaque semaine pour que chaque personne puisse résumer le travail effectué, les technologies utilisées et les problèmes rencontrés. Ce moment peut être très rapide et être fait sous forme de stand-up dans l'optique de diffuser les connaissances et des informations à toute l'équipe en plus de juger de l'état d'avancement du projet. 

Une autre problématique de notre développement est que nous n'avons pas basé pas notre développement sur les tests des fonctionnalités. Nous avons fait le développement de chaque portion des travaux pratiques en premier pour venir seulement tester seulement à fin. Dans le meilleur des cas, nous aurions basé notre développement sur la conception ou le design des tests dès le départ pour éviter des problèmes durs à trouver. Heureusement, aucun problème majeur n'est apparu avec notre technique, mais il se pourrait que dans un plus gros projet, il soit primordial d'adopter une approche TDD (test driven development).


2. Décrivez la démarche que vous aviez entrepris afin d'intégrer de nouveaux outils technologiques. Quelles étaient les étapes du processus? Comment avez-vous réagis aux différents bogues? Exploriez-vous à l'aide de tests unitaires ou manuels? Qu'avez-vous appris suite à cette démarche?

Afin d'intégrer de nouveaux outils technologiques, nous avons suivi un certain processus. Tout d'abord, nous avons commencé par sonder le terrain des outils technologiques en fonction de nos besoins, c'est-à-dire l'analyse de la qualité, de la sécurité et de la couverture du code. Nous avons donc regardé en ligne de nombreuses listes (notamment celle se trouvant ici: https://github.com/analysis-tools-dev/static-analysis, proposée par un collègue sur le discord du cours, merci à lui) couvrant ces sujets. Celles-ci nous ont permis d'avoir une bonne idée des offres sur le marché. Une fois une liste d'outils à notre disposition, nous faisions un tri en fonction de nos besoins et limitations. Par exemple, nous éliminions les outils payants. Certains outils fonctionnent pour plusieurs langages de programmation, mais certains sont plus spécialisés et fonctionnent mieux avec certains langages. Dans notre cas, puisque nous utilisons Java, on essayait de prendre des outils très compatibles avec celui-ci, par exemple Jacoco. Une fois la sélection faite, nous intégrions ces outils dans notre projet. Nous n'avons pas fait face à beaucoup de bugs, mais quand c'était le cas, c'était souvent des erreurs d'inattention, et suivre la documentation nous débloquait. Concernant l'utilisation des outils, nous les avons principalement testés manuellement. En effet, simplement en intégrant les outils, on faisait plusieurs pushs et ceux-ci lançaient les analyses, ce qui nous montrait si cela fonctionnait correctement. En faisant la nouvelle "feature", nous avons aussi pu tester l'outil. Suite à cette démarche, nous avons pu remarquer qu'intégrer ces technologies au début du projet est une très bonne idée, car cela facilite le travail par la suite. En effet, on aurait pu voir directement les "code smells" et voir si l'on fait une erreur évidente (du moins pour l'analyseur). Nous avons fait nos tests des outils manuellement, mais nous comprenons qu'avoir des tests unitaires pour ceux-ci pourrait être une très bonne idée et pourrait être réutilisé pour différents projets, ce qui n'est pas négligeable. Nous avons aussi appris que prendre la peine de faire ne serait-ce qu'une courte recherche, plutôt que de se lancer tête baisser dans un nouvel outil est une très bonne idée. En effet, la recherche que nous avons effectuée nous a sauvé beaucoup de temps, mais nous a aussi permis de comprendre comment les différents outils fonctionnent en général.


3. Quels sont les bons coups de votre équipe? De quelles parties êtes-vous fiers? Nommez-en 3.

Nous avons réussi à garder un excellent niveau de constance entre les différentes phases du projet pour continuer notre développement selon des bons standards de gestion des versions, d'architecture du système et d'application des notions du clean code. Le résultat final du projet est mieux structuré et plus facile à comprendre. Il est également plus facile pour l'équipe de valider quelles nouvelles fonctionnalités seraient plus facile à implémenter dans le projet grâce à l'architecture choisie.

Nous avons réussi à répartir le travail de façon assez équitable pour que chaque coéquipier puisse toucher à plusieurs portions du développement et ainsi, l'apprentissage s'est fait plus efficacement tout au long du projet. Cette répartition égale à été possible grâce à l'attention avec laquelle nous avons séparé les travaux pratiques et les tâches en issues sur github. Ces issues avaient relativement toutes le même temps de complétion en plus de permettre aux membres de l'équipe de se lancer sur celles souhaitées sans enfermer personne dans des portions précises du développement. Ainsi, chaque personne a eu l'occasion de toucher à plusieurs parties du développement.

Finalement, le niveau de communication au sein de l'équipe était excellent dans le sens où tous les membres communiquaient efficacement sur la plateforme Discord en envoyant des messages d'avancement de façon régulière. Également, tous les membres étaient présents dans la grande majorité des rencontres ce qui a permis de faire un développement plus efficace grâce aux différents commentaires posés dans les rencontres.


4. Quel conseil donneriez-vous aux prochains étudiants qui doivent faire ce projet?

Un conseil qu'on peut donner à une équipe qui va faire le projet serait de bien s'appliquer lors des pull-requests en attitrant une et préférablement plusieurs personnes à la révision du code développé dans les branches. Dans l'optique d'ensuite merge le contenu de la nouvelle branche avec plus de confiance dans les branches principales pour éviter le plus possible de laisser des erreurs dans le code et de réviser en fonction des changements faits en parallèle dans le reste du code pour éviter les conflits. Cette pratique permet de grandement conserver la cohésion dans le projet entre les différentes étapes et membres de l'équipe.


5. Quels apprentissages, trucs ou techniques appris dans ce projet croyez-vous pouvoir utiliser plus tard? Décrivez-en au moins 2. Cela peut être des apprentissages techniques, pratiques, sur le travail d'équipe ou encore par rapport au processus.

Bien que nous avons utiliser Git pour le contrôle des versions dans d'autres cours, nous avons vraiment pris le temps d'utiliser l'outil de façon plus précise dans ce projet en utilisant des branches pour le développement, des pull-request pour intégrer les fonctionnalités, en plus de rester constant dans les normes pour documenter les versions. C'est une expérience qui nous sera utile pour le reste de notre parcours universitaire en plus d'être un bon exemple d'utilisation dans un cadre professionnel puisque beaucoup de compagnies utilisent des outils comme Git pour contrôler les versions des projets avec précision. 

Nous avons également, pour la première fois, installé un CI pipeline directement avec l'aide de la plateforme Github pour s'initier à l'intégration continue en nous permettant de tester lorsqu'on remet une nouvelle version fonctionnelle du logiciel en faisant des changements sur le projet. Avec une augmentation de la popularité du DevOps, il y a des bonnes chance que nous ayons à réutiliser des outils d'intégration continue dans nos vies professionnelles.



## Open Source

1. Nommez 3 avantages à contribuer à des projets open source en tant qu'entreprise et justifiez en quoi cela peut être bénéfique pour tous.

La participation d'une entreprise dans un projet open source permet de mettre à jour un projet existant avec les ressources d'une entreprise. Donc, probablement plus rapidement et plus efficacement que si plusieurs individus contribuaient de façon indépendante. L'entreprise fait donc avancer un projet déjà existant pour le bénéfice de tous et peut l'utiliser en plus bien souvent ```free of charge```.  C'est un positif pour la communauté qui voit un projet avancer et pour l'entreprise qui sauve des coûts.

Un défi que rencontrent souvent les entreprises est de créer des systèmes représentatifs des réalités du marché visé et s'assurer que les utilisateurs ont les fonctionnalités désirées. En contribuant et en utilisant des projets open source, on s'assure d'une manière que la communauté (ou le marché) est investi dans le développement et que normalement, les utilisateurs visés par le projet sont mieux représentés puisqu'ils contribuent à son avancement. 



2. Décrivez 3 défis qu'impose la mise en place d'un projet open source et justifiez.
https://opensource.guide/starting-a-project/
https://opensource.com/life/14/6/12-challenges-open-source-projects

Le premier défi de mise en place est qu'il faut considérer le temps requis pour gérer les contributions d'autres individus. En effet, si le projet open source attire l'engouement de plusieurs personnes, il se peut qu'il devienne plus dur de gérer toutes les demandes, les commentaires et les requêtes de support. Il faut donc considérer que le temps accordé à son propre projet open-source risque de ne pas être strictement pour le développement du code. D'autant plus qu'il faut prendre le temps de créer des bons documents pour la gestion du projet avec la communauté (```Open source license, README, Contributing guidelines, Code of conduct```) et pour protéger le projet avec les lois sur la propriété intellectuelle.

Ensuite, la mise en place d'un projet open source implique de créer un document pour décrire la vision du projet pour que les contributeurs puissent faire du développement dans la trajectoire souhaitée. Cette vision demande d'avoir une idée claire de ce que le projet devrait contenir, ce qui peut être une étape difficile à faire puisqu'il peut être complexe de se projeter dans le futur dans un projet en cours de développement et d'en plus, l'exprimer dans un document.

Finalement, un autre défi pour la mise en place d'un projet open source est de rendre le projet attirant pour que des individus veulent colaborer. Il faut donc faire une documentation intéressante, un concept qui suscite l'intérêt et bien sûr un nom qui attrape l'oeil.


3. Quelle information vous a-t-elle le plus surprise à propos de l'open source?

https://alternativeto.net/

Une information qui nous a grandement surpris est que beaucoup de frameworks et des applications très utilisés comme par exemple ```Ruby on Rails``` sont en open source et la communauté peut contribuer pour améliorer des fonctionnalités et ajouter de la documentation. Voir des très gros projets comme ce framework renforce le principe avancé par l'open source que l'entraide à travers la communauté est la meilleure manière d'avancer des projets. Il n'est donc pas nécessairement avantageux de garder le monopole sur le développement d'un projet pour garder privée la propriété intellectuelle du projet en entier.

## Outils de métriques
- 1 outil d'analyse de la **qualité du code**

Sonarcloud/SonarqubeScan:![sonarcloud-global](https://user-images.githubusercontent.com/73568394/165558480-8834283a-156e-43ae-8fbf-f49a54f64a1f.JPG)
![sonarcloud-code-smells](https://user-images.githubusercontent.com/73568394/165558538-178ddfd4-6e3e-4c49-bcb4-765c99518f22.JPG)
![code-quality-analysis](https://user-images.githubusercontent.com/73568394/165558563-87f5b343-3a7a-476c-8497-c5bf2c26d491.JPG)

- 1 outil de détection de **failles de sécurité**

Sonarcloud/SonarqubeScan:![sonarcloud-security](https://user-images.githubusercontent.com/73568394/165558693-ff0a9b4d-484d-4fee-a3dc-b4ab9754ee6b.JPG)

Snyk:![security-snyk-global](https://user-images.githubusercontent.com/73568394/165558725-835c141a-3d6c-4a64-9cb2-69da440ccbcc.JPG)
![security-snyk-detailed](https://user-images.githubusercontent.com/73568394/165558746-ce9699b8-0681-47b3-b3de-0519e764ee99.JPG)

- 1 outil de mesure du **test coverage**

Jacoco (rapport affiché directement dans Sonarcloud):
![code-coverage-dev](https://user-images.githubusercontent.com/73568394/165558900-465d2756-1174-4886-a8e2-c9be9d7a9ab1.JPG)
![newpr-no-tests-code-coverage](https://user-images.githubusercontent.com/73568394/165558977-419b6191-efb7-412c-9e24-d434ba6727be.JPG)
![code-coverage-new-pr-detailed](https://user-images.githubusercontent.com/73568394/165558993-dc1bbdae-21e0-4f78-a833-fb403f7a2ad3.JPG)


## Explications de la license choisie
La license se trouve dans le fichier LICENSE. Celle qui a été choisit est la license de MIT, pour plusieurs raisons.
- __Favorise le _open-source___: La license procure plusieurs permissions: elle permet d'utiliser le code, le modifier, le publier, le vendre, etc. Cela encourage la
distribution et le développement du code source.
- __Simple d'utilisation__: un projet qui possède cette license ont très peu de restrictions (comparément à d'autres licenses) et peuvent ainsi travailler sans crainte de faire une action interdite. Par exemple, la license Apache oblige un individu à indiquer quels changements ont été appliqués au code source dans le cas où cet individu souhaite republier le même code.
- __Protège les développeurs et le fondateur du projet__: si l'on décide de ne pas utiliser une license, tous les individus contribuant au projet deviennent un propriétaire exclusif de leur travail, et seulement de celui-ci. Cela signifie que personne ne peut copier, utiliser, distribuer ou modifier les contributions des autres, et cela s'applique également au fondateur du projet. Tiré de cette page web: [https://opensource.guide/legal/](https://opensource.guide/legal/).





