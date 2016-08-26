package organizerpack;

import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class NoteClass {
	private String note;
	
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
	   this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getNote() {
		return note;
	}
	
	public void createEntry(){

	    try {
	       String SQL = "insert into OrganizerNotes (UserId, note) values (1, ?)";
	       jdbcTemplateObject.update( SQL, note);

	    } catch (DataAccessException e) {
	       throw e;
	    }
	 }
}
