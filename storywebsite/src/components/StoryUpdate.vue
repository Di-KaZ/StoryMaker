<script lang="ts">
import { Vue } from 'vue-class-component';
import StoryDTO from '../dto/StoryDTO';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class StoryUpdate extends BaseStoryComponent {
	story: StoryDTO = {
		id: 2,
		name: 'bla bla bla',
		description: 'This zaeaezez',
		creationDate: '2021-02-09',
		userId: 2,
		coverUrl: '',
		firstIdBloc: 2,
	};

	public updateStory(event: Event) {
		event.preventDefault();
		this.fetch<StoryDTO>('http://localhost:8080/stories/update', METHODS.POST, this.story);
	}
}
</script>

<style scoped></style>

<template>
	<form action="">
		<input v-model="story.name" type="text" name="story_name" id="story_name" /><br />
		<input v-model="story.creationDate" type="date" /><br />
		<textarea
			v-model="story.description"
			name="story_description"
			id="story_description"
			cols="50"
			rows="30"
		></textarea
		><br />

		<button @click="updateStory">Mettre à jour l'histoire</button>
	</form>
</template>
