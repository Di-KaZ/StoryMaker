import Vuex from "vuex";
import { default as CreatorBlocStoryDTO } from "./types/CreatorBlocStoryDTO";
import Story from "./types/Story";

let lastId = 0;

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
     * @param dto the selected bloc for modification
     */
    selectBloc(state, dto: CreatorBlocStoryDTO): void {
      const { selectedBloc, blocs } = state;
      dto.bgcolor = "black";

      state.selectedBloc!.bgcolor = "gray";

      state.blocs = [
        ...blocs.filter(
          (b) =>
            b.bloc.id !== selectedBloc!.bloc.id && b.bloc.id !== dto.bloc.id
        ),
        selectedBloc!,
        dto,
      ];

      state.selectedBloc = dto;
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
      // if no bloc are selected exit
      if (state.selectedBloc === null) return;
      state.selectedBloc = dto;
      state.blocs = [
        ...state.blocs.filter((b) => b.bloc.id !== dto.bloc.id),
        dto,
      ];
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
        bgcolor: "black",
      };
      if (state.selectedBloc !== null) {
        state.selectedBloc.bgcolor = "gray";
      }
      state.blocs = [...state.blocs, newBloc];
      state.selectedBloc = newBloc;
    },
  },
});
