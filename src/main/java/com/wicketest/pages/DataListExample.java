package com.wicketest.pages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataListExample extends TemplatePage {
	
	Logger log = LoggerFactory.getLogger(DataListExample.class);	
	
	public class Person implements Serializable {
		
		String firstname;
		String lastname;		
		
		public Person() {
		}

		public Person(String firstname, String lastname) {
			this.firstname = firstname;
			this.lastname = lastname;
		}

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getLastname() {
			return lastname;
		}

		public void setLastname(String lastname) {
			this.lastname = lastname;
		}
		
		private static final long serialVersionUID = 3754407033439696245L;
	}

	public DataListExample() {
    	super();
    	
    	getLeftPanel().setVisible(false);
    	
    	List<Person> persons = new ArrayList<>(10);
    	persons.add(new Person("Smith", "John"));
    	persons.add(new Person("Johnson", "Emily"));
    	persons.add(new Person("Williams", "David"));
    	persons.add(new Person("Brown", "Sarah"));
    	persons.add(new Person("Davis", "Michael"));  	
    	
    	DataListPanel<Person> datalist = new DataListPanel<>("dataPanel", Person.class, persons);
    	add(datalist);    	
    }
	
	
        
	@Override
	public String getPageTitle() {
		return "Data List Example";
	}



	private static final long serialVersionUID = 1L;
}

