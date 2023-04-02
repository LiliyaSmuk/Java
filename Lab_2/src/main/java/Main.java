import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        var h1 = new Human(20, "Лиля", "Смук", LocalDate.of(2002, 6, 7), 60);
        var h2 = new Human(16, "Наташа", "Андреева", LocalDate.of(2007, 3, 4), 50);
        var h3 = new Human(60, "Левон", "Мартиросян", LocalDate.of(1963, 2, 12), 100);
        var h4 = new Human(90, "Лаврентий", "Кузьмин", LocalDate.of(1933, 4, 1), 75);
        var allHumans = List.of(h1, h2, h3, h4);
        for (Human h: allHumans){
            System.out.println("Фамилия: " +h.getLastName()+"; Имя: "+h.getFirstName()+"; Возвраст:"+h.getAge()+"; Дата рождения:"+h.getBirthDate()+"; Вес:"+h.getWeight());
        }

        System.out.println("\n1. Сортировка по дате рождения");
        var res=allHumans.stream().sorted(Comparator.comparing(Human::getBirthDate))
                .collect(Collectors.toList());
        for (Human h: res){
            System.out.println("Фамилия: " +h.getLastName()+"; Имя: "+h.getFirstName()+"; Возвраст:"+h.getAge()+"; Дата рождения:"+h.getBirthDate()+"; Вес:"+h.getWeight());        }

        System.out.println("\n2. Фильтрация по возрасту меньше, чем 50");
        Predicate<Human> byAge = pers -> pers.getAge() < 50;
        var res2 = allHumans.stream().filter(byAge)
                .collect(Collectors.toList());
        for (Human h: res2){
            System.out.println("Фамилия: " +h.getLastName()+"; Имя: "+h.getFirstName()+"; Возвраст:"+h.getAge()+"; Дата рождения:"+h.getBirthDate()+"; Вес:"+h.getWeight());        }

        System.out.println("\n3. Сортировка по весу");
        var res3=allHumans.stream().sorted(Comparator.comparing(Human::getWeight))
                .collect(Collectors.toList());
        for (Human h: res3){
            System.out.println("Фамилия: " +h.getLastName()+"; Имя: "+h.getFirstName()+"; Возвраст:"+h.getAge()+"; Дата рождения:"+h.getBirthDate()+"; Вес:"+h.getWeight());
        }

        System.out.println("\n4. Конкатенация всех имен в одну большую строку через пробел ");
        String res4 = allHumans.stream()
                .map(Human::getFirstName)
                .collect(Collectors.joining(" "));
        System.out.println(res4);
    }
}
