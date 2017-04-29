import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.naturalOrder;

public class CollectionStreamsTest {

    class Nation {
        String name;

        public Nation(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Nation{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    class Person {
        Integer age;
        Nation nation;

        public Person(Integer age, Nation nation) {
            this.age = age;
            this.nation = nation;
        }

        public Nation getNation() {
            return nation;
        }

        Integer getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "age=" + age +
                    ", nation=" + nation +
                    '}';
        }
    }

    final List<Person> people = new ArrayList<Person>();

    @Before
    public void init() {
        people.add(new Person(1, new Nation("Poland")));
        people.add(new Person(78, new Nation("Belgium")));
        people.add(new Person(78, new Nation("Poland")));
        people.add(new Person(3, new Nation("Germany")));

    }

    @Test
    public void findYoungestPerson() {
        System.out.println(people.stream()
                .max(Comparator.nullsLast(Comparator.comparing(Person::getAge, Comparator.nullsFirst(naturalOrder()))))
                .get());
    }


    public static int calculate7(List<Integer> numbers) {
        int total = 0;
        for (int number : numbers) {
            total += number;
        }
        return total;
    }

    @Test
    public void sum() {
        int sum = 0;
        System.out.println(people.stream()
                .map(Person::getAge)
                .reduce(sum, (x, y) -> x + y));
    }

    @Test
    public void groupByNationality() {
        System.out.println(
                people.stream()
                        .map(person -> String.valueOf(person.getAge()))
                        .collect(Collectors.joining(",")));

    }
}
