<script lang="ts">
import { Vue } from 'vue-class-component';
import BlocStoryDTO from '@/dto/BlocStoryDTO';
import Fetcher, { METHODS } from '@/utils/Fetcher';

export default class BlocStoryDelete extends Vue {
	blocStory: BlocStoryDTO = { id: 2, name: 'Continuer sa route', text: 'Lorem ipsum', storyId: 1 };

	public deleteBlocStory(event: Event) {
		event.preventDefault();
		Fetcher.fetch<BlocStoryDTO>(
			'http://localhost:8080/blocstories/delete/' + this.blocStory.id,
			METHODS.DELETE,
			this.blocStory.id,
		);
	}
}
</script>

<style scoped></style>

<template>
	<form action="">
		<p>{{ blocStory.name }}</p>

		<button @click="deleteBlocStory">Supprimer un bloc</button>
	</form>
</template>
