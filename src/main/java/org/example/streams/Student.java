package org.example.streams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Student implements Comparable<Student> {

    String name;
    int age;
    String middleName;

    List<String> hobbies = new ArrayList<>();

    @Override
    public int compareTo(Student o) {
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public Optional<String> getMiddleName() {
        if (middleName.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(middleName);
    }
}
