package ru.job4j.sellboard.controller;

import ru.job4j.sellboard.model.entity.enums.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Yury Matskevich
 */
public class ScrollListner implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		sc.setAttribute("engines", Engine.values());
		sc.setAttribute("frames", Frame.values());
		sc.setAttribute("manipulators", Manipulator.values());
		sc.setAttribute("sorts", Sort.values());
		sc.setAttribute("transmissions", Transmission.values());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}
