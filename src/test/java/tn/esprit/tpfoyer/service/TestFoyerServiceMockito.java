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

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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
    @Order(1)
    void testRetrieveFoyer() {
        when(foyerRepository.findByIdFoyer(1L)).thenReturn(Optional.of(testFoyer));
        Foyer foyer1 = foyerService.retrieveFoyer(1L);
        assertNotNull(foyer1);
        assertEquals("Foyer Amira", foyer1.getNomFoyer());
        assertEquals(1000, foyer1.getCapaciteFoyer());
    }
    @Test
    @Order(2)
    void testGetFoyerByNomUniversite_Found() {
        when(foyerRepository.findByUniversite_NomUniversite("Université A")).thenReturn(testFoyer);

        Foyer result = foyerService.getFoyerByNomUniversite("Université A");

        assertNotNull(result);
        assertEquals(testFoyer.getNomFoyer(), result.getNomFoyer());
        verify(foyerRepository, times(1)).findByUniversite_NomUniversite("Université A");
    }

    @Test
    @Order(3)
    void testGetFoyerByNomUniversite_NotFound() {
        when(foyerRepository.findByUniversite_NomUniversite("Université B")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            foyerService.getFoyerByNomUniversite("Université B");
        });

        assertEquals("Foyer non trouvé pour l'université nommée : Université B", exception.getMessage());
        verify(foyerRepository, times(1)).findByUniversite_NomUniversite("Université B");
    }



    @Test
    @Order(4)
    void testGetBlocsByFoyerByNom_Found() {
        when(foyerRepository.findByNomFoyer("Foyer A")).thenReturn(testFoyer);

        Set<Bloc> result = foyerService.getBlocsByFoyerByNom("Foyer A");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testBloc.getNomBloc(), result.iterator().next().getNomBloc());
        verify(foyerRepository, times(1)).findByNomFoyer("Foyer A");
    }

    @Test
    @Order(5)
    void testGetBlocsByFoyerByNom_NotFound() {
        when(foyerRepository.findByNomFoyer("Foyer B")).thenReturn(null);

        Set<Bloc> result = foyerService.getBlocsByFoyerByNom("Foyer B");

        assertTrue(result.isEmpty(), "La liste des blocs devrait être vide");
        verify(foyerRepository, times(1)).findByNomFoyer("Foyer B");
    }


    @Test
    @Order(6)
    void testAjouterFoyerEtAffecterAUniversite_Found() {
        Universite testUniversite = new Universite();
        testUniversite.setIdUniversite(1L);
        when(universityRepository.findById(1L)).thenReturn(Optional.of(testUniversite));
        when(foyerRepository.save(any(Foyer.class))).thenReturn(testFoyer);
        when(blocRepository.save(any(Bloc.class))).thenReturn(testBloc);
        when(universityRepository.save(any(Universite.class))).thenReturn(testUniversite);

        Foyer result = foyerService.ajouterFoyerEtAffecterAUniversite(testFoyer, 1L);

        assertNotNull(result);
        assertEquals(testFoyer, result);

        verify(foyerRepository, times(1)).save(testFoyer);
        verify(blocRepository, times(1)).save(testBloc);
        verify(universityRepository, times(1)).save(testUniversite);
    }

    @Test
    @Order(7)
    void testAjouterFoyerEtAffecterAUniversite_NotFound() {
        when(universityRepository.findById(1L)).thenReturn(Optional.empty());

        Foyer result = foyerService.ajouterFoyerEtAffecterAUniversite(testFoyer, 1L);

        assertNotNull(result);
        assertNull(result.getUniversite(), "L'université devrait être nulle si non trouvée.");
        verify(foyerRepository, times(1)).save(testFoyer);
        verify(blocRepository, times(1)).save(testBloc);
        verify(universityRepository, times(0)).save(any(Universite.class));
    }

    @Test
    @Order(8)
    void testGetTauxOccupationFoyers_Found() {
        Chambre chambreSimple = new Chambre();
        chambreSimple.setTypeC(TypeChambre.SIMPLE);
        chambreSimple.setReservations(Set.of(new Reservation()));

        Chambre chambreDouble = new Chambre();
        chambreDouble.setTypeC(TypeChambre.DOUBLE);
        chambreDouble.setReservations(Set.of(new Reservation()));

        testBloc.setChambres(Set.of(chambreSimple, chambreDouble));
        when(foyerRepository.findAll()).thenReturn(List.of(testFoyer));

        Map<Foyer, Double> result = foyerService.getTauxOccupationFoyers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(testFoyer));
        assertEquals(100.0, result.get(testFoyer), 0.1);
        verify(foyerRepository, times(1)).findAll();
    }

    @Test
    @Order(9)
    void testGetTauxOccupationFoyers_NotFound() {
        Chambre chambreSimple = new Chambre();
        chambreSimple.setTypeC(TypeChambre.SIMPLE);

        Chambre chambreDouble = new Chambre();
        chambreDouble.setTypeC(TypeChambre.DOUBLE);

        testBloc.setChambres(Set.of(chambreSimple, chambreDouble));
        when(foyerRepository.findAll()).thenReturn(List.of(testFoyer));

        Map<Foyer, Double> result = foyerService.getTauxOccupationFoyers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.containsKey(testFoyer));
        assertEquals(0.0, result.get(testFoyer), 0.1);
        verify(foyerRepository, times(1)).findAll();
    }


    @Test
    @Order(10)
    void testRetrieveFoyer_Found() {
        when(foyerRepository.findById(1L)).thenReturn(Optional.of(testFoyer));
        Foyer result = foyerService.retrieveFoyer(1L);
        assertNotNull(result);
        assertEquals(testFoyer.getIdFoyer(), result.getIdFoyer());
        verify(foyerRepository, times(1)).findById(1L);
    }

    @Test
    @Order(11)
    void testRetrieveFoyer_NotFound() {
        when(foyerRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> foyerService.retrieveFoyer(1L));
        verify(foyerRepository, times(1)).findById(anyLong());
    }

    @Test
    @Order(12)
    void testAddFoyer() {
        when(foyerRepository.save(any(Foyer.class))).thenReturn(testFoyer);
        Foyer result = foyerService.addFoyer(testFoyer);
        assertNotNull(result);
        assertEquals(testFoyer.getIdFoyer(), result.getIdFoyer());
        verify(foyerRepository, times(1)).save(testFoyer);
    }

    @Test
    @Order(13)
    void testModifyFoyer() {
        when(foyerRepository.save(any(Foyer.class))).thenReturn(testFoyer);
        Foyer result = foyerService.modifyFoyer(testFoyer);
        assertNotNull(result);
        assertEquals(testFoyer.getIdFoyer(), result.getIdFoyer());
        verify(foyerRepository, times(1)).save(testFoyer);
    }

    @Test
    @Order(14)
    void testRemoveFoyer() {
        doNothing().when(foyerRepository).deleteById(1L);
        foyerService.removeFoyer(1L);
        verify(foyerRepository, times(1)).deleteById(1L);
    }


    @Test
    @Order(15)
    void testRetrieveAllFoyers() {

        when(foyerRepository.findAll()).thenReturn(List.of(testFoyer));

        List<Foyer> result = foyerService.retrieveAllFoyers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testFoyer.getNomFoyer(), result.get(0).getNomFoyer());
        verify(foyerRepository, times(1)).findAll();
    }




}
