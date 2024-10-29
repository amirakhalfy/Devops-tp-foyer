package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IFoyerService {

    public List<Foyer> retrieveAllFoyers();
    public Foyer retrieveFoyer(Long foyerId);
    public void removeFoyer(Long foyerId);
    public Foyer modifyFoyer(Foyer foyer);
    public Foyer addFoyer(Foyer f);
    // Here we will add later methods calling keywords and methods calling JPQL
    public Foyer getFoyerByNomUniversite(String nomUniversite);
    public Set<Bloc> getBlocsByFoyerByNom(String nomFoyer);
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite);
    public Map<Foyer, Double> getTauxOccupationFoyers();



    }
