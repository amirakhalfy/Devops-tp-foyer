package tn.esprit.tpfoyer.service;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.*;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class TestFoyerServiceMockito {
    @Mock
    private FoyerRepository foyerRepository;
    @Mock
    private BlocRepository blocRepository;
    @Mock
    private UniversiteRepository universityRepository;

    @InjectMocks
    private FoyerServiceImpl foyerService;

    private Foyer testFoyer;
    private Bloc testBloc;
    private Chambre testChambre;
    @BeforeEach
    void setUp() {
        testFoyer = new Foyer();
        testFoyer.setIdFoyer(1L);
        testFoyer.setNomFoyer("Foyer Amira");
        testFoyer.setCapaciteFoyer(1000);

        testBloc = new Bloc();
        testBloc.setNomBloc("Bloc B1");
        Set<Bloc> blocs = new HashSet<>();
        blocs.add(testBloc);
        testFoyer.setBlocs(blocs);

        testChambre = new Chambre();
        testChambre.setIdChambre(1L);
        Set<Chambre> chambres = new HashSet<>();
        chambres.add(testChambre);
        testBloc.setChambres(chambres);
    }
    @AfterEach
    void tearDown() {
        reset(foyerRepository);
    }
    @Test
    void testRetrieveFoyer() {
        when(foyerRepository.findByIdFoyer(1L)).thenReturn(Optional.of(testFoyer));
        Foyer foyer1 = foyerService.retrieveFoyer(1L);
        assertNotNull(foyer1);
        assertEquals("Foyer Amira", foyer1.getNomFoyer());
        assertEquals(1000, foyer1.getCapaciteFoyer());
    }
    // Ce test garantit que la méthode retrieveFoyer gère correctement les situations où le foyer n'est pas trouvé.
    @Test
    void testRetrieveFoyerNotFound() {
        when(foyerRepository.findByIdFoyer(2L)).thenReturn(Optional.empty());
        Foyer foyer = foyerService.retrieveFoyer(2L);
        assertNull(foyer);
        verify(foyerRepository).findByIdFoyer(2L);
    }


}
