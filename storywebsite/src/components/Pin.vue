<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import { Component, Prop } from "vue-property-decorator";
import CreatorBlocStoryDTO from "@/types/CreatorBlocStoryDTO";

@Component({
  components: {},
})
export default class CreatorBlocStory extends BaseStoryComponent {
  @Prop(Object) readonly bloc: CreatorBlocStoryDTO | undefined;

  get backgroundConf() {
    return {
      x: 0,
      y: 0,
      width: 200,
      height: 200,
      cornerRadius: 10,
      fill: this.bloc!.selected ? "black" : "gray",
    };
  }

  get nameConf() {
    return {
      x: 15,
      y: 50,
      text: "Nom :\n" + this.bloc!.name,
      fontSize: 20,
      fontFamily: "Calibri",
      fill: "white",
    };
  }

  get contentConf() {
    return {
      x: 15,
      y: 100,
      text: "Contenu :\n" + this.bloc!.text,
      fontSize: 20,
      fontFamily: "Calibri",
      fill: "white",
    };
  }

  get inConf() {
    return {
      x: 100,
      y: 0,
      radius: 10,
      fill: "lightblue",
    };
  }

  get outConf() {
    return {
      x: 100,
      y: 200,
      radius: 10,
      fill: "pink",
    };
  }

  public select(event: any): void {
    // on set le bloc dans le creator state afin de l'afficher dans la sidebar
    const { x, y } = event.target.absolutePosition();
    this.$store.commit("MODIFY_BLOC", {
      ...this.bloc,
      x,
      y,
    });
  }
}
</script>

<style lang="scss"></style>

<template>
  <v-group
    ref="group"
    draggable="true"
    @dragend="select"
    @click="select"
    :config="{ x: bloc.x, y: bloc.y }"
  >
    <v-rect :config="backgroundConf" />
    <v-circle :config="inConf" />
    <v-text :config="nameConf" />
    <v-text :config="contentConf" />
    <v-circle @click="() => console.log('test')" :config="outConf" />
  </v-group>
</template>
