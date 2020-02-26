package ca.mcgill.ecse321.projectgroup11.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup11.javacode.AccountUser;

public interface AccountUserRepository extends CrudRepository<AccountUser, Integer>{

	AccountUser findAccountUserByuserID(Integer userid);
	AccountUser findAccountUserByEmail(String email);

}