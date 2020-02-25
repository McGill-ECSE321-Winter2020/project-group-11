package ca.mcgill.ecse321.projectgroup11.dao;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

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

	private static final Integer USER_KEY = 5;

	@BeforeEach
	public void setMockOutput() {
		lenient().when(userDao.findAccountUserByuserID(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(USER_KEY)) {
				Owner use = new Owner();
				use.setUserID(USER_KEY);
				return use;
			} else {
				return null;
			}
		});
	}
	@Test
	void test() {
		
		Owner ex = service.createOwner("allo", "manger", "noob", 50);
		
	}

}
