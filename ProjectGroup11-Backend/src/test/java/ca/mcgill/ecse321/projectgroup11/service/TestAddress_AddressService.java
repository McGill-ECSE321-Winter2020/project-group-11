
package ca.mcgill.ecse321.projectgroup11.service;


import org.junit.jupiter.api.BeforeEach;

/**
*
*@author ProjectGroup11
*
*
*/

@ExtendWith(MockitoExtension.class)
class TestAddress_AddressService{
	
	@Mock
	private AddressRepository addressDao;
	
	@InjectMocks
	private AddressService service;
	
	private static final int USER_KEY = 5;
	private static final int NONEXISTING_KEY = 20;
	
	@BeforeEach
	public void setUp() throws Exception {
		//When finding by ID - return an Adopter with ID USER_KEY if passed with ID user_key
		lenient().when(addressDao.findAddressById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(USER_KEY)) {
				Address address = new Address();
				address.setId(USER_KEY);
				return address;
			} else {
				return null;
			}
		});
		
	

		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		
		lenient().when(addressDao.save(any(Address.class))).thenAnswer(returnParameterAsAnswer);
		
		

		
	}
	
	
	// Address parameters: Integer ID, Integer streetNum, String street, String city, String province, String postal
	
	@Test
	void testCreateAddress() {
		Address address = null;
		try {
			address = service.createAddress(40 , 3234, "McGill Ave", "Montreal", "Quebec", "H3A 1A7");
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(address);
		// check error
		assertEquals(40, adress.getUserId());
		assertEquals(3234 , address.getStreetNumber());
		assertEquals("McGill Ave", address.getStreet());
		assertEquals("Montreal", address.getCity());
		assertEquals("Quebec", address.getProvince());
		assertEquals("H3A 1A7", adopter.getPostalCode());
	}
	
	
	@Test
	void testCreateAddressNoStreet() {
		
		
		Address address = null;
		string street = null;
		string error = null;
		
		try {
			address = service.createAddress(40 , 3234, street, "Montreal", "Quebec", "H3A 1A7");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(address);
		assertEquals("Invalid Street - not alphabetic", error);
	}
	
	
	@Test
	void testCreateAddressNotAlphabeticalStreet() {
		
		
		Address address = null;
		string street = "!#$Gtrq5h";
		string error = null;
		
		try {
			address = service.createAddress(40 , 3234, street, "Montreal", "Quebec", "H3A 1A7");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(address);
		assertEquals("Invalid Street - not alphabetic", error);
	}
	
	@Test
	void testCreateAddressOneWordStreet() {
		
		
		Address address = null;
		string street = "McGill";
		string error = null;
		
		try {
			address = service.createAddress(40 , 3234, street, "Montreal", "Quebec", "H3A 1A7");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(address);
		assertEquals("Invalid Street - must have at least two parts (include Street/Circle/etc.)", error);
	}
	
	
	@Test
	void testCreateAddressNotAlphabeticalCity() {
		
		
		Address address = null;
		string city = "!#$Gtrq5h";
		string error = null;
		
		try {
			address = service.createAddress(40 , 3234, "McGill Avenue", city, "Quebec", "H3A 1A7");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(address);
		assertEquals("Invalid City Name - not alphabetic", error);
	}
	
	@Test
	void testCreateAddressNotAlphabeticalProvince() {
		
		
		Address address = null;
		string province = "!#$Gtrq5h";
		string error = null;
		
		try {
			address = service.createAddress(40 , 3234, "McGill Avenue", "Montreal", province, "H3A 1A7");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(address);
		assertEquals("Invalid Province - not alphabetic", error);
	}
	
	@Test
	void testCreateAddressNoStreetNumber() {
		
		
		Address address = null;
		int streetNumber = null;
		string error = null;
		
		try {
			address = service.createAddress(40 , streetNumber, "McGill Avenue", "Montreal", "Quebec", "H3A 1A7");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(address);
		assertEquals("Street  Number < 1?", error);
	}
	
	@Test
	void testCreateAddressNegativeStreetNumber() {
		
		
		Address address = null;
		int streetNumber = -40;
		string error = null;
		
		try {
			address = service.createAddress(40 , streetNumber, "McGill Avenue", "Montreal", "Quebec", "H3A 1A7");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertNull(address);
		assertEquals("Street  Number < 1?", error);
	}
	
	//Update a nonexisting address
	@Test
	void testUpdatingAddress() {
		Address a = new Address();
		try {service.updateAddress(a);

		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getMessage() , "Cannot update address that is not in the database");
		}
	}
	
}