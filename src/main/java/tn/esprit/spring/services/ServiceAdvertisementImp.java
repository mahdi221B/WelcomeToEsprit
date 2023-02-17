package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.Advertisement;
import tn.esprit.spring.repositories.AdvertisementRepository;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceAdvertisementImp implements IServiceAdvertisement{
    private final AdvertisementRepository advertisementRepository;

    @Override
    public List<Advertisement> retrieveAllAdvertisements() {
        return advertisementRepository.findAll();
    }

    @Override
    public void deleteAdvertisement(Integer id) {
        advertisementRepository.delete(advertisementRepository.findById(id).get());
    }

    @Override
    public Advertisement retrieveAdvertisementById(Integer id) {
        return advertisementRepository.findById(id).get();
    }

    @Override
    public Advertisement addAdvertisement(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    @Override
    public Advertisement updateAdvertisement(Advertisement advertisement, Integer id) {
        advertisement.setId(id);
        return advertisementRepository.save(advertisement);
    }
}
