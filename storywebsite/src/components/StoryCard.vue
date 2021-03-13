<script lang="ts">
import { Vue } from 'vue-class-component';
import Story from '../types/Story';

// definit les props recu par le composant
class Props {
	dto?: Story;
}
// on set les props avec Vue.with
export default class StoryCard extends Vue.with(Props) {
	declare $router;

	private likes = 5;
	private dislikes = 10;

	public mounted(): void {
		//
	}

	// public getCoverUrl(): string | undefined {
	// 	return this.dto?.coverUrl;
	// }

	public getTitle(): string {
		return this.dto!.name;
	}

	public getAuthor(): string {
		return this.dto!.user.name;
	}

	public getDescription(): string {
		return this.dto!.description;
	}

	public getDateCrea(): string {
		return this.dto!.creationDate;
	}

	public playStory(event: Event): void {
		event.preventDefault();
		this.$router.push({ name: 'PlayStory', params: { id: this.dto?.id } });
	}
}
</script>

<style scoped lang="scss">
.card {
	margin: 20px;
	img {
		height: 300px;
		object-fit: cover;
	}
}

.cardFooter {
	display: flex;
	justify-content: space-between;
	.p-button {
		margin: 10px;
	}
}
</style>

<template>
	<Card class="card">
		<template #header>
			<!-- <img alt="name" :src="getCoverUrl()" /> -->
		</template>
		<template #title>
			{{ getTitle() }}
		</template>
		<template #subtitle> de {{ getAuthor() }} le {{ getDateCrea() }} </template>
		<template #content>{{ getDescription() }} </template>
		<template #footer>
			<div class="cardFooter">
				<Button @click="playStory">Jouer !</Button>
				<div>
					<Button>👍 {{ likes }}</Button>
					<Button>👎 {{ dislikes }}</Button>
				</div>
			</div>
		</template>
	</Card>
</template>
