package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Foyer;

import java.util.Optional;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long>
{

    Optional<Foyer> findByIdFoyer(long idfoyer);
    Foyer findByUniversite_NomUniversite(String nomUniversite);
    Foyer findByNomFoyer(String nomfoyer);

}
