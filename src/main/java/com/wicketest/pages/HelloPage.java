package com.wicketest.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public class HelloPage extends TemplatePage {

	private static final long serialVersionUID = 1L;
	
    public HelloPage() {
    	super();
    	add(new Label("helloMessage", "Hello WicketWorld!"));
    }
    
    @Override
    public String getPageTitle() {
    	return "Hello Page";
    }
}
