package com.canto.happy.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationService {

	public Map<String, String> validate(BindingResult result) {
		if (result.hasErrors()) {
			return result.getFieldErrors().stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
		}
		return null;
	}
}
