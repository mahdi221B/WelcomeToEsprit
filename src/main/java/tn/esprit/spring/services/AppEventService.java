package tn.esprit.spring.services;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entity.AppEvent;

import java.util.List;

public interface AppEventService  {

    public List<AppEvent> RetrieveAllAppEvent();
    public void DeleteAppEvent(Long id);
    public AppEvent RetrieveAppEventById(Long id);
    public AppEvent AddAppEvent(AppEvent appEvent);
    public AppEvent UpdateAppEvent(AppEvent appEvent,Long id);
}
