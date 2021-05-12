import java.sql.*;

public class Hello {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://yikang.pub:3306/test",
                "root", "asdf;lkj");) {
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
