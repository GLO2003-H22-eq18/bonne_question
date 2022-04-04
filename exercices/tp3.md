# TP3
### Rétrospective - Intégration continue et tests

#### Pipeline CI

- 1. Environ 5 minutes pour vérifier que chacun des tests étaient réussis.
- 2. Environ 10-15 secondes, le temps de voir si la pastille est verte ou non.
- 3. 
   - 3.1 : Un point positif pour le processus est qu'à chacun des push sur chacune des branches, on peut voir si la totalité de nos tests passe encore. Ainsi, on s'assure de la qualité de notre travail et on réduit les risques de merge des branches problématiques. 
   - 3.2 : Ça montre aux autres que les tests ont bien passé. On peut avoir une preuve visuel pour toute l'équipe que le code est fonctionnel et ainsi ne pas avoir à retester systématiquement manuellement tous les tests à chaque pull. Demande beaucoup moins d'effort pour l'ensemble de l'équipe.
   - 3.3 : Permettre d'avoir de bonnes rétroactions sur le travail des autres. En effet, on peut voir facilement si une personne de l'équipe à de la difficulté ou si elle ne soucis pas de la qualité de son travail.
- 4. Trop s'appuyer sur les tests qui roulent automatique et ne plus rien tester localement. Ceci peut devenir un problème pour l'équipe qui pourrait devenir de plus en plus paresseuse et ainsi faire des push inutile simplement pour tester leur code au lieu de le tester en local.

#### Tests 
- 1. Au départ,la proportion de l'implémentation du code fonctionnel et l'implémentation des tests étaient relativement semblable (50/50), mais plus projet avance et avec l'ajout de nouvelles features le temps pour l'implémentation du code augmente beaucoup plus que celui pour l'implémentation des tests. En effet, l'ajout de nouvelles features peut prendre beaucoup de temps puisqu'il s'agit de quelque chose de nouveau et donc du code que nous n'avons jamais fait. Cependant, pour l'implémentation des tests il s'agissait souvent de petite modification par rapport à du code déjà produit, donc très facile et rapide à écrire.
- 2. Nous devons passer plus de temps pour l'implémentation de nos tests et pour bien diviser la charge de travail, cependant comparativement au dernier livrable nous avons diminuer la taille de nos PRs et de nos issues puisque nous avions bien pris le temps de planifier nos tests et ce que nous voulions tester pour une issue en particulier. Évidemment, la planification et l'implémentation des tests consommait beaucoup de temps. Toutefois, le temps investi pour les tests nous a permis de sauver du temps pour l'implémentation du code fonctionnel en nous structurant. 
- 3. Oui, le fait d'avoir des tests nous permet de s'assurer que nos fonctions ont le comportement voulu. En effet, ça nous donne une preuve tangible que le code est réellement fonctionnel pour tous les cas, même en cas d'exception. Avoir les tests nous permettent de ne pas avoir peur de faire des changements puisque nous avons accès à une vérification rapide en tout temps de nos fonctions et de leur comportement. Un bon exemple serait l'utilisation de technologies diverses comme mongoDB. Même si nous décidions de changer complètement cette technologie, nos tests ne changeraient pas et assureraient le bon fonctionnement de nos classes. Aussi, on peut facilement voir l'effet d'un changement dans une partie précise du code de l'application sur l'ensemble du programme grâce à l'automatisation de nos tests. Cela nous donne la confiance qu'effectuer un changement, aussi petit soit-il, n'affecte pas le reste du programme.
- 4. 
   - 4.1 Dans les tests systèmes, nous avons beaucoup de variables dans le fichiers E2E utils. Nous allons les mettres dans les fichiers qui les utilisent directement.
   - 4.2 Pour l'ensemble des tests, nous pourrions mettre les fonctions qui sont utilisées par les tests à la fin du fichier de test qui l'utilise.
   - 4.3 Nous pourrions rapatrier les fonctions du domaine qui sont seulement utilisées par les tests dans les fichiers de tests.

### Stories

#### Story 1
  Un vendeur peut afficher plusieurs produits dans un paquet de produit à un prix avantageux. 
#### Story 2
  Un acheteur peut voir l'historique de toutes les offres qu'il a effectué.
#### Story 3
  Un acheteur reçoit une alerte lorsqu'une de ses offres est surpassée par une autre.
  
  
### Planification du travail sur Github

#### Projet:
![Project](https://user-images.githubusercontent.com/73801331/161141736-1f62123e-e38a-464c-a66d-753cf70ff32c.PNG)

#### Milestone:
![Milestone](https://user-images.githubusercontent.com/73801331/161141784-8d91c57b-c23c-44ad-8a74-04a4aa09cb33.PNG)

#### Issues:
No.1
![Issue1](https://user-images.githubusercontent.com/73801331/161141851-c7d30e96-c547-43ec-a9d8-c8342e048a0c.PNG)

No.2
![Issue2](https://user-images.githubusercontent.com/73801331/161141869-15788cf5-f95d-4fde-8658-9ed1848a70a1.PNG)

No.3
![Issue3](https://user-images.githubusercontent.com/73801331/161141887-37a521d2-0f41-4c3f-91fa-1ba9c8bd0e8a.PNG)

#### Pull Request (PR):
No.1
![PR1 1](https://user-images.githubusercontent.com/73801331/161141961-f986bedb-bfb6-47da-bd6c-b59ea07eeb60.PNG)
![PR1 2](https://user-images.githubusercontent.com/73801331/161141965-25bbb52e-e7d3-48b7-9b92-6e516be3a6c5.PNG)

No.2
![PR2](https://user-images.githubusercontent.com/73801331/161141993-9813de4d-d00f-45a8-8293-60fecc150b6f.PNG)

No.3
![PR3 1](https://user-images.githubusercontent.com/73801331/161142020-345d7d21-a250-4883-8e8f-211bdbf652a5.PNG)
![PR3 2](https://user-images.githubusercontent.com/73801331/161142022-ff992f55-6612-4099-9838-d77e9a19fa8c.PNG)

#### Arbre des commits et de branches:
![Commit_tree](https://user-images.githubusercontent.com/73801331/161142089-a9eda8c4-a223-4ad6-bb4a-dc1cd382bc13.PNG)
