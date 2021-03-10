package storyteam.server.story;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoryApplicationTests {
	@Test
	void test() {
		Assertions.assertThat("actual").isEqualTo("actual");
	}
}
