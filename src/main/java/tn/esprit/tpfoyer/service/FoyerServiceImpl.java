package tn.esprit.tpfoyer.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.*;

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
            Optional<Foyer> foyerOptional = foyerRepository.findByIdFoyer(foyerId);
            if (foyerOptional.isPresent()) {
                return foyerOptional.get();
            } else {
                throw new NoSuchElementException("foyer not found");
            }
        }


    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }
    public Foyer modifyFoyer(Foyer foyer) {
        return foyerRepository.save(foyer);
    }

    public void removeFoyer(Long foyerId) {
        foyerRepository.deleteById(foyerId);
    }

    /**devops*/
    public Foyer getFoyerByNomUniversite(String nomUniversite) {
        Foyer foyer = foyerRepository.findByUniversite_NomUniversite(nomUniversite);

        if (foyer == null) {
            throw new RuntimeException("Foyer non trouvé pour l'université nommée : " + nomUniversite);
        }

        return foyer;
    }
    public Set<Bloc> getBlocsByFoyerByNom(String nomFoyer) {
        Foyer foyer = foyerRepository.findByNomFoyer(nomFoyer);
        if (foyer == null) {
            return Collections.emptySet();
        }
        return foyer.getBlocs();
    }
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);

        foyerRepository.save(foyer);

        // Check if blocs is null before processing it
        if (foyer.getBlocs() != null) {
            for (Bloc bloc : foyer.getBlocs()) {
                bloc.setFoyer(foyer);
                blocRepository.save(bloc);
            }
        }

        if (universite != null) {
            universite.setFoyer(foyer);
            universiteRepository.save(universite);
        }

        return foyer;
    }

    public Map<Foyer, Double> getTauxOccupationFoyers() {
        List<Foyer> foyers = retrieveAllFoyers();
        Map<Foyer, Double> tauxOccupationMap = new HashMap<>();

        for (Foyer foyer : foyers) {
            long capaciteTotale = foyer.getBlocs().stream()
                    .flatMap(bloc -> bloc.getChambres().stream())
                    .mapToLong(chambre -> {
                        switch (chambre.getTypeC()) {
                            case SIMPLE:
                                return 1;
                            case DOUBLE:
                                return 2;
                            case TRIPLE:
                                return 3;
                            default:
                                return 0;
                        }
                    }).sum();

            long chambresReservees = foyer.getBlocs().stream()
                    .flatMap(bloc -> bloc.getChambres().stream())
                    .mapToLong(chambre -> {
                        if (!chambre.getReservations().isEmpty()) {
                            switch (chambre.getTypeC()) {
                                case SIMPLE:
                                    return 1;
                                case DOUBLE:
                                    return 2;
                                case TRIPLE:
                                    return 3;
                                default:
                                    return 0;
                            }
                        }
                        return 0;
                    }).sum();

            double tauxOccupation = capaciteTotale == 0 ? 100 : (double) chambresReservees / capaciteTotale * 100;

            // Si aucune chambre n'est réservée, on considère un taux d'occupation de 100%
            if (chambresReservees == 0) {
                tauxOccupation = 0;
            }

            tauxOccupationMap.put(foyer, tauxOccupation);
        }

        return tauxOccupationMap;
    }
}

