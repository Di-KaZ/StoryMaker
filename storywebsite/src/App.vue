<script lang="ts">
import { Vue } from "vue-class-component";

export default class App extends Vue {
  private _text = "Hello From Story";

  public clickStory() {
    this._text = ["Jacques", "Cecile", "Moussa"][Math.floor(Math.random() * 3)];
  }
}
</script>

<style lang="scss">
body {
  width: 100vw;
  height: 100vh;
  margin: 0;
  padding: 0;
}

#app {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100vw;
  height: 100vh;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>

<template>
  <h1>Hello {{ _text }} !</h1>
  <button v-on:click="clickStory">Change !</button>
</template>
