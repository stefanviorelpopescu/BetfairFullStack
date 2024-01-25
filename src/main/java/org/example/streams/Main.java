package org.example.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class Main {
    public static void main(String[] args) {

        List<String> names = List.of("Ion", "Maria", "Basescu");
        List<String> filteredNames = names.stream()
                .filter(name -> name.startsWith("M"))
                .map(s1 -> s1.toUpperCase())
                .toList();

        int sum = 0;
        String endLine = ".";
        filteredNames.forEach(s -> s += endLine);

        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(() -> 4);

        Student ion = new Student("Ion", 45, "", emptyList());
        Student maria = new Student("Maria", 42, "", emptyList());
        Set<Student> studentSet = new TreeSet<>((o1, o2) -> o1.getAge()-o2.getAge());
        studentSet.add(ion);
        studentSet.add(maria);
        studentSet.stream()
                .sorted(new StudentComparatorByAge().reversed()
                        .thenComparing(new StudentComparatorByName().reversed()))
                .map(Student::getAge)
                .filter(age -> age > 20)
                .forEach(System.out::println);

        int sum1 = studentSet.stream()
                .filter(student -> student.getName().startsWith("I"))
                .mapToInt(Student::getAge)
                .sum();

        Optional<String> optionalMiddleName = ion.getMiddleName();
        optionalMiddleName.ifPresent(System.out::println);

        String middleName = ion.getMiddleName().orElse("N/A");

        DayOfWeek dayOfWeek = DayOfWeek.fromDbValue(12).orElseThrow();
        DayOfWeek dayOfWeek2 = DayOfWeek.fromDbValue(12)
                .orElseThrow(() -> new IllegalArgumentException("Invalid day of week"));

        long hobbiesCount = studentSet.stream()
                .flatMap(student -> student.getHobbies().stream())
                .distinct()
                .count();

        Map<Integer, List<Student>> studentsByAge = new HashMap<>();
        studentSet.forEach(student -> studentsByAge.computeIfAbsent(student.getAge(), k -> new ArrayList<>()).add(student));

        Map<Integer, List<Student>> collect = studentSet.stream()
                .collect(Collectors.groupingBy(Student::getAge));

    }
}
