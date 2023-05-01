package tn.esprit.spring.services;

import com.google.ads.googleads.v13.resources.Campaign;
import tn.esprit.spring.entity.Advertisement;

import java.util.List;
import java.util.Map;

public interface IServiceAdvertisement {
    public List<Advertisement> retrieveAllAdvertisements();

    public void deleteAdvertisement(Integer id);

    public Advertisement retrieveAdvertisementById(Integer id);

    public Advertisement addAdvertisement(Advertisement advertisement);

    public Advertisement updateAdvertisement(Advertisement advertisement, Integer id);

    public Map<String,String> getCampaigns() ;
}