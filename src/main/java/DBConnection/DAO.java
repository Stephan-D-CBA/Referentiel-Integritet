package DBConnection;

import Domain.Department;
import Domain.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAO {

    private DBConnector conn = new DBConnector();
    

    public static List<Employee> getEmployyes() throws SQLException, ClassNotFoundException {
        String SQL = "SELECT * FROM employee";
        
        List<Employee> employees = new ArrayList<>();
        
        try (Connection con = DBConnector.connection();
                ResultSet rs = con.createStatement().executeQuery( SQL )) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setId( rs.getInt("emp_id") );
                e.setName( rs.getString("emp_name") );
                e.setTitle( rs.getString("emp_title") );
                e.setDepartments(getDepartments( rs.getInt("emp_id") ));
                employees.add( e );
            }
        }
        return employees;
    }
    
    public static List<Department> getDepartments(int id) throws SQLException, ClassNotFoundException {
        String SQL 
                = "SELECT * FROM department "
                + "JOIN employee ON department.dept_id = employee.FK_dept_id "
                + "WHERE employee.emp_id = ?";
        
        List<Department> departments = new ArrayList<>();
        
        try (Connection con = DBConnector.connection();
                PreparedStatement pStmt = con.prepareStatement(SQL) ) {
            
                pStmt.setInt(1, id);
                ResultSet rs = pStmt.executeQuery();
                while (rs.next()) {
                    Department d = new Department();
                    d.setId( rs.getInt("dept_id") );
                    d.setName( rs.getString("dept_name") );
                    d.setManager( rs.getInt("manager") );
                    d.setEmployees( getEmployees( rs.getInt("dept_id") ));
                    departments.add( d );
                }
        }
        return departments;   
    }
    
    public static List<Employee> getEmployees(int id) throws SQLException, ClassNotFoundException {
        String SQL 
                = "SELECT * FROM department "
                + "JOIN employee ON department.dept_id = employee.FK_dept_id "
                + "WHERE employee.emp_id = ?";
        
        List<Employee> employees = new ArrayList<>();
        
        try (Connection con = DBConnector.connection();
                PreparedStatement pStmt = con.prepareStatement(SQL) ) {
            
                pStmt.setInt(1, id);
                ResultSet rs = pStmt.executeQuery();
                while (rs.next()) {
                    Employee e = new Employee();
                    e.setId( rs.getInt("dept_id") );
                    e.setName( rs.getString("dept_name") );
                    e.setTitle( rs.getString("emp_title") );
                    e.setDepartments( getDepartments(e.getId()) );
                    employees.add( e );
                }
        }
        return employees;   
    }
    
    public static void createEmployee(String name, String title, int fKey) throws SQLException, ClassNotFoundException {
        String SQL 
                = "INSERT INTO employee "
                + "(emp_name, emp_title, FK_dept_id) values (?, ?, ?)";
        
        try (Connection con = DBConnector.connection();
                PreparedStatement pStmt = con.prepareStatement(SQL) ) {
                pStmt.setString(1, name);
                pStmt.setString(2, title);
                pStmt.setInt(3, fKey);
                pStmt.executeUpdate();
        }
    }
}


   

