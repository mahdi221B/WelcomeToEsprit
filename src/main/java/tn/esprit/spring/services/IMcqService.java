package tn.esprit.spring.services;

import tn.esprit.spring.entity.Mcq;

import java.io.IOException;
import java.util.Map;

public interface IMcqService {
    public Mcq generateMcq(String diplomaTitle) throws IOException ;



}
