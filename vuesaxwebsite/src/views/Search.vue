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
export default class Search extends BaseStoryComponent {
  private stories: Story[] = [];
  private page = 0;

  mounted() {
    this.fetch<Story[]>("story/search/" + this.page, METHODS.GET).then(
      (res) => (this.stories = res)
    );
    console.log(this.stories);
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
    <vs-row v-if="stories !== undefined" vs-justify="space-evenly">
      <vs-col type="flex" vs-justify="center" vs-align="center" vs-w="5">
        <story-card :infos="stories[0]" />
      </vs-col>
      <vs-col
        v-if="stories !== undefined"
        ype="flex"
        vs-justify="center"
        vs-align="center"
        vs-w="5"
      >
        <story-card :infos="stories[0]" />
      </vs-col>
    </vs-row>
    <vs-row vs-justify="center" vs-w="12">
      <vs-pagination :total="20" v-model="page" />
    </vs-row>
  </div>
</template>
