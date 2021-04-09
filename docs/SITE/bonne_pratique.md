# Les bonnes pratiques pour Vue.js 3 + Typescript

## Fonctionnement globale :

La hierarchie des dossier :

- Views
  > ce dossier contient les 'pages' **complete** de note site internet, tel que les page Home, Profil ect.
- utils
  > un dossier contenant fonctions / classe utilitaire pouvant servir dans tout le projet.
- router
  > contient juste le fichier index.ts il permet de gerer les differentes routes de notre site internet.
- components
  > contient des components vue, il ne sont que des bout de page réutilisable.
- assets
  > contient tout les fichiers annexe tel que les images svg et autre utilisé pour le developpement du site

## Crée une nouvelle route

Pour créer une nouvelle route on se rend sur le fichier :

> router/index.ts

Vous devriez voir un résultat similaire a ca :

```vue
import PlayStory from "../views/PlayStory.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/users/create",
    name: "CreateUser",
    component: UserSubscription,
  },
  {
    path: "/story/play/:id",
    name: "PlayStory",
    component: PlayStory,
  },
];
```

pour rajouter une route il faut premierement avoir crée une view et de l'importer pour example **PlayStory**
et on ajoute ca config dans note array de route.

- path : le chemin d'acces par lequel on accede a notre page via le navigateur
- name : le nom de la route afficher en nom de page
- component : la view a afficher lorsque l'on se rend sur le path spécifier

## Crée une nouvelle View ou Composant

Pour créer une nouvelle view il faut se rendre dans le dossier

> views

et pour un composant

> components

Depuis ce dossier créer un fichier en .vue le template de base seras celui ci :
Nous utilisons l'add-on class component pour vue ce qui force une syntaxe un peu particulière

```vue
<script lang="ts">
import { Vue } from "vue-class-component";

export default class Profile extends Vue {
  // notre code typescrupt ici
}
</script>

<style scoped lang="scss">
// le style css a appliquer pour cette route
</style>

<template>
  <!-- Le html de la route -->
</template>
```

## Definir des variables et methode dans nos view et composant

En reprennant l'example au ci-dessu si l'on veut crée des variables pour savegarder des donées on peut le faire ce dette façon :

```vue
<script lang="ts">
import { Vue } from 'vue-class-component';

export default class Profile extends Vue {
	private myVariable: string = "je suis une string";

	public methodeQuiModifieMaVariable(): void {
		this.maVariable = "Modification !";
	}
}

</script>

<style lang="scss"></style>

<template>
<h1>{{myVariable}}<h1>
<button @click="methodeQuiModifieMaVariable">Changer le texte</button>
</template>
```

Beaucoup de chose a voir ici.
Premierement la declaration de variable pour declarer une variable en typescript on precise son type apres le '**:**'.
Il es possible d'utiliser les type custom pour tout ce qui concerne le typescript aller voir la [documentation](https://www.typescriptlang.org/docs/handbook/2/objects.html) qui est très efficace.

Pour utiliser ces variable dans le template html on utilise la notation **{{myVariable}}**

Pour appeler une methode sur le click d'un bouton on utilise le **@click="nomdelamethode"**

!> Le refresh de la page a la modification de variable primitive est automatique.
Par contre lors de l'utilisation de type custom ou de tableau pour garantir un refresh il faut redefinir un nouvel objet et non pas juste modifer une valeur

## Passer des variables d'un coposant a un autre

Pour passer des variable d'un composant a une autre il faut definir une class permettant de repertorié toute les variable qu'on va recevoir du parent comme ci dessous :

?> cette class est definie dans le fichier du composant recuperant ces parametres.

```vue
<script lang="ts">
class Props {
	parentVariable1?: string,
	parentVariable2?: number
}
</script>
```

De cette façon on peut maitenant dire a note composant vue qu'il accepteras deux variable :

```vue
<script lang="ts">
class Props {
	parentVariable1?: string,
	parentVariable2?: number
}

export default class StoryCard extends Vue.with(Props) {
	getMaVariable1(): string | undefined {
		return this.parentVariable1;
	}
}
</script>
```

de cette façon on peut acceder a nos variable de parent depuis notre enfant.

!> Notez ici que les **'?'** apres les nom de variables. Vue 3 etant encore une technologie très rcente combiné a typescript + class component il n'existe pas de moyen simple de contouner ce dernier, du moins pas a ma connaissance.
Il signifie que le parametre est optionel et que si il n'est pas definir la variable retourneras undefined d'ou le type de retour a string | undefined pour la methode getVariable1

ET maintenant pour envoyé les parametres lors de l'appel a votre component dans un copoenent parent il suffit de preciser les champs de cette facon :

```vue
<script lang="ts">
export default class CardList extends Vue {
  private variableParent: string = "test";
}
</script>

<template>
  <StoryCard v-bind:parentVariable1="varaibleParent" />
</template>
```

?> noter que vous passer les variable par valeur et non pas par reference avec cette façon de faire il n'est donc pas possbile de modifer une variable parent depuis l'enfant
