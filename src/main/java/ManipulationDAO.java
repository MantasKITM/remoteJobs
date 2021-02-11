import javax.xml.transform.Result;
import java.sql.*;

public class ManipulationDAO {

    private final String URL = "jdbc:mysql://localhost:3306/jobs";
    private final String user = "root";
    private final String passwd = "";
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private String SQL;
    private ResultSet rs;

    public void read() {
        try {
            conn = DriverManager.getConnection(URL, user, passwd);
            stmt = conn.createStatement();
            SQL = "SELECT job_id, job_title, company_name, requirements, salary, location FROM jobs " +
                    "JOIN companies ON jobs.company_id=companies.company_id " +
                    "JOIN categories ON jobs.category_id=categories.category_id " +
                    "ORDER BY job_id ASC";
            rs = stmt.executeQuery(SQL);
            System.out.println("----- Aktyvūs darbo skelbimai:");
            while (rs.next()) {
                int job_id = rs.getInt(1);
                String job_title = rs.getString(2);
                String company_name = rs.getString(3);
                String req = rs.getString(4);
                String salary = rs.getString(5);
                String location = rs.getString(6);

                System.out.println("<<< Darbo skelbimas nr. " + job_id + " >>>");
                System.out.println("Pareigos: " + job_title + " --- Įmonė: " + company_name + " --- Darbo vieta: " + location);
                System.out.println("--- Reikalavimai: " + req);
                System.out.println("----- Altyginimas: " + salary);
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void search(String string) {
        string = "%" + string + "%";
        try {
            conn = DriverManager.getConnection(URL, user, passwd);
            SQL = "SELECT job_id, job_title, company_name, requirements, salary, location FROM jobs " +
                    "JOIN companies ON jobs.company_id=companies.company_id " +
                    "JOIN categories ON jobs.category_id=categories.category_id " +
                    "WHERE requirements LIKE ? " +
                    "OR job_title LIKE ? " +
                    "OR company_name LIKE ? " +
                    "OR location LIKE ? " +
                    "ORDER BY job_id ASC";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, string);
            pstmt.setString(2, string);
            pstmt.setString(3, string);
            pstmt.setString(4, string);
            rs = pstmt.executeQuery();

            System.out.println("----- Rasti darbo skelbimai:");
            while (rs.next()) {
                int job_id = rs.getInt(1);
                String job_title = rs.getString(2);
                String company_name = rs.getString(3);
                String req = rs.getString(4);
                String salary = rs.getString(5);
                String location = rs.getString(6);

                System.out.println("<<< Darbo skelbimas nr. " + job_id + " >>>");
                System.out.println("Pareigos: " + job_title + " --- Įmonė: " + company_name + " --- Darbo vieta: " + location);
                System.out.println("--- Reikalavimai: " + req);
                System.out.println("----- Altyginimas: " + salary);
                System.out.println();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
