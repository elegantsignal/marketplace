package by.itacademy.elegantsignal.marketplace.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {

	@GetMapping("/login")
	public ModelAndView login(
		@RequestParam(value = "error", required = false) final String error,
		@RequestParam(value = "logout", required = false) final String logout) {

		final ModelAndView model = new ModelAndView();
		model.setViewName("login");

		if (error != null) {
			model.addObject("error", "login failed");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		return model;

	}
}