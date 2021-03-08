<script lang="ts">
import Fetcher from '@/utils/Fetcher';
import { METHODS } from '@/utils/Fetcher';
import { Vue } from 'vue-class-component';
import UserDTO from '../dto/UserDTO';

export default class UserProfileDelete extends Vue {
	//Fausse initialisation d'un utilisateur => "Il s'est connecté et ile veux modifier ses informations de profil"
	user: UserDTO = { id: 6, name: 'azerty', password: '', email: '' };

	public deleteUser(event: Event) {
		event.preventDefault();
		Fetcher.fetch<UserDTO>('http://localhost:8080/users/delete/' + this.user.id, METHODS.DELETE, this.user.id);
	}
}
</script>

<style scoped></style>

<template>
	<p>Bienvenue {{ user.name }}</p>
	<br />
	<button @click="deleteUser">Supprimer son profil</button>
</template>
