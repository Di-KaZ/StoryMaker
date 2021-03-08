import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
import UserSubscription from '../components/UserSubscription.vue';
import StoryCreate from '../components/StoryCreate.vue'
import Home from '../views/Home.vue';
import PlayStory from '../views/PlayStory.vue';

const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		name: 'Home',
		component: Home,
	},
	{
		path: '/users/create',
		name: 'CreateUser',
		component: UserSubscription,
	},
	{
		path: '/story/create',
		name: 'CreateStory',
		component: StoryCreate,
	},
	{
		path: '/story/play/:id',
		name: 'PlayStory',
		component: PlayStory,
	},
];

const router = createRouter({
	history: createWebHashHistory(),
	routes,
});

export default router;
