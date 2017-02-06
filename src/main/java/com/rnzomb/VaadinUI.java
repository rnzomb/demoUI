package com.rnzomb;

import com.vaadin.annotations.Theme;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@SpringUI
@Theme("valo")
public class VaadinUI extends UI {

	@Autowired
	private CustomerService service;

	private Customer customer;

	private Grid grid = new Grid();
	private TextField firstName = new TextField("First name");
	private TextField lastName = new TextField("Last name");
	private TextField date = new TextField("Date of Birth");
	private TextField userName = new TextField("UserName");
	private TextField password = new TextField("Password");
	private Button saveBtn = new Button("Save", e -> saveCustomer());
	private Button deleteBtn = new Button("Delete", e -> deleteCustomer());
	private Button clearBtn = new Button("Clear", e -> clearFields());
	private Button addCustomerBtn = new Button("Add new customer");

	// Insert/Update flag...
	// We don't need ID index to insert new customer (ID = null)
	protected boolean idFlag = false;

	@Override
	protected void init(VaadinRequest request) {
		
		getPage().setTitle("User Interface");
		
		// specify ID for RIDE script
        addCustomerBtn.setId("addBtn");                            
		saveBtn.setId("saveBtn");
		
		final VerticalLayout vertLayout = new VerticalLayout(grid, addCustomerBtn);

		// max length of input data fields according to DB fields length
		firstName.setMaxLength(100);
		lastName.setMaxLength(100);
		date.setMaxLength(10);
		userName.setMaxLength(50);
		password.setMaxLength(50);

		updateGrid();

		grid.setWidth("1100px");
		grid.setColumns("firstName", "lastName", "date", "username", "password");
		grid.addSelectionListener(e -> updateForm());

		addCustomerBtn.addClickListener(e -> {
			grid.select(null);
			setFormVisible(true);
			idFlag = true;
		});

		HorizontalLayout toolbar = new HorizontalLayout(firstName, lastName, date, userName, password);
		toolbar.setSpacing(true);

		HorizontalLayout buttons = new HorizontalLayout(saveBtn, deleteBtn, clearBtn);
		buttons.setSpacing(true);

		vertLayout.addComponents(toolbar, buttons);
		vertLayout.setMargin(true);
		vertLayout.setSpacing(true);
		setContent(vertLayout);

	}

	private void updateGrid() {
		List<Customer> customers = service.getAllList();
		grid.setContainerDataSource(new BeanItemContainer<>(Customer.class, customers));
		setFormVisible(false);
	}

	private void updateForm() {
		if (grid.getSelectedRows().isEmpty()) {
			setFormVisible(false);
		} else {
			customer = (Customer) grid.getSelectedRow();
			BeanFieldGroup.bindFieldsUnbuffered(customer, this);
			setFormVisible(true);
		}
	}

	private void setFormVisible(boolean visible) {
		firstName.setVisible(visible);
		lastName.setVisible(visible);
		date.setVisible(visible);
		userName.setVisible(visible);
		password.setVisible(visible);
		saveBtn.setVisible(visible);
		deleteBtn.setVisible(visible);
		clearBtn.setVisible(visible);
	}

	private void clearFields() {
		firstName.clear();
		lastName.clear();
		date.clear();
		userName.clear();
		password.clear();
	}

	private void saveCustomer() {
		if (idFlag) {                
			// we don't need ID index, cause AUTO_INCREMENT
			customer = new Customer(0, firstName.getValue(), lastName.getValue(), date.getValue(), userName.getValue(),
					password.getValue());

			service.insertDB(customer);
		} else {
			service.updateDB(customer);
		}
		idFlag = false;
		setFormVisible(false);
		clearFields();
		updateGrid();
	}

	private void deleteCustomer() {
		service.delete(customer);
		setFormVisible(false);
		clearFields();
		updateGrid();
	}

}