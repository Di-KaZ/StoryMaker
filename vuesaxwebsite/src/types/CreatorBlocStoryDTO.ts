export default interface CreatorBlocStoryDTO {
  id: number;
  name: string;
  text: string;
  x: number;
  y: number;
  bgcolor: string;
  parent: { id?: number; name?: string };
}
