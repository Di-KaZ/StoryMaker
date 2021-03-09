<script lang="ts">
import { mixins, Vue } from 'vue-class-component';
import StoryDTO from '@/dto/StoryDTO';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class PlayStory extends BaseStoryComponent {
	private story: StoryDTO | null = null;
	declare $route: any;

	/**
	 * JAVA => Responentity<Story> = {name: "fwesgf", }
	 * Fetcher.fetch recup le json et regarde chaque champ de ce dernier example name = "fwegfwe"
	 * ce champ on le sauvegarde dans un nouveau StoryDTO ou le fait un name = json.name
	 */

	mounted() {
		this.fetch<StoryDTO>('http://localhost:8080/story/play/' + this.$route.params.id, METHODS.GET)
			.then(res => (this.story = res))
			.catch(error => this.errorToast(error.message, error.stack));
	}

	public testToast(): void {
		this.infoToast('Hey There !');
	}

	getCoverUrl(): string {
		if (this.story === null) {
			return '';
		}
		return this.story.coverUrl;
	}

	/**
	 *
	 */
	public fetchBlocStory(): null {
		return null;
	}
}
</script>

<style lang="scss"></style>

<template>
	<div>
		<Button @click="testToast">Vous etes sur la story numéro : {{ story?.name }}</Button>
		<img alt="name" :src="getCoverUrl()" style="height: 300px; object-fit: cover" />
		<h2>Vous êtes en train de jouer à {{ story?.name }}</h2>
		<!--<h3>{{story.blocs.first}}</h3>-->
		<!-- <Button @click="fecthBlocStory" value="choice1">Choix 1</Button> -->
		<!-- <Button @click="fecthBlocStory" value="choice2">Choix 2</Button> -->
	</div>
</template>
