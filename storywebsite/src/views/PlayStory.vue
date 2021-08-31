<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import { Component } from "vue-property-decorator";
import Story from "../types/Story";
import BlocStory from "../types/BlocStory";
import { GlobalState } from "../GlobalState";
import Comment from "../types/Comment";
import Comments from "../components/Comments.vue";

@Component({
  components: {
    Comments: Comments
  }
})
export default class PlayStory extends BaseStoryComponent {
  private story: Story | null = null;
  private user: any | null = null;
  private isLoadedData = false;
  public comment: Comment[] | null = null;

  beforeMount() {
    this.loadFirstBlocStory();
  }

  async loadFirstBlocStory() {
    this.story = await this.fetch<Story>("story/play/" + this.$route.params.id, METHODS.GET);
    this.isLoadedData = true;
    this.user = GlobalState.state.user;
  }

  onPlayStory() {
    this.$router.push(
      "/story/play/" + this.$route.params.id + "/blocstory/" + this.story?.firstBlocId
    );
  }
}
</script>

<style>
.container {
  width: 75%;
  margin: auto;
}

.story-info {
  margin-bottom: 2%;
}

.comment-header {
  border-bottom: 1px solid rgba(95, 95, 95, 0.2);
  padding-left: 10px;
  padding-right: 10px;
}

.comment-content {
  max-height: 500px;
  height: 50px;
  padding-left: 10px;
  padding-right: 10px;
}

.list-comments {
  margin-bottom: 2%;
}

.comment {
  border: 1px solid rgba(95, 95, 95, 0.2);
}
</style>

<template>
  <div v-if="user">
    <div v-if="isLoadedData">
      <h1>Vous jouez à : {{ story.name }}</h1>
      <br />
      <p>Description :</p>
      <h2>{{ story.description }}</h2>
      <vs-button @click="onPlayStory">Jouer</vs-button>
    </div>
    <br />
    <div class="container">
      <vs-row v-bind:key="comment.id" v-for="comment in story.comments" class="list-comments">
        <vs-col class="comment">
          <vs-col class="comment-header" vs-type="flex" vs-justify="space-between" vs-w="12">
            <p>{{ comment.creationDate }}</p>
            <p>{{ comment.user.name }}</p>
          </vs-col>
          <vs-col
            class="comment-content"
            vs-type="flex"
            vs-justify="flex-start"
            vs-align="center"
            vs-w="12"
          >
            <p>{{ comment.content }}</p>
          </vs-col>
          <vs-col vs-type="flex" vs-justify="flex-end" vs-w="12"><a href="#">répondre</a></vs-col>
        </vs-col>
      </vs-row>
      <comments></comments>
    </div>
  </div>
  <div v-else>Veuillez vous connecter pour accéder à cette story</div>
</template>