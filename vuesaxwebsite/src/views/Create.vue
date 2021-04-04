<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import CreateSideBar from "@/components/CreateSideBar.vue";
import { Component, Watch } from "vue-property-decorator";
import { CreatorState } from "@/CreatorState";
import CreatorBlocStory from "@/components/CreatorBlocStory.vue";
import CreatorBlocStoryDTO from "@/types/CreatorBlocStoryDTO";
import BlocStory from "@/types/BlocStory";
import CreatorConnection from "@/components/CreatorConnection.vue";

@Component({
  components: {
    sidebar: CreateSideBar,
    creatorBlocStory: CreatorBlocStory,
    creatorConnection: CreatorConnection,
  },
  store: CreatorState,
})
export default class Create extends BaseStoryComponent {
  private width: number;
  private height: number;

  constructor() {
    super();
    this.width = 0;
    this.height = 0;
  }

  public mounted(): void {
    const canva = (this.$refs.canva as unknown) as HTMLDivElement;
    this.width = canva.offsetWidth;
    this.height = canva.offsetHeight;
  }
}
</script>

<style lang="scss">
html,
body,
#app {
  height: 100vh;
}

#create {
  height: 100%;
  display: flex;
}

#canva {
  flex-grow: 0;
}
</style>

<template>
  <div ref="canva" id="create">
    <sidebar />
    <v-stage
      id="canva"
      :config="{
        width: width,
        height: height,
      }"
    >
      <v-layer>
        <creator-connection
          v-for="(dto, index) in $store.state.connections"
          :coord="dto"
          :key="index"
        />
        <creator-bloc-story
          v-for="dto in $store.state.blocs"
          v-bind:key="JSON.stringify(dto)"
          :dto="dto"
        ></creator-bloc-story>
      </v-layer>
    </v-stage>
  </div>
</template>
