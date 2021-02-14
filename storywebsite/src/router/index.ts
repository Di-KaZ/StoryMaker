import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
import Home from '../views/Home.vue';
import Profile from '../views/Profile.vue';

const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		name: 'Home',
		component: Home,
	},
	{
		path: '/profile',
		name: 'Profile',
		component: Profile,
	},
	{
		path: '/profile',
		name: 'Profile',
		component: Profile,
	},
];

const router = createRouter({
	history: createWebHashHistory(),
	routes,
});

export default router;
