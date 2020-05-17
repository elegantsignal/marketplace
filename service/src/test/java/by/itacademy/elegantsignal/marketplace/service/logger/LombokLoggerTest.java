package by.itacademy.elegantsignal.marketplace.service.logger;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j
public class LombokLoggerTest {

	@Test
	public void testSlf4jAnnotation() {
		log.info("info message");
		log.warn("warm message");
		log.error("error message");
	}

}