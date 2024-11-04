package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;

import java.util.List;
import java.util.Set;

public interface IFoyerService {

    public List<Foyer> retrieveAllFoyers();
    public Foyer retrieveFoyer(Long foyerId);
    public Foyer addFoyer(Foyer f);
    public void removeFoyer(Long foyerId);
    public Foyer modifyFoyer(Foyer foyer);

    Foyer ajouterFoyerEtAffecterAUniversite(Foyer testFoyer, long l);
    public Set<Bloc> getBlocsByFoyerByNom(String nomFoyer);
    // Here we will add later methods calling keywords and methods calling JPQL

}
