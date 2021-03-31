<script lang="ts">
import Story from '../types/Story';
import { Vue } from 'vue-class-component';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class Home extends BaseStoryComponent {
	stories: Story[] | null = null;	

	mounted() {
		this.fetch<Story[]>('story/page/0', METHODS.GET)
			.then(res => (this.stories = res))
			.catch(error => this.errorToast(error.message));
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
		<h2 class="title">📖 Les Stories du moment 📖</h2>
		<Carousel
			v-if="stories !== null"
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
