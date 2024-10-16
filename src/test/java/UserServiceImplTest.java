import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.repository.EtudiantRepository;
import tn.esprit.tpfoyer.service.EtudiantServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)

class UserServiceImplTest {
    @Mock
    EtudiantRepository userRepository;

    @InjectMocks
    EtudiantServiceImpl userService;

    Etudiant user = new Etudiant( 1L,"John", "Doe");


    List<Etudiant> listUsers = new ArrayList<>() {
        {
            add(new Etudiant(2L,"John", "Doe"));
            add(new Etudiant(3L,"John", "Doe"));
        }
    };

    @Test
    @Order(1)
    public void testRetrieveUser() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).
                thenReturn(Optional.of(user));
        Etudiant user1 = userService.retrieveEtudiant(2L);
        Assertions.assertNotNull(user1);

    }

    @Test
    @Order(2)

    public void testRetrieveAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(listUsers);
        List<Etudiant> listU = userService.retrieveAllEtudiants();
        Assertions.assertEquals(2, listU.size());
    }
}

