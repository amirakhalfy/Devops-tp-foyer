package tn.esprit.tpfoyer.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Foyer;

import java.util.List;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class FoyerServiceImplMock {

    @Autowired
    IFoyerService us;
    @Test
    @Order(1)
    public void testRetrieveAllUsers() {
        List<Foyer> listUsers = us.retrieveAllFoyers();
        Assertions.assertEquals(0, listUsers.size());
    }

    @Test
    @Order(2)
    public void testAddFoyer() {
        Foyer foyer = new Foyer();
        foyer.setNomFoyer("Foyer Central");
        foyer.setCapaciteFoyer(100);

        Foyer savedFoyer = us.addFoyer(foyer);
        Assertions.assertNotNull(savedFoyer.getIdFoyer(), "Foyer ID should not be null after saving");
        Assertions.assertEquals("Foyer Central", savedFoyer.getNomFoyer(), "Foyer name should be 'Foyer Central'");
        Assertions.assertEquals(100, savedFoyer.getCapaciteFoyer(), "Foyer capacity should be 100");
    }

    @Test
    @Order(3)
    public void testRetrieveFoyer() {
        Long foyerId = 1L; // Replace with a known ID in your test data
        Foyer retrievedFoyer = us.retrieveFoyer(foyerId);
        Assertions.assertNotNull(retrievedFoyer, "Foyer should be retrieved successfully");
        Assertions.assertEquals(foyerId, retrievedFoyer.getIdFoyer(), "Foyer ID should match");
    }

    @Test
    @Order(4)
    public void testUpdateFoyer() {
        Long foyerId = 1L; // Replace with a known ID in your test data
        Foyer foyer = us.retrieveFoyer(foyerId);
        foyer.setNomFoyer("Updated Foyer Name");
        foyer.setCapaciteFoyer(150);

        Foyer updatedFoyer = us.modifyFoyer(foyer);
        Assertions.assertEquals("Updated Foyer Name", updatedFoyer.getNomFoyer(), "The name should be updated");
        Assertions.assertEquals(150, updatedFoyer.getCapaciteFoyer(), "The capacity should be updated to 150");
    }

    @Test
    @Order(5)
    public void testDeleteFoyer() {
        Long foyerId = 1L; // Replace with a known ID in your test data
        us.removeFoyer(foyerId);
        Foyer deletedFoyer = us.retrieveFoyer(foyerId);
        Assertions.assertNull(deletedFoyer, "Foyer should be null after deletion");
    }
}
