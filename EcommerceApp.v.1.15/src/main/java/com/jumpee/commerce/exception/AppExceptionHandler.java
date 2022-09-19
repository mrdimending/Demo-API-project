package com.jumpee.commerce.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler
{
	ErrorHandler error = new ErrorHandler();
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InternalServerError.class)
	public ResponseEntity<ErrorHandler> handleInternalServerError(InternalServerError exception, WebRequest webRequest)
	{
		error.setCode(505);
		error.setMessage("Oops..Something went wrong!");
		error.setError("Internal Server Error");
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(TokenHandlerException.class)
	public ResponseEntity<ErrorHandler> handleTokenHandlerException(TokenHandlerException exception, WebRequest webRequest)
	{
		error.setCode(400);
		error.setMessage("Invalid Token!");
		error.setError("Bad Request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<ErrorHandler> handleInsufficientBalanceException(InsufficientBalanceException exception, WebRequest webRequest)
	{
		error.setCode(400);
		error.setMessage("You do not have enough balance in your account to make this transaction");
		error.setError("Bad Request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorHandler> handleAccessDeniedException (AccessDeniedException exception, WebRequest webRequest)
	{
		error.setCode(401);
		error.setMessage("Access Denied! You need to create an account to access this feat");
		error.setError("Unauthorized");
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
		
	}
	@ExceptionHandler(NotFoundHandler.class)
	public ResponseEntity<ErrorHandler> handleNotFoundHandler(NotFoundHandler exception, WebRequest webRequest)
	{
		error.setCode(404);
		error.setMessage("Email or Password is incorrect!");
		error.setError("Not Found");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(ShippingNotFound.class)
	public ResponseEntity<ErrorHandler> handleShippingNotFound(ShippingNotFound exception, WebRequest webRequest)
	{
		error.setCode(404);
		error.setMessage("Please add shipping details");
		error.setError("Not Found");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ErrorHandler> handleNullPointerException(NullPointerException exception, WebRequest webRequest)
	{
		ErrorHandler error = new ErrorHandler();
		error.setCode(404);
		error.setMessage("Please input valid value");
		error.setError("Not Found");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<ErrorHandler> handleItemNotFoundException(ItemNotFoundException exception, WebRequest webRequest)
	{
		ErrorHandler error = new ErrorHandler();
		error.setCode(404);
		error.setMessage("No product were found matching your selection..");
		error.setError("Not Found");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorHandler> handleProductNotFoundException(ProductNotFoundException exception, WebRequest webRequest)
	{
		ErrorHandler error = new ErrorHandler();
		error.setCode(404);
		error.setMessage("No product matched your search");
		error.setError("Not Found");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(AuthHandlerException.class)
	public ResponseEntity<ErrorHandler> handleAuthHandlerException(AuthHandlerException exception, WebRequest webRequest)
	{
		error.setCode(401);
		error.setMessage("Account not verified");
		error.setError("Unauthorized");
		return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
		
	}
	@ExceptionHandler(NullHandlerException.class)
	public ResponseEntity<ErrorHandler> handleNullHandlerException(NullHandlerException exception, WebRequest webRequest)
	{
		error.setCode(404);
		error.setMessage("Please input valid value");
		error.setError("Not Found");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<ErrorHandler> handleTransactionNotFoundException(TransactionNotFoundException exception, WebRequest webRequest)
	{
		error.setCode(404);
		error.setMessage("Transaction Empty");
		error.setError("Not Found");
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(EmailCheckException.class)
	public ResponseEntity<ErrorHandler> handleEmailCheckException(EmailCheckException exception, WebRequest webRequest)
	{
		error.setCode(409);
		error.setMessage("Email already taken!");
		error.setError("Conflict");
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		
	}
	@ExceptionHandler(StockHandlerException.class)
	public ResponseEntity<ErrorHandler> handleStockHandlerException(StockHandlerException exception, WebRequest webRequest)
	{
		error.setCode(409);
		error.setMessage("Out of stock");
		error.setError("Conflict");
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
		
	}
	@ExceptionHandler(AuthCheckerHandler.class)
	public ResponseEntity<ErrorHandler> handleAuthCheckerHandler(AuthCheckerHandler exception, WebRequest webRequest)
	{
		error.setCode(400);
		error.setMessage("Invalid Request!");
		error.setError("Bad Request");
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		
	}
	
	
}