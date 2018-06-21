package human;

import interfaces.Executable;

//5. *Создайте класс Student, производный от Human, новое поле — название факультета
public class Student extends Human implements Executable {
    // название факультета
    private String faculty;

    public Student(Human human, String faculty) {
        super(human);
        this.faculty = faculty;
    }
//
    public Student(String surname, String name, String patronymic, int age, String faculty) {
        super(surname, name, patronymic, age);
        this.faculty = faculty;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public void execute() {
        System.out.println("Student = " + faculty);
    }
}
