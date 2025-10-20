
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository; 

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Customer Repository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// RowMapper per mapejar resultats de la base de dades a objectes Customer
	private static final class CustomerRowMapper implements RowMapper<Customer> { 
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException { //mapejar cada linia de dada en el Resultset.
			Customer customer = new Customer(); 
			customer.setId(rs.getLong("id"));
			customer.setFirstName (rs.getString("f_name"));
			customer.setLastName(rs.getString("l_name"));
			return customer;
		}
	}
	//Crear taula Customers
	public void createTableCustomers(){
		jdbcTemplate.execute("DROP TABLE customers if EXISTS");
		jdbcTemplate.execute("CREATE TABLE customers (id SERIAL, f_name VARCHAR(255), L_name VARCHAR(255))");
	}
	
	//inserir dades cada vegada que executem projecte (es millorarà a mysql)
	public void insertSampleData() {
		jdbcTemplate.update("INSERT INTO customers (f_name, l_name) VALUES (?, ?)", "John", "Doe"); 
		jdbcTemplate.update("INSERT INTO customers (f_name, L_name) VALUES (?, ?)", "Jane",
		"Smith");
		jdbcTemplate.update("INSERT INTO customers (f_name, L_name) VALUES (?, ?)", "Bob",
		"Johnson");
	}
	
	//Buscar dades tots els registres de customers
	public List<Customer> findAll() {
		return jdbcTemplate.query("SELECT id, f_name, L_name FROM customers", new CustomerRowMapper());
	}
}