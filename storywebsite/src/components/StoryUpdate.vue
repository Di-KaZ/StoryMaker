<script lang="ts">
import { Vue } from 'vue-class-component';
import Story from '../types/Story';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class StoryUpdate extends BaseStoryComponent {
	story: Story = {
		id: 1,
		name: 'bla bla bla',
		description: 'This zaeaezez',
		creationDate: '2021-02-09',
		user: { name: 'GET_MOUSSED' },
	};

	public updateStory(event: Event) {
		event.preventDefault();
		this.fetch<Story>('stories/update', METHODS.POST, this.story);
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
