package tn.esprit.spring.controllers;

import tn.esprit.spring.services.IInterviewService;
import tn.esprit.spring.entity.Interview;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/interview")
public class InterviewController {

    private final IInterviewService iInterviewService;
    @PostMapping("/add")
    @ResponseBody
    public Interview addInterview(@RequestBody Interview interview){
        return iInterviewService.addInterview(interview);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Interview updateInterview(@RequestBody Interview interview, @PathVariable("id") Integer id){
        return iInterviewService.updateInterview(interview,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteInterview(@PathVariable("id") Integer id){
        iInterviewService.deleteInterview(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Interview getInterviewById(@PathVariable("id") Integer id){
        return iInterviewService.retrieveInterviewById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Interview> getAllInterview(){
        return iInterviewService.retrieveAllInterviews();
    }
}
