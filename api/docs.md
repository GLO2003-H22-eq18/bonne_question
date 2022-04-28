# How to get started

Bienvenue sur le meilleur site de vente anonyme au Québec!

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

# Documentation

## Services

- Health
- Vendeur
- Produit
- Offre
- Vente

## Types généraux

```ts
type DateTime: date et heure selon ISO-8601/RFC-3339 (au timezone UTC)
type Date: date (sans heures) selon ISO-8601/RFC-3339
type Amount: float/double positif arrondi à 2 décimales
type Email: <identifiant>@<service>.<extension> (ex: "john.doe1@gmail.com")
type PhoneNumber: 11 chiffres, en string (ex: +1 819 123 4567 => "18191234567")
type ProductCategory = "sports" | "electronics" | "apparel" | "beauty" | "housing" | "other"
```

## Retours d'erreur

### Format

```ts
{
    code: ErrorCode,
    description: string
}
```

### Types

| ErrorCode        | statut HTTP | description                                                                          |
| ---------------- | ----------- | ------------------------------------------------------------------------------------ |
| `MISSING_PARAM`  | 400         | un paramètre (URL, header, JSON, etc.) est manquant                                  |
| `INVALID_PARAM`  | 400         | un paramètre (URL, header, JSON, etc.) est invalide (vide, négatif, trop long. etc.) |
| `ITEM_NOT_FOUND` | 404         | un élément est introuvable (id invalide ou inexistant)                               |

> L'exception pour le statut 500 n'est pas inscrite ici car elle devrait en principe ne jamais être retournée par le serveur

## Informations additionnelles

- Un paramètre "*manquant*" correspond à une valeur inexistante (champs non présent) ou nulle (`Null` en Java ou `null` en JSON).
- Un paramètre "*vide*" correspond à une valeur dont le contenu est "vide" (ex: string vide `""` ou contenant seulement des caractères "vide" (`'\t'`, `'\n'`, `' '`), chiffre vide `0`, etc.).
- Lorsqu'un paramètre est nullable (avec `| null` après sont type), alors ce dernier peut être manquant sans générer d'erreur.
- Un champ non nullable doit retourner une erreur de type `MISSING_PARAM` si la valeur reçue est manquante.
- Tous les paramètres d'URL (query params) sont optionnels et peuvent donc être manquants sans retourner d'erreur.
- Les valeurs "vides" des paramètres d'URL (ex: "") doivent être considérées comme étant des vraies valeurs (ex: filtrer par `title = ""` doit retourner les titres égals à `""`).
- Par défaut, une valeur vide ne doit pas retourner d'exception (sauf si explicitement décrit dans les énoncés de features).

## Références

- RFC-3339 \[[1](https://datatracker.ietf.org/doc/html/rfc3339)\] et ISO-8601 \[[1](https://www.w3.org/TR/NOTE-datetime)\] \[[2](https://www.loc.gov/standards/datetime/iso-tc154-wg5_n0038_iso_wd_8601-1_2016-02-16.pdf)\]: Références pour les format *date* et *datetime* standards

# Health Check

Afin de correctement signaler que l'application se porte bien, une vérification de la connexion à la base de données est faite.

## Détails techniques

**Requête**

`GET /health`

**Réponse**

#### Status

- `200 OK` : tout est fonctionnel.
- `500 Server Error` : au moins un des services n'est pas fonctionnel.

#### Body

```ts
{
    api: boolean,
    db: boolean
}
```

#### Exemples

**Succès**

```json
{
    "api": true,
    "db": true
}
```

**Échec db**

```json
{
    "api": true,
    "db": false
}
```

**Échec api**

```json
{}
```

# Création d'un vendeur

## Description

En tant que vendeur, j'aimerais pouvoir me créer un compte afin de pouvoir accéder aux fonctionnalités de l'application.

## Critères de succès

1. Le vendeur doit avoir 18 ans ou plus.
2. Le vendeur doit avoir un nom.
3. Le vendeur doit avoir une biographie.
4. Le nouveau vendeur est sauvegardé dans l'application.
5. Le nouveau vendeur possède un identifiant **unique**.

## Détails techniques

**Route**

```
POST /sellers
```

**Requête**

```ts
{
  name: string,
  bio: string,
  birthDate: Date
}
```

**Réponse**

```
HTTP 201 CREATED
Headers:
  Location: <baseUrl>/sellers/<sellerId>
```

> Exemple de Location : http://staging-bonne-question.herokuapp.com:80/sellers/626ab9bd7a5a9c60360cfa80

**Exceptions**

- `MISSING_PARAMETER` si un champ de la requête est manquant.
- `INVALID_PARAMETER` si le nom, la biographie ou l'âge est invalide.

## Changelog

- **29 janvier 2022** : Ajout du critère sur l'unicité de l'id.

# Obtention vendeur

## Description

En tant qu'acheteur, j'aimerais pouvoir visualiser les informations publiques associées à un vendeur.

## Critères de succès

1. À la création, le vendeur ne possède aucun produits.

## Détails techniques

**Route**

```
GET /sellers/{sellerId}
```

**Réponse**

```
HTTP 200 OK
```

```ts
{
  id: string,
  name: string,
  createdAt: DateTime,
  bio: string,
  products: [
    {
      id: string,
      createdAt: DateTime,
      title: string,
      description: string,
      categories: ProductCategory[],
      suggestedPrice: Amount,
      offers: {
        mean: Amount | null,
        count: int
      }
    }
  ]
}
```

**Exceptions**

- `ITEM_NOT_FOUND` si le vendeur avec `id=sellerId` n'existe pas.

## Changelog

- **21 février 2022** : ajout du champ `categories` dans la réponse de produit

# Création produit

## Description

En tant que vendeur, j'aimerais pouvoir mettre mes produits en vente.

## Critères de succès

1. Le nouveau produit est sauvegardé dans l'application.
2. Les nouveaux produits aparaissent dans les informations du vendeur (tel que décrit [ici](https://github.com/glo2003/H22-Iteration1/blob/master/features/2.seller-get.md)).
3. Le titre ne doit pas être vide
4. La description ne doit pas être vide
5. Le prix suggéré doit être minimum 1$.
6. Le produit peut ne pas être associé à une catégorie.
7. L'id du nouveau produit est unique (par rapport aux autres produits).

## Détails techniques

**Route**

```
POST /products
```

**Requête - headers**

```
X-Seller-Id: string
```

**Requête - body**

```ts
{
  title: string,
  description: string,
  suggestedPrice: number,
  categories: ProductCategory[] | null
}
```

**Réponse - status**

`201 CREATED`

**Response - headers**

```
Location: string
```

> Exemple de Location : http://staging-bonne-question.herokuapp.com:80/products/626abb4f7a5a9c60360cfa81 

**Exceptions**

- `MISSING_PARAMETER` si un champ de la requête est manquant.
- `INVALID_PARAMETER` si le titre, la description, une catégorie ou le montant est invalide.
- `ITEM_NOT_FOUND` si le vendeur n'existe pas.

## Changelog

- **4 février 2022** : Ajout de précisions sur les champs
- **4 février 2022** : Correction du status code

# Obtention produit

## Description

En tant qu'acheteur, j'aimerais pouvoir visualiser les détails d'un produit qui m'intéresse.

## Détails techniques

**Route**

```
GET /products/{productId}
```

**Réponse - status**

`200 OK`

**Réponse - body**

```ts
{
  id: string,
  createdAt: DateTime,
  title: string,
  description: string,
  suggestedPrice: Amount,
  categories: ProductCategory[],
  seller: {
    id: string,
    name: string
  },
  offers: {
    mean: Amount | null,
    count: int
  }
}
```

**Exemple:**

```json
{
  "id": "abc",
  "createdAt": "2022-01-01T22:22:22.2222Z",
  "title": "A cool hairbrush",
  "description": "Pink and all",
  "suggestedPrice": 5.01,
  "categories": [
    "beauty"
  ],
  "seller": {
    "id": "123",
    "name": "John Doe"
  },
  "offers": { 
    "count": 0 // sans offres
  }
}
```

```json
{
  ...,
  "offers": { // avec offres
    "mean": 10.34,
    "count": 2
  }
}
```

# Obtention produits filtrés

## Description

En tant qu'acheteur, j'aimerais pouvoir filtrer les produits afin de trouver ceux qui m'intéressent.

## Critères de succès

1. Les résultats doivent satisfaire TOUS les critères indiqués.
2. Le filtre de titre est inclusif et insensible à la case. Ex: le produit avec titre "sOmEtHiNg" est retourné si le filtre de titre "eth" est appliqué.
3. Les produits retournés doivent posséder au moins l'une des catégories du filtre, mais pas nécessairement toutes.
4. Les filtres de prix sont inclusifs.

## Détails techniques

**Route**

`GET /products?<filters>`

**Requête - query params**

- `sellerId` (`string`) : id du vendeur vendant le produit
- `title` (`string`) : titre du produit
- `categories` (`ProductCategory[]`) : catégories du produit
- `minPrice` (`Amount`) : prix initial minimum du produit
- `maxPrice` (`Amount`) : prix initial maximum du produit

**Réponse - status**

`200 OK`

**Réponse - body**

```ts
{
  products: [
    {
      id: string,
      createdAt: DateTime,
      title: string,
      description: string,
      suggestedPrice: Amount,
      categories: ProductCategory[],
      seller: {
        id: string,
        name: string
      },
      offers: {
        mean: Amount | null,
        count: int
      }
    }
  ]
}
```

# Création d'offre sur un produit

## Description

En tant qu'acheteur, je veux effectuer une offre sur un produit afin de signaler mon intérêt d'achat.

## Critères de succès

1. L'offre est sauvegardée dans l'application.
2. Les nouvelles offres aparaissent dans les informations du produit associé (tel que décrit dans [get product](https://github.com/glo2003/H22-Iteration2/blob/master/features/2.product-get.md) et [filter products](https://github.com/glo2003/H22-Iteration2/blob/master/features/3.products-get.md)).
3. Les formats de email et de numéro de téléphones sont validés.
4. Le montant de l'offre doit être supérieur ou égal au montant suggéré du produit.
5. Un même client peut faire plusieurs offres. Celles-ci n'ont pas besoin d'être successivements plus élevées.
6. Le message doit être d'au moins 100 caractères.
7. Toutes les informations doivent être présentes et non-vides.
8. Rien ne confirme que l'offre n'a pas été crée par le vendeur (il n'y a pas d'authentification pour la personne qui fait une offre).

## Détails techniques

**Requête**

`POST /products/{productId}/offers`

#### Payload

```ts
{
  name: string,
  email: Email,
  phoneNumber: PhoneNumber,
  amount: Amount,
  message: string
}
```

#### Exemples

**Valide**

```json
{
  "name": "John Doe",
  "email": "john.doe@email.com",
  "phoneNumber": "18191234567",
  "amount": 48.23,
  "message": "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet."
}
```

**Réponse**

#### Status

- `200 OK`

**Exceptions**

- `ITEM_NOT_FOUND` si le produit n'existe pas.
- `INVALID_PARAMETER` si un des champs est invalide.
- `MISSING_PARAMETER` si un des champs est manquant (`null`).

# Obtention vendeur courant

## Description

En tant que vendeur, j'aimerais pouvoir accéder aux détails de mon compte, particulièrement afin de visualiser mes produits ainsi que les offres des acheteurs.

## Critères de succès

1. Si aucune offre n'a été faite sur le produit :
    1. Les montants minimum, maximum et moyens des offres sont facultatifs (`null` ou non-présents).
    2. La liste d'offres est vide.
    3. Le compte est à 0.
2. Le vendeur affiché est celui correspondant au `X-Seller-Id`
3. **Seul le vendeur courant** peut voir sa date de naissance ainsi que les détails intrinsèques des offres sur ses produits.

## Détails techniques

**Requête**

`GET /sellers/@me`

#### Headers

- `X-Seller-Id` : `string`
    - ID du vendeur.

**Réponse**

#### Body

```ts
{
  id: string,
  name: string,
  createdAt: DateTime,
  bio: string,
  birthDate: Date,
  products: [
    {
      id: string,
      title: string,
      description: string,
      createdAt: DateTime,
      suggestedPrice: Amount,
      categories: ProductCategory[],
      offers: {
        min: Amount| null,
        max: Amount | null,
        mean: Amount | null,
        count: int,
        items: [
          {
            id: string,
            createdAt: DateTime,
            amount: Amount,
            message: string,
            buyer: {
              name: string,
              email: Email,
              phoneNumber: PhoneNumber
            }
          }
        ]
      }
    }
  ]
}
```

#### Exemple

**Avec offres**

```json
{
  "id": "abc",
  "name": "John Doe",
  "createdAt": "2020-08-09T22:56:33.928Z",
  "bio": "A franctic lunatic",
  "birthDate": "1970-07-30",
  "products": [
    {
      "id": "123",
      "title": "A nice shoe.",
      "description": "Pink and all",
      "createdAt": "2022-01-01T22:22:22.2222Z",
      "suggestedPrice": 23.65,
      "categories": [
        "apparel"
      ],
      "offers": {
        "min": 23.65,
        "max": 30,
        "mean": 26.83,
        "count": 2,
        "items": [
          {
            "id": "def",
            "createdAt": "2022-01-01T22:22:22.2222Z",
            "amount": 23.65,
            "message": "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.",
            "buyer": {
              "name": "Janny Smithy",
              "email": "j.s@email.com",
              "phoneNumber": "18191234568"
            }
          },
          {
            "id": "def",
            "createdAt": "2022-01-01T22:22:22.2222Z",
            "amount": 30,
            "message": "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.",
            "buyer": {
              "name": "Roger Smith",
              "email": "r.s@email.com",
              "phoneNumber": "18191234569"
            }
          }
        ]
      }
    }
  ]
}
```

**Sans offres**

```json
{
  "id": "abc",
  "name": "John Doe",
  "createdAt": "2020-08-09T22:56:33.928Z",
  "bio": "A franctic lunatic",
  "birthDate": "1970-07-30",
  "products": [
    {
      "id": "123",
      "title": "A nice shoe.",
      "description": "Pink and all",
      "createdAt": "2022-01-01T22:22:22.2222Z",
      "suggestedPrice": 23.65,
      "categories": [
        "apparel"
      ],
      "offers": {
        "count": 0,
        "items": []
      }
    }
  ]
}
```

**Sans produit**

```json
{
  "id": "abc",
  "name": "John Doe",
  "createdAt": "2020-08-09T22:56:33.928Z",
  "bio": "A franctic lunatic",
  "birthDate": "1970-07-30",
  "products": []
}
```

**Exceptions**

- `ITEM_NOT_FOUND` si le vendeur n'est pas trouvé.
- 
# Obtention des visionnements 

## Description
En tant que vendeur, j'aimerais pouvoir consulter le nombre de visionnements de chacun de mes produits.

## Critères de succès

1. Si aucun visionnement n'a été faite sur le produit :
    1. La liste des visionnements est vide.
    2. Le compte est à 0.
    3. Le plus récent visionnement est facultatif (`null` ou non-présent).
2. Le vendeur affiché est celui correspondant au `X-Seller-Id`
3. **Seul le vendeur courant** peut voir sa date de naissance ainsi que les détails intrinsèques des visionnements sur ses produits.

## Détails techniques

**Requête**

`GET /sellers/@me/views`

#### Headers

- `X-Seller-Id` : `string`
    - ID du vendeur.

**Réponse**

#### Body

```ts
{
  id: string,
  name: string,
  createdAt: DateTime,
  bio: string,
  birthDate: Date,
  products: [
    {
      id: string,
      title: string,
      description: string,
      createdAt: DateTime,
      suggestedPrice: Amount,
      categories: ProductCategory[],
      views: {
        count: int, 
        mostRecentView: DateTime, 
        items: [
          {
            id: string,
            createdAt: DateTime,
          }
        ]
      }
    }
  ]
}
```

#### Exemple

**Avec visionnements**

```json
{
  "id": "abc",
  "name": "John Doe",
  "createdAt": "2020-08-09T22:56:33.928Z",
  "bio": "A franctic lunatic",
  "birthDate": "1970-07-30",
  "products": [
    {
      "id": "123",
      "title": "A nice shoe.",
      "description": "Pink and all",
      "createdAt": "2022-01-01T22:22:22.2222Z",
      "suggestedPrice": 23.65,
      "categories": [
        "apparel"
      ],
      "views": {
        "count": 2,
        "mostRecentView": "2022-03-01T22:22:22.2222Z",
        "items": [
          {
            "id": "def",
            "createdAt": "2022-02-01T22:22:22.2222Z"
          },
          {
            "id": "def",
            "createdAt": "2022-03-01T22:22:22.2222Z"
          }
        ]
      }
    }
  ]
}
```

**Sans visionnements**

```json
{
  "id": "abc",
  "name": "John Doe",
  "createdAt": "2020-08-09T22:56:33.928Z",
  "bio": "A franctic lunatic",
  "birthDate": "1970-07-30",
  "products": [
    {
      "id": "123",
      "title": "A nice shoe.",
      "description": "Pink and all",
      "createdAt": "2022-01-01T22:22:22.2222Z",
      "suggestedPrice": 23.65,
      "categories": [
        "apparel"
      ],
      "views": {
        "count": 0,
        "items": []
      }
    }
  ]
}
```

**Sans produit**

```json
{
  "id": "abc",
  "name": "John Doe",
  "createdAt": "2020-08-09T22:56:33.928Z",
  "bio": "A franctic lunatic",
  "birthDate": "1970-07-30",
  "products": []
}
```

**Exceptions**

- `ITEM_NOT_FOUND` si le vendeur n'est pas trouvé.

# Versions

- **1.0** 

