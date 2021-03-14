<script lang="ts">
import { Component } from "vue-property-decorator";
import BaseStoryComponent from "./BaseStoryComponent";
import { GlobalState } from "../GlobalState";

@Component({})
export default class SideBar extends BaseStoryComponent {
  get active(): boolean {
    return GlobalState.state.sideBarOpen;
  }

  set active(value: boolean) {
    GlobalState.commit("toggleSideBar");
  }
}
</script>

<style lang="scss">
.header-sidebar {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  width: 100%;
  h4 {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
  }
  button {
    margin-left: 10px;
  }
}
.footer-sidebar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  button {
    border: 0px solid rgba(0, 0, 0, 0) !important;
    border-left: 1px solid rgba(0, 0, 0, 0.07) !important;
    border-radius: 0px !important;
  }
}

.vs-con-items {
  justify-content: flex-start;
  width: 100%;
}

.username {
  text-transform: uppercase;
}
</style>

<template>
  <vs-sidebar
    parent="body"
    default-index="1"
    color="primary"
    spacer
    v-model="active"
    position-right
  >
    <div class="header-sidebar" slot="header">
      <vs-avatar size="70px" />
      <h4 class="username">
        guest
        <vs-button color="primary" icon="more_horiz" type="flat"></vs-button>
      </h4>
    </div>
    <vs-sidebar-item index="1" icon="account_box">
      Mon profile
    </vs-sidebar-item>
    <vs-sidebar-item index="2" icon="book">
      Mes histoires
    </vs-sidebar-item>
    <div class="footer-sidebar" slot="footer">
      <vs-button icon="reply" color="danger" type="flat">log out</vs-button>
      <vs-button icon="settings" color="primary" type="border"></vs-button>
    </div>
  </vs-sidebar>
</template>
