<script lang="ts">
import { Vue } from "vue-class-component";

export default class App extends Vue {
  items = [
    {
      label: "Acceuil",
      to: "/",
    },
    {
      label: "Créer",
      to: "/",
      items: [
        {
          label: "Nouveau",
        },
        {
          label: "Mes histoires",
        },
      ],
    },
    {
      label: "Profil",
      to: "/",
      items: [
        {
          label: "Parametres",
        },
      ],
    },
  ];
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
  <Menubar :model="items"><h1>Story</h1></Menubar>
  <router-view />
</template>
