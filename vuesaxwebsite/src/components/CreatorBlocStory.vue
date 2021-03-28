<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import { Component } from "vue-property-decorator";
import CreatorBlocStoryDTO from "@/types/CreatorBlocStoryDTO";

const Props = BaseStoryComponent.extend({
  props: {
    dto: { type: Object as () => CreatorBlocStoryDTO },
  },
});

@Component({
  components: {},
})
export default class CreatorBlocStory extends Props {
  private backgroundConf = {
    x: 0,
    y: 0,
    width: 200,
    height: 300,
    fill: "gray",
  };

  private nameConf = {
    x: 0,
    y: 0,
    text: this.$props.dto.bloc.name,
    fontSize: 20,
    fontFamily: "Calibri",
    fill: "white",
  };

  public select(event: Event): void {
    // on set le bloc dans le creator state afin de l'afficher dans la sidebar
    const node = event.target as any;
    this.$store.commit("selectBloc", {
      ...this.$props.dto,
      x: node.x(),
      y: node.y(),
    });
  }

  public savePos(event: Event): void {
    const node = event.target as any;
    this.$store.commit("modifySelectedBloc", {
      ...this.$store.state.selectedBloc,
      x: node.x(),
      y: node.y(),
    });
  }
}
</script>

<style lang="scss"></style>

<template>
  <v-group
    draggable="true"
    :config="{ x: $props.dto.x, y: $props.dto.y }"
    @dragstart="select"
    @dragend="savePos"
    @click="select"
  >
    <v-rect :config="backgroundConf" />
    <v-text :config="nameConf" />
  </v-group>
</template>
