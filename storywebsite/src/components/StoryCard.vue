<script lang="ts">
import StoryDTO from '@/dto/StoryDTO';
import { Vue } from 'vue-class-component';

// definit les props recu par le composant
class Props {
	dto?: StoryDTO;
}
// on set les props avec Vue.with
export default class StoryCard extends Vue.with(Props) {
	private author = 'GET_MOUSSED';
	private likes = 5;
	private dislikes = 10;

	public mounted(): void {
		//
	}

	public getCoverUrl(): string | undefined {
		return this.dto?.coverUrl;
	}

	public getTitle(): string | undefined {
		return this.dto?.name;
	}

	public getAuthor(): string {
		return this.author;
	}

	public getDescription(): string | undefined {
		return this.dto?.description;
	}

	public playStory(event: Event): void {
		event.preventDefault();

		//
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
			<img alt="name" :src="getCoverUrl()" />
		</template>
		<template #title>
			{{ getTitle() }}
		</template>
		<template #subtitle> de {{ getAuthor() }} </template>
		<template #content>{{ getDescription() }} </template>
		<template #footer>
			<div class="cardFooter">
				<Button>Jouer !</Button>
				<div>
					<Button>👍 {{ likes }}</Button>
					<Button>👎 {{ dislikes }}</Button>
				</div>
			</div>
		</template>
	</Card>
</template>
