package com.rnzomb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CustomerService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// getting data via query and forming corresponding "Customer" instances
	public List<Customer> getAllList() {
		return jdbcTemplate.query("SELECT id, firstname, lastname, dateofbirth, username, password FROM customer",
				(rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("dateofbirth"), rs.getString("username"), rs.getString("password")));
	}
	
    // inserting new customer
	public void insertDB(Customer customer) {
		jdbcTemplate.update("INSERT INTO customer VALUES (null, '" + customer.getFirstName() + "', '"
				+ customer.getLastName() + "', '" + customer.getDate() + "', '" + customer.getUsername() + "', '"
				+ customer.getPassword() + "');");
	}
	
    // updating
	public void updateDB(Customer customer) {
		jdbcTemplate.update(
				"UPDATE customer SET firstname=?, lastname=?, dateofbirth=?, username=?, password=? WHERE id=?",
				customer.getFirstName(), customer.getLastName(), customer.getDate(), customer.getUsername(),
				customer.getPassword(), customer.getId());
	}

	public void delete(Customer customer) {
		jdbcTemplate.update("DELETE FROM customer WHERE id=?", customer.getId());
	}

}