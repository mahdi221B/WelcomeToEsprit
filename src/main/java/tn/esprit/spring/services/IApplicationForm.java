package tn.esprit.spring.services;



import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.ApplicationForm;

import java.util.List;

public interface IApplicationForm {


  // ApplicationForm createApplicationForm(ApplicationForm applicationForm, Long jobOfferId);
   ApplicationForm createApplicationForm(ApplicationForm applicationForm);
    ApplicationForm getApplicationFormById(Long id);
    List<ApplicationForm> getAllApplicationForms();
    void deleteApplicationFormById(Long id);
    ApplicationForm updateApplicationForm(ApplicationForm applicationForm, Long id);




    ////////////////////////////
 //   List<ApplicationForm> getClassifiedApplicationForms() throws Exception;



}
