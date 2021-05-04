
export default interface Comment {
    id: number;
    content: string;
    commentDate: string;
    user: string;
    story: string | null;
    subComment: string | null;
}