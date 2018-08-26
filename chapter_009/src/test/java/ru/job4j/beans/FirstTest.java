package ru.job4j.beans;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Yury Matskevich
 */
public class FirstTest {

	@Test
	public void tryToGetBeanWhichDefinedInXmlExplcity() {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("first.xml");
		context.getBean("first", First.class);
	}
}