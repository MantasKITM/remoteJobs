import java.sql.*;

public class CreateDefaultDAO {

    private final String URL = "jdbc:mysql://localhost:3306/jobs";
    private final String user = "root";
    private final String passwd = "";
    private Connection conn;
    private Statement stmt;
    private PreparedStatement prstmt;
    private String SQL;

    public void createTableSet() {
        try {
            conn = DriverManager.getConnection(URL, user, passwd);
            stmt = conn.createStatement();
            SQL = "CREATE TABLE IF NOT EXISTS categories (category_id INT PRIMARY KEY AUTO_INCREMENT, category_name TEXT)";
            stmt.execute(SQL);
            System.out.println("Lentelė sukurta sėkmingai...");
            SQL = "CREATE TABLE IF NOT EXISTS companies (company_id INT PRIMARY KEY AUTO_INCREMENT, company_name TEXT, description TEXT, hq TEXT, average_salary TEXT)";
            stmt.execute(SQL);
            System.out.println("Lentelė sukurta sėkmingai...");
            SQL = "CREATE TABLE IF NOT EXISTS jobs (" +
                    "job_id INT PRIMARY KEY AUTO_INCREMENT, job_title TEXT, company_id INT, category_id INT, salary TEXT, requirements TEXT, description TEXT, location TEXT, " +
                    "FOREIGN KEY (company_id) REFERENCES companies(company_id), FOREIGN KEY (category_id) REFERENCES categories (category_id))";
            stmt.execute(SQL);
            System.out.println("Lentelė sukurta sėkmingai...");
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
            System.out.println("Nuo duomenų bazės atsijungta.");
        }
    }

    public void createDefaultBase() {
        try {
            int updateCount;
            conn = DriverManager.getConnection(URL, user, passwd);
            stmt = conn.createStatement();
            SQL = "INSERT INTO categories (category_name) VALUES ('Software developer')";
            updateCount = stmt.executeUpdate(SQL);

            System.out.println("Į lentelę įvesta įrašų:" + updateCount);
            SQL = "INSERT INTO companies (company_name, description, hq, average_salary) VALUES ('Telia', 'Telia Company AB is a " +
                    "Swedish multinational telecommunications company and mobile network operator', 'Stockholm, Sweden', '90 000 EUR'), " +
                    "('Bitė Lietuva', 'Telekomunikacijų bendrovė, teikianti integruotas mobiliojo ryšio, duomenų perdavimo, interneto ir " +
                    "išmaniosios televizijos paslaugas Lietuvoje.', 'Vilnius, Lithuania', '70 000 EUR'), ('Cloudlix', 'Ltd. “Cloudlix” is a " +
                    "high quality hosting provider offering superb solutions since 2011 year and constantly growing.', 'Vilnius, Lithuania', " +
                    "'65 000 EUR'), ('OVH', 'VH is a French cloud computing company that offers VPS, dedicated servers and other web services.', " +
                    "'Roubaix, France', '120 000 EUR'), ('SEB Group', 'SEB, is a Swedish financial group for corporate customers, institutions and " +
                    "private individuals with headquarters in Stockholm.', 'Stockholm, Sweden', '100 000 EUR')";
            updateCount = stmt.executeUpdate(SQL);


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createDefaultEntry(String jobTitle, int companyId, int categoryId, String salary, String requirements, String description, String location) {
        try {
            conn = DriverManager.getConnection(URL, user, passwd);
            prstmt = conn.prepareStatement("INSERT INTO jobs (job_title, company_id, category_id, salary, requirements, description, location) VALUES (?,?,?,?,?,?,?)");
            prstmt.setString(1, jobTitle);
            prstmt.setInt(2, companyId);
            prstmt.setInt(3, categoryId);
            prstmt.setString(4, salary);
            prstmt.setString(5, requirements);
            prstmt.setString(6, description);
            prstmt.setString(7, location);
            prstmt.execute();
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
