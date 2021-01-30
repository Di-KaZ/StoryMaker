import "primeicons/primeicons.css"; //icons
import Button from "primevue/button";
import PrimveVue from "primevue/config";
import Menubar from "primevue/menubar";
import "primevue/resources/primevue.min.css"; //core css
import "primevue/resources/themes/saga-blue/theme.css"; //theme
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

const app = createApp(App);

// Vue Router for mutiple route (PrimeVue dependancy)
app.use(router);
// adding PrimeVue as ui Library
app.use(PrimveVue, { ripple: true });
app.component("Menubar", Menubar);
app.component("Button", Button);
app.mount("#app");
