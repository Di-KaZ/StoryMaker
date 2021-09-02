<script lang="ts">
import BaseStoryComponent, { METHODS } from "../components/BaseStoryComponent";
import { Component, Prop } from "vue-property-decorator";
import CreatorConnectionDTO from "../types/CreatorConnectionDTO";
import { WIDTH, BLOC_HEIGHT } from "../globals";

@Component({
  components: {}
})
export default class Link extends BaseStoryComponent {
  @Prop(Object) readonly link: CreatorConnectionDTO | undefined;

  get config() {
    //   eslint-disable-next-line
    let { x: inX, y: inY } = this.link!.in;
    //   eslint-disable-next-line
    let { x: outX, y: outY } = this.link!.out;

    inX += WIDTH / 2;
    outX += WIDTH / 2;
    inY += BLOC_HEIGHT;
    return {
      points: [inX, inY, inX, inY + 15, outX, outY - 15, outX, outY],
      stroke: "#4F7246",
      strokeWidth: 5,
      lineJoin: "round",
      lineCap: "round",
      tension: 0.5
    };
  }
}
</script>

<style lang="scss"></style>

<template>
  <v-arrow :config="config" />
</template>
