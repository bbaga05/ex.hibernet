package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                lastName VARCHAR(255) NOT NULL,
                age INT NOT NULL);
                """;

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(sql);
        } catch (SQLException e){
            System.out.println("Ошибка создания таблицы");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String  sql = "DROP TABLE IF EXISTS users;";

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()){
            statement.execute(sql);
            System.out.println("Таблица удалена.");
        } catch (SQLException e){
            System.out.println("Ошибка удаления таблицы");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User с именем "+ name + " добавлен в таблицу");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT id, name, lastName, age FROM users;";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        } return users;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users;";

        try (Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Юзеры удалены.");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
