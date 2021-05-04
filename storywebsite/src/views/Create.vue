<script lang="ts">
import BaseStoryComponent from "../components/BaseStoryComponent";
import CreateSideBar from "../components/CreateSideBar.vue";
import { Component } from "vue-property-decorator";
import { CreatorState } from "../CreatorState";
import CreatorStage from "../components/CreatorStage.vue";
import ToolBar from "../components/Toolbar.vue";

@Component({
  components: {
    sidebar: CreateSideBar,
    creatorStage: CreatorStage,
    toolBar: ToolBar,
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
  display: flex;
  height: 100%;
}

.wrapper {
  display: flex;
  flex-direction: column;
  height: 100%;
}
</style>

<template>
  <div class="wrapper">
    <vs-navbar> </vs-navbar>
    <div id="create">
      <sidebar />
      <creator-stage :blocs="$store.state.blocs" :links="$store.state.links" />
      <tool-bar />
    </div>
  </div>
</template>
