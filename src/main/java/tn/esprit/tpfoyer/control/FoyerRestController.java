package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.service.IFoyerService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerRestController {

    IFoyerService foyerService;

    // http://localhost:8089/tpfoyer/foyer/retrieve-all-foyers
    @GetMapping("/retrieve-all-foyers")
    public List<Foyer> getFoyers() {
        return foyerService.retrieveAllFoyers();
    }

    // http://localhost:8089/tpfoyer/foyer/retrieve-foyer/{foyer-id}
    @GetMapping("/retrieve-foyer/{foyer-id}")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long fId) {
        return foyerService.retrieveFoyer(fId);
    }

    // http://localhost:8089/tpfoyer/foyer/add-foyer
    @PostMapping("/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer f) {
        return foyerService.addFoyer(f);
    }

    // http://localhost:8089/tpfoyer/foyer/remove-foyer/{foyer-id}
    @DeleteMapping("/remove-foyer/{foyer-id}")
    public void removeFoyer(@PathVariable("foyer-id") Long fId) {
        foyerService.removeFoyer(fId);
    }

    // http://localhost:8089/tpfoyer/foyer/modify-foyer
    @PutMapping("/modify-foyer")
    public Foyer modifyFoyer(@RequestBody Foyer f) {
        return foyerService.modifyFoyer(f);
    }

    // http://localhost:8089/tpfoyer/foyer/get-by-universite/{nomUniversite}
    @GetMapping("/get-by-universite/{nomUniversite}")
    public Foyer getFoyerByNomUniversite(@PathVariable String nomUniversite) {
        return foyerService.getFoyerByNomUniversite(nomUniversite);
    }

    // http://localhost:8089/tpfoyer/foyer/get-blocs-by-foyer/{nomFoyer}
    @GetMapping("/get-blocs-by-foyer/{nomFoyer}")
    public Set<Bloc> getBlocsByFoyerByNom(@PathVariable String nomFoyer) {
        return foyerService.getBlocsByFoyerByNom(nomFoyer);
    }

    // http://localhost:8089/tpfoyer/foyer/add-and-assign-universite/{idUniversite}
    @PostMapping("/add-and-assign-universite/{idUniversite}")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer, @PathVariable long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer, idUniversite);
    }

    // http://localhost:8089/tpfoyer/foyer/taux-occupation
    @GetMapping("/taux-occupation")
    public Map<Foyer, Double> getTauxOccupationFoyers() {
        return foyerService.getTauxOccupationFoyers();
    }
}
