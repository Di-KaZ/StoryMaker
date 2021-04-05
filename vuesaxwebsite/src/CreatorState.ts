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
      const { x, y } = blocs.find((bl) => bl.id === b.parent.id)!;
      newConnections.push({
        childId: b.id,
        parentId: b.parent.id,
        xP: x + 100,
        yP: y + 300,
        xC: b.x + 100,
        yC: b.y,
      });
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

    updateConnection(state, draggedBloc: { id: number; x: number; y: number }) {
      // Récuperation des connections au enfants et mise a jour des coord du parent
      const childsConnections = state.connections
        .filter((c) => c.parentId && c.parentId === draggedBloc.id)
        .map((c) => {
          return { ...c, yP: draggedBloc.y + 300, xP: draggedBloc.x + 100 };
        });
      state.connections = [
        ...state.connections.filter(
          (c) =>
            c.childId &&
            c.childId !== draggedBloc.id &&
            c.parentId !== draggedBloc.id
        ),
        ...childsConnections,
      ];

      // Récuperation de la connection au parent
      const parentConnection = state.connections.find(
        (c) => c.childId === draggedBloc.id
      );
      if (parentConnection) {
        console.log("yahooo");
        parentConnection.xC = draggedBloc.x + 100;
        parentConnection.yC = draggedBloc.y;
        state.connections.push(parentConnection);
      }
    },
  },
});
