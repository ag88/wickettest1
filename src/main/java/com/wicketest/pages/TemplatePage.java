package com.wicketest.pages;

import org.apache.wicket.Component;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssUrlReferenceHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptUrlReferenceHeaderItem;
import org.apache.wicket.markup.head.MetaDataHeaderItem;
import org.apache.wicket.markup.head.StringHeaderItem;
import org.apache.wicket.markup.html.CrossOrigin;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplatePage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
	private Logger log = LoggerFactory.getLogger(TemplatePage.class);
	
	//public static final String CONTENT_ID = "contentComponent";
	
	protected Component topmenuPanel;
	protected Component headerPanel;
	protected Component leftPanel;
	protected Component footerPanel;
	
	public TemplatePage() {
		super(new PageParameters());
		add(new Label("PageTitle", getPageTitle()));
		createpage();	
	}

	public TemplatePage(IModel<?> model) {
		super(model);
		add(new Label("PageTitle", getPageTitle()));
		createpage();
	}


	public TemplatePage(PageParameters parameters) {
		super(parameters);
		add(new Label("PageTitle", getPageTitle()));
		createpage();
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		addBootStrapResources(response);
	}

	/**
	 * Adds the boot strap resources.
	 * Derived pages can override this to avoid adding bootstrap resources or change them
	 *
	 * @param response the response
	 */
	protected void addBootStrapResources(IHeaderResponse response) {
		//response.render(new StringHeaderItem("<!-- bootstrap headers -->".concat(System.lineSeparator())));
		CssUrlReferenceHeaderItem csshdr = CssUrlReferenceHeaderItem.forUrl(
			"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css");		
		csshdr.setIntegrity("sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH");
		csshdr.setCrossOrigin(CrossOrigin.ANONYMOUS);
		response.render(csshdr);
		response.render(new StringHeaderItem(System.lineSeparator()));
		JavaScriptUrlReferenceHeaderItem jshdr = JavaScriptUrlReferenceHeaderItem.forUrl(
			"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js");
		jshdr.setIntegrity("sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz");
		jshdr.setCrossOrigin(CrossOrigin.ANONYMOUS);
		response.render(jshdr);		
	}
	
	/**
	 * Create page.
	 * 
	 */
	protected void createpage() {
		add(topmenuPanel = new TopMenuPanel("topmenuPanel"));
		add(headerPanel = new HeaderPanel("headerPanel"));
		add(leftPanel = new LeftPanel("leftPanel"));
		add(footerPanel = new FooterPanel("footerPanel"));
		
		//add(new Label(CONTENT_ID, "Put your content here"));
	}

	/**
	 * Gets the page title.
	 * Derived pages should override this to set the page title
	 *
	 * @return the page title
	 */
	public String getPageTitle() {
		return "Template Page";
	} 
	
	public Component getTopmenuPanel() {
		return topmenuPanel;
	}

	public void setTopmenuPanel(Component topmenuPanel) {
		this.topmenuPanel = topmenuPanel;
	}

	public Component getHeaderPanel() {
		return headerPanel;
	}

	public void setHeaderPanel(Component headerPanel) {
		this.headerPanel = headerPanel;
	}

	public Component getLeftPanel() {
		return leftPanel;
	}

	public void setLeftPanel(Component leftPanel) {
		this.leftPanel = leftPanel;
	}

	public Component getFooterPanel() {
		return footerPanel;
	}

	public void setFooterPanel(Component footerPanel) {
		this.footerPanel = footerPanel;
	}
	
	

}
