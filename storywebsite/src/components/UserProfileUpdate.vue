<script lang="ts">
import UserDTO from '../dto/UserDTO';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class UserProfileUpdate extends BaseStoryComponent {
	//Fausse initialisation d'un utilisateur => "Il s'est connecté et ile veux modifier ses informations de profil"
	user: UserDTO = { id: 1, name: 'azerty', password: '12345', email: 'jacques.ducroux@gmail.com' };

	public update(event: Event) {
		event.preventDefault();
		this.fetch<UserDTO>('http://localhost:8080/users/update', METHODS.POST, this.user);
	}
}
</script>

<style scoped></style>

<template>
	<form action="">
		<!--v-model pour attribuer une nouvelle valeur a l'attribut name déclaré plus haut-->
		<label for="user_name">Nom d'utilisateur</label>
		<input type="text" v-model="user.name" name="user_name" id="user_name" /><br />
		<label for="password">Mot de passe</label>
		<input type="password" v-model="user.password" name="password" id="password" /><br />
		<label for="email">email</label>
		<input type="email" v-model="user.email" name="email" id="email" /><br />

		<!--@click appel à la méthode send quand on clique-->
		<button @click="update">Mise à jour du profil</button>
	</form>
</template>
