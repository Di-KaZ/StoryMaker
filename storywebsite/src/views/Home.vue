<script lang="ts">
import { Vue } from "vue-class-component";
import HelloPOJO from "../model/HelloPOJO";
import fetchApi from "../model/fetchAPI";

export default class Home extends Vue {
  private text: HelloPOJO | null = null;

  public async clickStory() {
    this.text = await fetchApi<HelloPOJO>("http://localhost:8081/helloWolrd");
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
    <h1>Hello {{ text }} !</h1>
    <Button v-on:click="clickStory">Change !</Button>
  </div>
</template>
