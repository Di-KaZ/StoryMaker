<script lang="ts">
import { Vue } from 'vue-class-component';
import Character from '../types/Character';

import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class CharacterCreation extends BaseStoryComponent {
	character: Character = { id: 0, name: '', stat: '', storyId: 1 };

	public createNewCharacter(event: Event) {
		event.preventDefault();
		this.fetch<Character>('characters/create', METHODS.POST, this.character);
	}
}
</script>

<style scoped></style>
<template>
	<form action="">
		<input v-model="character.name" type="text" name="character_name" id="character_name" /><br />
		<input v-model="character.stat" type="text" name="character_stat" id="character_stat" /><br />

		<button @click="createNewCharacter">Créer le personnage</button>
	</form>
</template>
