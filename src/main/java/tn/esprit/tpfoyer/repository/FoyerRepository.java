package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.tpfoyer.entity.Foyer;

import java.util.Optional;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long>
{

    /*devops*/
    Foyer findByUniversite_NomUniversite(String nomUniversite);
    Foyer findByNomFoyer(String nomfoyer);
    /* No need to code CRUD here. Its is already in the
    interfaces provided by the framework Spring Data JPA :
       - CrudRepository or
       - PagingAndSoringRepository or
       - JpaRepository
     */

    /* Keywords : */

}



