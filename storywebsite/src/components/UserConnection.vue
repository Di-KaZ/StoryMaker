<script lang="ts">
import { Vue } from 'vue-class-component';
import UserDTO from '@/dto/UserDTO';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class UserConnection extends BaseStoryComponent {
	name = '';
	password = '';
	loggedUser = false;
	loggedUsername = '';

	//Mettre en varuiable l'url Localhost:8080 => si en déployé lien du host
	/*Déclaration d'une méthode*/
	public connect() {
		//Remplacer le console.log par un fetch
		const user: UserDTO = { id: 0, name: this.name, password: this.password, email: '' };
		const response = this.fetch<UserDTO>('http://localhost:8080/users/get', METHODS.POST, user);
		response.then(data => {
			this.loggedUser = true;
			this.loggedUsername = data.name;
		});
	}

	public disconnect(event: Event) {
		event.preventDefault();
		this.loggedUser = false;
		this.loggedUsername = '';
		this.name = '';
		this.password = '';
	}
}
</script>

<style scoped></style>

<template>
	<div v-if="loggedUser != true">
		<form v-on:submit.prevent="onSubmit" action="">
			<!--v-model pour attribuer une nouvelle valeur a l'attribut name déclaré plus haut-->
			<label for="user_name">Nom d'utilisateur</label>
			<input type="text" v-model="name" name="user_name" id="user_name" /><br />
			<label for="password">Mot de passe</label>
			<input type="password" v-model="password" name="password" id="password" /><br />

			<!--@click appel à la méthode send quand on clique-->
			<button @click="connect">validate</button>
		</form>
	</div>
	<div v-if="loggedUser == true">
		<p>Bienvenue {{ loggedUsername }}</p>
		<br />
		<button @click="disconnect">Se déconnecter</button>
	</div>
</template>
