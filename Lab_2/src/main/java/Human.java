import java.time.LocalDate;

public class Human {
    private final int age;
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;
    private final int weight;

    public Human(int age, String firstName, String lastName, LocalDate birthDate, int weight) {
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.weight =weight;
    }
    public int getAge() {
        return this.age;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public LocalDate getBirthDate() {
        return this.birthDate;
    }
    public int getWeight() {
        return this.weight;
    }
}
