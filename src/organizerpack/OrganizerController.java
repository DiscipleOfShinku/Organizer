package organizerpack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Controller
public class OrganizerController {
	
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
		   NoteClass noteInstance = (NoteClass)context.getBean("NoteClass");
		   noteInstance.setNote(noteTyped.getNote());
		   noteInstance.createEntry();
		   return "redirect:noteCreated";
		}
		
		@RequestMapping(value = "/noteCreated", method = RequestMethod.GET)
		public void noteCreated() {
		   return;
		}
}
