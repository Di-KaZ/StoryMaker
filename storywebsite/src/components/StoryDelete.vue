<script lang="ts">
import { Vue } from 'vue-class-component';
import StoryDTO from '../dto/StoryDTO';
import fetchApi, { METHODS } from '@/model/fetchAPI';

export default class StoryDelete extends Vue {
	story: StoryDTO = {
		id: 3,
		name: 'bla bla bla',
		description: 'This story is good ! Play it Now!',
		creationDate: '',
		userId: 2,
		coverUrl: '',
	};

	public deleteStory(event: Event) {
		event.preventDefault();
		fetchApi<StoryDTO>('http://localhost:8080/stories/delete/' + this.story.id, METHODS.DELETE, this.story.id);
	}
}
</script>

<style scoped></style>

<template>
	<p>{{ story.name }}</p>
	<button @click="deleteStory">Supprimer l'histoire</button>
</template>
