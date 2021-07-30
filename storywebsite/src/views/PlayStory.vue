<script lang="ts">
import BaseStoryComponent, { METHODS } from "@/components/BaseStoryComponent";
import { Component } from "vue-property-decorator";
import Story from "../types/Story";
import BlocStory from "../types/BlocStory";
@Component({
   
})
export default class PlayStory extends BaseStoryComponent {

    private story: Story | null = null;
    private isLoadedData = false;
    beforeMount(){
        this.loadFirstBlocStory();
    }
    
    async loadFirstBlocStory(){
        this.story = await this.fetch<Story>("story/play/"+ this.$route.params.id, METHODS.GET);
        this.isLoadedData = true;
    }

    onPlayStory(){  
        this.$router.push("/story/play/"+ this.$route.params.id + "/blocstory/" + this.story?.firstBlocId);
    }
}
</script>
<template>
    <div v-if="isLoadedData">
        <h1>Vous jouez à : {{story.name}}</h1><br>
        <vs-button @click="onPlayStory">Jouer</vs-button>
    </div>
    
    
</template>