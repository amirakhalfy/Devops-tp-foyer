package tn.esprit.tpfoyer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class EtudiantServiceImplMockTest {

    @Mock
    EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    Etudiant etudiant = new Etudiant(1, "John", "Doe", 12345678, new Date(), null);
    List<Etudiant> listEtudiants = new ArrayList<Etudiant>() {
        {
            add(new Etudiant(1, "TARAK", "TRTR", 12343434, new Date(), null));
            add(new Etudiant(2, "ZIED", "ZDZD", 12345454, new Date(), null));
        }
    };

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialiser les mocks
    }

    @Test
    public void testRetrieveEtudiant() {
        // Configurer le mock pour renvoyer l'étudiant avec l'ID 1
        when(etudiantRepository.findByIdEtudiant(1L)).thenReturn(Optional.of(etudiant));

        // Appeler la méthode à tester
        Etudiant etudiant1 = etudiantService.retrieveEtudiant(1L);

        // Vérifier que l'étudiant récupéré n'est pas nul
        assertNotNull(etudiant1);

        // Vérifier que les propriétés de l'étudiant sont correctes
        assertEquals("John", etudiant1.getPrenomEtudiant());
        assertEquals("Doe", etudiant1.getNomEtudiant());
        assertEquals(12345678, etudiant1.getCinEtudiant());
    }
}
