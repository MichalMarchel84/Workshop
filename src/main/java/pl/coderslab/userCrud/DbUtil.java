package pl.coderslab.userCrud;

import org.mindrot.jbcrypt.BCrypt;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    public static final String MAIN_DB = "workshop2";
    public static final String TEST_DB = "workshop2_test";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/%s?useSSL=false&characterEncoding=utf8&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String pass = "coderslab";

    private static DataSource dataSource;

    public static Connection connect() throws SQLException {
        return getInstance().getConnection();
    }

    public static Connection connect(String db) throws SQLException{
        return connect();
    }

    private static DataSource getInstance() {
        if (dataSource == null) {
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/users");
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }

    public static String hashPasswd(String passwd) {
        return BCrypt.hashpw(passwd, BCrypt.gensalt(10));
    }

    public static boolean verifyPasswd(String passwd, String hash) {
        return BCrypt.checkpw(passwd, hash);
    }

}