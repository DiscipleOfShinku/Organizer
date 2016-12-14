package organizerpack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		
		@RequestMapping(value = "/addNote", method = RequestMethod.POST)
		public String addNote(@ModelAttribute("Organizer")NoteClass noteTyped, 
		ModelMap model) {
		   ApplicationContext context =
				   new ClassPathXmlApplicationContext("Beans.xml");
		   NoteClass noteInstance = (NoteClass)context.getBean("noteClass");
		   noteInstance.setNote(noteTyped.getNote());
		   noteInstance.createEntry();
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
			lastNote.getLastNote();
			request.setAttribute("lastNote", lastNote);
			return;
		}
}
