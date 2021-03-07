<script lang="ts">
import { Vue } from 'vue-class-component';
import { useToast } from 'primevue/usetoast';
import StoryDTO from '@/dto/StoryDTO';
import fetchApi, { METHODS } from '@/model/fetchAPI';

export default class Profile extends Vue {
	private toast = useToast();
	private story: StoryDTO | null = null;

	mounted() {
		fetchApi<string>('http://localhost:8080/helloworld', METHODS.GET);
	}

	public testToast() {
		this.toast.add({ severity: 'info', summary: 'Hey There !', detail: 'Message content', life: 3000 });
	}
}
</script>

<style lang="scss"></style>

<template>
	<Toast position="top-right" />
	<div>
		<Button @click="testToast">Vous etes sur la story numéro : {{ $route.params.id }}</Button>
	</div>
</template>
