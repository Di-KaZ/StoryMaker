<script lang="ts">
import { Vue } from 'vue-class-component';
import { useToast } from 'primevue/usetoast';
import StoryDTO from '@/dto/StoryDTO';
import fetchApi, { METHODS } from '@/model/fetchAPI';

export default class PlayStory extends Vue {
	private toast = useToast();
	private story: StoryDTO | null = null;
	declare $route: any;
	private blocStory: BlocStoryDTO | null = null;

	/**
	 * JAVA => Responentity<Story> = {name: "fwesgf", }
	 * fetchapi recup le json et regarde chaque champ de ce dernier example name = "fwegfwe"
	 * ce champ on le sauvegarde dans un nouveau StoryDTO ou le fait un name = json.name
	 */

	mounted() {
		console.log('OUI');
		fetchApi<StoryDTO>('http://localhost:8080/story/play/' + this.$route.params.id, METHODS.GET).then(
			res => (this.story = res),
		);
		fetchAPI<BlocStoryDTO>('http://localhost:8080/blocstories/get/' + this.story.firstIdBloc, METHODS.GET).then(
			res => (this.blocStory = res),
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
	<!--<h3> Chapitre {{story.blocs.id}}</h3>-->
	<Button @click="fecthBlocStory" value="choice1">Choix 1</Button>
	<Button @click="fecthBlocStory" value="choice2">Choix 2</Button>
</template>
