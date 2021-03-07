export default interface StoryBlocDTO {
	id: number;
	name: string;
	text: string;
	storyId: number;
	coverUrl: string;
	/*	choices: string[]; => a afficher dans les boutons*/
}
