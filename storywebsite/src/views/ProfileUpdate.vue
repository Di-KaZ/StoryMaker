<script lang="ts">
import { Vue } from 'vue-class-component';
import Fetcher from '@/utils/Fetcher';
import { METHODS } from '@/utils/Fetcher';
import UserDTO from '../dto/UserDTO';
export default class Profile extends Vue {
    userIsConnected = true;
    user: UserDTO = {id: 1, name: '', password: '', email: '' };

    public setSubscription(){
        this.userIsConnected = !this.userIsConnected;
    }

    public deleteProfile(){
        if(confirm("Voulez-vous vraiment supprimer votre compte ?")){
           Fetcher.fetch<UserDTO>('http://localhost:8080/users/delete/' + this.user.id, METHODS.DELETE);
        }
    }
}
</script>

<style lang="scss"></style>

<template>
    <div class="centered" v-if="userIsConnected != true">
        <button @click="setSubscription">Se connecter</button>
        <UserConnection></UserConnection>
    </div>
    <div class="centered" v-if="userIsConnected == true">
        <UserProfileUpdate></UserProfileUpdate>
        <button @click="deleteProfile">Supprimer mon profile</button>
    </div>
</template>