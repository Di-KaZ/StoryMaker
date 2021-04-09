<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import { Component, Prop } from "vue-property-decorator";
import CreatorConnectionDTO from "../types/CreatorConnectionDTO";

@Component({
  components: {},
})
export default class Link extends BaseStoryComponent {
  @Prop(Object) readonly link: CreatorConnectionDTO | undefined;

  get config() {
    //   eslint-disable-next-line
    let { x: inX, y: inY } = this.link!.in;
    //   eslint-disable-next-line
    let { x: outX, y: outY } = this.link!.out;
    inX += 100;
    outX += 100;
    inY += 200;
    return {
      points: [inX, inY, inX, inY + 15, outX, outY - 15, outX, outY],
      stroke: "red",
      strokeWidth: 5,
      lineJoin: "round",
      lineCap: "round",
      tension: 0.5,
    };
  }
}
</script>

<style lang="scss"></style>

<template>
  <v-arrow :config="config" />
</template>
