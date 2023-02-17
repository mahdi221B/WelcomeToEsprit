package tn.esprit.spring.controllers;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.React;
import tn.esprit.spring.services.IServiceReact;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/react")
public class ReactController {
    private final IServiceReact iserviceReact;
    @PostMapping("/add")
    @ResponseBody
    public React addReact(@RequestBody React react){
        return iserviceReact.addReact(react);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public React updateReact(@RequestBody React react, @PathVariable("id") Integer id){
        return iserviceReact.updateReact(react,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteReact(@PathVariable("id") Integer id){
        iserviceReact.deleteReact(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public React getReactById(@PathVariable("id") Integer id){
        return iserviceReact.retrieveReactById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<React> getAllReact(){
        return iserviceReact.retrieveAllReacts();
    }
}
