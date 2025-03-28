package com.wicketest.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginForm extends Form {

	Logger log = LoggerFactory.getLogger(LoginForm.class);

	private Label loginStatus;
	private TextField usernameField;
	private PasswordTextField passwordField;


	public LoginForm(String id) {
		super(id);

		usernameField = new TextField<String>("username", Model.of(""));
		passwordField = new PasswordTextField("password", Model.of(""));
		loginStatus = new Label("loginStatus", Model.of(""));

		add(usernameField);
		add(passwordField);
		add(loginStatus);
	}

	public final void onSubmit() {
		String username = (String)usernameField.getDefaultModelObject();
		String password = (String)passwordField.getDefaultModelObject();

		if(username.equals("test") && password.equals("test")) 
			loginStatus.setDefaultModelObject("logged in");
		else
			loginStatus.setDefaultModelObject("Wrong username or password!");
	}
}
