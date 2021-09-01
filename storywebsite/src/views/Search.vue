<script lang="ts">
import BaseStoryComponent, { METHODS } from "../components/BaseStoryComponent";
import StoryCard from "../components/StoryCard.vue";
import { Component } from "vue-property-decorator";
import Story from "../types/Story";

@Component({
  components: {
    StoryCard: StoryCard,
  },
})
export default class Search extends BaseStoryComponent {
  private storiesRight: Story[] = [];
  private storiesLeft: Story[] = [];
  private page = 1;
  private search = "";
  private busy = false;
  private finalPage = false;

  public async loadNewPage(restartSearch:boolean = false) {
    console.log("coucou")
    if(restartSearch == true){
      this.page = 1;
      this.finalPage = false;
      this.storiesRight = [];
      this.storiesLeft = [];
    }
    if (this.finalPage) return;
    this.busy = true;
    try {
      const stories = await this.fetch<Story[]>(
        "story/search/" + this.page,
        METHODS.GET, {
          urlparams:{
            storyname:this.search
          }
        }
      );
      // on separe les stories en deux listes pour pouvoir les afficher cote a cote
      stories.forEach((story, index) => {
        if (index % 2 === 0) {
          this.storiesRight = [...this.storiesRight, story];
        } else {
          this.storiesLeft = [...this.storiesLeft, story];
        }
      });
    } catch (error) {
        this.finalPage = true;
        this.infoToast(
        "Désolé !",
        "Aucune story ne correspond a votre recherche ou alors vous etes arrivé a la fin des resultas"
      );
    }
    this.page++;
    this.busy = false;
  }
}
</script>
<template>
  <div class="home">
    <vs-row vs-justify="center">
      <vs-input
        class="search"
        icon="search"
        placeholder="Rechercher"
        v-model="search"
        v-on:input="loadNewPage(true)"
      />
    </vs-row>
    <div
      v-infinite-scroll="loadNewPage"
      infinite-scroll-disabled="busy"
      infinite-scroll-distance="15"
    >
      <vs-row vs-justify="space-evenly">
        <vs-col
          v-for="story in storiesLeft"
          :key="story.description"
          type="flex"
          vs-justify="center"
          vs-align="center"
          vs-w="5"
        >
          <story-card :infos="story" />
        </vs-col>
        <vs-col
          v-for="story in storiesRight"
          :key="story.id"
          type="flex"
          vs-justify="center"
          vs-align="center"
          vs-w="5"
        >
          <story-card :infos="story" />
        </vs-col>
      </vs-row>
    </div>
  </div>
</template>

<style scoped lang="scss">
h1 {
  padding-bottom: 30px;
  span {
    font-style: italic;
    color: #00a1cd;
  }
}
.search {
  padding-bottom: 30px;
}
</style>
