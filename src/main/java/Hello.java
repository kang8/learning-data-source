import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;

public class Hello {
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://yikang.pub:3306/test");
        config.setUsername("root");
        config.setPassword("asdf;lkj");
        config.addDataSourceProperty("connectionTimeout", "1000");
        config.addDataSourceProperty("idleTimeout", "60000");
        config.addDataSourceProperty("maximumPoolSize", "10");

        DataSource ds = new HikariDataSource(config);
        try (Connection connection = ds.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("SELECT * FROM task")) {
                    while (resultSet.next()) {
                        final int id = resultSet.getInt(1);
                        final int computed = resultSet.getInt(2);
                        System.out.println(id + "  " + computed);
                    }
                }
            }
        } catch (SQLException sqlException) {
            System.out.printf(sqlException.getMessage());
        }
    }

}
