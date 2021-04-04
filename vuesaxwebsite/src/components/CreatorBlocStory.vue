<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import { Component, Prop } from "vue-property-decorator";
import CreatorBlocStoryDTO from "@/types/CreatorBlocStoryDTO";

@Component({
  components: {},
})
export default class CreatorBlocStory extends BaseStoryComponent {
  @Prop(Object) readonly dto: CreatorBlocStoryDTO | undefined;

  get backgroundConf() {
    return {
      x: 0,
      y: 0,
      width: 200,
      height: 300,
      cornerRadius: 10,
      fill: this.dto!.bgcolor,
    };
  }

  get nameConf() {
    return {
      x: 15,
      y: 0,
      text: "Nom :\n" + this.dto!.name,
      fontSize: 20,
      fontFamily: "Calibri",
      fill: "white",
    };
  }

  get contentConf() {
    return {
      x: 15,
      y: 50,
      text: "Contenu :\n" + this.dto!.text,
      fontSize: 20,
      fontFamily: "Calibri",
      fill: "white",
    };
  }

  public select(event: any): void {
    // on set le bloc dans le creator state afin de l'afficher dans la sidebar
    const { x, y } = event.target.absolutePosition();
    this.$store.commit("modifySelectedBloc", {
      ...this.dto,
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
    :config="{ x: $props.dto.x, y: $props.dto.y }"
    @dragend="select"
    @click="select"
  >
    <v-rect :config="backgroundConf" />
    <v-text :config="nameConf" />
    <v-text :config="contentConf" />
  </v-group>
</template>
