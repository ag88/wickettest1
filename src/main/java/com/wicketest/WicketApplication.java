package com.wicketest;

import org.apache.wicket.csp.CSPDirective;
import org.apache.wicket.csp.CSPDirectiveSrcValue;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import com.wicketest.pages.HomePage;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.wicketest.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();			

		// best place to do this is in Application#init()
		//Bootstrap.install(this);
		
		// if you want to customize bootstrap:
		BootstrapSettings settings = new BootstrapSettings();
		settings.useCdnResources(true);
		settings.setAutoAppendResources(false);
		Bootstrap.install(this, settings);

		// needed for the styling used by the quickstart
		getCspSettings().blocking()
			.add(CSPDirective.STYLE_SRC, CSPDirectiveSrcValue.SELF)
			.add(CSPDirective.STYLE_SRC, "https://fonts.googleapis.com/css")
			.add(CSPDirective.FONT_SRC, "https://fonts.gstatic.com");

		// add your configuration here
	}
}
