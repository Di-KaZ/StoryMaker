<script lang="ts">
import BaseStoryComponent, { METHODS } from "../components/BaseStoryComponent";
import { Component, Prop } from "vue-property-decorator";
import CreatorBlocStoryDTO from "../types/CreatorBlocStoryDTO";

@Component({
  components: {}
})
export default class CreatorBlocStory extends BaseStoryComponent {
  @Prop(Object) readonly bloc: CreatorBlocStoryDTO | undefined;
  private outHover = false;
  private inHover = false;

  get backgroundConf() {
    return {
      x: 0,
      y: 0,
      width: 200,
      height: 200,
      cornerRadius: 10,
      fill: this.bloc!.selected ? "black" : "gray"
    };
  }

  get nameConf() {
    return {
      x: 15,
      y: 50,
      text: "Nom du choix :\n" + this.bloc!.name,
      fontSize: 20,
      fontFamily: "Calibri",
      fill: "white"
    };
  }

  get contentConf() {
    return {
      x: 15,
      y: 100,
      text: "Contenu :\n" + this.bloc!.text,
      fontSize: 20,
      fontFamily: "Calibri",
      fill: "white"
    };
  }

  get inConf() {
    return {
      x: 100,
      y: 0,
      radius: 10,
      fill: this.inHover ? "red" : "lightblue"
    };
  }

  get outConf() {
    return {
      x: 100,
      y: 200,
      radius: 10,
      fill: this.outHover ? "red" : "pink"
    };
  }

  public selectDrag(event: any): void {
    // on set le bloc dans le creator state afin de l'afficher dans la sidebar
    const x = event.target.x();
    const y = event.target.y();

    this.$store.commit("MODIFY_BLOC", {
      ...this.bloc,
      x,
      y
    });
  }

  public select(): void {
    this.$store.commit("MODIFY_BLOC", this.bloc);
  }

  public drag(event: any) {
    const x = event.target.x();
    const y = event.target.y();
    this.$store.commit("UPDATE_CONNECTION", {
      ...this.bloc,
      x,
      y
    });
  }

  public onOutHover() {
    this.outHover = true;
  }
  public onInHover() {
    this.inHover = true;
  }

  public onOutLeave() {
    this.outHover = false;
  }

  public onInLeave() {
    this.inHover = false;
  }
}
</script>

<style lang="scss">
.blocStory {
  cursor: pointer;
}
</style>

<template>
  <v-group
    class="blocStory"
    ref="group"
    draggable="true"
    @dragmove="drag"
    @dragend="selectDrag"
    @dblclick="select"
    :config="{ x: bloc.x, y: bloc.y }"
  >
    <v-rect :config="backgroundConf" />
    <v-circle @mouseenter="onInHover" @mouseleave="onInLeave" :config="inConf" />
    <v-text :config="nameConf" />
    <v-text :config="contentConf" />
    <v-circle @mouseenter="onOutHover" @mouseleave="onOutLeave" :config="outConf" />
  </v-group>
</template>
