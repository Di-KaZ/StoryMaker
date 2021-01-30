<script lang="ts">
import { Vue } from "vue-class-component";

export default class Home extends Vue {
  private _text = "PrimeVue";

  public clickStory() {
    this._text = ["Jacques", "Cecile", "Moussa"][Math.floor(Math.random() * 3)];
  }
}
</script>

<style lang="scss">
body {
  margin: 0;
  padding: 0;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.centered {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
}
</style>

<template>
  <div class="centered">
    <h1>Hello {{ _text }} !</h1>
    <Button v-on:click="clickStory">Change !</Button>
  </div>
</template>
