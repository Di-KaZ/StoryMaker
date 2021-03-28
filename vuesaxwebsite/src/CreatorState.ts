import Vuex from "vuex";
import { default as CreatorBlocStoryDTO } from "./types/CreatorBlocStoryDTO";
import Story from "./types/Story";

let lastId = 0;

/**
 * Global state to share variable in all component of the app
 * to use wisely 🧙‍♂️
 */
export const CreatorState = new Vuex.Store({
  state: {
    story: {
      id: 0,
      name: "",
      description: "",
      creationDate: "",
      user: { name: "" },
    } as Story,
    selectedBloc: null as CreatorBlocStoryDTO | null,
    blocs: [] as CreatorBlocStoryDTO[],
  },
  getters: {
    getBlocs: (state) => state.blocs,
  },
  mutations: {
    /**
     * @param state previous state (this)
     * @param story story object with modification
     */
    modifyStory(state, story: Story): void {
      state.story = story;
    },
    /**
     * set the current selected bloc id
     * @param state prebious state (this)
     * @param bloc the selected bloc for modification
     */
    selectBloc(state, bloc: CreatorBlocStoryDTO): void {
      state.selectedBloc = bloc;
    },
    setBlocs(state, blocs: CreatorBlocStoryDTO[]) {
      state.blocs = blocs;
    },
    /**
     * modify a single bloc of the sotry
     * @param state prebious state (this)
     * @param dto bloc to modify
     */
    modifySelectedBloc(state, dto: CreatorBlocStoryDTO): void {
      console.log(dto.bloc);
      // if no bloc are selected exit
      if (state.selectedBloc === null) {
        return;
      }
      state.selectedBloc = dto;
      state.blocs = state.blocs.filter((b) => b.bloc.id !== dto.bloc.id);
      state.blocs = [...state.blocs, dto];
    },
    /**
     * Add a new bloc to the list
     * @param state
     * @param bloc
     */
    addNewBloc(state): void {
      const newBloc = {
        bloc: {
          id: lastId,
          name: "new_block_" + lastId++,
          text: "",
        },
        x: 10,
        y: 10,
      };
      state.selectedBloc = newBloc;
      state.blocs = [...state.blocs, newBloc];
    },
  },
});
