<script lang="ts">
import BaseStoryComponent, { METHODS } from "../components/BaseStoryComponent";
import { Component } from "vue-property-decorator";
import Story from "../types/Story";
import { GlobalState } from "../GlobalState";
import comments from "../components/Comments.vue";

@Component({
  components: {
    comments: comments
  }
})
export default class PlayStory extends BaseStoryComponent {
  public story: Story;
  public isLoadedData: boolean = false;
  public comments: Comment[];

  beforeMount() {
    this.loadStory();
  }

  async loadStory() {
    this.story = await this.fetch<Story>("story/play/" + this.$route.params.id, METHODS.GET);
    this.isLoadedData = true;
  }

  get user() {
    return GlobalState.state.user;
  }

  onPlayStory() {
    if (parseInt(this.story.firstBlocId) === -1) {
      this.errorToast("Cette story n'est pas jouable !", "Cette story contient une erreur.");
      return;
    }
    this.$router.push(
      "/story/play/" + this.$route.params.id + "/blocstory/" + this.story?.firstBlocId
    );
  }
}
</script>

<style lang="scss">
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
.title {
  position: absolute;
  z-index: 1;
  bottom: 8px;
  left: 16px;
  color: #fff;
}
#story-description {
  //   height: 100px;
  //   max-height: 200px;
  word-wrap: break-space;
  text-align: justify;
}
.scrollable {
  overflow-y: scroll;
  height: 100vh;
  width: 100vw;
  padding: 0;
  padding-bottom: 10vh;
  margin: 0;
}
</style>

<template>
  <div v-if="user" class="scrollable">
    <div v-if="isLoadedData">
      <div class="image">
        <h1 class="title">Vous jouez à : {{ story.name }}</h1>
        <img :src="story.cover" />
      </div>
    </div>
    <br />
    <div class="container">
      <vs-row id="story-description">
        <vs-col vs-w="12">
          <p>Description :</p>
          <p>
            {{ story.description }}
          </p>
        </vs-col>
        <vs-col vs-w="12">
          <vs-button @click="onPlayStory">Jouer</vs-button>
        </vs-col>
      </vs-row>

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
          <vs-col vs-type="flex" vs-justify="flex-end" vs-w="12"
            ><a href="#">répondre (WIP)</a></vs-col
          >
        </vs-col>
      </vs-row>
      <comments />
    </div>
  </div>
  <div v-else>Veuillez vous connecter pour accéder à cette story</div>
</template>
