package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Foyer;

import java.util.List;

public interface IFoyerService {

    public List<Foyer> retrieveAllFoyers();
    public Foyer retrieveEtudiant(Long foyerId);
    public void removeFoyer(Long foyerId);
    public Foyer modifyFoyer(Foyer foyer);

    // Here we will add later methods calling keywords and methods calling JPQL

}
