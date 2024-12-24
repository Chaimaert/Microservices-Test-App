package com.rapport.generator;

import com.rapport.generator.exception.ReportGenerationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeneratorApplicationTests {

	@Test
	void contextLoads() {
		throw new ReportGenerationException("Error occurred while generating the report");

	}

}
