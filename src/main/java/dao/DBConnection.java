package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection CONNECTION;

    public static Connection getConnection() {
        if (CONNECTION != null) {
            return CONNECTION;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // load the driver
            CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowMultiQueries=true", "root", "password");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return CONNECTION;
    }

    public static void closeConnection() {
        if (CONNECTION != null) {
            try {
                CONNECTION.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
