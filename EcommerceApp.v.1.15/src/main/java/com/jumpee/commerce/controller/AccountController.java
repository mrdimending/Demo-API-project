package com.jumpee.commerce.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jumpee.commerce.exception.AccessDeniedException;
import com.jumpee.commerce.exception.NullHandlerException;
import com.jumpee.commerce.model.Details;
import com.jumpee.commerce.model.Password;
import com.jumpee.commerce.model.User;
import com.jumpee.commerce.model.Wallet;
import com.jumpee.commerce.repository.AuthRepository;
import com.jumpee.commerce.repository.UserRepository;
import com.jumpee.commerce.response.AuthResponse;
import com.jumpee.commerce.response.Message;
import com.jumpee.commerce.service.AuthService;
import com.jumpee.commerce.service.DetailsService;
import com.jumpee.commerce.service.UserService;
import com.jumpee.commerce.service.WalletService;
import com.jumpee.commerce.utils.JwtUtils;
import com.jumpee.commerce.view.View;

@RestController
public class AccountController 
{
	@Autowired
	private UserService userService;
	@Autowired
	private AuthService authService;
	@Autowired
	private DetailsService detailsService;
	@Autowired
	private WalletService walletService;
	@Autowired
	private JwtUtils jwtUtils;
	
	private final UserRepository userRepository;
	private String code, message;
	
	AccountController(UserRepository userRepository, AuthRepository authRepository)
	{
		this.userRepository = userRepository;
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> checkAccount(@Valid @RequestBody User checkUser)
	{
		if(userRepository.findByEmail(checkUser.getEmail()) == null)
		{
			throw new NullHandlerException();
		}
		code = jwtUtils.generateJwt(checkUser);
		message = userService.accountValidation(checkUser);
		authService.updateAuth(checkUser, code);
		return ResponseEntity.ok().body(new AuthResponse(code, message));
	}
	@GetMapping("/myaccount")
	@JsonView(View.Base.class)
	public ResponseEntity<?> myAccount(@RequestParam String email, @RequestParam String token) throws Exception
	{
		if (!userService.findUser(email) || !authService.findAuthz(token))
		{
			throw new AccessDeniedException();
		}
		User getUserDetails = userService.getDetails(email);
		authService.checkRelation(getUserDetails, token);
		return ResponseEntity.ok().body(getUserDetails);
	}
	@PostMapping("/myaccount/add-address")
	@JsonView(View.Base.class)
	public ResponseEntity<?> addAddress(@RequestParam String email, @RequestParam String token, @Valid @RequestBody Details details)
	{
		if(!authService.findAuthz(token) || !userService.findUser(email))
		{
			throw new AccessDeniedException();
		}
		User user = userRepository.findByEmail(email);
		detailsService.detailsUpdate(user, details);
		return ResponseEntity.ok().body(user);
	}
	@DeleteMapping("/myaccount/delete-address/{id}")
	public ResponseEntity<?> addrDelete(@PathVariable("id") int id, @RequestParam String token)
	{
		
		int addrId = id;
		if(!authService.findAuthz(token) || detailsService.findById(addrId) == null)
		{
			throw new AccessDeniedException();
		}
		else
		{
			User tokenUserId = authService.findUserByToken(token);
			User detailsUserId = detailsService.findUserByAddrId(addrId);
			if(tokenUserId.equals(detailsUserId))
			{
				message = detailsService.deteleDetailsById(addrId);
				return ResponseEntity.ok().body(new Message(message));
			}
			throw new AccessDeniedException();
		}
	}
	@PutMapping("/myaccount/update-address/{id}")
	public ResponseEntity<?> addrUpdate(@PathVariable("id") int id, @RequestParam String token, @Valid @RequestBody Details details)
	{
		int addrId = id;
		if(!authService.findAuthz(token) || detailsService.findById(addrId) == null)
		{
			throw new AccessDeniedException();
		}
		else
		{
			User tokenUserId = authService.findUserByToken(token);
			User detailsUserId = detailsService.findUserByAddrId(addrId);
			if(tokenUserId.equals(detailsUserId))
			{
				message = detailsService.updateDetailsById(addrId, details);
				return ResponseEntity.ok().body(new Message(message));
			}
			throw new AccessDeniedException();
		}
	}
	@PutMapping("/myaccount/logout")
	public ResponseEntity<?> logOut(@RequestParam String email, @RequestParam String token)
	{
		if(!authService.findAuthz(token) || !userService.findUser(email))
		{
			throw new AccessDeniedException();
		}
		else
		{
			User userId = userService.findByEmail(email);
			User tokenUserId = authService.findUserByToken(token);
			if(userId.equals(tokenUserId))
			{
				message = authService.accountLogout(userId);
				return ResponseEntity.ok().body(new Message(message));
			}
			throw new AccessDeniedException();
		}
	}
	@PostMapping("/myaccount/deposit")
	public ResponseEntity<?> openWallet(@RequestParam String email, @RequestParam String token, @RequestBody Wallet wallet)
	{
		if(!authService.findAuthz(token) || !userService.findUser(email))
		{
			throw new AccessDeniedException();
		}
		else
		{
			User userId = userService.findByEmail(email);
			User tokenUserId = authService.findUserByToken(token);
			if(userId.equals(tokenUserId))
			{
				message = walletService.deposit(userId, wallet);
				return ResponseEntity.ok().body(new Message(message));
			}
			throw new AccessDeniedException();
		}
	}
	@PutMapping("/myaccount/new-password")
	public ResponseEntity<?> changePassword(@RequestParam String email, @RequestParam String token, @RequestBody Password pass)
	{
		if(!authService.findAuthz(token) || !userService.findUser(email))
		{
			throw new AccessDeniedException();
		}
		else
		{
			User userId = userService.findByEmail(email);
			User tokenUserId = authService.findUserByToken(token);
			if(userId.equals(tokenUserId))
			{
				message = userService.newPassword(userId, pass);
				return ResponseEntity.ok().body(new Message(message));
			}
			throw new AccessDeniedException();
		}
	}
}
