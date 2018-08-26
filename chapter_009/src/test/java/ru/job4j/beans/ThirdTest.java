package ru.job4j.beans;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Yury Matskevich
 */
public class ThirdTest {

	@Test
	public void tryToGetBeanWhichDefinedInJavaCode() {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(AppContext.class);
		context.getBean("third", Third.class);
	}
}