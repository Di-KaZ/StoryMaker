import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

/**
 * Global state to share variable in all component of the app
 * to use wisely 🧙‍♂️
 */
export const GlobalState = new Vuex.Store({
  state: {
    sideBarOpen: false,
  },
  mutations: {
    toggleSideBar(state) {
      state.sideBarOpen = !state.sideBarOpen;
    },
  },
});
