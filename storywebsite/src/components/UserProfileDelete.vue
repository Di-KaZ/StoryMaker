<script lang="ts">
import { Vue } from 'vue-class-component';
import UserDTO from '../dto/UserDTO';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class UserProfileDelete extends BaseStoryComponent {
	//Fausse initialisation d'un utilisateur => "Il s'est connecté et ile veux modifier ses informations de profil"
	user: UserDTO = { id: 6, name: 'azerty', password: '', email: '' };

	public deleteUser(event: Event) {
		event.preventDefault();
		this.fetch<UserDTO>('http://localhost:8080/users/delete/' + this.user.id, METHODS.DELETE, this.user.id);
	}
}
</script>

<style scoped></style>

<template>
	<p>Bienvenue {{ user.name }}</p>
	<br />
	<button @click="deleteUser">Supprimer son profil</button>
</template>
