package com.collageadmission.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.collageadmission.exception.DAOException;
import com.collageadmission.exception.InvalidDepartmentException;
import com.collageadmission.exception.InvalidStudentException;
import com.collageadmission.model.Department;
import com.collageadmission.model.Student;
import com.collageadmission.util.ConnectionUtil;
import com.collageadmission.util.Logger;
import com.collageadmission.validator.DepartmentValidator;

public class DepartmentDAO {


	public static List<Department> getALlDepartment() throws DAOException, SQLException {
		List<Department> departmentList = new ArrayList<>();

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "SELECT * FROM Departments;";
			try (Statement statement = connection.createStatement()) {

				try (ResultSet resultSet = statement.executeQuery(query)) {

					while (resultSet.next()) {
						Department department = new Department();
						
						department.setId(resultSet.getInt("id"));
						department.setName( resultSet.getString("dept_name"));
						departmentList.add(department);
					}
				}
			} catch (SQLException e) {

				e.printStackTrace();
				throw new DAOException("No Department found");
			}

		}
		return departmentList;

	}
	
	public static void addDepartment(Department department) throws InvalidDepartmentException, SQLException {
		try {
			DepartmentValidator.validateDepartment(department);
		} catch (InvalidDepartmentException e) {
			e.printStackTrace();
			throw new InvalidDepartmentException("Invalid department", e);
		}

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query2 = "INSERT INTO departments(id,dept_name) VALUES (102,MECH);";
			String query3 = "INSERT INTO departments(id,dept_name) VALUES (103,EEE);";
			String query4 = "INSERT INTO departments(id,dept_name) VALUES (104,ECE);";
			String query5 = "INSERT INTO departments(id,dept_name) VALUES (105,IT);";
			try (Statement statement = connection.createStatement()) {

				statement.addBatch(query2);
				statement.addBatch(query3);
				statement.addBatch(query4);
				statement.addBatch(query5);

				int[] rows = statement.executeBatch();

				Logger.info("added");

				for (int row : rows) {

					if (row <= 0) {
					    connection.rollback();
					}

				}
				connection.commit();

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new InvalidDepartmentException("Cannot add a new department");
		}
	}

	public static boolean updateDepartment(Department department, int id)
			throws DAOException, InvalidDepartmentException, SQLException {
		DepartmentValidator.validateDepartment(department);

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "UPDATE departments SET dept_name = ? WHERE id = ?";

			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setString(1, department.getName());
				pst.setInt(2, id);

				int rows = pst.executeUpdate();

				return (rows > 0);

			}

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}

	public static boolean removeDepartment(int id) throws DAOException, InvalidDepartmentException, SQLException {

		DepartmentValidator.validateId(id);

		try (Connection connection = ConnectionUtil.getConnection()) {
			String query = "DELETE FROM departments WHERE id = ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {

				pst.setInt(1, id);

				int rows = pst.executeUpdate();

				return (rows > 0) ;
			}
		} catch (SQLException e) {

			e.printStackTrace();
			throw new DAOException(e.getMessage());
		}

	}
	
	
	
	public static List<Department> findDepartmentByName(String name)
			throws DAOException, SQLException, InvalidDepartmentException {

		List<Department> departmentList = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection()) {

			String query = "SELECT * FROM departments WHERE dept_name  LIKE ?";
			try (PreparedStatement pst = connection.prepareStatement(query)) {
				pst.setString(1, "%" + name + "%");

				try (ResultSet resultSet = pst.executeQuery()) {
					while (resultSet.next()) {
						Department department = new Department();
						
						department.setId(resultSet.getInt("id"));
						department.setName( resultSet.getString("dept_name"));
						departmentList.add(department);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException(e.getMessage());
			}
		}
		return departmentList;
	}
}
