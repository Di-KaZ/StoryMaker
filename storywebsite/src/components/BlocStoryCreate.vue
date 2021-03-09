<script lang="ts">
import { Vue } from 'vue-class-component';
import BlocStoryDTO from '@/dto/BlocStoryDTO';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class BlocStoryCreate extends BaseStoryComponent {
	blocStory: BlocStoryDTO = { id: 0, name: '', text: '', storyId: 1 };

	public createBlocStory(event: Event) {
		event.preventDefault();
		console.log(this.blocStory);
		this.fetch<string>('http://localhost:8080/blocstories/create', METHODS.POST, this.blocStory);
	}
}
</script>

<style scoped></style>

<template>
	<form action="">
		<input v-model="blocStory.name" type="text" name="blocStory_name" id="blocStory_name" /><br />
		<textarea
			v-model="blocStory.text"
			name="blocStory_text"
			id="blocStory_text"
			cols="50"
			rows="30"
			placeholder="La description de votre Histoire épique"
		></textarea
		><br />

		<button @click="createBlocStory">Ajouter un bloc</button>
	</form>
</template>
