import Vuex from "vuex";
import { default as CreatorBlocStoryDTO } from "./types/CreatorBlocStoryDTO";
import { default as CreatorConnectionDTO } from "./types/CreatorConnectionDTO";
import Story from "./types/Story";

let lastIdBloc = 0;

function updateConnection(
  blocs: CreatorBlocStoryDTO[]
): CreatorConnectionDTO[] {
  const newConnections: CreatorConnectionDTO[] = [];

  blocs.forEach((b) => {
    if (b.parent.id) {
      console.log(`Connection between ${b.name} and ${b.parent.name}`);
      const { x, y } = blocs.find((bl) => bl.id === b.parent.id)!;
      newConnections.push({ blocId: b.id, xP: x, yP: y, xC: b.x, yC: b.y });
    }
  });
  return newConnections;
}

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
    connections: [] as CreatorConnectionDTO[],
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
     * modify a single bloc of the sotry
     * @param state prebious state (this)
     * @param dto bloc to modify
     */
    modifySelectedBloc(state, dto: CreatorBlocStoryDTO): void {
      const { selectedBloc, blocs } = state;

      // if no bloc are selected exit
      if (selectedBloc === null) return;

      selectedBloc.bgcolor = "gray";
      dto.bgcolor = "black";
      state.selectedBloc = dto;

      if (selectedBloc.id !== dto.id) {
        state.blocs = [
          ...blocs.filter((b) => b.id !== selectedBloc!.id && b.id !== dto.id),
          selectedBloc,
          dto,
        ];
      } else {
        // le selected bloc est le meme que celui recu en param donc on ajoute pas les deux
        state.blocs = [
          ...blocs.filter((b) => b.id !== selectedBloc!.id && b.id !== dto.id),
          dto,
        ];
      }
      state.connections = updateConnection(state.blocs);
    },
    setBlocs(state, blocs: CreatorBlocStoryDTO[]) {
      state.blocs = blocs;
    },
    /**
     * Add a new bloc to the list
     * @param state
     * @param bloc
     */
    addNewBloc(state): void {
      const newBloc = {
        id: lastIdBloc,
        name: "new_block_" + lastIdBloc++,
        text: "",
        x: 10,
        y: 10,
        bgcolor: "black",
        parent: {},
      };
      if (state.selectedBloc !== null) {
        state.selectedBloc.bgcolor = "gray";
      }
      state.blocs = [...state.blocs, newBloc];
      state.selectedBloc = newBloc;
    },
  },
});
