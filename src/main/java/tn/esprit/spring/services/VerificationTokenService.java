package tn.esprit.spring.services;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.VerificationToken;

public interface VerificationTokenService {

    public VerificationToken findByToken(String token);

    public VerificationToken findByUser(User user);
    public void save(User user,String token);
}
