package ca.mcgill.ecse321.projectgroup11.service;

import ca.mcgill.ecse321.projectgroup11.dao.PetsRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestPet_PetService {
    @Mock
    private PetsRepository petsRepository;

    @InjectMocks
    private PetService petService;


}