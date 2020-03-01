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
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.service.AccountUserService;

import static org.junit.jupiter.api.Assertions.*;
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
class TestOwner_AccountUserService{

	@Mock
	private AccountUserRepository userDao;

	@InjectMocks
	private AccountUserService service;

	private static final int USER_KEY = 5;
	private static final int NONEXISTING_KEY = 20;
	private static final String EMAIL_KEY = "team@hotmail.com";

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
	// Try creating Owner with only first name
	@Test
	void testCreateOwnerFirstNameOrLastNameOnly() {
		String name = "Jean ";
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
	// Try create Owner with an incorrect email 
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
	// Try create Owner with a password less than 4 characters
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
	// Try create Owner with a password with 4 characters (BoundaryTesting)
	void testCreateOwner4CharPassword() {
		String error = "";
		String password = "ok";
		Owner owner = null;
		owner = service.createOwner("manger jouer", "notemail@hotmail.com", "okok", 50);


		assertNotNull(owner);
		assertEquals("okok", owner.getPassword());
	}
	@Test
	// Try create Adopter with a password with 20 characters (BoundaryTesting)
	void testCreateOwner20CharPassword() {
		String error = "";
		String password = "ok";
		Owner owner = null;
		owner = service.createOwner("manger jouer", "notemail@hotmail.com", "okokokokokokokokokok", 50);


		assertNotNull(owner);
		assertEquals("okokokokokokokokokok", owner.getPassword());
	}

	@Test
	// Try create owner with a password more than 20 characters
	void testCreateOwnerLongPassword(){
		Owner manger = null;
		try {
			manger = service.createOwner("manger jouer", "notemail@hotmail.com", "okiwoejfoweifojfoewifowjofweoifjwieojfoiwefiowej", 50);

		}

		catch(Exception e) {
			String error = e.getMessage();
			assertNull(manger);
			assertEquals( "Invalid Password - must be between 4 and 20 characters", error );


		}
	}
	@Test
	//Try creating an Owner with email already in the database
	void testCreateOwnerWithEmailAlreadyInDB() {
		Owner owner = null;

		try {
			owner = service.createOwner("Jean Salem", EMAIL_KEY, "numérotons", 99);

		}
		catch (Exception e) {
			assertNull(owner);
			assertEquals(e.getMessage() , "Email Already used");
		}
	}


	@Test
	// Try creating an Owner with same ID as the one executed before each 

	void testCreateOwnerSameID() {
		Owner manger = null;
		try {
			manger = service.createOwner("manger jouer", "notemail@hotmail.com", "ofiowej", 5);
		}

		catch(Exception e) {
			String error = e.getMessage();
			assertNull(manger);
			assertEquals( "ID already used", error );
		}
	}
	@Test
	// Try creating an Owner with an incorrect phone number
	void testCreateOwnerIncorrectPhoneNumber() {
		Owner manger = null;

		try {
			manger = service.createOwner("jaime le", "ok@hotmail.com", "404", "Munko", "Jsp ou je vis, j'avoue la D , ok ok non ononon", null, 50, null);
		}
		catch(Exception e) {
			assertNull(manger);
			assertEquals(e.getMessage() , "Invalid Phone Number");
		}
	}
	@Test
	// Try creating an Owner with a correct phone number (Boundary Testing)
	void testCreateOwner12CharPhoneNumber() {
		Address a = new Address();
		Owner manger = null;
		Pet b = new Pet();

		manger = service.createOwner("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je vis, j'avoue la D , ok ok non ononon", a, 50, b);
		assertNotNull(manger);
		assertEquals(manger.getPhoneNumer() , "514-495-0371");

	}
	@Test
	// Try creating an Owner with a phone number that has no "-" (Boundary Testing)
	void testCreateOwner10CharPhoneNumber() {
		Address a = new Address();
		Owner manger = null;
		Pet b = new Pet();

		manger = service.createOwner("jaime le", "ok@hotmail.com", "5144950371", "Munko", "Jsp ou je vis, j'avoue la D , ok ok non ononon", a, 50, b);
		assertNotNull(manger);
		assertEquals(manger.getPhoneNumer() , "5144950371");

	}
	@Test
	// Try creating an Owner with an incorrect phone number (we only accept local phone number (xxx)xxx-xxxx (Boundary Testing)
	void testCreateOwnerMoreCharPhoneNumber() {
		Address a = new Address();
		Owner manger = null;
		Pet b = new Pet();

		try {manger = service.createOwner("jaime le", "ok@hotmail.com", "1-800-514-495-0371", "Munko", "Jsp ou je vis, j'avoue la D , ok ok non ononon", a, 50, b); }
		catch (Exception e) {
		assertNull(manger);
		assertEquals(e.getMessage() , "Invalid Phone Number");
		}

	}

	@Test
	// Try creating an Owner with a too small description < 20 
	void testCreateOwnerShortDescription() {
		Owner manger =null;
		try {
			manger = service.createOwner("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je vis", null, 50, null);
		}
		catch(Exception e) {
			assertNull(manger);
			assertEquals(e.getMessage() , "Invalid Description - must not be less than 20 or greater than 5000 characters");
		}
	}

	@Test
	// Try creating an Owner with a large description > 5000
	void testCreateOwnerLongDescription() {
		Owner manger = null;
		try {
			manger = service.createOwner("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je visJsp ou je vis", null, 50, null);
		}
		catch(Exception e) {
			assertNull(manger);
			assertEquals(e.getMessage() , "Invalid Description - must not be less than 20 or greater than 5000 characters");
		}
	}
	@Test
	// Try creating an Owner with null address
	void testCreateOwnerNullAddress() {
		Owner manger = null;

		try {
			manger = service.createOwner("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je vis okokokokokokokkokokok", null, 50, null);
		}
		catch(Exception e) {
			assertNull(manger);
			assertEquals(e.getMessage() , "address must be written");
		}
	}
	@Test
	// Try creating an Owner with null Pet
	void testCreateOwnerNullPet() {
		Owner manger =null;
		Address a = new Address();
		try {
			manger = service.createOwner("jaime le", "ok@hotmail.com", "514-495-0371", "Munko", "Jsp ou je vis okokokokokokokkokokok", a, 50, null);
		}
		catch(Exception e) {
			assertNull(manger);
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
	public void testGetNonExistingOwner() {
		assertNull(service.getAccountUserByID(NONEXISTING_KEY));
	}

	@Test
	// Try to get an null account user by browsing for nonexisting iD
	public void testGetExistingOwner() {
		assertEquals(USER_KEY, service.getAccountUserByID(USER_KEY).getUserID());
	}


	@Test
	// Finally creating correctly an owner with the three constructors
	public void testFinalCreateOwner() {
		Pet x = new Pet();
		Pet y = new Pet();
		Address z = new Address();

		Owner a = service.createOwner("Jean Michel", "ok@hotmail.com", "vivaAlgeria", 20);
		Owner b = service.createOwner("Yacine duNeufNeuf", "bc@hotmail.com", "HamzaBenatia", 30, x);
		Owner c = service.createOwner("Jean Michael", "ng@hotmail.com", "514-495-0371", "intellijide", "un jour jserais back dans le 99 , cs.villeray turin villeray",z , 70, y);

		assertNotNull(a);
		assertNotNull(b);
		assertNotNull(c);

		// Verifying fields for first constructor
		assertEquals(a.getFirstName(), "Jean");
		assertEquals(a.getLastName() , "Michel");
		assertEquals(a.getAddress() , null);
		assertEquals(a.getPet() , null);
		assertEquals(a.getPassword(), "vivaAlgeria");
		assertEquals(a.getUserID(), 20);
		assertEquals(a.getEmailAddress(), "ok@hotmail.com");
		assertEquals(a.getPhoneNumer() , null);
		assertEquals(a.getDescription(), null);

		// Verifying fields for second constructor

		assertEquals(b.getFirstName(), "Yacine");
		assertEquals(b.getLastName(), "duNeufNeuf");
		assertEquals(b.getPet().contains(x) , true);
		assertEquals(b.getAddress() , null);
		assertEquals(b.getEmailAddress(), "bc@hotmail.com");
		assertEquals(b.getPassword(), "HamzaBenatia");
		assertEquals(b.getUserID(), 30);
		assertEquals(b.getPhoneNumer(), null);
		assertEquals(b.getDescription(), null);

		// Verifying fields for third constructor


		assertEquals(c.getFirstName(), "Jean");
		assertEquals(c.getLastName(), "Michael");
		assertEquals(c.getPet().contains(y) , true);
		assertEquals(c.getAddress().contains(z) , true);
		assertEquals(c.getEmailAddress(), "ng@hotmail.com");
		assertEquals(c.getPassword(), "intellijide");
		assertEquals(c.getUserID(), 70);
		assertEquals(c.getPhoneNumer() ,"514-495-0371" );
		assertEquals(c.getDescription(), "un jour jserais back dans le 99 , cs.villeray turin villeray");
	}





}
