import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Create from "../views/Create.vue";
import Home from "../views/Home.vue";
import PlayStory from "../views/PlayStory.vue";
import PlayStoryBloc from "../views/PlayStoryBloc.vue";
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
	{
		path: "/story/play/:id",
		name: "PlayStory",
		component: PlayStory,
	},
	// FIXME voir si ça marche : oui oui ouiiii, ca marche
	{
		path: "/story/play/:id/blocstory/:idbloc",
		name: "PlayStoryBloc",
		component: PlayStoryBloc,
	},
];

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	routes,
});

export default router;
