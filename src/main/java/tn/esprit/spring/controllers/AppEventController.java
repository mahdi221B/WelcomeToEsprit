package tn.esprit.spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.AppEvent;
import tn.esprit.spring.services.AppEventService;
import tn.esprit.spring.services.IServiceUserManager;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AppEvent")
public class AppEventController {
    private final AppEventService appEventService;
    private final IServiceUserManager  iServiceUserManager;

    @PostMapping("/add")
    @ResponseBody
    public AppEvent addAppEvent(@RequestBody AppEvent appEvent){
        return appEventService.AddAppEvent(appEvent);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public AppEvent updateAppEvent(@RequestBody AppEvent appEvent, @PathVariable("id") Long id){
        return appEventService.UpdateAppEvent(appEvent,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteAppEvent(@PathVariable("id") Long id){
        appEventService.DeleteAppEvent(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public AppEvent getAppEventById(@PathVariable("id") Long id){
        return appEventService.RetrieveAppEventById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<AppEvent> getAllAppEvent(){
        return appEventService.RetrieveAllAppEvent();
    }
    @GetMapping("teamlist")
    @ResponseBody
    public   void  affectuserstoteam(){
           appEventService.affectuserstoteam();
    }

    @GetMapping("teachertojury/{id}")
    @ResponseBody
    public String teachertojury(@PathVariable("id") int id){
        return appEventService.assignteacheertojury(id);
    }

}
