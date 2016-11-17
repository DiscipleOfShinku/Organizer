package organizerpack;

import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
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
	    	jdbcTemplateObject.execute("create schema SQLWooh create table SQLWooh.OrganizerNotes (Id int IDENTITY(1,1) PRIMARY KEY, UserId int NOT NULL, Note varchar(1000), NoteDate smalldatetime)");
		       String SQL = "insert into SQLWooh.OrganizerNotes (UserId, note) values (1, ?)";
		       jdbcTemplateObject.update( SQL, note);    	
	    }
	    catch (UncategorizedSQLException ue) {
	    	try {
		       String SQL = "insert into SQLWooh.OrganizerNotes (UserId, note) values (1, ?)";
		       jdbcTemplateObject.update( SQL, note);
	    	}
			catch (DataAccessException e) {
				   throw e;
			}
		}
		catch (DataAccessException e) {
			   throw e;
		}

	 }
}
