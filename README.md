# Bonne Question

Bonne Question est un projet développé dans le cadre du cours GLO-2003 à la session d'hiver 2022 à l'université Laval. Le projet consiste au développement d'un site de vente anynonyme au Québec.

## Liens utiles
Code de conduite: https://github.com/GLO2003-H22-eq18/bonne_question/blob/main/code-of-conduct.md
License: https://github.com/GLO2003-H22-eq18/bonne_question/blob/main/LICENSE
Développement et contribution: https://github.com/GLO2003-H22-eq18/bonne_question/blob/main/contributing.md
API staging: https://staging-bonne-question.herokuapp.com/
API production: https://production-bonne-question.herokuapp.com/

![CD Staging](https://github.com/GLO2003-H22-eq18/bonne_question/actions/workflows/cd_staging.yml/badge.svg)
![CD Production](https://github.com/GLO2003-H22-eq18/bonne_question/actions/workflows/cd_production.yml/badge.svg)
![CI](https://github.com/GLO2003-H22-eq18/bonne_question/actions/workflows/ci.yml/badge.svg)

## Requis

- Java 11
- Maven

## Setup

### Compilation

```
mvn clean install
```

### Exécution

```
mvn exec:java
```
