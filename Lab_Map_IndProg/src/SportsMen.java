import java.util.Map;

public class SportsMen {
    private String name;
    private String surname;
    private String diploma;
    private String category;
    public SportsMen(String name, String surname, String diploma, String category) {
        this.name = name;
        this.surname = surname;
        this.diploma = diploma;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDiploma() {
        return diploma;
    }

    public String getCategory() {
        return category;
    }
    @Override
    public String toString() {
        return name + " " + surname + " (" + diploma + ") " + category;
    }
}