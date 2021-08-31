<script lang="ts">
import BaseStoryComponent from "./BaseStoryComponent";
// eslint-disable-next-line no-unused-vars
import Story from "../types/Story";
import { Component } from "vue-property-decorator";

const StoryCardProps = BaseStoryComponent.extend({
  props: {
    infos: { type: Object as () => Story }
  }
});

@Component({})
export default class StoryCard extends StoryCardProps {
  declare infoToast: (infoMsg: string, detail?: string) => void;
  public likes = 0;

  get username(): string {
    return this.infos.user.name + " " + this.infos.creationDate;
  }

  get storyname(): string {
    return this.infos.name;
  }

  get description(): string {
    return this.infos.description;
  }

  public addLike(): void {
    this.likes += 1;
  }

  public play(): void {
    this.$router.push("/story/play/"+ this.infos.id);
    this.infoToast("Play clicked !");
  }
}
</script>

<style scoped lang="scss">
h2 {
  text-transform: capitalize;
}

h4 span {
  text-transform: uppercase;
}

.card {
  margin: 15px;
}

.container-img {
  height: 200px;
  width: 100%;
  img {
    object-fit: cover;
    object-position: 50% 50%;
    border-radius: 9px;
  }
}

button {
  margin: 5px;
}
</style>

<template>
  <vs-card v-if="infos" actionable class="card">
    <div slot="media" class="container-img">
      <img src="https://source.unsplash.com/random" alt="alt" />
    </div>
    <h2>{{ storyname }}</h2>
    <h4>
      De <span>{{ username }}</span>
    </h4>
    <div>
      <span>{{ description }}</span>
    </div>
    <div slot="footer">
      <vs-row vs-justify="flex-end">
        <vs-button @click="play" type="gradient" color="success" icon="play_arrow">
          Jouer
        </vs-button>
        <vs-button @click="addLike" type="gradient" color="danger" icon="favorite">{{
          likes
        }}</vs-button>
        <vs-button type="gradient" color="primary" icon="share"></vs-button>
      </vs-row>
    </div>
  </vs-card>
</template>
