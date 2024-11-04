package tn.esprit.tpfoyer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    FoyerRepository foyerRepository;
    UniversiteRepository universiteRepository;
    BlocRepository blocRepository;

    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    public Foyer retrieveFoyer(Long foyerId) {
        return foyerRepository.findById(foyerId).get();
    }

    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    public Foyer modifyFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer testFoyer, long l) {
        Universite universite = universiteRepository.findById(l).orElse(null);

        foyerRepository.save(testFoyer);

        // Check if blocs is null before processing it
        if (testFoyer.getBlocs() != null) {
            for (Bloc bloc : testFoyer.getBlocs()) {
                bloc.setFoyer(testFoyer);
                blocRepository.save(bloc);
            }
        }

        if (universite != null) {
            universite.setFoyer(testFoyer);
            universiteRepository.save(universite);
        }

        return testFoyer;
    }

    public void removeFoyer(Long foyerId) {
        foyerRepository.deleteById(foyerId);
    }

    public Foyer getFoyerByNomUniversite(String universitéA) {
        Foyer foyer = foyerRepository.findByUniversite_NomUniversite(universitéA);

        if (foyer == null) {
            throw new RuntimeException("Foyer non trouvé pour l'université nommée : " + universitéA);
        }
        return foyer;
    }

    public Set<Bloc> getBlocsByFoyerByNom(String nomFoyer) {
        Foyer foyer = (Foyer) foyerRepository.findByNomFoyer(nomFoyer);
        if (foyer == null) {
            return Collections.emptySet();
        }
        return foyer.getBlocs();
    }
}