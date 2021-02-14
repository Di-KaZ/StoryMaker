<script lang="ts">
import StoryDTO from '@/dto/Story';
import { Vue } from 'vue-class-component';
import fetchApi, { METHODS } from '../model/fetchAPI';

export default class Home extends Vue {
	stories: StoryDTO[] = Array(30).fill({
		name: 'Hey',
		coverUrl: 'https://source.unsplash.com/random',
		blocs: [],
		likes: 0,
	});
}
</script>

<style lang="scss">
.centered {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 100%;
}
.title {
	align-self: flex-start;
}
</style>

<template>
	<h2 class="title">Les stories recente</h2>
	<Carousel :value="stories" :numVisible="3" :numScroll="1" :autoplayInterval="3000" :circular="true">
		<template #item="story">
			<StoryCard v-bind:dto="story.data" :key="`stories-${index}`" />
		</template>
	</Carousel>
</template>
