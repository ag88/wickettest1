package com.wicketest.pages;

import java.beans.BeanDescriptor;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.IModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;


public class DataRowPanel<T> extends Panel {
	
	Logger log = LoggerFactory.getLogger(DataRowPanel.class);
		
	Class<T> itemclass;
	
	public DataRowPanel(String id, IModel<T> model, Class<T> itemclass) {
		super(id, model);
		this.itemclass = itemclass;
		init();
	}

	protected void init() {		
    	Marker marker = MarkerFactory.getMarker("init()");
    	T bean = (T) getDefaultModelObject();    			
    	
    	try {
			BeanInfo beaninfo = Introspector.getBeanInfo(itemclass);
			PropertyDescriptor[] propdesc = beaninfo.getPropertyDescriptors();						
			
			RepeatingView fields = new RepeatingView("dataField");
			for(PropertyDescriptor pd: propdesc) {				
				try {
					Object o = pd.getReadMethod().invoke(bean);
					if(o != null)
						fields.add(new Label(fields.newChildId(),o.toString()));
					else
						fields.add(new Label(fields.newChildId(), ""));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					log.error(marker, e.getMessage());
				}	
			}
			add(fields);			
			
		} catch (IntrospectionException e) {
			log.error(marker, e.getMessage());
		}    	
	}
	
	private static final long serialVersionUID = 773465778036471818L;
}

