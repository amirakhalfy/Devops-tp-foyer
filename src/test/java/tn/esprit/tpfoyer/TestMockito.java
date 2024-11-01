package tn.esprit.tpfoyer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.ChambreRepository;
import tn.esprit.tpfoyer.service.ChambreServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChambreServiceTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private ChambreServiceImpl chambreService;

    private Chambre testChambre;

    @BeforeEach
    void setUp() {
        testChambre = new Chambre();
        testChambre.setIdChambre(1L);
        testChambre.setNumeroChambre(101);
        testChambre.setTypeC(TypeChambre.SIMPLE); // Assuming SIMPLE is a value in TypeChambre enum
        testChambre.setReservations(new HashSet<>()); // Initialize with an empty set
    }

    @AfterEach
    void tearDown() {
        reset(chambreRepository);
    }

    @Test
    @Order(1)
    void testRetrieveAllChambres() {
        when(chambreRepository.findAll()).thenReturn(List.of(testChambre));

        List<Chambre> result = chambreService.retrieveAllChambres();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testChambre.getNumeroChambre(), result.get(0).getNumeroChambre());
        assertEquals(testChambre.getTypeC(), result.get(0).getTypeC());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    @Order(2)
    void testRetrieveChambre() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(testChambre));

        Chambre result = chambreService.retrieveChambre(1L);

        assertNotNull(result);
        assertEquals(testChambre.getNumeroChambre(), result.getNumeroChambre());
        assertEquals(testChambre.getTypeC(), result.getTypeC());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    @Order(3)
    void testAddChambre() {
        when(chambreRepository.save(testChambre)).thenReturn(testChambre);

        Chambre result = chambreService.addChambre(testChambre);

        assertNotNull(result);
        assertEquals(testChambre.getNumeroChambre(), result.getNumeroChambre());
        assertEquals(testChambre.getTypeC(), result.getTypeC());
        verify(chambreRepository, times(1)).save(testChambre);
    }

    @Test
    @Order(4)
    void testModifyChambre() {
        when(chambreRepository.save(testChambre)).thenReturn(testChambre);

        Chambre result = chambreService.modifyChambre(testChambre);

        assertNotNull(result);
        assertEquals(testChambre.getNumeroChambre(), result.getNumeroChambre());
        assertEquals(testChambre.getTypeC(), result.getTypeC());
        verify(chambreRepository, times(1)).save(testChambre);
    }

    @Test
    @Order(5)
    void testRemoveChambre() {
        doNothing().when(chambreRepository).deleteById(1L);

        chambreService.removeChambre(1L);

        verify(chambreRepository, times(1)).deleteById(1L);
    }
}
