<script lang="ts">
import { Vue } from 'vue-class-component';

export default class App extends Vue {
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
		{
			label: 'Profil',
			to: '/',
			items: [
				{
					label: 'Parametres',
				},
			],
		},
	];
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

.centered {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	width: 100%;
	height: 100%;
}

.logo {
	height: 60px;
}
</style>

<template>
	<Menubar :model="menu_items">
		<template #start>
			<img class="logo" src="./assets/logo.png" alt="Logo" />
		</template>
	</Menubar>
	<router-view />
</template>
