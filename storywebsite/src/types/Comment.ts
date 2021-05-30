
export default interface Comment {
    id: number;
    content: string;
    commentDate: Date | null;
    username: string | null;
    story: number | null;
    subComment: Comment | null;
}