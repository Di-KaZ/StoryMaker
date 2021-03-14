<script lang="ts">
import { Component } from "vue-property-decorator";
import BaseStoryComponent from "./components/BaseStoryComponent";
import SideBar from "./components/SideBar.vue";
import NavBar from "./components/NavBar.vue";

@Component({
  components: {
    navBar: NavBar,
    sideBar: SideBar,
  },
})
export default class App extends BaseStoryComponent {}
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Montserrat&display=swap");
html {
  font-family: "Montserrat", sans-serif;
  margin: 0;
  padding: 0;
  background-size: cover;
  height: 100vh;
}
.logo {
  height: 40px;
}
</style>

<template>
  <div id="app">
    <nav-bar />
    <side-bar />
    <router-view />
  </div>
</template>
