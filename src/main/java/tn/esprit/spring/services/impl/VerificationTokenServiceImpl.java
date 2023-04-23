package tn.esprit.spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.VerificationToken;
import tn.esprit.spring.repositories.VerificationTokenRepository;
import tn.esprit.spring.services.VerificationTokenService;

import java.sql.Timestamp;
import java.util.Calendar;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private VerificationTokenRepository verificationTokenRepository;
    @Autowired
    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }
    @Transactional
    @Override
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Transactional
    @Override
    public VerificationToken findByUser(User user) {
        return verificationTokenRepository.findByUser(user);
    }

    @Transactional
    @Override
    public void save(User user, String token) {
        VerificationToken verificationToken=new VerificationToken(token,user);
        verificationToken.setExpiryDate(calculateExpiryDate(24*60));
        verificationTokenRepository.save(verificationToken);
    }

    private Timestamp calculateExpiryDate(int expiryTimeInMinutes){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MINUTE,expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }
}
