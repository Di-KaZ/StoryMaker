package storyteam.server.story.object;

import java.util.ArrayList;
import java.util.List;

import storyteam.server.story.dto.StoryBlocDTO;
import storyteam.server.story.inter.IStoryBlocManager;

public class StoryBlocManagerTester implements IStoryBlocManager {
	private String name = "Chapter 1: A strange cat.";
	private String content = "Here is a vibing cat. He looks so smooth 🌊";
	private String urlImage = "https://media.tenor.com/images/f093e051245a0f0676af057a47708432/tenor.gif";
	private List<String> choices = new ArrayList<String>();

	public StoryBlocManagerTester() {
		// unused but should get the stored entity of a story bloc
		choices.add("Pet the cat 😻");
		choices.add("Vibe with him 🕺");
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public List<String> getChoiceName() {
		return choices;
	}

	@Override
	public String getImage() {
		return urlImage;
	}

	@Override
	public StoryBlocDTO toDTO() {
		return new StoryBlocDTO(name, urlImage, choices);
	}
}
