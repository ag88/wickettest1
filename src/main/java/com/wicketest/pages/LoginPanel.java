package com.wicketest.pages;

import org.apache.wicket.markup.html.panel.Panel;

public class LoginPanel extends Panel {

	private static final long serialVersionUID = 1L;

	public LoginPanel(String id) {
		super(id);
		
		LoginForm form = new LoginForm("loginForm");
		add(form);
	}
}
