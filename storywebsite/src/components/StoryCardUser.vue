<script lang="ts">
import BaseStoryComponent, { METHODS } from "./BaseStoryComponent";
// eslint-disable-next-line no-unused-vars
import Story from "../types/Story";
import { Component, Prop } from "vue-property-decorator";
import { CreatorState } from "../CreatorState";

@Component({})
export default class StoryCardUser extends BaseStoryComponent {
  @Prop(Object) readonly infos: Story | undefined;

  get username(): string {
    return this.infos!.creationDate;
  }

  get storyname(): string {
    return this.infos!.name;
  }

  get description(): string {
    return this.infos!.description;
  }

  get cover(): string {
    return this.infos!.cover;
  }

  get id() {
    return this.infos!.id;
  }

  public play(): void {
    this.$router.push("/story/play/" + this.id);
  }

  public async modify() {
    var data = await this.fetch<any>("creator/load", METHODS.GET, {
      urlparams: { id: this.id }
    });
    CreatorState.commit("LOAD_JSON", JSON.stringify(data));
    this.$router.push("/story/create/");
  }

  public async deleteStory() {
    if (this.infos != null) {
      await this.fetch<any>("story/delete/" + this.id, METHODS.GET);
    }
    this.infoToast("Story supprimé avec succes veuillez recharger la page");
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
      <img :src="cover" alt="alt" />
    </div>
    <h2>{{ storyname }}</h2>
    <h4>
      Derniere modification le <span>{{ username }}</span>
    </h4>
    <div>
      <span>{{ description }}</span>
    </div>
    <div slot="footer">
      <vs-row vs-justify="flex-end">
        <vs-button @click="play" type="gradient" color="success" icon="play_arrow">
          Jouer
        </vs-button>
        <vs-button @click="modify" type="gradient" color="primary">
          Modifier
        </vs-button>
        <vs-button type="gradient" color="danger" @click="deleteStory"> Supprimer</vs-button>
      </vs-row>
    </div>
  </vs-card>
</template>
