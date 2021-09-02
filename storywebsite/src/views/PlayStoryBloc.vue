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
  <div v-if="isLoadedData && user">
    <div v-if="blocStory.cover" class="image">
      <img :src="blocStory.cover" />
    </div>
    <br />
    <h3 class="titleBloc">{{ blocStory.name }}</h3>
    <div class="content">
      <p>{{ blocStory.content }}</p>
    <p v-if="choices == null || choices.length == 0">Vous avez terminé la partie !</p>
     <vs-button class="choices" @click="onPlayBlocStory(choice.id)" v-for="choice in choices" :key="choice.id">
      {{choice.name}}
    </vs-button>
    </div>

  </div>
  <div v-else>Veuillez vous connecter pour accéder à cette story</div>
</template>

<style scoped lang="scss">
.title {
  position: absolute;
  z-index: 1;
  bottom: 8px;
  left: 16px;
  color: #fff;
}
.titleBloc {
  margin : 10px;
}
.image {
  position: relative;
  width: 100%;
  height: 400px;
  img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    filter: brightness(50%);
  }
}
.content{
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: justify;
}
.choices{
  width: max-content;
  display: flex;
  flex-direction: column;
  justify-self: center;
  align-items: center;
  padding: 15px;
  margin: 5px;
}
p{
  margin-left: 10px;
}
</style>
