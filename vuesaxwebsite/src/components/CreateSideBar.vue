<script lang="ts">
import BaseStoryComponent from "./BaseStoryComponent";
import Story from "../types/Story";
import { Component } from "vue-property-decorator";
import { CreatorState } from "@/CreatorState";

@Component({})
export default class CreateSideBar extends BaseStoryComponent {
  get parentSelect() {
    if (
      CreatorState.state.selectedBloc !== null &&
      CreatorState.state.selectedBloc.parent.id
    ) {
      return CreatorState.state.selectedBloc.parent.id;
    }
    return 0;
  }

  set parentSelect(id: number) {
    CreatorState.commit("modifySelectedBloc", {
      ...CreatorState.state.selectedBloc,
      parent: {
        id: id,
        name: CreatorState.state.blocs.find((b) => b.id === id)?.name,
      },
    });
  }

  get name(): string | null {
    if (CreatorState.state.selectedBloc) {
      const { name } = CreatorState.state.selectedBloc;
      return name;
    }
    return null;
  }

  set name(name: string | null) {
    CreatorState.commit("modifySelectedBloc", {
      ...CreatorState.state.selectedBloc,
      name,
    });
  }

  set text(text: string | null) {
    CreatorState.commit("modifySelectedBloc", {
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

  get parents(): { id: number; name: string }[] {
    const { blocs, selectedBloc } = CreatorState.state;

    return blocs
      .filter((b) => b.id !== selectedBloc?.id)
      .map(({ id, name }) => {
        return {
          id,
          name,
        };
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
    <vs-input v-model="name" label="Nom"></vs-input>
    <vs-input v-model="text" label="Texte"></vs-input>
    <vs-select
      autocomplete
      placeholder="select"
      v-model="parentSelect"
      label="Parent"
    >
      <vs-select-item
        v-for="parent in parents"
        :key="parent.id"
        :value="parent.id"
        :text="parent.name"
      />
    </vs-select>
  </div>
</template>
