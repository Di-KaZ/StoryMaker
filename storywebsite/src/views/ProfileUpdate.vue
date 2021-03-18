<script lang="ts">
import { Vue } from 'vue-class-component';
import User from '../types/User';
import BaseStoryComponent, { METHODS } from '../utils/BaseStoryComponent';

export default class Profile extends BaseStoryComponent {
	userIsConnected = true;
	user: User = { name: '', email: '' };

	public setSubscription() {
		this.userIsConnected = !this.userIsConnected;
	}

	public deleteProfile() {
		if (confirm('Voulez-vous vraiment supprimer votre compte ?')) {
			this.fetch<User>('users/delete/' + this.user.name, METHODS.DELETE);
		}
	}
}
</script>

<style lang="scss"></style>

<template>
	<div>
		<div class="centered" v-if="userIsConnected != true">
			<button @click="setSubscription">Se connecter</button>
			<UserConnection></UserConnection>
		</div>
		<div class="centered" v-if="userIsConnected == true">
			<UserProfileUpdate></UserProfileUpdate>
			<button @click="deleteProfile">Supprimer mon profile</button>
		</div>
	</div>
</template>
