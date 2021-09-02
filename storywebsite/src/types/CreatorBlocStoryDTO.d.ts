export default interface CreatorBlocStoryDTO {
	id: string;
	name: string;
	text: string;
	cover: string;
	x: number;
	y: number;
	selected: boolean;
	in?: CreatorBlocStoryDTO;
	out: CreatorBlocStoryDTO[];
}
