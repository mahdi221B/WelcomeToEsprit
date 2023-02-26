package tn.esprit.spring.services;

import tn.esprit.spring.entity.FAQ;

import java.util.List;

public interface IServiceFAQ {
    public void addFAQ(FAQ faq);
    public void updateFAQ(FAQ faq);
    public void deleteFAQ(int id);
    public List<FAQ> displayFAQ();
}
