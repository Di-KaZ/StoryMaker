<script lang="ts">
import StoryDTO from '@/dto/Story';
import { Vue } from 'vue-class-component';

// definit les props recu par le composant
class Props {
	dto?: StoryDTO;
}

// on set les props avec Vue.with
export default class StoryCard extends Vue.with(Props) {
	getCoverUrl(): string | undefined {
		return this.dto?.coverUrl;
	}

	getTitle(): string | undefined {
		return this.dto?.name;
	}
}
</script>

<style scoped></style>

<template>
	<Card>
		<template #header>
			<img alt="name" :src="getCoverUrl()" />
		</template>
		<template #title>
			{{ getTitle() }}
		</template>
		<template #content>
			test
		</template>
		<template #footer>
			<Button>Jouer !</Button>
		</template>
	</Card>
</template>
