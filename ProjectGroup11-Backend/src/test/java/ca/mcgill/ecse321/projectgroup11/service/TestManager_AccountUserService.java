
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
import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
import ca.mcgill.ecse321.projectgroup11.javacode.Manager;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.javacode.Shelter;
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

/**
 * 
 * @author ProjectGroup11
 *
 */

@ExtendWith(MockitoExtension.class)
class TestManager_AccountUserService{
	
	@Mock
	private AccountUserRepository userDao;

	@InjectMocks
	private AccountUserService service;

	private static final int USER_KEY = 5;
	private static final int NONEXISTING_KEY = 20;
	private static final String EMAIL_KEY = "team@hotmail.com";

	@BeforeEach
	public void setUp() throws Exception {
		//When finding by ID - return an Adopter with ID USER_KEY if passed with ID user_key
		lenient().when(userDao.findAccountUserByuserID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USER_KEY)) {
				Manager manager = new Manager();
				manager.setUserID(USER_KEY);
				manager.setEmailAddress("bien@hotmail.com");
				return manager;
			} else {
				return null;
			}
		});
		
		lenient().when(userDao.findAccountUserByEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EMAIL_KEY)) {
				Manager a = new Manager();
				a.setEmailAddress(EMAIL_KEY);
				return a;
			} else {
				return null;
			}
		});

		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
		lenient().when(userDao.save(any(Manager.class))).thenAnswer(returnParameterAsAnswer);
		
		

		
	}
	
	
	@Test
	void testCreateManager() {
		Manager manager = null;
		try {
			manager = service.createManager("Hello World", "ken@hotmail.com", "MUNRO", 40);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(manager);
		// check error
		assertEquals("Hello", manager.getFirstName());
		assertEquals("World", manager.getLastName());
		assertEquals("ken@hotmail.com", manager.getEmailAddress());
		assertEquals("MUNRO", manager.getPassword());
		assertEquals(40, manager.getUserID());
	}

	// Try create Manager with an incorrect name (recall account user service => name + last name separed by a space)
	@Test
	void testCreateAdopterIncorrectName() {
		String name = " ";
		String error = null;
		Manager manager = null;
		try {
			manager = service.createManager(name, "ken@hotmail.com", "MUNRO", 40);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(manager);
		// check error
		assertEquals("Invalid Name", error);
		
	}
	@Test
	//Try creating an Manager with email already in the database
	void testCreateManagerWithEmailAlreadyInDB() {
		
		try {
			Manager manger = service.createManager("Jean Salem", EMAIL_KEY, "numérotons", 99);
			
		}
		catch (Exception e) {
			assertEquals(e.getMessage() , "Email Already used");
		}
	}
	// Try creating Manager with only first name
	@Test
	void testCreateManagerFirstNameOrLastNameOnly() {
		String name = "Jean ";
		String error = null;
		Manager manager = null;
		try {
			manager = service.createManager(name, "ken@hotmail.com", "MUNRO", 40);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNull(manager);
		// check error
		assertEquals("Invalid Name", error);
		
	}
	
	
	@Test
	// Try create Manager with an incorrect email 
	void testCreateManagerIncorrectEmail() {
		String error = "";
		String email = "notemail";
		Manager manager = null;
		try {
			manager = service.createManager("manger jouer", email, "noob", 50);

		} catch(Exception e) {
			error = e.getMessage();
		}
		
		assertNull(manager);
		assertEquals( "Invalid Email", error );
	}
	
	@Test
	// Try create Manager with a password less than 4 characters
	void testCreateManagerShortPassword() {
		String error = "";
		String password = "ok";
		Manager manager = null;
		try {
			manager = service.createManager("manger jouer", "notemail@hotmail.com", "ok", 50);
		} catch(Exception e) {
			error = e.getMessage();
		}
		
		assertNull(manager);
		assertEquals( "Invalid Password - must be between 4 and 20 characters", error );
	}

	@Test
	// Try create Manager with a password more than 20 characters
	void testCreateManagerLongPassword(){
		try {
			Manager manager = service.createManager("manger jouer", "notemail@hotmail.com", "okiwoejfoweifojfoewifowjofweoifjwieojfoiwefiowej", 50);

		}

		catch(Exception e) {
			String error = e.getMessage();
			assertEquals( "Invalid Password - must be between 4 and 20 characters", error );


		}
	}


	@Test
	// Try creating an Manager with same ID as the one executed before each 

	void testCreateManagerSameID() {
		try {
			Manager manger = service.createManager("manger jouer", "notemail@hotmail.com", "ofiowej", 5);
		}

		catch(Exception e) {
			String error = e.getMessage();
			assertEquals( "ID already used", error );
		}
	}
	@Test
	// Try creating an Manager with an incorrect phone number
	void testCreateManagerIncorrectPhoneNumber() {
		try {
			Manager manger = service.createManager("jaime le", "ok@hotmail.com", "404", "Munko", "Jsp ou je vis, j'avoue la D , ok ok non ononon", null, 50, null);
		}
		catch(Exception e) {
			assertEquals(e.getMessage() , "Invalid Phone Number");
		}
	}
	@Test
	// Try creating an Manager with a too small description < 20 
	void testCreateManagerShortDescription() {
		try {
			Manager manger = service.createManager("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je vis", null, 50, null);
		}
		catch(Exception e) {
			assertEquals(e.getMessage() , "Invalid Description - must not be less than 20 or greater than 5000 characters");
		}
	}
	
	@Test
	// Try creating an Manager with a large description > 5000
	void testCreateManagerLongDescription() {
		try {
			Manager manger = service.createManager("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je vis", null, 50, null);
		}
		catch(Exception e) {
			assertEquals(e.getMessage() , "Invalid Description - must not be less than 20 or greater than 5000 characters");
		}
	}
	
	
	@Test
	// Try to find Manager by id using service
	void testFindingManagerByID() {
		assertEquals(USER_KEY, (service.getAccountUserByID(USER_KEY).getUserID()));
	}

	@Test
	// Try to update an Manager which is not in DB
	void testUpdatingManager() {
		Manager a = new Manager();
		try {service.updateManager(a);

		}
		catch (Exception e) {
			assertEquals(e.getMessage() , "Cannot update manager that is not in the database");
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
	
	
	@Test
	// Finally creating correctly an Manager with the two constructors
	public void testFinalCreateAdopter() {
	
		Address z = new Address();
		Shelter x = new Shelter();
		
		Manager a = service.createManager("Jean Michel", "ok@hotmail.com", "vivaAlgeria", 20);
		Manager c = service.createManager("Jean Michael", "ng@hotmail.com", "514-495-0371", "intellijide", "un jour jserais back dans le 99 , cs.villeray turin villeray",z , 70, x);
		
		assertNotNull(a);
		assertNotNull(c);
		
		// Verifying fields for first constructor
		assertEquals(a.getFirstName(), "Jean");
		assertEquals(a.getLastName() , "Michel");
		assertEquals(a.getAddress() , null);
		assertEquals(a.getPassword(), "vivaAlgeria");
		assertEquals(a.getUserID(), 20);
		assertEquals(a.getEmailAddress(), "ok@hotmail.com");
		assertEquals(a.getPhoneNumer() , null);
		assertEquals(a.getDescription(), null);
		
		// Verifying fields for second constructor
		
		

		

		assertEquals(c.getFirstName(), "Jean");
		assertEquals(c.getLastName(), "Michael");
		assertEquals(c.getAddress().contains(z) , true);
		assertEquals(c.getEmailAddress(), "ng@hotmail.com");
		assertEquals(c.getPassword(), "intellijide");
		assertEquals(c.getUserID(), 70);
		assertEquals(c.getPhoneNumer() ,"514-495-0371" );
		assertEquals(c.getDescription(), "un jour jserais back dans le 99 , cs.villeray turin villeray");
		assertEquals(c.getShelter() ,x );
	}
	
	



}
