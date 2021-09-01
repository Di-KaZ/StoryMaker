import Vuex from "vuex";
import { default as CreatorBlocStoryDTO } from "./types/CreatorBlocStoryDTO";
import { default as Link } from "./types/CreatorConnectionDTO";
import Story from "./types/Story";

const story: Story = {
	id: 0,
	name: "",
	description: "",
	creationDate: "",
	user: { name: "" },
	firstBlocId: "0",
	cover:"",
	comments:[]
};
const links: Link[] = [];
const blocs: CreatorBlocStoryDTO[] = [];
const selectedBloc: CreatorBlocStoryDTO | null = null;
const firstBlocId = undefined;

function updateLinks(blocs: CreatorBlocStoryDTO[]): Link[] {
	const links: Link[] = [];
	blocs.forEach(bloc => {
		if (bloc.in) {
			// mise a jour du parent
			bloc.in = blocs.find(b => b.id === bloc.in?.id);
			// creation d'un link
			links.push({ in: bloc.in!, out: bloc });
		}
	});
	return links;
}

export const CreatorState = new Vuex.Store({
	state: {
		story,
		selectedBloc: selectedBloc as CreatorBlocStoryDTO | null,
		blocs,
		links,
		firstBlocId
	},
	mutations: {
		/**
		 *
		 * @param state current state
		 * @param payload new bloc to add
		 */
		ADD_BLOC(state, payload: CreatorBlocStoryDTO) {
			if (state.selectedBloc !== null) {
				state.blocs.splice(
					state.blocs.findIndex(b => b.id === state.selectedBloc!.id),
					1,
					{ ...state.selectedBloc, selected: false }
				);
			}
			payload.selected = true;
			state.selectedBloc = payload;
			state.blocs.push(payload);
		},
		/**
		 * Delete selected bloc
		 * @param state current state
		 * @returns
		 */
		DELETE_BLOC(state) {
			// FIXME ne supprime pas le bon bloc
			if (state.selectedBloc === null) return;
			//remove ref to this bloc
			for (let i = 0; i < state.blocs.length; i++) {
				if (state.blocs[i].in && state.blocs[i].in?.id == state.selectedBloc.id) {
					state.blocs[i].in = undefined;
				}
				// TODO creer un nouveau tableau puis l'assigné au lieu de retirer des elements pendant l'iteration
				for (let j = 0; j < state.blocs[i].out.length; j++) {
					if (state.blocs[i].out[j].id == state.selectedBloc.id) {
						state.blocs[i].out.splice(j, 1);
					}
				}
			}
			state.blocs.splice(
				state.blocs.findIndex(b => b.id === state.selectedBloc!.id),
				1
			);
			state.links = updateLinks(state.blocs);
		},
		/**
		 * Selectionne et/ou modifie un bloc
		 * @param state current state
		 * @param payload : CreatorBlocStoryDTO bloc to modify
		 * @returns
		 */
		MODIFY_BLOC(state, payload: CreatorBlocStoryDTO) {
			if (!state.selectedBloc) return;
			if (state.selectedBloc.id !== payload.id) {
				// Désélection de l'ancien
				state.blocs.splice(
					state.blocs.findIndex(b => b.id === state.selectedBloc?.id),
					1,
					{ ...state.selectedBloc, selected: false }
				);
			}
			payload.selected = true;
			state.selectedBloc = payload;
			state.blocs.splice(
				state.blocs.findIndex(b => b.id === payload.id),
				1,
				payload
			);
			state.links = updateLinks(state.blocs);
		},
		/**
		 *
		 * @param state current state
		 * @param payload parent bloc
		 * @returns
		 */
		SET_CONNECTION(state, payload: CreatorBlocStoryDTO) {
			if (!state.selectedBloc) return;
			state.selectedBloc = { ...state.selectedBloc, in: payload };

			state.blocs.splice(
				state.blocs.findIndex(b => b.id === state.selectedBloc?.id),
				1,
				state.selectedBloc
			);

			state.blocs.splice(
				state.blocs.findIndex(b => b.id === payload.id),
				1,
				{ ...payload, out: [...payload.out, state.selectedBloc] }
			);
			state.links = updateLinks(state.blocs);
		},
		/**
		 * update position of links when bloc is dragged
		 * @param state current state
		 * @param payload dragged bloc
		 */
		UPDATE_CONNECTION(state, payload) {
			state.blocs.splice(
				state.blocs.findIndex(b => b.id === payload.id),
				1,
				payload
			);
			// TODO optimize this to refresh only the connection needed
			state.links = updateLinks(state.blocs);
		},
		EXPORT_JSON({ blocs, story }) {
			const total = { story, blocs };

			const jsonBlocs = JSON.stringify(total, (key, value) => {
				if (key === "in") {
					return { id: value.id, x: value.x, y: value.y };
				}
				if (key === "out") {
					return undefined;
				}
				return value;
			});
			console.log(jsonBlocs);
		},
		LOAD_JSON(state, payload) {
			state.blocs = JSON.parse(payload);
			state.links = updateLinks(state.blocs);
		},
		MODIFY_STORY(state, payload) {
			state.story = payload;
		}
	}
});

export const getStoryToJson = (story: Story, blocs: CreatorBlocStoryDTO[]) => {
	const total = { story, blocs };

	const jsonBlocs = JSON.stringify(total, (key, value) => {
		if (key === "in") {
			return { id: value.id, x: value.x, y: value.y };
		}
		if (key === "out") {
			return undefined;
		}
		return value;
	});
	return JSON.parse(jsonBlocs);
};
