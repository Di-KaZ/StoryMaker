<script lang="ts">
import BaseStoryComponent from "@/components/BaseStoryComponent";
import CreateSideBar from "@/components/CreateSideBar.vue";
import { Component } from "vue-property-decorator";
import { CreatorState } from "@/CreatorState";
import CreatorStage from "@/components/CreatorStage.vue";

@Component({
  components: {
    sidebar: CreateSideBar,
    CreatorStage: CreatorStage,
  },
  store: CreatorState,
})
export default class Create extends BaseStoryComponent {}
</script>

<style lang="scss">
html,
body,
#app {
  height: 100vh;
  overflow: hidden;
}

#create {
  height: 100%;
  display: flex;
}
</style>

<template>
  <div id="create">
    <sidebar />
    <creator-stage :blocs="$store.state.blocs" :links="$store.state.links" />
  </div>
</template>
