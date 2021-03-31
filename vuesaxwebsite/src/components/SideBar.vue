<script lang="ts">
import { Component } from "vue-property-decorator";
import BaseStoryComponent, { METHODS } from "./BaseStoryComponent";
import { GlobalState } from "../GlobalState";
import { use } from "chai";
import User from "@/types/User";
import jscookie from "js-cookie";

@Component({})
export default class SideBar extends BaseStoryComponent {
  public username = "";
  public password = "";
  public confirmPassword = "";
  public email = "";

  get user(): User | null {
    return GlobalState.state.user;
  }

  set user(user: User | null) {
    GlobalState.commit("changeUser", user);
  }

  private loginRegister = false;

  /**
   * get la variable active pour savoir si la sidebar est ouverte
   */
  get active(): boolean {
    return GlobalState.state.sideBarOpen;
  }

  /**
   * set la variable depuis l state global au moment ou active change
   */
  set active(value: boolean) {
    GlobalState.commit("toggleSideBar");
  }

  /**
   * Quand on clique sur le bouton se connecter rejoindre de la sidebar
   */
  public popUpConnect(): void {
    this.loginRegister = true;
  }

  /**
   * Function applé l'orsque l'utilisateur clique le bouton Nous rejoindre !
   */
  public registerNewUser(): void {
    // this.user = { name: "GET_MOUSSED", email: "test@gmail.com" };
    this.fetch<User>("user/login", METHODS.POST, "", {
      name: this.username,
      email: this.email,
      password: this.password,
    })
      .then((user) => {
        console.log("test");
      })
      .catch(() => {
        this.errorToast("Fail");
      });
  }

  /**
   * Deconnecte l'utilisateur
   */
  public disconnectUser(): void {
    this.user = null;
  }

  /**
   * Connecte l'utilisateur
   */
  public connectUser(): void {
    this.fetch<User>("user/login", METHODS.POST, "", {
      name: this.username,
      password: this.password,
    })
      .then((user) => {
        if (user === undefined) {
          console.log("here");
          this.user = null;
        } else {
          this.user = user;
          // console.log(jscookie.get('token'));
        }
      })
      .catch(() => {
        this.errorToast(
          "Désolé !",
          "Auncun compte n'a été trouver avec ces informations."
        );
      });
    this.loginRegister = false;
    // this.user = { name: "GET_MOUSSED", email: "test@gmail.com" };
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

.vs-input--input {
  padding: 5px;
}

.popUp {
  z-index: 100000;
}
</style>

<template>
  <div>
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
          {{ user === null ? "guest" : user.name }}
          <vs-button color="primary" icon="more_horiz" type="flat"></vs-button>
        </h4>
      </div>
      <vs-sidebar-item index="1" icon="account_box">
        Mon profile
      </vs-sidebar-item>
      <vs-sidebar-item index="2" icon="book"> Mes histoires </vs-sidebar-item>
      <div class="footer-sidebar" slot="footer">
        <vs-button
          @click="disconnectUser"
          v-if="user"
          icon="reply"
          color="danger"
          type="flat"
          >Deconnection</vs-button
        >
        <vs-button
          v-if="!user"
          @click="popUpConnect"
          icon="how_to_reg"
          color="success"
          type="flat"
          >Connection / S'inscrire</vs-button
        >
        <vs-button icon="settings" color="primary" type="border"></vs-button>
      </div>
    </vs-sidebar>
    <vs-popup
      class="popUp"
      title="Se connecter / Créer un compte"
      color="primary"
      :active.sync="loginRegister"
    >
      <vs-tabs alignment="center">
        <vs-tab label="Connection" value="1">
          <vs-row>
            <vs-col
              vs-type="flex"
              vs-justify="center"
              vs-align="center"
              vs-w="12"
            >
              <vs-input
                icon="account_circle"
                placeholder="Nom d'utilisateur"
                v-model="username"
              />
            </vs-col>
            <vs-col
              vs-type="flex"
              vs-justify="center"
              vs-align="center"
              vs-w="12"
            >
              <vs-input
                icon="lock"
                placeholder="Mot de passe"
                type="password"
                v-model="password"
              />
            </vs-col>
            <vs-col
              vs-type="flex"
              vs-justify="center"
              vs-align="center"
              vs-w="12"
            >
              <vs-button color="primary" type="gradient" @click="connectUser"
                >Connection</vs-button
              >
            </vs-col>
          </vs-row>
        </vs-tab>
        <vs-tab label="Nouveau ?">
          <vs-row>
            <vs-col
              vs-type="flex"
              vs-justify="center"
              vs-align="center"
              vs-w="12"
            >
              <vs-input
                icon="account_circle"
                placeholder="Nom d'utilisateur"
                v-model="username"
              />
            </vs-col>
            <vs-col
              vs-type="flex"
              vs-justify="center"
              vs-align="center"
              vs-w="12"
            >
              <vs-input icon="email" placeholder="E-mail" v-model="email" />
            </vs-col>
            <vs-col
              vs-type="flex"
              vs-justify="center"
              vs-align="center"
              vs-w="12"
            >
              <vs-input
                icon="lock"
                type="password"
                placeholder="Mot de passe"
                v-model="password"
              />
            </vs-col>
            <vs-col
              vs-type="flex"
              vs-justify="center"
              vs-align="center"
              vs-w="12"
            >
              <vs-input
                icon="lock"
                placeholder="Confirmer"
                type="password"
                v-model="confirmPassword"
              />
            </vs-col>
            <vs-col
              vs-type="flex"
              vs-justify="center"
              vs-align="center"
              vs-w="12"
            >
              <vs-button
                color="primary"
                type="gradient"
                @click="registerNewUser"
                >Nous rejoindre !</vs-button
              >
            </vs-col>
          </vs-row>
        </vs-tab>
      </vs-tabs>
    </vs-popup>
  </div>
</template>
