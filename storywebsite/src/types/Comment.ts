
export default interface Comment {
    id: number;
    content: string;
    commentDate: Date | null;
    username: string | null;
    story: string | null;
    subComment: Comment | null;
}