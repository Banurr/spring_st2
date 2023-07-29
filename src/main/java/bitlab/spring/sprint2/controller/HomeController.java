package bitlab.spring.sprint2.controller;

import bitlab.spring.sprint2.models.ApplicationRequest;
import bitlab.spring.sprint2.services.ApplicationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ApplicationRequestService applicationRequestService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("req",applicationRequestService.allRequests());
        return "home";
    }

    @GetMapping("/add-request")
    public String addRequest(){
        return "addreq";
    }

    @PostMapping("/add-request")
    public String adding(@RequestParam(name = "fio") String name,
                         @RequestParam(name = "course") String course,
                         @RequestParam(name = "phone") String phone,
                         @RequestParam(name = "comment") String comment){
        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setUserName(name);
        applicationRequest.setCourseName(course);
        applicationRequest.setPhone(phone);
        applicationRequest.setCommentary(comment);
        applicationRequest.setHandled(false);
        applicationRequestService.addRequest(applicationRequest);
        return "redirect:/";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable(name = "id") Long id,Model model){
        model.addAttribute("req",applicationRequestService.getAppRequest(id));
        return "details";
    }

    @GetMapping("/handle/{id}/{type}")
    public String handle(@PathVariable(name = "id") Long id,
                         @PathVariable(name = "type") int type){
        if(type==0)
        {
            applicationRequestService.processRequest(id);
        }
        else
        {
            applicationRequestService.deleteRequest(id);
        }
        return "redirect:/";
    }
    @GetMapping("/filter/{type}")
    public String filter(@PathVariable(name = "type") int type,Model model){
        model.addAttribute("req",applicationRequestService.filteredRequests(type==1));
        return "home";
    }
}
