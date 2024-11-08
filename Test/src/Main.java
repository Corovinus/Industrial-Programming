public class Main {
    public static void main(String[] args) {

        Animal animal = new vorobey();
        animal.eat();
        animal.sound();

    }
}

interface Animal {
    void eat();
    void sound();
}

class pig implements Animal {
    public void eat() {
        System.out.println("Морковь");
    }
    public void sound() {
        System.out.println("Хрю");
    }
}

interface bird extends Animal {
    void sound();
}
class chicken implements bird {
    public void eat() {
        System.out.println("Семки");
    }
    public void sound() {
        System.out.println("Ко-ко");
    }
}
class vorobey implements bird {
    public void eat() {
        System.out.println("хлэб");
    }
    public void sound() {
        System.out.println("Чирик");
    }
}