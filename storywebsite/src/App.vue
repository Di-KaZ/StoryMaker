<script lang="ts">
import Menu from 'primevue/menu';
import { Vue } from 'vue-class-component';

export default class App extends Vue {
	declare $refs: {
		menu: Menu;
	};

	user_menu = [
		{
			label: 'Profil / Connextion',
			to: '/profile',
		},
		{
			label: 'Parametres',
			to: '/',
		},
	];
	menu_items = [
		{
			label: 'Acceuil',
			to: '/',
		},
		{
			label: 'Créer',
			to: '/',
			items: [
				{
					label: 'Nouveau',
				},
				{
					label: 'Mes histoires',
				},
			],
		},
	];

	toggle(event: Event) {
		this.$refs.menu.toggle(event);
	}
}
</script>

<style lang="scss">
@import url('https://cdn.jsdelivr.net/npm/victormono@latest/dist/index.min.css');
body {
	margin: 0;
	padding: 0;
	font-family: 'Victor Mono', Avenir, Helvetica, Arial, sans-serif !important;
	-webkit-font-smoothing: antialiased;
	-moz-osx-font-smoothing: grayscale;
}

.logo {
	height: 60px;
}
</style>

<template>
	<Toast />
	<Menubar :model="menu_items">
		<template #start>
			<img class="logo" src="./assets/logo.png" alt="Logo" />
		</template>
		<template #end>
			<Avatar icon="pi pi-user" shape="circle" size="large" @click="toggle" />
			<Menu id="user_menu" ref="menu" :model="user_menu" :popup="true" />
		</template>
	</Menubar>
	<router-view />
</template>
