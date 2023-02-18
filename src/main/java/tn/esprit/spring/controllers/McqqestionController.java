package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Mcqqestion;
import tn.esprit.spring.entity.Mcreponse;
import tn.esprit.spring.services.IQuestionService;
import tn.esprit.spring.services.IReponseService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/mcqqestion")
public class McqqestionController {
    private final IQuestionService iQuestionService;
    @PostMapping("/add")
    @ResponseBody
    public Mcqqestion addQuestion(@RequestBody Mcqqestion mcqqestion){
        return iQuestionService.addQuestion(mcqqestion);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Mcqqestion updateQuestion(@RequestBody Mcqqestion mcqqestion, @PathVariable("id") Integer id){
        return iQuestionService.updateQuestion(mcqqestion,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteQuestion(@PathVariable("id") Integer id){
        iQuestionService.deleteQuestion(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Mcqqestion getQuestionById(@PathVariable("id") Integer id){
        return iQuestionService.retrieveQuestionById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Mcqqestion> getAllQuestion(){
        return iQuestionService.retrieveAllQuestions();
    }
}
