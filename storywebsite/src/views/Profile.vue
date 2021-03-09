<script lang="ts">
import { Vue } from 'vue-class-component';
export default class Profile extends Vue {
    subscription = false;

    public setSubscription(){
        this.subscription = !this.subscription;
    }
}
</script>

<style lang="scss"></style>

<template>
    <div class="centered" v-if="subscription == true">
        <button @click="setSubscription">Se connecter</button>
        <UserSubscription></UserSubscription>
    </div>
    <div class="centered" v-if="subscription != true">
        <button @click="setSubscription">s'inscrire</button>
        <UserConnection></UserConnection>
    </div>
</template>
