<script lang="ts">
import { Vue } from 'vue-class-component';
import BlocStoryDTO from '@/dto/BlocStoryDTO';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class BlocStoryDelete extends BaseStoryComponent {
	blocStory: BlocStoryDTO = { id: 2, name: 'Continuer sa route', text: 'Lorem ipsum', storyId: 1 };

	public deleteBlocStory(event: Event) {
		event.preventDefault();
		this.fetch<BlocStoryDTO>(
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
