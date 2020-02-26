package ca.mcgill.ecse321.projectgroup11.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse321.projectgroup11.dao.AccountUserRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.AccountUser;
import ca.mcgill.ecse321.projectgroup11.javacode.Address;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.service.AccountUserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;



import org.junit.jupiter.api.Test;


@ExtendWith(MockitoExtension.class)
class TestOwner_AccountUserService{
	
	@Mock
	private AccountUserRepository userDao;

	@InjectMocks
	private AccountUserService service;

	private static final int USER_KEY = 5;
	private static final int NONEXISTING_KEY = 20;

	@BeforeEach
	public void setUp() throws Exception {
		//When finding by ID - return an Owner with ID USER_KEY if passed with ID user_key
		lenient().when(userDao.findAccountUserByuserID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USER_KEY)) {
				Owner person = new Owner();
				person.setUserID(USER_KEY);
				person.setEmailAddress("bien@hotmail.com");
				return person;
			} else {
				return null;
			}
		});

		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
		lenient().when(userDao.save(any(Owner.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	
	@Test
	void testCreateOwner() {
		Owner owner = null;
		try {
			owner = service.createOwner("Hello World", "ken@hotmail.com", "MUNRO", 40);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(owner);
		// check error
		assertEquals("Hello", owner.getFirstName());
		assertEquals("World", owner.getLastName());
		assertEquals("ken@hotmail.com", owner.getEmailAddress());
		assertEquals("MUNRO", owner.getPassword());
		assertEquals(40, owner.getUserID());
	}

	// Try create owner with an incorrect name (recall account user service => name + last name separed by a space)
	@Test
	void testCreateOwnerIncorrectName() {
		String name = " ";
		String error = null;
		Owner owner = null;
		try {
			owner = service.createOwner(name, "ken@hotmail.com", "MUNRO", 40);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(owner);
		// check error
		assertEquals("Invalid Name", error);
		
	}
	
	
	@Test
	// Try create owner with an incorrect email 
	void testCreateOwnerIncorrectEmail() {
		String error = "";
		String email = "notemail";
		Owner owner = null;
		try {
			owner = service.createOwner("manger jouer", email, "noob", 50);

		} catch(Exception e) {
			error = e.getMessage();
		}
		
		assertNull(owner);
		assertEquals( "Invalid Email", error );
	}
	
	@Test
	// Try create owner with a password less than 4 characters
	void testCreateOwnerShortPassword() {
		String error = "";
		String password = "ok";
		Owner owner = null;
		try {
			owner = service.createOwner("manger jouer", "notemail@hotmail.com", "ok", 50);
		} catch(Exception e) {
			error = e.getMessage();
		}
		
		assertNull(owner);
		assertEquals( "Invalid Password - must be between 4 and 20 characters", error );
	}

	@Test
	// Try create owner with a password more than 20 characters
	void testCreateOwnerLongPassword(){
		try {
			Owner manger = service.createOwner("manger jouer", "notemail@hotmail.com", "okiwoejfoweifojfoewifowjofweoifjwieojfoiwefiowej", 50);

		}

		catch(Exception e) {
			String error = e.getMessage();
			assertEquals( "Invalid Password - must be between 4 and 20 characters", error );


		}
	}


	@Test
	// Try creating an Owner with same ID as the one executed before each 

	void testCreateOwnerSameID() {
		try {
			Owner manger = service.createOwner("manger jouer", "notemail@hotmail.com", "ofiowej", 5);
		}

		catch(Exception e) {
			String error = e.getMessage();
			assertEquals( "ID already used", error );
		}
	}
	@Test
	// Try creating an Owner with an incorrect phone number
	void testCreateOwnerIncorrectPhoneNumber() {
		try {
			Owner manger = service.createOwner("jaime le", "ok@hotmail.com", "404", "Munko", "Jsp ou je vis, j'avoue la D , ok ok non ononon", null, 50, null);
		}
		catch(Exception e) {
			assertEquals(e.getMessage() , "Invalid Phone Number");
		}
	}
	@Test
	// Try creating an Owner with a too small description < 20 
	void testCreateOwnerShortDescription() {
		try {
			Owner manger = service.createOwner("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je vis", null, 50, null);
		}
		catch(Exception e) {
			assertEquals(e.getMessage() , "Invalid Description - must not be less than 20 or greater than 5000 characters");
		}
	}
	
	@Test
	// Try creating an Owner with a large description > 5000
	void testCreateOwnerLongDescription() {
		try {
			Owner manger = service.createOwner("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je vis", null, 50, null);
		}
		catch(Exception e) {
			assertEquals(e.getMessage() , "Invalid Description - must not be less than 20 or greater than 5000 characters");
		}
	}
	@Test
	// Try creating an Owner with null address
	void testCreateOwnerNullAddress() {
		try {
			Owner manger = service.createOwner("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je vis okokokokokokokkokokok", null, 50, null);
		}
		catch(Exception e) {
			assertEquals(e.getMessage() , "address must be written");
		}
	}
	@Test
	// Try creating an Owner with null Pet
	void testCreateOwnerNullPet() {
		Address a = new Address();
		try {
			Owner manger = service.createOwner("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je vis okokokokokokokkokokok", a, 50, null);
		}
		catch(Exception e) {
			assertEquals(e.getMessage() , "Can't have a null pet, use other constructor");
		}
	}
	





	@Test
	// Try to find owner by id using service
	void testFindingOwnerByID() {
		assertEquals(USER_KEY, (service.getAccountUserByID(USER_KEY).getUserID()));
	}

	@Test
	// Try to update an owner which is not in DB
	void testUpdatingOwner() {
		Owner a = new Owner();
		try {service.updateOwner(a);

		}
		catch (Exception e) {
			assertEquals(e.getMessage() , "Cannot update owner that is not in the database");
		}
	}
	
	@Test
	// Try to get an null account user by browsing for nonexisting iD
	public void testGetNonExistingPerson() {
		assertNull(service.getAccountUserByID(NONEXISTING_KEY));
	}
	
	@Test
	// Try to get an null account user by browsing for nonexisting iD
	public void testGetExistingPerson() {
		assertEquals(USER_KEY, service.getAccountUserByID(USER_KEY).getUserID());
	}
	
	



}
