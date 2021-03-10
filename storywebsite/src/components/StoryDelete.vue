<script lang="ts">
import { Vue } from 'vue-class-component';
import StoryDTO from '../dto/StoryDTO';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class StoryDelete extends BaseStoryComponent {
	story: StoryDTO = {
		id: 3,
		name: 'bla bla bla',
		description: 'This story is good ! Play it Now!',
		creationDate: '',
		userId: 2,
		coverUrl: '',
		firstIdBloc: 2,
	};

	public deleteStory(event: Event) {
		event.preventDefault();
		this.fetch<StoryDTO>('stories/delete/' + this.story.id, METHODS.DELETE, this.story.id);
	}
}
</script>

<style scoped></style>

<template>
	<p>{{ story.name }}</p>
	<button @click="deleteStory">Supprimer l'histoire</button>
</template>
