package by.itacademy.elegantsignal.marketplace.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class HomePageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		LOGGER.info("HomePageController method has been called");

		return "home";
	}

}