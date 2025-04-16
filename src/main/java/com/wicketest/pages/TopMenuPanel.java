package com.wicketest.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.AbstractLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.agilecoders.wicket.core.markup.html.bootstrap.button.dropdown.MenuBookmarkablePageLink;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.INavbarComponent;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar.Position;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarDropDownButton;

public class TopMenuPanel extends Panel {

	Logger log = LoggerFactory.getLogger(TopMenuPanel.class);
	
	private static final long serialVersionUID = 1L;

	
	public class MenuComponent implements INavbarComponent {
		
		private static final long serialVersionUID = 1L;
		
		Component button;
		Navbar.ComponentPosition position;
		
		public MenuComponent(Component button, Navbar.ComponentPosition position ) {
			this.button = button;
			this.position = position;			
		}
		
        @Override
        public Component create(String markupId) {
            return button;
        }

        @Override
        public Navbar.ComponentPosition getPosition() {
            return position;
        }
    };

	
	public TopMenuPanel(String id) {
		super(id);
		Navbar navbar = new Navbar("navbar");
		navbar.fluid(true);
		navbar.setBrandName(Model.of("Project name"));
		
		navbar.setPosition(Position.DEFAULT);
		
		
		NavbarDropDownButton dropdown = new NavbarDropDownButton(Model.of("Home")) {
			@Override
			protected java.util.List<AbstractLink> newSubMenuButtons(String buttonMarkupId) {
				List<AbstractLink> buttons = new ArrayList<>();
				
				//NavbarButton<?> button = new NavbarButton<HomePage>(HomePage.class, Model.of("Home"));
				MenuBookmarkablePageLink<?> button = 
						new MenuBookmarkablePageLink<HomePage>(	HomePage.class, Model.of("Home"));
				button.setMarkupId("button1");
				buttons.add(button);
				button = new MenuBookmarkablePageLink<HelloPage>(HelloPage.class, Model.of("Hello"));
				button.setMarkupId("button2");
				buttons.add(button);
				button = new MenuBookmarkablePageLink<TemplatePage>(TemplatePage.class, Model.of("Template"));
				button.setMarkupId("button3");
				buttons.add(button);
				button = new MenuBookmarkablePageLink<SimpleLoginPage>(SimpleLoginPage.class, Model.of("Login"));
				button.setMarkupId("button4");
				button = new MenuBookmarkablePageLink<DataListExample>(DataListExample.class, Model.of("Data list example"));
				button.setMarkupId("button5");
				buttons.add(button);			

				return buttons;
			};
		};
		
		
		
		navbar.addComponents(new MenuComponent(dropdown, Navbar.ComponentPosition.LEFT));
		
		/*
		navbar.addComponents(
	    		new MenuButton("Home", HomePage.class, Navbar.ComponentPosition.LEFT), 
	    		new MenuButton("Hello", HelloPage.class, Navbar.ComponentPosition.LEFT),
	    		new MenuButton("template", TemplatePage.class, Navbar.ComponentPosition.LEFT),
	    		new MenuButton("login", SimpleLoginPage.class, Navbar.ComponentPosition.LEFT)	    		
		        );
		*/
	       
	    add(navbar);
		
	}
}
