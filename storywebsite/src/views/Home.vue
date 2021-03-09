<script lang="ts">
import StoryDTO from '@/dto/StoryDTO';
import { Vue } from 'vue-class-component';

export default class Home extends Vue {
	stories: StoryDTO[] = Array(10).fill({
		name: 'Hey',
		coverUrl: 'https://source.unsplash.com/random',
		blocs: [],
		likes: 0,
		description: "Ceci est une description de test rien d'interessant ici...",
	});

	mounted() {
		// Fetcher.fetch<StoryDTO[]>('', METHODS.GET).then(res => (this.stories = res));
	}
}
</script>

<style lang="scss">
.title {
	text-align: center;
	font-size: 40px;
}

.hideCarousselNav {
	display: none;
}
</style>

<template>
	<div class="centered">
		<Toast position="top-right" />
		<h2 class="title">📖 Les Stories du moment 📖</h2>
		<Carousel
			:value="stories"
			:numVisible="2"
			:numScroll="1"
			:autoplayInterval="4000"
			:circular="true"
			indicatorsContentClass="hideCarousselNav"
		>
			<template #item="story">
				<StoryCard v-bind:dto="story.data" :key="story.data" />
			</template>
		</Carousel>
	</div>
</template>
