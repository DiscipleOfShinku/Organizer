package organizerpack;

import java.sql.Timestamp;
import javax.sql.DataSource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class NoteClass {
	private Integer id;
	private String note;
	private Timestamp noteDate;
	
	private JdbcTemplate jdbcTemplateObject;
	
	public NoteClass(){
		System.out.println("creating NoteClass object.");
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
	    	jdbcTemplateObject.update("use Organizer");
	    	String SQL = "EXEC Entries.AddNote @userid= 1, @note = ?, @noteDate = ?";
		    jdbcTemplateObject.update( SQL, note, timeStamp);    	
	    }
		catch (DataAccessException e) {
			   throw e;
		}

	 }
	
	public NoteClass getLastNote() {
	    try {
	    	jdbcTemplateObject.update("use Organizer");
	    	SqlRowSet res = jdbcTemplateObject.queryForRowSet("EXEC Entries.GetLatNote @userid = 1");
	    	res.first();
	    	id = res.getInt("id");
	    	note = res.getString("note");
	    	noteDate = res.getTimestamp("noteDate");
	    	return this;
	    }
		catch (DataAccessException e) {
			   throw e;
		}
	}
}
