package tn.esprit.spring.controllers;

import com.google.ads.googleads.v13.resources.Campaign;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.services.IServiceAdvertisement;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/advertisement")
public class AdvertisementController {
    private final IServiceAdvertisement iServiceAdvertisement;
    @PostMapping("/add")
    @ResponseBody
    public Advertisement addAdvertisement(@RequestBody Advertisement advertisement){
        return iServiceAdvertisement.addAdvertisement(advertisement);
    }
    @PutMapping("/update/{id}")
    @ResponseBody
    public Advertisement updateAdvertisement(@RequestBody Advertisement advertisement, @PathVariable("id") Integer id){
        return iServiceAdvertisement.updateAdvertisement(advertisement,id);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void deleteAdvertisement(@PathVariable("id") Integer id){
         iServiceAdvertisement.deleteAdvertisement(id);
    }
    @GetMapping("/get/{id}")
    @ResponseBody
    public Advertisement getAdvertisementById(@PathVariable("id") Integer id){
        return iServiceAdvertisement.retrieveAdvertisementById(id);
    }
    @GetMapping("/getall")
    @ResponseBody
    public List<Advertisement> getAllAdvertisement(){
        return iServiceAdvertisement.retrieveAllAdvertisements();
    }
    @GetMapping("/getCampaigns")
    @ResponseBody
    public Map<String,String> getCampaigns() {
        return iServiceAdvertisement.getCampaigns();
    }
}