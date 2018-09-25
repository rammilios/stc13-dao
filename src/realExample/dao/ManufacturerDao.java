package realExample.dao;

import com.sun.istack.internal.NotNull;
import realExample.ConnectionManager.ConnectionManager;
import realExample.ConnectionManager.ConnectionManagerJdbcImpl;
import realExample.pojo.Manufacturer;
import realExample.pojo.Mobile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerDao implements DAO<Manufacturer, String> {

    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean create(Manufacturer manufacturer) {
        boolean result = false;
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     ManufacturerDao.SQLMobile.INSERT.QUERY)) {
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getCountry());
            result = preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Manufacturer read(final Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     ManufacturerDao.SQLMobile.GET.QUERY)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
               Manufacturer manufacturer = new Manufacturer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
                return manufacturer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(final Manufacturer manufacturer) {

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     ManufacturerDao.SQLMobile.UPDATE.QUERY)) {
            preparedStatement.setString(1, manufacturer.getName());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.setInt(3, manufacturer.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(final Integer id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     ManufacturerDao.SQLMobile.DELETE.QUERY)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    enum SQLMobile {
        GET("SELECT * FROM manufacturer WHERE id = ?"),
        INSERT("INSERT INTO manufacturer values (DEFAULT, ?, ?)"),
        DELETE("DELETE FROM manufacturer WHERE id=?"),
        UPDATE("UPDATE manufacturer SET name=?, country=?" + "WHERE id=?");

        String QUERY;

        SQLMobile(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
