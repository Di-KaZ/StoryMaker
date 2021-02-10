package storyteam.server.story.object;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storyteam.server.story.dto.StoryDTO;
import storyteam.server.story.inter.IStoryBlocManager;
import storyteam.server.story.inter.IStoryManager;

public class StoryManagerTester implements IStoryManager {
	private static Logger LOGGER = LoggerFactory.getLogger(StoryManagerTester.class);

	private String name = "The vibing Cat and you";

	private String urlCover = "https://i.pinimg.com/originals/15/14/6c/15146c00b84e01dea09c675bc635e175.gif";

	Map<String, IStoryBlocManager> blocs = new HashMap<>();

	public StoryManagerTester() {
		// fake story bloc
		IStoryBlocManager bloc = new StoryBlocManagerTester();
		blocs.put(bloc.getName(), bloc);
		// unused but should get the entity
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IStoryBlocManager getBloc(String name) {
		return blocs.get(name);
	}

	@Override
	public String getCover() {
		return urlCover;
	}

	@Override
	public boolean addBloc(IStoryBlocManager bloc) {
		LOGGER.info("called addbloc");
		return true;
	}

	@Override
	public IStoryBlocManager getFirstBloc() {
		return blocs.values().iterator().next();
	}

	@Override
	public StoryDTO toDTO() {
		return new StoryDTO(name, urlCover,
				blocs.values().stream().map(bloc -> bloc.toDTO()).collect(Collectors.toList()));
	}
}
