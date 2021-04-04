<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import { Component, Prop } from "vue-property-decorator";
import CreatorConnectionDTO from "../types/CreatorConnectionDTO";

@Component({
  components: {},
})
export default class CreatorConnection extends BaseStoryComponent {
  @Prop(Object) readonly coord: CreatorConnectionDTO | undefined;

  get config() {
    return {
      points: [this.coord!.xP, this.coord!.yP, this.coord!.xC, this.coord!.yC],
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
  <v-line :config="config" />
</template>
