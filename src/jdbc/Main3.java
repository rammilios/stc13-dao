package jdbc;

import java.sql.*;

public class Main3 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/mobile",
                "postgres",
                "Melisa123");

        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE mobile SET price=5000 WHERE id = ?");
        for (String arg : args) {
            preparedStatement.setInt(1, Integer.parseInt(arg));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.close();
    }
}
