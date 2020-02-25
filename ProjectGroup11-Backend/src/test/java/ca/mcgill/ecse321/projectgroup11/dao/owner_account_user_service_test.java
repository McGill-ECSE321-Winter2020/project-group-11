package ca.mcgill.ecse321.projectgroup11.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.mockito.MockitoAnnotations;

import ca.mcgill.ecse321.projectgroup11.javacode.AccountUser;
import ca.mcgill.ecse321.projectgroup11.javacode.Owner;
import ca.mcgill.ecse321.projectgroup11.service.AccountUserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyInt;


import org.junit.jupiter.api.Test;

class owner_account_user_service_test {
	@Mock
	private AccountUserRepository userDao;

	@InjectMocks
	private AccountUserService service;

	private static final int USER_KEY = 5;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
		
	@Test
	void testCreateOwner() {
		Owner id = service.createOwner("String Char", "ken@hotmail.com", "MUNRO", 40);
	// Try create owner with an incorrect name (recall account user service => name + last name separed by a space)
		try {
			Owner manger = service.createOwner("manger", "ok@hotmail.com", "noob", 50);

		}
		
		catch(Exception e) {
			String error = e.getMessage();
			assertEquals( "Invalid Name", error );
			
			
		}
	// Try create owner with an incorrect email 
		try {
			Owner manger = service.createOwner("manger jouer", "notemail", "noob", 50);

		}
		
		catch(Exception e) {
			String error = e.getMessage();
			assertEquals( "Invalid Email", error );
			
			
		}
		// Try create owner with a password less than 4 characters
		try {
			Owner manger = service.createOwner("manger jouer", "notemail@hotmail.com", "ok", 50);

		}
		
		catch(Exception e) {
			String error = e.getMessage();
			assertEquals( "Invalid Password - must be between 4 and 20 characters", error );
			
			
		}
		// Try create owner with a password more than 4 characters
		try {
			Owner manger = service.createOwner("manger jouer", "notemail@hotmail.com", "okiwoejfoweifojfoewifowjofweoifjwieojfoiwefiowej", 50);

		}
		
		catch(Exception e) {
			String error = e.getMessage();
			assertEquals( "Invalid Password - must be between 4 and 20 characters", error );
			
			
		}
		
	}

}
