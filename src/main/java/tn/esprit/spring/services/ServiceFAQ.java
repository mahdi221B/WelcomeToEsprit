package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.FAQ;

import tn.esprit.spring.repositories.FAQRepository;

import java.util.List;
@Service
public class ServiceFAQ implements IServiceFAQ{

    @Autowired
    private FAQRepository faqRepository;
    @Override
    public void addFAQ(FAQ faq)  {faqRepository.save(faq);}

    @Override
    public void updateFAQ(FAQ faq) {faqRepository.save(faq);

    }

    @Override
    public void deleteFAQ(int id) {faqRepository.deleteById(id);

    }

    @Override
    public List<FAQ> displayFAQ() {return faqRepository.findAll();

    }
}
