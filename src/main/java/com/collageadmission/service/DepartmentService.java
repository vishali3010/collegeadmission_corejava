package com.collageadmission.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.collageadmission.dao.DepartmentDAO;
import com.collageadmission.exception.DAOException;
import com.collageadmission.exception.InvalidDepartmentException;
import com.collageadmission.exception.InvalidStudentException;
import com.collageadmission.model.Department;
import com.collageadmission.model.Student;
import com.collageadmission.validator.DepartmentValidator;

public class DepartmentService {
	

		public DepartmentService() {
//			private constructor
		}

		public static boolean addDepartment(Department department) throws InvalidDepartmentException, SQLException {
			if (DepartmentValidator.validateDepartment(department)) {
				DepartmentDAO.addDepartment(department);
			}
			return true;

		}

		public static boolean listAllDepartment() throws DAOException, SQLException {
			DepartmentDAO.getALlDepartment();
			return true;
		}

		public static boolean updateDepartment(Department department, int id) throws InvalidDepartmentException, DAOException, SQLException {
			if (DepartmentValidator.validateDepartment(department) && DepartmentValidator.validateId(id)) {
				DepartmentDAO.updateDepartment(department, id);
			}
			return true;

			
		}

		public static boolean removeDepartment(int id) throws InvalidDepartmentException, DAOException, SQLException {
			if (DepartmentValidator.validateId(id)) {
				DepartmentDAO.removeDepartment(id);
			}
			return true;
		}

		public static List<Department> findDepartmentByName(String name)
				throws InvalidDepartmentException, DAOException, SQLException, InvalidStudentException {
			List<Department> departmentList = new ArrayList<>();
			if (DepartmentValidator.validateDepartmentName(name)) {
				DepartmentDAO.findDepartmentByName(name);
			}
			return departmentList;

		}

	}


