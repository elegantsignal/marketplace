package by.itacademy.elegantsignal.marketplace.service.logger;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {

	private static final Logger LOGGER1 = LoggerFactory.getLogger("mylogger");

	private static final Logger LOGGER2 = LoggerFactory.getLogger(LoggerTest.class);

	@Test
	public void testLoggerLevels1() {
		LOGGER1.debug("debug message1");
		LOGGER1.info("info message1");
		LOGGER1.warn("warn message1");
		// LOGGER.error("error", new Exception());
	}

	@Test
	public void testLoggerLevels2() {
		LOGGER2.debug("debug message2");
		LOGGER2.info("info message2");
		LOGGER2.warn("warn message2");
		// LOGGER.error("error", new Exception());
	}

}