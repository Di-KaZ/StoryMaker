<script lang="ts">
import {Vue} from "vue-class-component";
import StoryDTO from "@/dto/Story";
import fetchAPI, {METHODS} from "@/model/fetchAPI";

export default class StoryCreate extends Vue{
  //Changer creationDate
  story: StoryDTO = {id: 0, name: "", description: "", creationDate: "", userId: 2}

  public createNewStory(event: Event){
    event.preventDefault();
    //Voir pour essayer de passer l'id d'un utilisateur connecté !
    fetchAPI<StoryDTO>("http://localhost:8080/stories/create", METHODS.POST, this.story);
  }


}
</script>

<style scoped>

</style>

<template>
  <form action="">
    <input v-model="story.name" type="text" name="story_name" id="story_name" /><br>
    <input v-model="story.creationDate" type="date" /><br>
    <textarea v-model="story.description" name="story_description" id="story_description" cols="50" rows="30" placeholder="La description de votre Histoire épique"></textarea><br>

    <button @click="createNewStory">Créer ton histoire !</button>

  </form>
</template>
