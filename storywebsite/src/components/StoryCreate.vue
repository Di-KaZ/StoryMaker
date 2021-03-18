<script lang="ts">
import { Vue } from 'vue-class-component';
import Story from '../types/Story';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class StoryCreate extends BaseStoryComponent {
	//Changer creationDate
	//On admet ici que l'utilisateur s'est connecté et il y'a un cookie qui a son id
	story: Story = { id: 0, name: '', description: '', creationDate: '', user: { name: 'GET_MOUSSED' } };

	public createNewStory(event: Event) {
		event.preventDefault();
		//Voir pour essayer de passer l'id d'un utilisateur connecté !
		this.fetch<Story>('stories/create', METHODS.POST, this.story);
	}
}
</script>

<style scoped></style>

<template>
	<div class="centered">
		<form action="">
			<input v-model="story.name" type="text" name="story_name" id="story_name" /><br />
			<input v-model="story.creationDate" type="date" /><br />
			<textarea
				v-model="story.description"
				name="story_description"
				id="story_description"
				cols="50"
				rows="30"
				placeholder="La description de votre Histoire épique"
			></textarea
			><br />

			<button @click="createNewStory">Créer ton histoire !</button>
		</form>
	</div>
</template>
