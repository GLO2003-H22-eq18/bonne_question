# TP2

### Stratégie des branches et _commits_ 
Nous avons décidé en équipe de choisir la stratégie **_Git flow_** afin de séparer nos différentes branches selon nos _features_ à développer. Notre branche principale est ``dev`` qu'on _merge_ à notre branche ``main`` lors des remises (_jalons_).

![BranchAndCommitStrategy](https://uploads.toptal.io/blog/image/129305/toptal-blog-image-1551794424851-b3d5928bc33edfc954ef460062e5cbcc.png)
(ref: https://www.toptal.com/software/trunk-based-development-git-flow)

### Métriques mesurées
| Métrique mesurée                       | Moyenne | Minimum | Maximum |
| -------------------------------------- |:-------:|:-------:|:-------:|
| Implémenter une issue                  | 1h30 min | 30 min   | 3 h      |
| Intégrer une PR                        | 30 min   | 15 min   | 1 h      |
| Nb. de personnes sur une issue         | 2       | 1       | 3       |
| Nb. de personnes pour _review_ une PR  | 2       | 1       | 3       |
| Issues en implémentation en même temps | 3       | 2       | 5       |
| PR en _review_ à tout moment           | 1       | 0       | 2       |


### Rétrospective sur le processus

**1.** Selon nous, les issues prenaient un temps raisonnable à être réalisées puisque leur charge de travail était convenable en n'étant ni trop grosses, ni trop petites. En moyenne, elles prenaient 1h30 à être complétées. Ainsi, nous concluons que le temps idéal approximatif pour la réalisation d'une issue est d'environ 1h30. Du côté des pull-requests, elles prenaient généralement elles aussi un temps raisonnable à être complétées. En moyenne, elles permettaient de fermer deux issues, ce qui est raisonnable. Ainsi, nous jugeons que le temps approximatif pour chaque pull-request est d'environ 3 heures de travail (2 issues de 1h30 en moyenne) et de 30 minutes pour son intégration.

**2.** Plus une _issue/pull-request_ est de grande taille, plus elle est longue à terminer. Comme déjà mentionné, la taille de nos _issues_ et _pull-requests_ était convenable. Chaque _pull-request_ permettait de fermer en moyenne 2 issues. Sinon, il peut arriver que certaines _issues_ différentes sont en réalité très similaires dans le travail à effectuer, ce qui implique que la réalisation de la première _issue_ peut prendre considérablement plus de temps à faire puisqu'il faut penser à inclure du temps pour la recherche d'information. La taille à donc un effet important sur le temps de réalisation d'_issues/pull-requests_, mais il ne faut pas oublier d'attribuer une valeur temporelle aux apprentissages à travers le projet, surtout que ces valeurs sont en constant changement. Il est d'autant plus important de considérer le temps pour la recherche dans un contexte acamdémique d'un projet comme celui-ci.

**3.** Amélioration du processus
- Uniformisation du code: lors des prochains livrables, il serait pertinent de s'assurer que le code est uniforme et ce, tout au long du travail en commençant le tout début. Par exemple, durant l'écriture des tests, il aurait été judicieux que chaque membre se base davantage sur les tests des autres pour ne pas avoir à uniformiser le code à la toute fin.
- Certaines _pull-requests_ contenaient trop de changements. Ceci était majoritairement causé par des _commits_ trop lourds en incluant parfois plusieurs rectifications d'erreurs de plusieurs fichiers en même temps. À l'avenir, il faut que chaque membre de l'équipe pense à _commit_ ses changements plus fréquemment et créer les pull-requests lorsque nécessaire.
- Les pull-requests étaient presque toujours en _review_ par un membre de l'équipe autre que le créateur de la _pull-request_. Toutefois, certaines _pull-requests_ ont été créées puis _mergées_ sans l'accord et le _review_ d'au moins un autre membre de l'équipe. Cela a créé de la confusion au sein de l'équipe, qui avait parfois du mal à déterminer où le projet en était. À l'avenir, toutes les _pull-requests_ devraient être en _review_ et _merged_ par au minimum un autre membre de l'équipe.

### Planification du travail sur Github

#### Project:
![Project](https://user-images.githubusercontent.com/73801331/156835979-b0ed4660-0f92-478b-9faf-81b78f44e8b9.PNG)

#### Milestone:
![Milestone](https://user-images.githubusercontent.com/73801331/156836003-fc464524-8903-4dce-9b89-e42412be22ce.PNG)

#### Issues:
#1
![Issues1](https://user-images.githubusercontent.com/73801331/156836041-e8d886cb-97bb-4163-9e7b-589136098826.PNG)

#2
![Issues2](https://user-images.githubusercontent.com/73801331/157116603-d948c602-3ba3-4865-a651-0ca8b56f3605.PNG)

#3
![Issues3](https://user-images.githubusercontent.com/73801331/156836093-2aa4c538-523a-402b-b3db-0d87aef8aa28.PNG)

#### Pull Request (PR):
#1
![PullRequest1 0](https://user-images.githubusercontent.com/73801331/156836138-a112ddf3-48fd-4eeb-b51b-244c5f3a53bd.PNG)
![PullRequest1 1](https://user-images.githubusercontent.com/73801331/156836155-a9e9de47-f604-44be-ba9e-dd2f74e4e7ce.PNG)

#2
![PullRequest2 0](https://user-images.githubusercontent.com/73801331/156836172-6661e7bc-4326-404c-ba14-9cfb23e657ca.PNG)
![PullRequest2 1](https://user-images.githubusercontent.com/73801331/156836173-68175a1c-da94-4213-927d-0049fa4a995c.PNG)

#3
![PullRequest3 0](https://user-images.githubusercontent.com/73801331/156836208-0bede0ec-e757-4675-9d6d-4783ffa8d90d.PNG)
![PullRequest3 1](https://user-images.githubusercontent.com/73801331/156836211-c6ef1d45-0f4b-41b0-98d5-441d8bf666d4.PNG)

#### Arbre des commits et de branches :
![Branch_tree](https://user-images.githubusercontent.com/73801331/156836283-bc0a3d3e-1881-4de7-afe1-d4a061363845.png)
