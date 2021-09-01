<script lang="ts">
import { Component } from "vue-property-decorator";
import BaseStoryComponent, { METHODS } from "../components/BaseStoryComponent";
import { GlobalState } from "../GlobalState";
import Comment from "../types/Comment";
import User from "../types/User";

@Component({})
export default class Comments extends BaseStoryComponent {
  public content = "";
  public newComment: Comment = {
    id: 0,
    content: "",
    commentDate: null,
    username: null,
    story: this.$route.params.id,
    subComment: null
  };

  public addComment(event: Event): void {
    this.newComment.commentDate = new Date();
    this.newComment.content = this.content;
    const user = (GlobalState.state.user as unknown) as User;
    this.newComment.username = user.name;
    console.log(this.newComment.subComment);
    event.preventDefault();
    //Ici on peut passer soit directement un objet dans notre body (qui est déjà au bon format)
    //Ou alors on passe manuellement chaque élements:
    // nom : this.nom
    // email : this.email
    this.fetch<Comment>("comment/create", METHODS.POST, {
      body: this.newComment
    });
  }
}
</script>

<style lang="scss" scoped></style>

<template>
  <div>
    <vs-row>
      <vs-textarea type="text" v-model="content" label="commentaire" id="comment-content" />
    </vs-row>
    <vs-row>
      <vs-button color="primary" type="gradient" @click="addComment">Commenter</vs-button>
    </vs-row>
  </div>
</template>
