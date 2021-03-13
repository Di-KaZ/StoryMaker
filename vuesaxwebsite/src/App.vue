<script lang="ts">
import { Component } from "vue-property-decorator";
import BaseStoryComponent from "./components/BaseStoryComponent";

@Component({})
export default class App extends BaseStoryComponent {
  private activeitem = 0;
  private sidebarActive = false;

  public toggleSideBar(): void {
    this.sidebarActive = !this.sidebarActive;
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

.header-sidebar {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: 100%;
  h4 {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
  }
  button {
    margin-left: 10px;
  }
}
.footer-sidebar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  button {
    border: 0px solid rgba(0, 0, 0, 0) !important;
    border-left: 1px solid rgba(0, 0, 0, 0.07) !important;
    border-radius: 0px !important;
  }
}

.vs-con-items {
  justify-content: flex-start;
  width: 100%;
}
</style>

<template>
  <div id="app">
    <vs-navbar
      v-model="activeitem"
      text-color="#00A1CD"
      active-text-color="#00A1CD"
      color="white"
      type="fund"
    >
      <div slot="title">
        <vs-navbar-title>
          <img class="logo" src="./assets/logo.png" alt="Logo" />
        </vs-navbar-title>
      </div>
      <vs-navbar-item index="0" style="justify-self: flex-start">
        <router-link to="/">
          Accueil
        </router-link>
      </vs-navbar-item>
      <vs-navbar-item index="1">
        <router-link to="/">
          Créer
        </router-link>
      </vs-navbar-item>
      <vs-avatar
        @click="toggleSideBar()"
        size="large"
        style="margin-left: auto; margin-right: 15px"
      />
    </vs-navbar>
    <vs-sidebar
      parent="body"
      default-index="1"
      color="primary"
      spacer
      v-model="sidebarActive"
      position-right
    >
      <div class="header-sidebar" slot="header">
        <vs-avatar size="70px" />
        <h4>
          GET_MOUSSED
          <vs-button color="primary" icon="more_horiz" type="flat"></vs-button>
        </h4>
      </div>

      <vs-sidebar-item index="1" icon="question_answer">
        Dashboard
      </vs-sidebar-item>

      <vs-sidebar-item index="2" icon="gavel">
        History
      </vs-sidebar-item>

      <vs-divider icon="person" position="left">
        User
      </vs-divider>

      <vs-sidebar-item index="3" icon="verified_user">
        Configurations
      </vs-sidebar-item>
      <vs-sidebar-item index="4" icon="account_box">
        Profile
      </vs-sidebar-item>
      <vs-sidebar-item index="5">
        Card
      </vs-sidebar-item>

      <div class="footer-sidebar" slot="footer">
        <vs-button icon="reply" color="danger" type="flat">log out</vs-button>
        <vs-button icon="settings" color="primary" type="border"></vs-button>
      </div>
    </vs-sidebar>
    <router-view />
  </div>
</template>
