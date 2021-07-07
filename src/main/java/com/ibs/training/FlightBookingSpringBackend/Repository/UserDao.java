package com.ibs.training.FlightBookingSpringBackend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibs.training.FlightBookingSpringBackend.Model.User;
@Repository
public interface UserDao extends JpaRepository<User, Integer>{

		/*public User saveUser(User user);*/
		

}

