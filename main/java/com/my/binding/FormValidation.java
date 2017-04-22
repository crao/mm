package com.my.binding;

import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
import org.springframework.validation.Errors;  
import org.springframework.validation.ValidationUtils;  
import org.springframework.validation.Validator;  
  

  
public class FormValidation implements Validator {  
  
 private Pattern pattern;  
 private Matcher matcher;  
  
 private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
 String ID_PATTERN = "[0-9]+";  
 String STRING_PATTERN = "[a-zA-Z]+";  
 String MOBILE_PATTERN = "[0-9]{10}";  
  

  
 @Override  
 public void validate(Object target, Errors errors) {  
  
 Register register = (Register) target;  
  
 
// input string conatains numeric values only  
  if (register.getMobile() != null) {  
   pattern = Pattern.compile(ID_PATTERN);  
   matcher = pattern.matcher(register.getMobile().toString());  
   if (!matcher.matches()) {  
    errors.rejectValue("Enter a numeric value", MOBILE_PATTERN);  
   }  
  
// input string can not exceed that a limit  
   if (register.getMobile().toString().length() > 5) {  
    errors.rejectValue("Id should not contain more than 5 digits", MOBILE_PATTERN);  
   }  
  }  
  
  ValidationUtils.rejectIfEmpty(errors, "firstName", "required.firstName",  
    "Name is required.");  
  
// input string conatains characters only  
  if (!(register.getFirstName() != null && register.getFirstName().isEmpty())) {  
   pattern = Pattern.compile(STRING_PATTERN);  
   matcher = pattern.matcher(register.getFirstName());  
   if (!matcher.matches()) {  
    errors.rejectValue("Enter a valid name", STRING_PATTERN);  
   }  
  }  
  
//radio buttons validation  
  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender",  
    "required.gender", "Select your gender");  
  
  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email",  
    "required.email", "Email is required.");  
  
// email validation in spring  
  if (!(register.getEmail() != null && register.getEmail().isEmpty())) {  
   pattern = Pattern.compile(EMAIL_PATTERN);  
   matcher = pattern.matcher(register.getEmail());  
   if (!matcher.matches()) {  
    errors.rejectValue("email", "email.incorrect",  
      "Enter a correct email");  
   }  
  }  
  
  
  
// phone number validation  
 
  
  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",  
    "required.password", "Password is required.");  
  
// password matching validation  
  if (!register.getPassword().equals(register.getPassword())) {  
   errors.rejectValue("confirmPassword", "password.mismatch",  
     "Password does not match");  
  }  
  
// drop down list validation  
    
  

  
  // checkbox validation   
  
 }

@Override
public boolean supports(Class<?> clazz) {
	// TODO Auto-generated method stub
	return false;
}  
}  
