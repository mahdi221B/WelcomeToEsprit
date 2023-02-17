package tn.esprit.spring.services;

import tn.esprit.spring.entity.React;

import java.util.List;

public interface IServiceReact {
    public List<React> retrieveAllReacts();
    public void deleteReact(Integer id);
    public React retrieveReactById(Integer id);
    public React addReact(React react);
    public React updateReact(React react,Integer id);
}
