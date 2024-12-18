package org.example.lab6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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

    public ObservableList<Person> loadAll() {
        ObservableList<Person> people = FXCollections.observableArrayList();
        String query = "SELECT Id, Name, Age FROM people ORDER BY sort_order ASC";

        try (Connection connection = DataBaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                people.add(new Person(rs.getInt("Id"), rs.getString("Name"), rs.getInt("Age")));
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText("Операция выполнена успешно");
            alert.setContentText("Всё прошло без ошибок.");

            // Отображаем alert и ждём, пока пользователь нажмёт кнопку "ОК"
            alert.showAndWait();

        } catch (SQLException e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ой");
            alert.setHeaderText("Операция выполнена провально...");
            alert.setContentText("Ошибка при загрузке данных " + e.getMessage());

            // Отображаем alert и ждём, пока пользователь нажмёт кнопку "ОК"
            alert.showAndWait();
            e.printStackTrace();
        }

        return people;
    }

    public void addPerson(Person person) {
        String insertQuery = "INSERT INTO people (ID,Name, Age) VALUES (?, ?, ?)";
        try (Connection connection = DataBaseConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, person.getId());
            preparedStatement.setString(2, person.getName());
            preparedStatement.setInt(3, person.getAge());
            preparedStatement.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText("Операция выполнена успешно");
            alert.setContentText("Всё прошло без ошибок.");

            // Отображаем alert и ждём, пока пользователь нажмёт кнопку "ОК"
            alert.showAndWait();
            System.out.println("Person добавлен: " + person.getName());
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ой");
            alert.setHeaderText("Операция выполнена провально...");
            alert.setContentText("Ошибка при добавлении Person: " + e.getMessage());

            System.out.println("Ошибка при добавлении Person: " + e.getMessage());
        }
    }

    public void saveAll(ObservableList<Person> people) {
        String updateQuery = "UPDATE people SET Name = ?, Age = ?, sort_order = ? WHERE Id = ?";
        String insertQuery = "INSERT INTO people (Name, Age, sort_order) VALUES (?, ?, ?)";

        try (Connection connection = DataBaseConnection.getInstance().getConnection();
             PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
             PreparedStatement insertStmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            connection.setAutoCommit(false);

            for (int i = 0; i < people.size(); i++) {
                Person p = people.get(i);
                int currentSortOrder = i + 1; // Порядок в списке

                if (p.getId() > 0) {
                    // Обновляем существующую запись
                    updateStmt.setString(1, p.getName());
                    updateStmt.setInt(2, p.getAge());
                    updateStmt.setInt(3, currentSortOrder);
                    updateStmt.setInt(4, p.getId());
                    updateStmt.executeUpdate();
                } else {
                    // Добавляем новую запись
                    insertStmt.setString(1, p.getName());
                    insertStmt.setInt(2, p.getAge());
                    insertStmt.setInt(3, currentSortOrder);
                    insertStmt.executeUpdate();

                    try (ResultSet generatedKeys = insertStmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            p.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }

            connection.commit();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успех");
            alert.setHeaderText("Операция выполнена успешно");
            alert.setContentText("Всё прошло без ошибок.");

            // Отображаем alert и ждём, пока пользователь нажмёт кнопку "ОК"
            alert.showAndWait();
            System.out.println("Все изменения сохранены с учетом сортировки.");

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ой");
            alert.setHeaderText("Операция выполнена провально...");
            alert.setContentText("Ошибка при сохранении: " + e.getMessage());

            System.out.println("Ошибка при сохранении: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Person buildPerson(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        int age = resultSet.getInt("Age");
        return new Person(id, name, age);
    }
}
