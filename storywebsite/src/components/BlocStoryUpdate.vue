<script lang="ts">
import { Vue } from 'vue-class-component';
import BlocStoryDTO from '@/dto/BlocStoryDTO';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class BlocStoryUpdate extends BaseStoryComponent {
	idBlocStory = 3;
	blocStory: BlocStoryDTO = { id: 3, name: 'continuer sa route', text: 'Lorem ipsum', storyId: 1 };

	public getBlocStory(event: Event) {
		event.preventDefault();
		const response = this.fetch<BlocStoryDTO>(
			'blocstories/get/' + this.idBlocStory,
			METHODS.POST,
			this.idBlocStory,
		);
		response.then(data => {
			this.blocStory.id = data.id;
			this.blocStory.name = data.name;
			this.blocStory.text = data.text;
			console.log(this.blocStory.name);
		});
	}

	public updateBlocStory(event: Event) {
		event.preventDefault();
		this.fetch<BlocStoryDTO>('blocstories/update', METHODS.POST, this.blocStory);
	}
}
</script>

<style scoped></style>

<template>
	<button @click="getBlocStory">Get info</button>
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

		<button @click="updateBlocStory">Ajouter un bloc</button>
	</form>
</template>
