<script lang="ts">
//Les import NE PAS OUBLIER lang="ts"
import { Vue } from 'vue-class-component';
import User from '../types/User';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class Subscription extends BaseStoryComponent {
	/*Déclaration des variables*/
	name = '';
	password = '';
	email = '';

	/*Déclaration d'une méthode*/
	public send(event: Event) {
		event.preventDefault();
		const user: User = { name: this.name, email: this.email };
		//Remplacer le console.log par un fetch
		this.fetch<User>('users/create', METHODS.POST, user);

		// Le then ici va récupérer les infos de l'utilisateur et on stock son id, son pseudo dans un cookie.
	}
}
</script>

<style scoped lang="scss"></style>

<template>
	<div class="centered">
		<form action="">
			<!--v-model pour attribuer une nouvelle valeur a l'attribut name déclaré plus haut-->
			<label for="user_name">Nom d'utilisateur</label>
			<input type="text" v-model="name" name="user_name" id="user_name" /><br />
			<label for="password">Mot de passe</label>
			<input type="password" v-model="password" name="password" id="password" /><br />
			<label for="email">email</label>
			<input type="email" v-model="email" name="email" id="email" /><br />

			<!--@click appel à la méthode send quand on clique-->
			<button @click="send">validate</button>
		</form>
	</div>
</template>
