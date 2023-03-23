package tn.esprit.spring.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.AppEvent;
import tn.esprit.spring.services.AppEventService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/AppEvent")
@Api
public class AppEventController {
    private final AppEventService appEventService;
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
}
