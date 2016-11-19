package organizerpack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class NoteClass {
	private Integer id;
	private String note;
	private Timestamp noteDate;
	
	private JdbcTemplate jdbcTemplateObject;
	
	public NoteClass(){
		System.out.println("creating NoteClass");
	}

	public void setDataSource(DataSource dataSource) {
	   this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNoteDate(Timestamp noteDate) {
		this.noteDate = noteDate;
	}
	
	public Timestamp getNoteDate() {
		return noteDate;
	}
	
	public void createEntry(){
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
	    try {
	    	jdbcTemplateObject.execute("create schema SQLWooh create table SQLWooh.OrganizerNotes (ID int IDENTITY(1,1) PRIMARY KEY, UserID int NOT NULL, Note varchar(1000), NoteDate smalldatetime)");
		       String SQL = "insert into SQLWooh.OrganizerNotes (UserID, Note, NoteDate) values (1, ?, ?)";
		       jdbcTemplateObject.update( SQL, note, timeStamp);    	
	    }
	    catch (UncategorizedSQLException ue) {
	    	try {
		       String SQL = "insert into SQLWooh.OrganizerNotes (UserID, Note, NoteDate) values (1, ?, ?)";
		       jdbcTemplateObject.update( SQL, note, timeStamp);
	    	}
			catch (DataAccessException e) {
				   throw e;
			}
		}
		catch (DataAccessException e) {
			   throw e;
		}

	 }
	
	public NoteClass getLastNote() {
	    try {
	    	SqlRowSet res = jdbcTemplateObject.queryForRowSet("select top 1 ID, Note, NoteDate from SQLWooh.OrganizerNotes order by NoteDate desc");
	    	res.first();
	    	id = res.getInt("ID");
	    	note = res.getString("Note");
	    	noteDate = res.getTimestamp("NoteDate");
	    	return this;
	    }
		catch (DataAccessException e) {
			   throw e;
		}
	}
}
