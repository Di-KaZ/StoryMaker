import "animate.css";
import "material-icons/iconfont/material-icons.css";
import Vue from "vue";
import infiniteScroll from "vue-infinite-scroll";
import Vuesax from "vuesax";
import "vuesax/dist/vuesax.css";
import App from "./App.vue";
import router from "./router";

Vue.config.productionTip = false;

Vue.use(Vuesax, {
  // options here
});

Vue.use(infiniteScroll);

new Vue({
  router,
  render: (h) => h(App),
}).$mount("#app");
