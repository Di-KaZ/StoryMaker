<script lang="ts">
import BaseStoryComponent, { METHODS } from "./BaseStoryComponent";
import Story from "../types/Story";
import { Component, Prop } from "vue-property-decorator";
import { CreatorState, getStoryToJson } from "../CreatorState";
import CreatorBlocStoryDTO from "../types/CreatorBlocStoryDTO";
import vue2Dropzone from "vue2-dropzone";
import "vue2-dropzone/dist/vue2Dropzone.min.css";

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
    vueDropzone: vue2Dropzone
  }
})
export default class ToolBar extends BaseStoryComponent {
  @Prop(Boolean) readonly canSave: boolean | undefined;

  $refs!: {
    dropzone: any;
  };

  public options = {
    url: "https://httpbin.org/post",
    maxFilesize: 2,
    dictDefaultMessage: "Cliquer/Glisser votre story ici",
    maxFiles: 1
  };

  public addBloc(): void {
    const id = ID();

    CreatorState.commit("ADD_BLOC", {
      id,
      name: "bloc_" + id,
      text: "",
      x: 0,
      y: 0,
      cover: "",
      selected: false,
      out: []
    } as CreatorBlocStoryDTO);
  }

  public async save() {
    if (!this.canSave) {
      this.errorToast(
        "Une erreur est présente dans la story",
        "corriger la avant de sauvegarder, vous pouvez tout de meme l'exporter pour l'editer plus tard"
      );
      return;
    }
    const story = await this.fetch<Story>("creator/save", METHODS.POST, {
      body: getStoryToJson(this.$store.state.story, this.$store.state.blocs)
    });
    CreatorState.commit("LOAD_JSON", JSON.stringify(story));
    this.infoToast("Votre story a été sauvegarder !");
  }

  public exportstory() {
    CreatorState.commit("EXPORT_JSON");
    this.successToast(`la story ${CreatorState.state.story.name} à été exporter avec succes !`);
  }

  public async loadFile(file: File): Promise<void> {
    const text = await file.text();
    CreatorState.commit("LOAD_JSON", text);
    this.$refs.dropzone.removeAllFiles();
    this.successToast(`la story ${CreatorState.state.story.name} à été charger dans l'éditeur`);
  }
}
</script>

<style scoped lang="scss">
.toolbar {
  padding: 15px;
  display: flex;
  flex-direction: column;
}

.space {
  margin: 5px;
}
button {
  margin-left: 5px;
  margin-right: 5px;
}
</style>

<template>
  <div class="toolbar">
    <vs-button class="space" color="primary" type="gradient" @click="addBloc"
      >Nouveau bloc</vs-button
    >
    <vs-button class="space" color="success" type="gradient" @click="exportstory"
      >Exporter la story sur mon pc</vs-button
    >
    <vs-button class="space" color="danger" type="gradient" @click="save"
      >Sauvegarder la story</vs-button
    >
    <vue-dropzone
      ref="dropzone"
      id="dropzone"
      class="space"
      :options="options"
      type="file"
      @vdropzone-success="loadFile"
    />
  </div>
</template>
