<script lang="ts">
import BaseStoryComponent, { METHODS } from "../components/BaseStoryComponent";
import { Component, Prop } from "vue-property-decorator";
import CreatorBlocStoryDTO from "../types/CreatorBlocStoryDTO";
import { WIDTH, BLOC_HEIGHT, BAR_HEIGHT, FONT_SIZE } from "../globals";

@Component({
  components: {}
})
export default class CreatorBlocStory extends BaseStoryComponent {
  @Prop(Object) readonly bloc: CreatorBlocStoryDTO | undefined;
  private outHover = false;
  private inHover = false;
  private image: HTMLImageElement | null = null;

  beforeMount() {
    const image = new Image();
    image.onload = () => {
      this.image = image;
    };
    image.src = this.bloc!.cover;
  }

  get backgroundConf() {
    return {
      x: 0,
      y: 0,
      width: WIDTH,
      height: BLOC_HEIGHT,
      fill: "#394648"
    };
  }

  get imageConf() {
    if (this.image) {
      return {
        image: this.image,
        width: 150,
        height: 150,
        x: WIDTH / 2 - 75,
        y: 100
      };
    }
    return { image: null };
  }

  get titleBarConf() {
    return {
      x: 0,
      y: 0,
      width: WIDTH,
      height: BAR_HEIGHT,
      fill: this.bloc!.selected ? "#69995D" : "#2C3F27"
    };
  }

  get nameConf() {
    return {
      x: 15,
      y: 30,
      text: "Nom du choix :\n" + this.bloc!.name,
      fontSize: FONT_SIZE,
      fontFamily: "Calibri",
      fill: "#F8E9E9"
    };
  }

  get contentConf() {
    return {
      x: 15,
      y: 270,
      text: "Contenu :\n\n" + this.bloc!.text,
      fontSize: FONT_SIZE,
      width: WIDTH - 25,
      fontFamily: "Calibri",
      fill: "#F8E9E9",
      wrap: "char"
    };
  }

  get inConf() {
    return {
      x: WIDTH / 2,
      y: 0,
      radius: 10,
      fill: this.inHover ? "red" : "lightblue"
    };
  }

  get outConf() {
    return {
      x: WIDTH / 2,
      y: BLOC_HEIGHT,
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
    <v-rect :config="titleBarConf" />
    <v-circle @mouseenter="onInHover" @mouseleave="onInLeave" :config="inConf" />
    <v-image :config="imageConf" />
    <v-text :config="nameConf" />
    <v-text :config="contentConf" />
    <v-circle @mouseenter="onOutHover" @mouseleave="onOutLeave" :config="outConf" />
  </v-group>
</template>
