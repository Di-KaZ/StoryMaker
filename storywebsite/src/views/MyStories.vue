<script lang="ts">
import BaseStoryComponent, { METHODS } from "../components/BaseStoryComponent";
import { Component } from "vue-property-decorator";
import Story from "../types/Story";
import { GlobalState } from "../GlobalState";
import comments from "../components/Comments.vue";
import StoryCardUser from "../components/StoryCardUser.vue";

@Component({
  components: {
    comments: comments,
    storyCardUser: StoryCardUser
  }
})
export default class MyStories extends BaseStoryComponent {
  public stories: Story[];
  public isLoadedData: boolean = false;
  public comments: Comment[];

  beforeMount() {
    this.loadUserStories();
  }

  async loadUserStories() {
    this.stories = await this.fetch<Story[]>("user/infos/story", METHODS.GET);
    this.isLoadedData = true;
  }

  get user() {
    return GlobalState.state.user;
  }
}
</script>

<style lang="scss"></style>

<template>
  <div v-if="isLoadedData">
    <h1>Mes Stories</h1>
    <story-card-user v-for="story in stories" :infos="story" :key="story.id" />
  </div>
</template>
