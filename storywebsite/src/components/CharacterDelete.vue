<script lang="ts">
import { Vue } from 'vue-class-component';
import Character from '../types/Character';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class CharacterUpdate extends BaseStoryComponent {
	character: Character = { id: 2, name: 'john', stat: 'stat', storyId: 2 };

	public deleteCharacter(event: Event) {
		event.preventDefault();
		this.fetch<Character>('characters/delete/' + this.character.id, METHODS.DELETE, this.character.id);
	}
}
</script>

<style scoped></style>
<template>
	<form action="">
		<p>{{ character.name }}</p>

		<button @click="deleteCharacter">Suppression du personnage</button>
	</form>
</template>
