import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import Home from "../views/Home.vue";
import UserSubscription from "../components/UserSubscription.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/users/create",
    name: "CreateUser",
    component: UserSubscription,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes,
});

export default router;
