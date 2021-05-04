<script lang="ts">
//J'en ai besoin pour faire le fetch
import Component from "vue-class-component";
import BaseStoryComponent, { METHODS } from "../components/BaseStoryComponent";
import Comment from "../types/Comment";
import Story from "../types/Story";
import { GlobalState } from "../GlobalState";
import User from "../types/User";
import Cookies from "js-cookie";

//a ne pas oublier
@Component({})
export default class StoryInfo extends BaseStoryComponent {
  public story: Story | null = null;
  public comment: Comment[] | null = null;
  public id: any = this.$route.params.id;
  public authentify: any = Cookies.get("token");

  beforeMount() {
    this.fetch<Story>("story/info/" + this.id, METHODS.GET).then(
      (res) => (this.story = res)
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
  height: 50px;
}

.comment-content {
  max-height: 500px;
}
</style>

<template>
  <!-- Il faut un element "root" pour chaque template -->
  <div>
    <div class="container">
      <div class="story-info">
        <h1>{{ story.name }}</h1>
        <img src="../assets/image.jpg" alt="" />
      </div>
      <vs-row v-bind:key="comment.id" v-for="comment in story.comments">
        <vs-col
          class="comment-header"
          vs-type="flex"
          vs-justify="space-between"
          vs-align="center"
          vs-w="12"
        >
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
      </vs-row>
      <vs-row v-if="authentify">
        <textarea name="" id="" cols="30" rows="10"></textarea>
      </vs-row>
    </div>
  </div>
</template>
