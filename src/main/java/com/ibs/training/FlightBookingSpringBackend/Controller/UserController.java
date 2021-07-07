package com.ibs.training.FlightBookingSpringBackend.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibs.training.FlightBookingSpringBackend.Model.User;
import com.ibs.training.FlightBookingSpringBackend.Repository.UserDao;
import com.ibs.training.FlightBookingSpringBackend.Status.Status;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("")
public class UserController {

	@Autowired
	UserDao userDao;
    /*@PostMapping("/register")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
		
		
		
	}
*/
    @PostMapping("/users/register")
    public Status registerUser(@Valid @RequestBody User newUser) {
        List <User> users = userDao.findAll();
        System.out.println("New user: " + newUser.toString());
        for (User user : users) {
            System.out.println("Registered user: " + newUser.toString());
            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return Status.USER_ALREADY_EXISTS;
            }
        }
        userDao.save(newUser);
        return Status.SUCCESS;
    }
    @PostMapping("/users/login")
    public Status loginUser(@Valid @RequestBody User user) {
        List<User> users = userDao.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(true);
                userDao.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    @PostMapping("/users/logout")
    public Status logUserOut(@Valid @RequestBody User user) {
        List<User> users = userDao.findAll();
        for (User other : users) {
            if (other.equals(user)) {
                user.setLoggedIn(false);
                userDao.save(user);
                return Status.SUCCESS;
            }
        }
        return Status.FAILURE;
    }
    @DeleteMapping("/users/all")
    public Status deleteUsers() {
        userDao.deleteAll();
        return Status.SUCCESS;
    }
}
    
    

