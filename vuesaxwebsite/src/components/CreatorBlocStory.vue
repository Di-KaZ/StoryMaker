<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import { Component, Prop } from "vue-property-decorator";
import CreatorBlocStoryDTO from "@/types/CreatorBlocStoryDTO";

@Component({
  components: {},
})
export default class CreatorBlocStory extends BaseStoryComponent {
  @Prop(Object) readonly dto: CreatorBlocStoryDTO | undefined;

  private backgroundConf = {
    x: 0,
    y: 0,
    width: 200,
    height: 300,
    cornerRadius: 10,
    fill: "gray",
  };

  private nameConf = {
    x: 0,
    y: 0,
    text: "Nom :\n" + this.$props.dto.bloc.name,
    fontSize: 20,
    fontFamily: "Calibri",
    fill: "white",
  };

  constructor() {
    super();
    this.backgroundConf.fill = this.dto!.bgcolor;
  }

  public select(event: any): void {
    // on set le bloc dans le creator state afin de l'afficher dans la sidebar
    const { x, y } = event.target.absolutePosition();
    this.$store.commit("selectBloc", {
      ...this.dto,
      x,
      y,
    });
  }

  public savePos(event: any): void {
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
    @dragend="savePos"
    @click="select"
  >
    <v-rect :config="backgroundConf" />
    <v-text :config="nameConf" />
  </v-group>
</template>
