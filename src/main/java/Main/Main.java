package Main;

import DBConnection.DAO;
import Domain.Department;
import Domain.Employee;
import java.sql.SQLException;

/**
 *
 * @author super
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        DAO.createEmployee("Stephan", "Forviret", 1);
        
        for (Employee e : DAO.getEmployyes()) {
            System.out.println(e);
            for (Department d : DAO.getDepartments(e.getId())) {
                System.out.println(d);
            }
        }
    }
    
}
