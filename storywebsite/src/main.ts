import 'primeicons/primeicons.css'; //icons
import Button from 'primevue/button';
import Card from 'primevue/card';
import PrimveVue from 'primevue/config';
import Menubar from 'primevue/menubar';
import 'primevue/resources/primevue.min.css'; //core css
import 'primevue/resources/themes/vela-green/theme.css'; //theme
import { createApp } from 'vue';
import App from './App.vue';
import StoryCard from './components/StoryCard.vue';
import router from './router';
import UserSubscription from "@/components/UserSubscription.vue";
import UserConnection from "./components/UserConnection.vue";
import UserProfileUpdate from "@/components/UserProfileUpdate.vue";
import UserProfileDelete from "@/components/UserProfileDelete.vue";
import StoryCreate from "@/components/StoryCreate.vue";
import StoryUpdate from "@/components/StoryUpdate.vue";
import StoryDelete from "@/components/StoryDelete.vue";
import CharacterCreation from "@/components/CharacterCreation.vue";
import CharacterUpdate from "@/components/CharacterUpdate.vue";
import CharacterDelete from "@/components/CharacterDelete.vue";
import BlocStoryCreate from "@/components/BlocStoryCreate.vue";
import BlocStoryUpdate from "@/components/BlocStoryUpdate.vue";
import BlocStoryDelete from "@/components/BlocStoryDelete.vue";

const app = createApp(App);

// Vue Router for mutiple route (PrimeVue dependancy)
app.use(router);
// adding PrimeVue as ui Library
app.use(PrimveVue, { ripple: true });

// Component that we use
app.component('Menubar', Menubar);
app.component('Button', Button);
app.component('Card', Card);
app.component('StoryCard', StoryCard);
app.component('UserSubscription', UserSubscription);
app.component('UserConnection', UserConnection);
app.component('UserProfileUpdate', UserProfileUpdate);
app.component('UserProfileDelete', UserProfileDelete);
app.component('StoryCreate', StoryCreate);
app.component('StoryUpdate', StoryUpdate);
app.component('StoryDelete', StoryDelete);
app.component('CharacterCreation', CharacterCreation);
app.component('CharacterUpdate', CharacterUpdate);
app.component('CharacterDelete', CharacterDelete);
app.component('BlocStoryCreate', BlocStoryCreate);
app.component('BlocStoryUpdate', BlocStoryUpdate);
app.component('BlocStoryDelete', BlocStoryDelete);


// Mount the app
app.mount('#app');
