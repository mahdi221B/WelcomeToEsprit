package tn.esprit.spring.services;

import tn.esprit.spring.entity.Advertisement;

import java.util.List;

public interface IServiceAdvertisement {
    public List<Advertisement> retrieveAllAdvertisements();
    public void deleteAdvertisement(Integer id);
    public Advertisement retrieveAdvertisementById(Integer id);
    public Advertisement addAdvertisement(Advertisement advertisement);
    public Advertisement updateAdvertisement(Advertisement advertisement,Integer id);

}
