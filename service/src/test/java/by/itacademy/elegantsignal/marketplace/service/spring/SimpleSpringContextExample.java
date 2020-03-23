package by.itacademy.elegantsignal.marketplace.service.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SimpleSpringContextExample {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSpringContextExample.class);

	public static void main(final String[] args) {
		final ApplicationContext context = new ClassPathXmlApplicationContext("context-example.xml");
		LOGGER.info("main bean by type:{}", context.getBean(MainBean.class));

		LOGGER.info("main bean by name:{}", context.getBean("mainBean"));

		LOGGER.info("bean names:{}", context.getBeanDefinitionNames().toString());

		((ClassPathXmlApplicationContext) context).close();

	}
}
