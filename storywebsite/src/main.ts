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
import Subscription from './components/Subscription.vue';
import router from './router';
import UserConnection from "./components/UserConnection.vue";
import UserProfileUpdate from "@/components/UserProfileUpdate.vue";
import UserProfileDelete from "@/components/UserProfileDelete.vue";

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
app.component('Subscription', Subscription);
app.component('UserConnection', UserConnection);
app.component('UserProfileUpdate', UserProfileUpdate);
app.component('UserProfileDelete', UserProfileDelete);

// Mount the app
app.mount('#app');
