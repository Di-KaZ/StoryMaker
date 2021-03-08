<script lang="ts">
import { Vue } from 'vue-class-component';
import { useToast } from 'primevue/usetoast';
import StoryDTO from '@/dto/StoryDTO';
import Fetcher, { METHODS } from '@/utils/Fetcher';

export default class PlayStory extends Vue {
	private toast = useToast();
	private story: StoryDTO | null = null;
	declare $route: any;

	/**
	 * JAVA => Responentity<Story> = {name: "fwesgf", }
	 * Fetcher.fetch recup le json et regarde chaque champ de ce dernier example name = "fwegfwe"
	 * ce champ on le sauvegarde dans un nouveau StoryDTO ou le fait un name = json.name
	 */

	mounted() {
		Fetcher.fetch<StoryDTO>('http://localhost:8080/story/play/' + this.$route.params.id, METHODS.GET).then(
			res => (this.story = res),
		);
	}

	public testToast(): void {
		this.toast.add({ severity: 'info', summary: 'Hey There !', detail: 'Message content', life: 3000 });
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
	<Button @click="testToast">Vous etes sur la story numéro : {{ story?.name }}</Button>
	<img alt="name" :src="getCoverUrl()" style="height: 300px; object-fit: cover" />
	<h2>Vous êtes en train de jouer à {{ story?.name }}</h2>
	<!--<h3>{{story.blocs.first}}</h3>-->
	<Button @click="fecthBlocStory" value="choice1">Choix 1</Button>
	<Button @click="fecthBlocStory" value="choice2">Choix 2</Button>
</template>
