<script lang="ts">
import BaseStoryComponent from "./BaseStoryComponent";
import Story from "../types/Story";
import { Component } from "vue-property-decorator";
import { CreatorState } from "@/CreatorState";
import CreatorBlocStoryDTO from "@/types/CreatorBlocStoryDTO";

const ID = function() {
  return (
    "_" +
    Math.random()
      .toString(36)
      .substr(2, 9)
  );
};

@Component({
  components: {},
})
export default class ToolBar extends BaseStoryComponent {
  public addBloc(): void {
    const id = ID();

    CreatorState.commit("ADD_BLOC", {
      id,
      name: "bloc_" + id,
      text: "",
      x: 0,
      y: 0,
      selected: false,
      out: [],
    } as CreatorBlocStoryDTO);
  }

  public debug(): void {
    console.log("blocs :", this.$store.state.blocs);
    console.log("Selected bloc :", this.$store.state.selectedBloc);
    console.log("links:", this.$store.state.links);
  }
}
</script>

<style scoped lang="scss">
.toolbar {
  position: fixed;
  bottom: 40px;
  right: 40px;
  padding: 15px;
  display: flex;
  border-radius: 35px;
  box-shadow: 0px 0px 20px -6px rgba(0, 0, 0, 0.75);
}

button {
  margin-left: 5px;
  margin-right: 5px;
}
</style>

<template>
  <div class="toolbar">
    <vs-tooltip text="Ajouter un nouveau bloc">
      <vs-button color="primary" type="gradient" @click="addBloc"
        >Nouveau</vs-button
      >
    </vs-tooltip>
    <vs-button color="primary" type="gradient" @click="exportstory"
      >Exporter</vs-button
    >
    <vs-button color="danger" type="gradient" @click="debug">Debug</vs-button>
  </div>
</template>
