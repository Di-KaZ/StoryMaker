<script lang="ts">
import { Component } from "vue-property-decorator";
import BaseStoryComponent from "./BaseStoryComponent";
import { GlobalState } from "../GlobalState";

@Component({})
export default class App extends BaseStoryComponent {
  private active = 0;

  public toggleSideBar(value: boolean): void {
    GlobalState.commit("toggleSideBar");
  }
}
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
  <vs-navbar
    v-model="active"
    text-color="#00A1CD"
    active-text-color="#00A1CD"
    color="white"
    type="fund"
  >
    <div slot="title">
      <vs-navbar-title>
        <img class="logo" src="../assets/logo.png" alt="Logo" />
      </vs-navbar-title>
    </div>
    <vs-navbar-item index="0" style="justify-self: flex-start">
      <router-link to="/" index="0">
        Accueil
      </router-link>
    </vs-navbar-item>
    <vs-navbar-item index="1">
      <router-link to="/story/create">
        Créer
      </router-link>
    </vs-navbar-item>
    <vs-navbar-item index="2">
      <router-link to="/story/search">
        Rechercher
      </router-link>
    </vs-navbar-item>
    <vs-avatar
      @click="toggleSideBar()"
      size="large"
      style="margin-left: auto; margin-right: 15px"
    />
  </vs-navbar>
</template>
