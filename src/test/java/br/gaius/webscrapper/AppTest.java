package br.gaius.webscrapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.fail;

public class AppTest{

	@DisplayName("should return false on first try")
	@Test
	void should_ReturnFalse_When_FirstTry() {
		fail("tries and miserably fail on first attempt");
	}
}