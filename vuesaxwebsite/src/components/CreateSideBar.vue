<script lang="ts">
import BaseStoryComponent from "./BaseStoryComponent";
import Story from "../types/Story";
import { Component } from "vue-property-decorator";
import { CreatorState } from "@/CreatorState";

@Component({})
export default class CreateSideBar extends BaseStoryComponent {
  get name(): string | null {
    if (CreatorState.state.selectedBloc?.bloc.name) {
      return CreatorState.state.selectedBloc.bloc.name;
    }
    return null;
  }

  set name(name: string | null) {
    CreatorState.commit("modifySelectedBloc", {
      ...CreatorState.state.selectedBloc,
      bloc: { ...CreatorState.state.selectedBloc?.bloc, name: name },
    });
  }

  public addNewBloc(): void {
    CreatorState.commit("addNewBloc");
  }

  public debug(): void {
    console.log("blocs :", this.$store.state.blocs);
    console.log("Selected bloc :", this.$store.state.selectedBloc);
  }
}
</script>

<style scoped lang="scss">
#panel {
  width: 30%;
  flex-grow: 2;
  border-right: 1px solid blue;
  margin-left: 20px;
  margin-right: 20px;
}
</style>

<template>
  <div id="panel">
    <vs-button color="primary" type="gradient" @click="addNewBloc"
      >Add new bloc</vs-button
    >

    <vs-button color="danger" type="gradient" @click="debug">debug</vs-button>
    <vs-input v-model="name"></vs-input>
  </div>
</template>
