<script lang="ts">
import BaseStoryComponent, { METHODS } from "../components/BaseStoryComponent";
import { Component } from "vue-property-decorator";
import BlocStory from "../types/BlocStory";
@Component({})
export default class PlayStory extends BaseStoryComponent {
  private blocStory: BlocStory | null = null;
  private choices: BlocStory[] | null = null;
  private isLoadedData = false;
  beforeMount() {
    this.loadFirstBlocStory();
  }

  async loadFirstBlocStory() {
    this.blocStory = await this.fetch<BlocStory>(
      "story/play/" + this.$route.params.id + "/bloc/" + this.$route.params.idbloc,
      METHODS.GET
    );
    this.choices = await this.fetch<BlocStory[]>(
      "blocstory/get/choices/" + this.$route.params.idbloc,
      METHODS.GET
    );
    console.log(this.blocStory.cover);
    this.isLoadedData = true;
  }

  onPlayBlocStory(idbloc: number) {
    this.$router.push("/story/play/" + this.$route.params.id + "/blocstory/" + idbloc);
    this.$router.go(0);
  }
}
</script>
<template>
  <div v-if="isLoadedData">
    <div class="image">
      <img :src="blocStory.cover" />
      <h3>{{ blocStory.name }}</h3>
    </div>
    <br />
    <p>{{ blocStory.content }}</p>
    <p v-if="choices == null || choices.length == 0">Vous avez terminé la partie !</p>
    <vs-button @click="onPlayBlocStory(choice.id)" v-for="choice in choices" :key="choice.id">{{
      choice.name
    }}</vs-button>
  </div>
</template>

<style scoped lang="scss">
.image {
  width: 100%;
  height: 400px;
  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
  }
}
</style>
