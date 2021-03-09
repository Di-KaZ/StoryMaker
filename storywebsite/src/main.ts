import BlocStoryCreate from '@/components/BlocStoryCreate.vue';
import BlocStoryDelete from '@/components/BlocStoryDelete.vue';
import BlocStoryUpdate from '@/components/BlocStoryUpdate.vue';
import CharacterCreation from '@/components/CharacterCreation.vue';
import CharacterDelete from '@/components/CharacterDelete.vue';
import CharacterUpdate from '@/components/CharacterUpdate.vue';
import StoryCreate from '@/components/StoryCreate.vue';
import StoryDelete from '@/components/StoryDelete.vue';
import StoryUpdate from '@/components/StoryUpdate.vue';
import UserProfileDelete from '@/components/UserProfileDelete.vue';
import UserProfileUpdate from '@/components/UserProfileUpdate.vue';
import UserSubscription from '@/components/UserSubscription.vue';
import 'primeicons/primeicons.css'; //icons
import Avatar from 'primevue/avatar';
import Button from 'primevue/button';
import Card from 'primevue/card';
import Carousel from 'primevue/carousel';
import PrimveVue from 'primevue/config';
import Menu from 'primevue/menu';
import Menubar from 'primevue/menubar';
import 'primevue/resources/primevue.min.css'; //core css
import 'primevue/resources/themes/vela-green/theme.css'; //theme
import Toast from 'primevue/toast';
import ToastService from 'primevue/toastservice';
import { createApp } from 'vue';
import App from './App.vue';
import StoryCard from './components/StoryCard.vue';
import UserConnection from './components/UserConnection.vue';
import router from './router';

const app = createApp(App);

// Vue Router for mutiple route (PrimeVue dependancy)
app.use(router);
// adding PrimeVue as ui Library
app.use(PrimveVue, { ripple: true });

app.use(ToastService);

// Component that we use
app.component('Menubar', Menubar);
app.component('Button', Button);
app.component('Card', Card);
app.component('StoryCard', StoryCard);
app.component('UserSubscription', UserSubscription);
app.component('UserConnection', UserConnection);
app.component('UserProfileUpdate', UserProfileUpdate);
app.component('UserProfileDelete', UserProfileDelete);
app.component('Avatar', Avatar);
app.component('Menu', Menu);
app.component('Carousel', Carousel);
app.component('StoryCreate', StoryCreate);
app.component('StoryUpdate', StoryUpdate);
app.component('StoryDelete', StoryDelete);
app.component('CharacterCreation', CharacterCreation);
app.component('CharacterUpdate', CharacterUpdate);
app.component('Toast', Toast);
app.component('CharacterDelete', CharacterDelete);
app.component('BlocStoryCreate', BlocStoryCreate);
app.component('BlocStoryUpdate', BlocStoryUpdate);
app.component('BlocStoryDelete', BlocStoryDelete);

// Mount the app
app.mount('#app');
