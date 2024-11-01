package tn.esprit.tpfoyer;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.*;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class TestMockito {

    @Mock
    private FoyerRepository foyerRepository;


    @InjectMocks
    private FoyerServiceImpl foyerService;

    private Foyer testFoyer;

    @BeforeEach
    void setUp() {
        testFoyer = new Foyer();
        testFoyer.setIdFoyer(1L);
        testFoyer.setNomFoyer("Foyer A");

    }

    @AfterEach
    void tearDown() {
        reset(foyerRepository);
    }


    @Test
    @Order(1)
    void testRetrieveAllFoyers() {

        when(foyerRepository.findAll()).thenReturn(List.of(testFoyer));

        List<Foyer> result = foyerService.retrieveAllFoyers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testFoyer.getNomFoyer(), result.get(0).getNomFoyer());
        verify(foyerRepository, times(1)).findAll();
    }
}