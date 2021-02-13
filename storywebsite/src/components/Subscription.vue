<script lang="ts">
//Les import NE PAS OUBLIER lang="ts"
import {Vue} from 'vue-class-component';
import UserDTO from '@/dto/UserDTO';
import fetchApi, {METHODS} from "@/model/fetchAPI";

export default class Subscription extends Vue {
	/*Déclaration des variables*/
  name = "";
  password = "";
  email = "";

	/*public initialisateUser(event: any) {
		event.preventDefault();
		let user: UserDTO = { name: name, password: this.user.password, email: this.user.email };
		console.log(this.user);
	}*/

  /*Déclaration d'une méthode*/
  public send(event: Event){
    event.preventDefault();
    const user: UserDTO = {id: 0, name: this.name, password: this.password, email: this.email}
    //Remplacer le console.log par un fetch
    fetchApi<UserDTO>("http://localhost:8080/users/create", METHODS.POST, user);
  }
}
</script>

<style scoped></style>

<template>
	<form action="">
    <!--v-model pour attribuer une nouvelle valeur a l'attribut name déclaré plus haut-->
    <label for="user_name">Nom d'utilisateur</label>
		<input type="text" v-model="name"  name="user_name" id="user_name" /><br>
    <label for="password">Mot de passe</label>
		<input type="password" v-model="password" name="password" id="password" /><br>
    <label for="email">email</label>
		<input type="email" v-model="email" name="email" id="email" /><br>

    <!--@click appel à la méthode send quand on clique-->
		<button @click="send">validate</button>
	</form>
</template>
