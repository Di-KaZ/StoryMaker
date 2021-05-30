<script lang="ts">
//J'en ai besoin pour faire le fetch
import Component from "vue-class-component";
import BaseStoryComponent, { METHODS } from "../components/BaseStoryComponent";
import Comment from "../types/Comment";
import Story from "../types/Story";
import { GlobalState } from "../GlobalState";
import Cookies from "js-cookie";
import { filter } from "vue/types/umd";

//a ne pas oublier
@Component({})
export default class StoryInfo extends BaseStoryComponent {
  public story: Story | null = null;
  public comment: Comment[] | null = null;
  public id: any = this.$route.params.id;
  public authentify: any = Cookies.get("token");
  public content = "";
  public newComment: Comment = {
    id: 0,
    content: "",
    commentDate: null,
    username: null,
    story: this.id,
    subComment: null,
  };

  beforeMount() {
    this.fetch<Story>("story/info/" + this.id, METHODS.GET).then(
      (res) => (this.story = res)
    );
  }

  public addComment(event: Event): void {
    this.newComment.commentDate = new Date();
    this.newComment.content = this.content;
    this.newComment.username = GlobalState.state.user.name;
    event.preventDefault();
    //Ici on peut passer soit directement un objet dans notre body (qui est déjà au bon format)
    //Ou alors on passe manuellement chaque élements:
    // nom : this.nom
    // email : this.email
    this.fetch<Comment>("comment/create", METHODS.POST, {
      body: this.newComment,
    });
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
  <!-- Il faut un element "root" pour chaque template ici la div-->
  <div>
    <div class="container">
      <div class="story-info">
        <h1>{{ story.name }}</h1>
        <br />
        <p>Description :</p>
        <h2>{{ story.description }}</h2>
      </div>
      <vs-row
        v-bind:key="comment.id"
        v-for="comment in story.comments"
        class="list-comments"
      >
        <vs-col class="comment">
          <vs-col
            class="comment-header"
            vs-type="flex"
            vs-justify="space-between"
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
        </vs-col>
      </vs-row>
      <div v-if="authentify">
        <vs-row>
          <vs-textarea
            type="text"
            v-model="content"
            label="commentaire"
            id="comment-content"
          />
        </vs-row>
        <vs-row>
          <vs-button color="primary" type="gradient" @click="addComment"
            >Commenter</vs-button
          >
        </vs-row>
      </div>
    </div>
  </div>
</template>
