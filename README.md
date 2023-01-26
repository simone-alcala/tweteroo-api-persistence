<p align="center">
  <img src="https://www.svgrepo.com/download/452234/java.svg" height="110" alt="Java Logo" />
  <img src="https://www.svgrepo.com/download/354380/spring-icon.svg" height="100" alt="Java Logo" />
</p>

## Get started with Tweteroo-API

# 

## ROUTES:

## Authentication

```yml 
POST /sign-in
    - Route to sign-in
    - body: {
        "username": "lorem",
        "avatar": "http://loremipsum.png"
      }
```

## Tweets

```yml 
POST /tweets
    - Route to add a tweet
    - body: {
        "username": "lorem",
        "tweet": "lorem ipsum",
      }
```

```yml 
GET /tweets?page=0&size=5
    - Route to get all tweets pageable
    - body: {}
    - response: [
        {
          "username": "lorem",
          "avatar": "http://loremipsum.png",
          "tweet": "lorem ipsum"
        }
      ]
```

```yml 
GET /tweets/{lorem}
    - Route to get all user's tweets by its username
    - body: {}
    - response: [
        {
          "username": "lorem",
          "avatar": "http://loremipsum.png",
          "tweet": "lorem ipsum"
        }
      ]
```

## API doc:

  > http://localhost:8080/swagger-ui/index.html