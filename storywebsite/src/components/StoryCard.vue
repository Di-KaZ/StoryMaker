<script lang="ts">
import StoryDTO from '@/dto/Story';
import { Vue } from 'vue-class-component';

// definit les props recu par le composant
class Props {
	dto?: StoryDTO;
}
// on set les props avec Vue.with
export default class StoryCard extends Vue.with(Props) {
	public getCoverUrl(): string {
		return this.dto!.coverUrl;
	}

	public getTitle(): string {
		return this.dto!.name;
	}
}
</script>

<style scoped lang="scss">
.card {
	margin-bottom: 2rem;
}
</style>

<template>
	<Card class="card">
		<template #header>
			<img alt="name" :src="getCoverUrl()" style="height: 300px; object-fit: cover" />
		</template>
		<template #title>
			{{ getTitle() }}
		</template>
		<template #footer>
			<Button>Jouer !</Button>
		</template>
	</Card>
</template>
