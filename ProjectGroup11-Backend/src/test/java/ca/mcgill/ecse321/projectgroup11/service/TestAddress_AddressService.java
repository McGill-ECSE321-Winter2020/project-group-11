
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

import ca.mcgill.ecse321.projectgroup11.dao.AddressRepository;
import ca.mcgill.ecse321.projectgroup11.javacode.AccountUser;
import ca.mcgill.ecse321.projectgroup11.javacode.Address;
import ca.mcgill.ecse321.projectgroup11.javacode.Adopter;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.javacode.Pet;
import ca.mcgill.ecse321.projectgroup11.service.AddressService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;



import org.junit.jupiter.api.Test;

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
		assertEquals(40, address.getId());
		assertEquals(3234 , address.getStreetNumber());
		assertEquals("McGill Ave", address.getStreet());
		assertEquals("Montreal", address.getCity());
		assertEquals("Quebec", address.getProvince());
		assertEquals("H3A 1A7", address.getPostalCode());
	}
	
	
	@Test
	void testCreateAddressNoStreet() {
		
		
		Address address = null;
		String street = null;
		String error = null;
		
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
		String street = "!#$Gtrq5h";
		String error = null;
		
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
		String street = "McGill";
		String error = null;
		
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
		String city = "!#$Gtrq5h";
		String error = null;
		
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
		String province = "!#$Gtrq5h";
		String error = null;
		
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
		Integer streetNumber = null;
		String error = null;
		
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
		Integer streetNumber = -40;
		String error = null;
		
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
		String error = null;
		try {
			service.updateAddress(a);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals(error , "Cannot update address that is not in the database");
	}
	
	
}