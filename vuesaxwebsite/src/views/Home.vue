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
    this.fetch<Story[]>("story/page/0", METHODS.GET)
      .then((res) => (this.stories = res))
      .catch((error) => this.errorToast(error.message, error.status))
      .finally(() =>
        this.successToast(
          "story/page/0",
          "fetched " + this.stories.length + " storie(s)"
        )
      );
  }
}
</script>

<style scoped lang="scss"></style>

<template>
  <div class="home">
    <vs-row vs-justify="center">
      <h1>📖 Les Stories du moment 📖</h1>
    </vs-row>
    <vs-row vs-justify="space-evenly">
      <vs-col type="flex" vs-justify="center" vs-align="center" vs-w="5">
        <story-card :infos="stories[0]" />
      </vs-col>
      <vs-col type="flex" vs-justify="center" vs-align="center" vs-w="5">
        <story-card :infos="stories[0]" />
      </vs-col>
    </vs-row>
    <vs-row vs-justify="space-evenly">
      <vs-col type="flex" vs-justify="center" vs-align="center" vs-w="5">
        <story-card :infos="stories[0]" />
      </vs-col>
      <vs-col type="flex" vs-justify="center" vs-align="center" vs-w="5">
        <story-card :infos="stories[0]" />
      </vs-col>
    </vs-row>
    <vs-pagination :total="5" v-model="page" type="gradient" color="danger" />
  </div>
</template>
