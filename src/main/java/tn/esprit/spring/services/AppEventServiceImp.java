package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.AppEvent;
import tn.esprit.spring.repositories.AppEventRepository;

import java.util.List;

@Service
public class AppEventServiceImp implements  AppEventService {


    @Autowired
    AppEventRepository appEventRepository;
    @Override
    public List<AppEvent> RetrieveAllAppEvent() {
        return appEventRepository.findAll()  ;
    }

    @Override
    public void DeleteAppEvent(Long id) {
             appEventRepository.delete(appEventRepository.findById(id).get());

    }

    @Override
    public AppEvent RetrieveAppEventById(Long id) {
        return  appEventRepository.findById(id).get();
    }

    @Override
    public AppEvent AddAppEvent(AppEvent appEvent) {
        return appEventRepository.save(appEvent);

    }

    @Override
    public AppEvent UpdateAppEvent(AppEvent appEvent, Long id) {
        appEvent.setId(id);
        return appEventRepository.save(appEvent);
    }
}
