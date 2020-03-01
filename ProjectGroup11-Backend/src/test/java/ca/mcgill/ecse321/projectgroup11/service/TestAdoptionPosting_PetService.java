package ca.mcgill.ecse321.projectgroup11.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.projectgroup11.dao.AdoptionPostingRepository;
import ca.mcgill.ecse321.projectgroup11.dao.PetProfileRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
import ca.mcgill.ecse321.projectgroup11.javacode.AdoptionPosting;
import ca.mcgill.ecse321.projectgroup11.javacode.PetProfile;
import ca.mcgill.ecse321.projectgroup11.service.PetService;


/**
 *
 *@author ProjectGroup11
 *Tests the PetProfile methods in PetService
 *
 */

@ExtendWith(MockitoExtension.class)
public class TestAdoptionPosting_PetService {
	@Mock
	private AdoptionPostingRepository adoptionPostingDao;

	@InjectMocks
	private PetService service;


	public static final Integer USER_ID = 5;
	public static final Integer NONEXISTING_ID = 20;


	@BeforeEach
	public void setUp() throws Exception {
		//When finding by ID - return an Adopter with ID USER_KEY if passed with ID user_key
		lenient().when(adoptionPostingDao.findAdoptionPostingById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USER_ID)) {
				AdoptionPosting adoptionPosting = new AdoptionPosting();
				adoptionPosting.setId(USER_ID);


				return adoptionPosting;
			} else {
				return null;
			}
		});	

		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(adoptionPostingDao.save(any(AdoptionPosting.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	

}
