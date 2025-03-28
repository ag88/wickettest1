package com.wicketest.pages;

public class SimpleLoginPage extends TemplatePage {	
	
	private static final long serialVersionUID = 1L;

	public SimpleLoginPage(){
		super();
		add(new LoginPanel("loginPanel"));
		getLeftPanel().setVisible(false);
	}

	@Override
	public String getPageTitle() {
		return "Login";
	}
}
