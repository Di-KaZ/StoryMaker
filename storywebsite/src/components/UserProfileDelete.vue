<script lang="ts">
import { Vue } from 'vue-class-component';
import User from '../types/User';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class UserProfileDelete extends BaseStoryComponent {
	//Fausse initialisation d'un utilisateur => "Il s'est connecté et ile veux modifier ses informations de profil"
	user: User = { name: 'azerty', email: '' };

	public deleteUser(event: Event) {
		event.preventDefault();
		this.fetch<User>('users/delete/' + this.user.name, METHODS.DELETE);
	}
}
</script>

<style scoped></style>

<template>
	<div>
		<p>Bienvenue {{ user.name }}</p>
		<br />
		<button @click="deleteUser">Supprimer son profil</button>
	</div>
</template>
