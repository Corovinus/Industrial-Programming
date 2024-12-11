package org.example.lab6;

import javafx.collections.ObservableList;
import javafx.scene.text.Text;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataAccess {
    private static final DataAccess INSTANCE = new DataAccess();
    public static DataAccess getInstance(){
        return INSTANCE;
    }

    public List<Person> findAll(){
        try(Connection connection = DataBaseConnection.getInstance().getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM people");
            List<Person> ans = new ArrayList<>();
            while(resultSet.next()){
                ans.add(buildPerson(resultSet));
            }
            return ans;
        }catch (SQLException e){
            System.out.println(e.getErrorCode());
            return null;
        }
    }
    public void addPerson(Person person) {
        String insertQuery = "INSERT INTO people (ID,Name, Age) VALUES (?, ?, ?)";
        try (Connection connection = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(0, person.getId());
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.executeUpdate();

            System.out.println("Person добавлен: " + person.getName());
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении Person: " + e.getMessage());
        }
    }

    public void saveAll(ObservableList<Person> people) {
        String updateQuery = "UPDATE people SET Name = ?, Age = ? WHERE Id = ?";
        try (Connection connection = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            if (connection != null) {
                System.out.println("Соединение с базой данных успешно установлено.");
            }

            connection.setAutoCommit(false);  // отключаем автокоммит для управления вручную

            for (Person p : people) {
                if (p.getId() <= 0) {
                    System.out.println("Некорректный ID для Person: " + p.getId());
                    continue;  // пропускаем некорректные записи
                }

                preparedStatement.setString(1, p.getName());
                preparedStatement.setInt(2, p.getAge());
                preparedStatement.setInt(3, p.getId());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Person с ID " + p.getId() + " успешно обновлен.");
                } else {
                    System.out.println("Не удалось обновить Person с ID " + p.getId());
                }
            }

            connection.commit();  // явный коммит
            System.out.println("Все изменения сохранены в базе данных.");
        } catch (SQLException e) {
            System.out.println("Ошибка при сохранении всех данных: " + e.getMessage());
        }
    }



    private Person buildPerson(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        int age = resultSet.getInt("Age");
        return new Person(id, name, age);
    }
}
