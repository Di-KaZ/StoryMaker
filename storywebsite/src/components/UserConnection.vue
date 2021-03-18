<script lang="ts">
import { Vue } from 'vue-class-component';
import User from '../types/User';
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
		const user: User = { name: this.name, email: '' };
		const response = this.fetch<User>('users/get', METHODS.POST, user);
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
	<div>
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
	</div>
</template>
