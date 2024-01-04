package com.collageadmission.validator;

import java.sql.SQLException;
import java.util.regex.Pattern;

import com.collageadmission.errors.DepartmentErrors;
import com.collageadmission.exception.InvalidDepartmentException;
import com.collageadmission.model.Department;


public class DepartmentValidator {
	
	private DepartmentValidator() {
		
	}
	

	
	
	public static boolean validateDepartment(Department department) throws InvalidDepartmentException , SQLException{
		if (department == null) {
			throw new InvalidDepartmentException(DepartmentErrors.INVALID_DEPARTMENT);
		}
		try {
			validateDepartmentName(department.getName());
			validateDepartmentName(department.getName());
			validateId(department.getId());
			
			
		} catch (InvalidDepartmentException e) {
			throw new InvalidDepartmentException(DepartmentErrors.INVALID_DEPARTMENT);
		}

		return true;
	}
	
	
	public static boolean validateDepartmentName(String name) throws InvalidDepartmentException,SQLException {
		String regex = "^[A-Za-z]{2,30}$";
		boolean matches = Pattern.compile(regex).matcher(name).matches();
		if (name  == null || !matches) {
			throw new InvalidDepartmentException(DepartmentErrors.INVALID_NAME);

		}
		return true;
		
	}
	
	public static boolean validateId(int id) throws InvalidDepartmentException,SQLException {
		if (id <= 0) {
			throw new InvalidDepartmentException(DepartmentErrors.INVALID_ID);
		}
		return true;
	}
}
