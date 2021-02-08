<script lang="ts">
import {Options, Vue} from "vue-class-component";
import HelloWorld from "./components/HelloWorld.vue";

@Options({
  components: {
    HelloWorld
  }
})
export default class App extends Vue {
  name = "toto";
  counter = 0;

  public counterplus() {
    this.counter++;
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>

<template>
  <img alt="Vue logo" src="./assets/logo.png"/>

  <h1>COUCOU {{ name }}</h1>

  <p>
    Bienvenue sur notre projet MESI Nous avons eu l'idée de faire un RPG Maker,
    chaque utilisateurs va pouvoir créer une histoire
  </p>

  <p v-on:click="counterplus">Vous êtes le {{ counter }} visiteur</p>

  <p v-if="_counter === 10">Vous êtes le {{ counter }} visiteur !</p>
</template>
