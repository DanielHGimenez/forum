package br.com.dhg.testebexs;

import br.com.dhg.testebexs.controller.PerguntasController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TesteBexsApplicationTests {

	@Autowired
	private PerguntasController perguntasController;

	@Test
	void contextLoads() {

		assertThat(perguntasController).isNotNull();

	}

}
