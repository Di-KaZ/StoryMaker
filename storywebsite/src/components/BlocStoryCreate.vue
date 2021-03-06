<script lang="ts">
import {Vue} from "vue-class-component";
import fetchAPI, {METHODS} from "@/model/fetchAPI";
import BlocStoryDTO from "@/dto/BlocStoryDTO";

export default class BlocStoryCreate extends Vue{
  blocStory: BlocStoryDTO = {id: 0, name: "", text: "", storyId: 1};

  public createBlocStory(event: Event){
    event.preventDefault();
    console.log(this.blocStory);
    fetchAPI<BlocStoryDTO>("http://localhost:8080/blocstories/create", METHODS.POST, this.blocStory);
  }

}
</script>

<style scoped>

</style>

<template>
  <form action="">
    <input v-model="blocStory.name" type="text" name="blocStory_name" id="blocStory_name" /><br>
    <textarea v-model="blocStory.text" name="blocStory_text" id="blocStory_text" cols="50" rows="30" placeholder="La description de votre Histoire épique"></textarea><br>

    <button @click="createBlocStory">Ajouter un bloc</button>

  </form>
</template>
