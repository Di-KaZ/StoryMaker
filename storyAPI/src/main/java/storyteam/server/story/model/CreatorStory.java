package storyteam.server.story.model;

import java.util.HashSet;
import java.util.Set;

public class CreatorStory {
	private StoryInfo story;
	private Set<CreatorBloc> blocs;

	public CreatorStory(Story bddStory) {
		story = new StoryInfo();
		story.setId(bddStory.getId());
		story.setName(bddStory.getName());
		story.setDescription(bddStory.getDescription());
		story.setCreationDate(bddStory.getCreationDate());
		story.setUsername(bddStory.getUser().getName());
		story.setFirstBlocId(bddStory.getFirstBlocId().toString());
		story.setCover(bddStory.getCover());
		blocs = new HashSet<CreatorBloc>();
		Double x = 0D;
		Double y = 0D;
		for (var bloc : bddStory.getBlocStories()) {
			var tmpBloc = new CreatorBloc();
			tmpBloc.setId(bloc.getId().toString());
			tmpBloc.setName(bloc.getName());
			tmpBloc.setText(bloc.getContent());
			tmpBloc.setX(x);
			tmpBloc.setY(y);
			tmpBloc.setSelected(x == 0D);
			if (bloc.getPreviousBlocId() != null) {
				tmpBloc.setIn(new CreatorBlocPrevious());
				tmpBloc.getIn().setId(bloc.getPreviousBlocId().toString());
			}
			x += 350D;
			blocs.add(tmpBloc);
		}
		blocs.forEach(bloc -> {
			var parent = blocs.stream().filter(firstBloc -> bloc.getId().equals(firstBloc.getId())).findFirst();
			parent.ifPresent(parentBloc -> {
				bloc.getIn().setX(parentBloc.getX());
				bloc.getIn().setY(parentBloc.getY());
			});
		});
	}

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
