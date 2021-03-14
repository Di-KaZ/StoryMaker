<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import StoryCard from "@/components/StoryCard.vue";
import { Component } from "vue-property-decorator";
import Story from "../types/Story";

@Component({
  components: {
    StoryCard: StoryCard,
  },
})
export default class Home extends BaseStoryComponent {
  private stories: Story[] = [];
  private page = 0;

  beforeMount() {
    this.fetch<Story[]>("story/trending", METHODS.GET)
      .then((res) => (this.stories = res))
      .catch((error) => this.errorToast(error.message, error.status));
  }
}
</script>

<style scoped lang="scss">
h1 {
  padding-bottom: 30px;
  span {
    font-style: italic;
    color: #00a1cd;
  }
}
</style>

<template>
  <div class="home">
    <vs-row vs-justify="center">
      <h1>📖 Les <span>Stories</span> du moment 📖</h1>
    </vs-row>
    <vs-row vs-justify="space-evenly">
      <vs-col type="flex" vs-justify="center" vs-align="center" vs-w="5">
        <story-card :infos="stories[0]" />
      </vs-col>
      <vs-col type="flex" vs-justify="center" vs-align="center" vs-w="5">
        <story-card :infos="stories[1]" />
      </vs-col>
    </vs-row>
    <vs-row vs-justify="space-evenly">
      <vs-col type="flex" vs-justify="center" vs-align="center" vs-w="5">
        <story-card :infos="stories[2]" />
      </vs-col>
      <vs-col type="flex" vs-justify="center" vs-align="center" vs-w="5">
        <story-card :infos="stories[3]" />
      </vs-col>
    </vs-row>
  </div>
</template>
