package ca.mcgill.ecse321.projectgroup11.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup11.javacode.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	User findUserByuserID(Integer user_userid);

}