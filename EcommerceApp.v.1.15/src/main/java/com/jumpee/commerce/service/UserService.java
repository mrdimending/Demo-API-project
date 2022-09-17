package com.jumpee.commerce.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jumpee.commerce.repository.RoleRepository;
import com.jumpee.commerce.repository.UserRepository;
import com.jumpee.commerce.exception.AuthHandlerException;
import com.jumpee.commerce.exception.NotFoundHandler;
import com.jumpee.commerce.mail.MailService;
import com.jumpee.commerce.model.Password;
import com.jumpee.commerce.model.Role;
import com.jumpee.commerce.model.User;
@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private MailService mailService;
	
	public User findById(int id)
	{
		return userRepository.findById(id);
	}

	public User findByEmail(String email) 
	{
		return userRepository.findByEmail(email);
	}
	
	public User createUser(User newUser, String token)
	{
		
		String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
		//String token = RandomString.make(5);
		String subject = "Code Verification";
		String messageBody = "<div class=\"mail-header\" style=\"text-align: center; \">\r\n"
				+ "	<h2 style=\"letter-spacing: 1em\">THANKS FOR SIGNING UP !</h2>\r\n"
				+ "	<p>You're almost ready to get started. Please copy the code below and paste it to verify your account!</p>\r\n"
				+ "	<div class=\"mail-code\" style=\"background-color: #f2f2f2; margin:auto;width: 50%;border: 3px solid #black;padding: 5px; letter-spacing: 1em\">\r\n"
				+ "		<h2>"+token+"</h2>\r\n"
				+ "	</div>\r\n"
				+ "</div>";
		newUser.setPassword(encodedPassword);
		this.mailService.sendEmail(newUser.getEmail(), subject, messageBody);
		return userRepository.save(newUser);
	}

	public User userActivate(User user) 
	{
		User emailExists = userRepository.findByEmail(user.getEmail());
		Role roleExists = roleRepository.findById(-1);
		if(emailExists == null)
		{
			throw new NotFoundHandler();
		}
		emailExists.setActive(true);
		emailExists.setRoles(roleExists);
		return userRepository.save(emailExists);
	}

	public String accountValidation(@Valid User checkUser) 
	{
		User accountExist = userRepository.findByEmail(checkUser.getEmail());
		boolean password = bCryptPasswordEncoder.matches(checkUser.getPassword(), accountExist.getPassword());
		if(accountExist.getEmail() != null && password == true)
		{
			if(!(accountExist.isActive()))
			{
				throw new AuthHandlerException();
			}
			
			return "Welcome to Jumpee, "+accountExist.getFirst_name();
		}
		throw new NotFoundHandler();
	}

	public boolean findUser(String user) 
	{
		User getEmail = userRepository.findByEmail(user);
		
		if(getEmail == null)
		{
			return false;
		}
		return true;
	}

	public User getDetails(String email) 
	{
		User accountExist = userRepository.findByEmail(email);
		return accountExist;
	}

	public String newPassword(User userId, Password pass) 
	{
		boolean password = bCryptPasswordEncoder.matches(pass.getOldPassword(), userId.getPassword());
		if (password == true) 
		{
			if (pass.getNewPassword().equals(pass.getConfirmPassword())) 
			{
				String newPass = bCryptPasswordEncoder.encode(pass.getNewPassword());
				userId.setPassword(newPass);
				userRepository.save(userId);
			}
			return "Password and Confirm password not match";
		}
		return "Old password is incorrect";
	}

}


