package organizerpack;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Controller
public class OrganizerController {
	
		@RequestMapping(value = "/login", method = RequestMethod.GET)
		public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
	
			ModelAndView model = new ModelAndView();
			if (error != null) {
				model.addObject("error", "Invalid username and password!");
			}
	
			if (logout != null) {
				model.addObject("msg", "You've been logged out successfully.");
			}
			model.setViewName("login");
	
			return model;
		}
	
	    @RequestMapping(value = "/", method = RequestMethod.GET)
	    public String redirect() {
	      return "redirect:main";
	    }
	   
	    @RequestMapping(value = "/main", method = RequestMethod.GET)
	    public ModelAndView main() {
		  return new ModelAndView("main", "command", new NoteClass());
	    }
		
	    @Transactional
		@RequestMapping(value = "/addNote", method = RequestMethod.POST)
		public String addNote(@ModelAttribute("Organizer")NoteClass noteTyped, 
		ModelMap model) {
		    ApplicationContext context =
				   new ClassPathXmlApplicationContext("Beans.xml");
		    NoteClass noteInstance = (NoteClass)context.getBean("noteClass");
		    noteInstance.setNote(noteTyped.getNote());
	    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    	def.setName("addNoteTransaction");
	    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    	PlatformTransactionManager txManager = context.getBean("transactionManager", DataSourceTransactionManager.class);
	    	TransactionStatus status = txManager.getTransaction(def);
	    	try {
	    		noteInstance.createEntry();
	    	}
	    	catch (DataAccessException e) {
	            txManager.rollback(status);
	            throw e;
	         }
	    	txManager.commit(status);
	    	return "redirect:noteCreated";
		}
		
		@RequestMapping(value = "/noteCreated", method = RequestMethod.GET)
		public void noteCreated() {
		   return;
		}

		@RequestMapping(value = "/showLastNote", method = RequestMethod.GET)
		public void showLastNote(HttpServletRequest request) {
			   ApplicationContext context =
					   new ClassPathXmlApplicationContext("Beans.xml");
			NoteClass lastNote = context.getBean("noteClass", NoteClass.class);
	    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
	    	def.setName("addNoteTransaction");
	    	def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
	    	PlatformTransactionManager txManager = context.getBean("transactionManager", DataSourceTransactionManager.class);
	    	TransactionStatus status = txManager.getTransaction(def);
	    	try {
	    		lastNote.getLastNote();
	    	}
	    	catch (DataAccessException e) {
	            txManager.rollback(status);
	            throw e;
	         }
	    	txManager.commit(status);
			request.setAttribute("lastNote", lastNote);
			return;
		}
}
