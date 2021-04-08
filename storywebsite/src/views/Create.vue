<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import CreateSideBar from "@/components/CreateSideBar.vue";
import { Component, Watch } from "vue-property-decorator";
import { CreatorState } from "@/CreatorState";
import CreatorBlocStory from "@/components/CreatorBlocStory.vue";
import CreatorBlocStoryDTO from "@/types/CreatorBlocStoryDTO";
import BlocStory from "@/types/BlocStory";
import Link from "@/components/Link.vue";

@Component({
  components: {
    sidebar: CreateSideBar,
    creatorBlocStory: CreatorBlocStory,
    creatorLink: Link,
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

  public handleZoom(event: any) {
    const stage = event.target;
    const oldScale = stage.scaleX();
    const pointer = stage.getPointerPosition();
    const mousePointTo = {
      x: (pointer.x - stage.x()) / oldScale,
      y: (pointer.y - stage.y()) / oldScale,
    };
    const newScale = event.evt.deltaY < 0 ? oldScale * 1.1 : oldScale / 1.1;
    const newPos = {
      x: pointer.x - mousePointTo.x * newScale,
      y: pointer.y - mousePointTo.y * newScale,
    };
    stage.scale({ x: newScale, y: newScale });
    stage.position(newPos);
    stage.batchDraw();
  }
}
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
        draggable: true,
      }"
      @wheel="handleZoom"
    >
      <v-layer>
        <creator-bloc-story
          v-for="bloc in $store.state.blocs"
          v-bind:key="JSON.stringify(bloc, ['id', 'name'])"
          :bloc="bloc"
        ></creator-bloc-story>
        <creator-link
          v-for="(dto, index) in $store.state.links"
          :link="dto"
          :key="index"
        />
      </v-layer>
    </v-stage>
  </div>
</template>
