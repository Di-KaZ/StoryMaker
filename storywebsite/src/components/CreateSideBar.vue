<script lang="ts">
import BaseStoryComponent from "./BaseStoryComponent";
import { Component } from "vue-property-decorator";
import { CreatorState } from "../CreatorState";
import CreatorBlocStoryDTO from "../types/CreatorBlocStoryDTO";
import ToolBar from "./Toolbar.vue";

const ID = function() {
  return (
    "_" +
    Math.random()
      .toString(36)
      .substr(2, 9)
  );
};

@Component({
  components: {
    toolBar: ToolBar
  }
})
export default class CreateSideBar extends BaseStoryComponent {
  private counterDanger = false;
  private active = true;
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
      CreatorState.state.blocs.find(b => b.id === id)
    );
  }

  get nameStory(): string | null {
    const { name } = CreatorState.state.story;
    return name;
  }

  set nameStory(name: string | null) {
    CreatorState.commit("MODIFY_STORY", {
      ...CreatorState.state.story,
      name
    });
  }

  get firstBlocStory(): string | undefined {
    const { firstBlocId } = CreatorState.state.story;
    return firstBlocId;
  }

  set firstBlocStory(firstBlocId: string | undefined) {
    CreatorState.commit("MODIFY_STORY", {
      ...CreatorState.state.story,
      firstBlocId
    });
  }

  get descStory(): string | null {
    const { description } = CreatorState.state.story;
    return description;
  }

  set descStory(desc: string | null) {
    CreatorState.commit("MODIFY_STORY", {
      ...CreatorState.state.story,
      description: desc
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
    CreatorState.commit("MODIFY_BLOC", {
      ...CreatorState.state.selectedBloc,
      name
    });
  }

  set coverBloc(cover: string | null) {
    CreatorState.commit("MODIFY_BLOC", {
      ...CreatorState.state.selectedBloc,
      cover
    });
  }

  get coverBloc() {
    if (CreatorState.state.selectedBloc) {
      return CreatorState.state.selectedBloc.cover;
    }
    return null;
  }

  set text(text: string | null) {
    CreatorState.commit("MODIFY_BLOC", {
      ...CreatorState.state.selectedBloc,
      text
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
      return blocs.filter(b => b.id !== selectedBloc?.id);
    }
    return [];
  }

  get blocs() {
    return CreatorState.state.blocs;
  }

  get cover() {
    return CreatorState.state.story.cover;
  }

  set cover(cover: string | null) {
    CreatorState.commit("MODIFY_STORY", {
      ...CreatorState.state.story,
      cover
    });
  }

  get validCoverBloc() {
    return !(
      !this.coverBloc ||
      (this.coverBloc &&
        this.coverBloc.length > 0 &&
        (this.coverBloc.endsWith(".gif") ||
          this.coverBloc.endsWith(".png") ||
          this.coverBloc.endsWith(".jpeg") ||
          this.coverBloc.endsWith(".jpg")))
    );
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
      out: []
    } as CreatorBlocStoryDTO);
  }

  public deleteBloc(): void {
    CreatorState.commit("DELETE_BLOC");
  }

  get validStoryName(): boolean {
    return !(this.nameStory && this.nameStory.length > 0);
  }

  get validCoverUrl(): boolean {
    return !(
      this.cover &&
      this.cover.length > 0 &&
      (this.cover.endsWith(".gif") ||
        this.cover.endsWith(".png") ||
        this.cover.endsWith(".jpeg") ||
        this.cover.endsWith(".jpg"))
    );
  }

  get validFirstBloc(): boolean {
    return !(parseInt(this.firstBlocStory) !== -1);
  }
}
</script>

<style scoped lang="scss">
#panel {
  width: 350px;
  overflow-y: scroll;
  padding: 15px;
  padding-bottom: 100px;
}
.center-space {
  display: flex;
  flex-direction: column;
  > * {
    margin: 5px;
    margin-left: 20px;
  }

  .delete-btn {
    align-self: left;
    justify-content: left;
  }
}
</style>

<template>
  <div id="panel">
    <vs-divider color="primary">
      Action générales
    </vs-divider>
    <tool-bar :canSave="!validStoryName && !validCoverUrl && !validFirstBloc" />
    <vs-divider color="primary">
      Modification informations Story
    </vs-divider>
    <div class="center-space">
      <vs-input
        v-model="nameStory"
        label="Nom de l'histoire"
        :danger="validStoryName"
        danger-text="Veuillez enter un nom"
      ></vs-input>
      <vs-input
        v-model="cover"
        label="Image :"
        :danger="validCoverUrl"
        danger-text="l'url de l'image n'est pas valide"
      ></vs-input>
      <vs-textarea v-model="descStory" label="Description"></vs-textarea>
      <vs-select
        autocomplete
        placeholder="select"
        label="Début de la story"
        v-model="firstBlocStory"
        :danger="validFirstBloc"
        danger-text="Veuillez choisir le bloc de commencement de votre story"
      >
        <vs-select-item v-for="bloc in blocs" :key="bloc.id" :value="bloc.id" :text="bloc.name" />
      </vs-select>
    </div>
    <vs-divider color="primary">
      Modification du bloc séléctioné
    </vs-divider>
    <div class="center-space">
      <vs-input v-model="name" label="Nom du choix"></vs-input>
      <vs-input
        v-model="coverBloc"
        label="Image du choix"
        :danger="validCoverBloc"
        danger-text="Veuillez entrer un lien d'image valide"
      ></vs-input>
      <vs-textarea
        counter="250"
        :counter-danger.sync="counterDanger"
        v-model="text"
        label="Contenu"
        width="100%"
        heigth="300px"
      ></vs-textarea>
      <vs-select
        autocomplete
        placeholder="select"
        label="Identifiant du choix parent"
        v-model="currentParent"
      >
        <vs-select-item
          v-for="parent in parents"
          :key="parent.id"
          :value="parent.id"
          :text="parent.name"
        />
      </vs-select>
      <vs-button class="delete-btn" color="danger" type="gradient" @click="deleteBloc"
        >Supprimer le bloc</vs-button
      >
    </div>
    <vs-divider color="primary">
      Listes des blocs (WIP)
    </vs-divider>
    <div class="center-space" label="Blocs" icon="view_list">
      <vs-collapse accordion>
        <vs-collapse-item v-for="bloc in blocs" v-bind:key="JSON.stringify(bloc, ['id', 'name'])">
          <div slot="header">{{ bloc.name }}</div>
        </vs-collapse-item>
      </vs-collapse>
    </div>
  </div>
</template>
