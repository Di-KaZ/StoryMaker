<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import { Component, Prop } from "vue-property-decorator";
import Link from "./Link.vue";
import CreatorBlocStory from "@/components/CreatorBlocStory.vue";
import CreatorBlocStoryDTO from "@/types/CreatorBlocStoryDTO";
import Zoomer from "./Zoomer.vue";

@Component({
  components: {
    creatorBlocStory: CreatorBlocStory,
    creatorLink: Link,
    Zoomer: Zoomer,
  },
})
export default class CreatorStage extends BaseStoryComponent {
  @Prop(Array) readonly blocs: CreatorBlocStoryDTO[] | undefined;
  @Prop(Array) readonly links: Link[] | undefined;

  private width: number;
  private height: number;

  constructor() {
    super();
    this.width = 0;
    this.height = 0;
  }

  public handleZoom(event: any) {
    const stage = event.target;
    if (!stage.getPointerPosition) return;
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

  public mounted(): void {
    const canva = (this.$refs.canva as unknown) as HTMLDivElement;
    this.width = canva.clientWidth;
    this.height = canva.clientHeight;
  }
}
</script>

<style scoped lang="scss">
#canva {
  flex-grow: 2;
}
</style>

<template>
  <div ref="canva" id="canva">
    <v-stage
      :config="{
        width,
        height,
        draggable: true,
        container: 'canva',
      }"
      @wheel="handleZoom"
    >
      <v-layer>
        <creator-bloc-story
          v-for="bloc in blocs"
          v-bind:key="JSON.stringify(bloc, ['id', 'name'])"
          :bloc="bloc"
        ></creator-bloc-story>
        <creator-link v-for="(dto, index) in links" :link="dto" :key="index" />
      </v-layer>
    </v-stage>
    <zoomer />
  </div>
</template>
