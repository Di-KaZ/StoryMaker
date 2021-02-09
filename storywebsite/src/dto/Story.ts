import StoryBlocDTO from './StoryBloc';

export default interface StoryDTO {
	name: string;
	coverUrl: string;
	blocs: StoryBlocDTO[];
}
