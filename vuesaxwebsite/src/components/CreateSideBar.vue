<script lang="ts">
import BaseStoryComponent from "./BaseStoryComponent";
import Story from "../types/Story";
import { Component } from "vue-property-decorator";
import { CreatorState } from "@/CreatorState";
import CreatorBlocStoryDTO from "@/types/CreatorBlocStoryDTO";

const ID = function() {
  // Math.random should be unique because of its seeding algorithm.
  // Convert it to base 36 (numbers + letters), and grab the first 9 characters
  // after the decimal.
  return (
    "_" +
    Math.random()
      .toString(36)
      .substr(2, 9)
  );
};

@Component({})
export default class CreateSideBar extends BaseStoryComponent {
  get currentParent(): string | undefined {
    const { selectedBloc } = CreatorState.state;
    if (selectedBloc !== null && selectedBloc.in) {
      return selectedBloc.in.id;
    }
    return undefined;
  }

  set currentParent(id: string | undefined) {
    CreatorState.commit(
      "SET_CONNECTION",
      CreatorState.state.blocs.find((b) => b.id === id)
    );
  }

  get name(): string | null {
    if (CreatorState.state.selectedBloc) {
      const { name } = CreatorState.state.selectedBloc;
      return name;
    }
    return null;
  }

  set name(name: string | null) {
    CreatorState.commit("MODIFY_BLOC", {
      ...CreatorState.state.selectedBloc,
      name,
    });
  }

  set text(text: string | null) {
    CreatorState.commit("MODIFY_BLOC", {
      ...CreatorState.state.selectedBloc,
      text,
    });
  }

  get text(): string | null {
    if (CreatorState.state.selectedBloc) {
      const { text } = CreatorState.state.selectedBloc;
      return text;
    }
    return null;
  }

  get parents(): CreatorBlocStoryDTO[] {
    const { blocs, selectedBloc } = CreatorState.state;
    if (selectedBloc) {
      return blocs.filter((b) => b.id !== selectedBloc?.id);
    }
    return [];
  }

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

  public deleteBloc(): void {
    CreatorState.commit("DELETE_BLOC");
  }

  public debug(): void {
    console.log("blocs :", this.$store.state.blocs);
    console.log("Selected bloc :", this.$store.state.selectedBloc);
    console.log("links:", this.$store.state.links);
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
    <vs-button color="primary" type="gradient" @click="addBloc"
      >Add new bloc</vs-button
    >
    <vs-button color="danger" type="gradient" @click="debug">debug</vs-button>
    <vs-input v-model="name" label="Nom"></vs-input>
    <vs-input v-model="text" label="Texte"></vs-input>
    <vs-select
      autocomplete
      placeholder="select"
      label="Parent"
      v-model="currentParent"
    >
      <vs-select-item
        v-for="parent in parents"
        :key="parent.id"
        :value="parent.id"
        :text="parent.name"
      />
    </vs-select>
    <vs-button color="danger" type="gradient" @click="deleteBloc"
      >Delete</vs-button
    >
  </div>
</template>
