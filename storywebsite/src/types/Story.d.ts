import Comment from "./Comment";
import User from "./User";

export default interface Story {
	id: number;
	name: string;
	description: string;
	creationDate: string;
	user: User;
	firstBlocId: string;
	comments: Comment[];
	cover: string;
}
