package storyteam.server.story.model;

import java.util.Set;

public class CreatorStory {
	private StoryInfo story;
	private Set<CreatorBloc> blocs;

	public StoryInfo getStory() {
		return this.story;
	}

	public void setStory(StoryInfo story) {
		this.story = story;
	}

	public Set<CreatorBloc> getBlocs() {
		return this.blocs;
	}

	public void setBlocs(Set<CreatorBloc> blocs) {
		this.blocs = blocs;
	}

}
