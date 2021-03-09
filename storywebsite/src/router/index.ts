import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
import Profile from '../views/Profile.vue';
import ProfileUpdate from '../views/ProfileUpdate.vue';
import StoryCreate from '../components/StoryCreate.vue';
import Home from '../views/Home.vue';
import PlayStory from '../views/PlayStory.vue';

const routes: Array<RouteRecordRaw> = [
	{
		path: '/',
		name: 'Home',
		component: Home,
	},
	{
		path: '/profile',
		name: 'loginUser',
		component: Profile,
	},
	{
		path: '/profile/update',
		name: 'updateUser',
		component: ProfileUpdate,
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
