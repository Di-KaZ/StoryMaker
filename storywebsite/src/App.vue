<script lang="ts">
import { Component } from "vue-property-decorator";
import BaseStoryComponent, { METHODS } from "./components/BaseStoryComponent";
import SideBar from "./components/SideBar.vue";
import NavBar from "./components/NavBar.vue";
import Cookies from "js-cookie";
import User from "./types/User";
import { GlobalState } from "./GlobalState";

@Component({
  components: {
    navBar: NavBar,
    sideBar: SideBar,
  },
})
export default class App extends BaseStoryComponent {
  async beforeMount() {
    const token = Cookies.get("token");
    if (token !== undefined) {
      try {
        const user = await this.fetch<User>("user/infos", METHODS.GET);
        this.user = user;
      } catch (error) {
        this.errorToast("Session Invalide", "Veuillez vous reconnecter");
      }
    }
  }
  mounted() {
    this.$vs.theme = "dark";
  }

  get user(): User | null {
    return GlobalState.state.user;
  }

  set user(user: User | null) {
    GlobalState.commit("changeUser", user);
  }
}
</script>

<style lang="scss">
@import url("https://fonts.googleapis.com/css2?family=Montserrat&display=swap");
html {
  font-family: "Montserrat", sans-serif;
  margin: 0;
  padding: 0;
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
    <transition
      mode="out-in"
      enter-active-class="animate__animated animate__fadeInUp animate__faster"
      leave-active-class="animate__animated animate__fadeOut animate__faster"
    >
      <router-view />
    </transition>
  </div>
</template>
