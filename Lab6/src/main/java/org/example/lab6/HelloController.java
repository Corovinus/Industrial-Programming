package org.example.lab6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.util.converter.IntegerStringConverter;

import java.util.Comparator;
import java.util.List;

public class HelloController {
    private static ObservableList<Person> peopleData = FXCollections.observableArrayList();;

    @FXML
    private TableColumn<Person, Integer> idColumn, ageColumn;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableView<Person> table;

    @FXML
    public void initialize() {
        // Разрешаем редактирование таблицы
        table.setEditable(true);

        // ID Column (целые числа)
        idColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        idColumn.setOnEditCommit(event -> {
            Person person = event.getRowValue();
            person.setId(event.getNewValue());
            System.out.println("ID изменён: " + person.getId());
        });

        // Name Column (текст)
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            Person person = event.getRowValue();
            person.setName(event.getNewValue());
            System.out.println("Имя изменено: " + person.getName());
        });

        // Age Column (целые числа)
        ageColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ageColumn.setOnEditCommit(event -> {
            Person person = event.getRowValue();
            person.setAge(event.getNewValue());
            System.out.println("Возраст изменён: " + person.getAge());
        });
    }
    @FXML
    protected void onLoadButtonClick() {
        ObservableList<Person> people = DataAccess.getInstance().loadAll();
        peopleData.clear();
        peopleData.addAll(people);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        table.setItems(peopleData);
    }

    @FXML
    protected void onAddButtonClick() {
        Person newPerson = new Person(0, "Новый Человек", 25); // ID = 0
        peopleData.add(newPerson);
        table.setItems(peopleData);
    }

    @FXML
    protected void onSaveButtonClick() {
        DataAccess.getInstance().saveAll(peopleData);
        System.out.println("Изменения сохранены в базе данных.");
    }

    @FXML
    protected void onSortButtonClick() {
        FXCollections.sort(peopleData, Comparator.comparing(Person::getName));
        table.refresh();
        System.out.println("Список отсортирован в UI.");
    }



}