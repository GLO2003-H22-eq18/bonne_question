# TP4
# Obtention des visionnements (vendeur courant)

## Description
En tant que vendeur, j'aimerais pouvoir consulter le nombre de visionnements de chacun de mes produits.

## Critères de succès

1. Si aucun visionnement n'a été faite sur le produit :
    1. La liste des visionnements est vide.
    2. Le compte est à 0.
2. Le vendeur affiché est celui correspondant au `X-Seller-Id`
3. **Seul le vendeur courant** peut voir sa date de naissance ainsi que les détails intrinsèques des visionnements sur ses produits.

## Détails techniques

### Requête

`GET /sellers/@me/views`

#### Headers

- `X-Seller-Id` : `string`
    - ID du vendeur.

### Réponse

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
        "items": [
          {
            "id": "def",
            "createdAt": "2022-01-01T22:22:22.2222Z"
          },
          {
            "id": "def",
            "createdAt": "2022-01-01T22:22:22.2222Z"
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

### Exceptions

- `ITEM_NOT_FOUND` si le vendeur n'est pas trouvé.
