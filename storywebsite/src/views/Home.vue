<script lang="ts">
import StoryDTO from '@/dto/Story';
import { Vue } from 'vue-class-component';
import fetchApi, { METHODS } from '../model/fetchAPI';

export default class Home extends Vue {
	test: StoryDTO | null = null;

	public async getStory(): Promise<void> {
		this.test = await fetchApi<StoryDTO>('http://localhost:8080/story/?id=1', METHODS.GET);
	}
}
</script>

<style lang="scss">
body {
	margin: 0;
	padding: 0;
	font-family: Avenir, Helvetica, Arial, sans-serif;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

.centered {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 100%;
}
</style>

<template>
	<div class="centered">
		<h1 v-if="test !== null"><StoryCard v-bind:dto="test" /></h1>
		<Button v-on:click="getStory">Change !</Button>

<!--    <UserSubscription></UserSubscription>-->
<!--    <UserConnection></UserConnection>-->
<!--    <UserProfileUpdate></UserProfileUpdate>-->
<!--    <UserProfileDelete></UserProfileDelete>-->
<!--    <StoryCreate></StoryCreate>-->
<!--    <StoryUpdate></StoryUpdate>-->
<!--    <StoryDelete></StoryDelete>-->
<!--    <CharacterCreation></CharacterCreation>-->
<!--    <CharacterUpdate></CharacterUpdate>-->
    <CharacterDelete></CharacterDelete>
	</div>
</template>
