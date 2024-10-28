package tn.esprit.tpfoyer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EtudiantServiceImplMockTest {

    @Mock
    private EtudiantRepository etudiantRepository;

    @InjectMocks
    private EtudiantServiceImpl etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    public void setUp() {
        etudiant = new Etudiant();
        etudiant.setIdEtudiant(1L);
        etudiant.setNomEtudiant("Doe"); // Notez que nous utilisons "nomEtudiant"
        etudiant.setPrenomEtudiant("John"); // Notez que nous utilisons "prenomEtudiant"
        etudiant.setCinEtudiant(123456);
        etudiant.setDateNaissance(new Date());
    }

    @Test
    public void testRetrieveEtudiant() {
        // Configurez le mock pour renvoyer l'étudiant avec l'ID 1
        when(etudiantRepository.findByIdEtudiant(1L)).thenReturn(Optional.of(etudiant));

        // Appeler la méthode à tester avec l'ID 1
        Etudiant etudiant1 = etudiantService.retrieveEtudiant(1L);

        // Vérifier que l'étudiant récupéré n'est pas nul
        assertNotNull(etudiant1);

        // Vérifier les propriétés de l'étudiant
        assertEquals("Doe", etudiant1.getNomEtudiant()); // Utiliser nomEtudiant
        assertEquals("John", etudiant1.getPrenomEtudiant()); // Utiliser prenomEtudiant
        assertEquals(123456, etudiant1.getCinEtudiant());
    }
}