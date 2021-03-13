<script lang="ts">
import { Vue } from 'vue-class-component';
import Story from '../types/Story';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class StoryDelete extends BaseStoryComponent {
	story: Story = {
		id: 3,
		name: 'bla bla bla',
		description: 'This story is good ! Play it Now!',
		creationDate: '',
		user: { name: 'GET_MOUSSED' },
	};

	public deleteStory(event: Event) {
		event.preventDefault();
		this.fetch<Story>('stories/delete/' + this.story.id, METHODS.DELETE, this.story.id);
	}
}
</script>

<style scoped></style>

<template>
	<div>
		<p>{{ story.name }}</p>
		<button @click="deleteStory">Supprimer l'histoire</button>
	</div>
</template>
