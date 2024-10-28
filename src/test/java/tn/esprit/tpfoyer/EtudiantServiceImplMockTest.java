package tn.esprit.tpfoyer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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


    Etudiant etudiant = new Etudiant(0, "John", "Doe", 12345678, new Date(), null);
    List<Etudiant> listEtudiants = new ArrayList<Etudiant>() {
        {
            add(new Etudiant(1, "TARAK", "TRTR", 12343434, new Date(), null));
            add(new Etudiant(2, "ZIED", "ZDZD", 12345454, new Date(), null));
        }
    };

    @Test
    public void testRetrieveEtudiant() {
        Mockito.when(etudiantRepository.findByIdEtudiant(Mockito.anyLong())).thenReturn(Optional.of(etudiant));
        Etudiant etudiant1 = etudiantService.retrieveEtudiant(2L);
        Assertions.assertNotNull(etudiant1);


    }


}
