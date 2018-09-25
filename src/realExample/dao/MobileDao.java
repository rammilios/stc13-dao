package realExample.dao;

import com.sun.istack.internal.NotNull;
import realExample.ConnectionManager.ConnectionManager;
import realExample.ConnectionManager.ConnectionManagerJdbcImpl;
import realExample.pojo.Mobile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MobileDao implements DAO<Mobile, String> {

    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean create(@NotNull Mobile mobile) {
        boolean result = false;
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                SQLMobile.INSERT.QUERY)) {
            preparedStatement.setString(1, mobile.getModel());
            preparedStatement.setFloat(2, mobile.getPrice());
            preparedStatement.setInt(3, mobile.getManufacturer());
            result = preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Mobile read(@NotNull final Integer id) {
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                SQLMobile.GET.QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Mobile mobile = new Mobile(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getFloat(3),
                        resultSet.getInt(4));
                return mobile;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(@NotNull final Mobile mobile) {

        try (Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                SQLMobile.UPDATE.QUERY)) {
            preparedStatement.setString(1, mobile.getModel());
            preparedStatement.setFloat(2, mobile.getPrice());
            preparedStatement.setInt(3, mobile.getManufacturer());
            preparedStatement.setInt(4, mobile.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(@NotNull final Integer id) {
        try (Connection connection = connectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(
                SQLMobile.DELETE.QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    enum SQLMobile {
        GET("SELECT * FROM mobile WHERE id = ?"),
        INSERT("INSERT INTO mobile values (DEFAULT, ?, ?, ?)"),
        DELETE("DELETE FROM mobile WHERE id=?"),
        UPDATE("UPDATE mobile SET model=?, price=?, manufacturer_id=? " + "WHERE id=?");

        String QUERY;

        SQLMobile(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}