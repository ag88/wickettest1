package com.wicketest.pages;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class DataListPanel<T extends Serializable> extends Panel {
	
	Logger log = LoggerFactory.getLogger(DataListPanel.class);
	
	Dao<T> dao;
	Class<T> itemclass;
	List<T> items;
	
	public DataListPanel(String id, Class<T> itemclass, Dao<T> dao) {
		super(id);
		this.dao = dao;
		this.itemclass = itemclass;
		init();
	}

	public DataListPanel(String id, Class<T> itemclass, List<T> items) {
		super(id);
		this.items = items;
		this.itemclass = itemclass;
		init();
	}
	protected void init() {		
    	Marker marker = MarkerFactory.getMarker("init()");
    	
    	try {
			BeanInfo beaninfo = Introspector.getBeanInfo(itemclass);
			PropertyDescriptor[] propdesc = beaninfo.getPropertyDescriptors();
			RepeatingView hdrItems = new RepeatingView("hdrRepeater");
			for(PropertyDescriptor pd : propdesc) {
				log.debug(marker, "{}", pd.getName());
				hdrItems.add(new Label(hdrItems.newChildId(), pd.getName()));
			}
			add(hdrItems);
		
			if (this.items == null)
				items = dao.findAll();
			RepeatingView dataRows = new RepeatingView("dataRepeater");
			for(T item : items) {
				dataRows.add(new DataRowPanel<T>(dataRows.newChildId(), new Model<T>(item), itemclass));	
			}
			add(dataRows);
			
		} catch (IntrospectionException e) {
			log.error(marker, e.getMessage());
		}
    	
	}
	
	private static final long serialVersionUID = 773465778036471818L;
}

