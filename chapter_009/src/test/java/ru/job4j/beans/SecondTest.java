package ru.job4j.beans;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Yury Matskevich
 */
public class SecondTest {

	@Test
	public void tryToGetBeanWhichDefinedViaAnnotationExplcity() {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("second.xml");
		context.getBean("second", Second.class);
	}
}