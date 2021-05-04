import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Create from "../views/Create.vue";
import Home from "../views/Home.vue";
import Search from "../views/Search.vue";
import StoryInfo from "../views/StoryInfo.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/story/search",
    name: "Search",
    component: Search,
  },
  {
    path: "/story/create",
    name: "StoryCreate",
    component: Create,
  },
  //A changer après, faudrait une URL du genre /story/1/info
  {
    path: "/story/:id/info",
    name: "StoryInfo",
    component: StoryInfo,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
