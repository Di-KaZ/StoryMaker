# Route Rest API Story

Voici une liste de toutes les requetes possible pour lapi rest Stories.
Via cette doc j'espere pouvoir maintenir une cohérence dans les differentes routes et apporter une facilité a trouvé les requetes possible pour des utilisateur vouloant l'utilisé ou meme pour eviter d'avoir a se balader dans le code pour trouver ce qu'on cherche.

?> Elle seras alimenté et modifié au cours du developpement de cette derniere.

# USER

### 'user/login?username?password' POST

Envoie une requete permettant de recuperer un token d'authentification permettant d'acceder au route protégé par l'auhtentification.

**Params :**

- username -> username username of the user
- password -> password of the user

**Return :**

- token -> token authentificating the user (to save as cookie for example)

### 'user/register?username?password?email?' POST

Crée un nouvel utilisateur sur l'api avec les parametres renseigné.
**Params :**

- username -> username of the new user
- password -> password of the new user
- email -> email of the new user

**Return :**

- response -> 'ok' or 'ko'

### 'user/profile/{username}' GET

Récupere les information public (et privé si le nom d'utilisateur correspond à l'utilsateur connecté)
concernant l'utilisateur en question.

Récupère les infos du profil de l'utilisateur
**Url params:**

- username -> username of the wanted profile

**Return :**

- userinfos

### 'user/profile/{username}/stories/{page}' GET

Recupère une page des stories réalisé par l'utilisateur.
**Url params :**

- username -> username of the wanted profile
- page -> stories page number of this user

**Return :**

- list of stories

# STORY

### 'story/play/{storyId}/bloc/{blocId}' GET

Accede a un certain bloc d'une certaine histoire.

**Url params :**

- storyId -> id de la stories ou recupéré le bloc
- blocId -> id du bloc a récupéré vennant de la story

**Return :**

- block story -> un bloc de l'histoire en cours

### 'story/getjson/{storyId}' GET

Récupere le json complet de la story

**Url Params :**

- storyId -> id de la story a récuperer

**Return :**

- le json complet de la story (pour un export par example)

### 'story/page/{num}' GET

Récupere une page de toutes les stories sauvegarder sur l'api la taille d'une page est actuellement definit a 15 stories

**Url params :**

- num -> numero de la page voulue

**Return :**

- une page de stories

### 'story/search?username?storyname?tags?orderby/page/{num}' GET

Récupere une page de toutes les stories sauvegarder sur l'api avec un fuzzymatch sur les differents parametres la taille d'une page est actuellement definit a 15 stories

**Url params :**

- num -> numero de la page voulue

**Params**

- username -> une partie du nom d'un utilisateur
- storyname -> une partie du nom de la story
- tags -> liste des tags sur la story (separé par un underscore **'\_'**)
- orderby -> l'ordre dans lequel trié la liste valeur (like|dislike|play)

**Return :**

- une page de stories
