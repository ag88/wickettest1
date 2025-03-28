package com.wicketest.pages;

import org.apache.wicket.request.Response;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.MetaDataHeaderItem;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends TemplatePage {
	private static final long serialVersionUID = 1L;
	
	private Logger log = LoggerFactory.getLogger(HomePage.class);

	public HomePage(final PageParameters parameters) {
		super(parameters);

		getHeaderPanel().setVisible(false);
		getLeftPanel().setVisible(false);
		getFooterPanel().setVisible(false);
		
		add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

		// TODO Add your page's components here
		
		add(new Link<Void>("hello"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				//we redirect browser to another page.
                setResponsePage(HelloPage.class);
			}
		});

		add(new Link<Void>("template"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				//we redirect browser to another page.
                setResponsePage(TemplatePage.class);
			}
		});

		add(new Link<Void>("login"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				//we redirect browser to another page.
                setResponsePage(SimpleLoginPage.class);
			}
		});
		
	}
		
	@Override
	public String getPageTitle() {
		return "Apache Wicket Quickstart";
	}
	
}
