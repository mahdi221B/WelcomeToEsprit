package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Mcreponse;
import tn.esprit.spring.services.IReponseService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/mcqqestion")
public class McqqestionController {
    private final IQuestionService iReponseService;
    @PostMapping("/add")
    @ResponseBody
    public Mcreponse addReponse(@RequestBody Mcreponse mcreponse){
        return iReponseService.addReponse(mcreponse);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Mcreponse updateReponse(@RequestBody Mcreponse mcreponse, @PathVariable("id") Integer id){
        return iReponseService.updateReponse(mcreponse,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteReponse(@PathVariable("id") Integer id){
        iReponseService.deleteReponse(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Mcreponse getReponseById(@PathVariable("id") Integer id){
        return iReponseService.retrieveReponseById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Mcreponse> getAllReponse(){
        return iReponseService.retrieveAllReponses();
    }
}
