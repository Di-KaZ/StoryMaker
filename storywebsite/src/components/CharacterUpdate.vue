<script lang="ts">
import {Vue} from "vue-class-component";
import CharacterDTO from "@/dto/CharacterDTO";
import fetchApi, {METHODS} from "@/model/fetchAPI";

export default class CharacterUpdate extends Vue{
  character: CharacterDTO = {id: 2, name: "john", stat: "stat", storyId: 2};

  public updateCharacter(event:Event){
    event.preventDefault();
    fetchApi<CharacterDTO>("http://localhost:8080/characters/update", METHODS.POST, this.character);
  }
}
</script>

<style scoped>

</style>
<template>
  <form action="">
    <input v-model="character.name" type="text" name="character_name" id="character_name" /><br>
    <input v-model="character.stat" type="text" name="character_stat" id="character_stat" /><br>

    <button @click="updateCharacter">Mise à jour du personnage</button>

  </form>
</template>