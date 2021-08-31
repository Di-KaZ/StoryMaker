<script lang="ts">
import { Component } from "vue-property-decorator";
import BaseStoryComponent from "./BaseStoryComponent";

@Component({})
export default class Zoomer extends BaseStoryComponent {
  private zoom = 1;
}
</script>

<style lang="scss" scoped>
.Zoomer {
  position: fixed;
  z-index: 10;
  bottom: 40px;
  right: 40px;
}
</style>

<template>
  <div class="Zoomer">
    <vs-input-number v-model="zoom" :step="0.1" />
  </div>
</template>
