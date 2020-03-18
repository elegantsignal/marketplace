package by.itacademy.elegantsignal.marketplace.service.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.itacademy.elegantsignal.marketplace.service.IUserService;


public class ServiceSpringContextExample {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSpringContextExample.class);

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
		LOGGER.info("ICarService: {}", context.getBean(IUserService.class));
		LOGGER.info("all beans: {}", context.getBeanDefinitionNames());

		((ClassPathXmlApplicationContext) context).close();

	}
}
