package org.example.lab6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

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
    protected void onLoadButtonClick() {
        List<Person> people = DataAccess.getInstance().findAll();
        for (Person p : people) {
            peopleData.add(new Person(p.getId(), p.getName(), p.getAge()));
        }

        idColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("Name"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<Person, Integer>("Age"));

        table.setItems(peopleData);
    }
    @FXML
    protected void onAddButtonClick() {
        Person newPerson = new Person(0, "Новый Человек", 25);
        DataAccess.getInstance().addPerson(newPerson);

        peopleData.add(newPerson);
        table.setItems(peopleData);
    }
    @FXML
    protected void onSaveButtonClick(){
        peopleData = table.getItems();
        DataAccess.getInstance().saveAll(peopleData);
    }
}