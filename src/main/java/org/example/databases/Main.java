package org.example.databases;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class Main {
    public static final String DB_CONNECTION_URL = "jdbc:postgresql://localhost:5432/postgres?user=postgres&password=root";

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            DriverManager.setLoginTimeout(60); // wait 1 min; optional: DB may be busy, good to set a higher timeout
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e){
            System.err.println("Can’t load driver. Verify CLASSPATH");
            System.err.println(e.getMessage());
        } catch (InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
//        insertData(5, "Elena Farago", 76, "1234");
        selectData(70);
    }

    private static void insertData(long id, String name, int age, String phone) {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement ps = conn.prepareStatement("insert into authors (id, name, age, phone) values (?, ?, ?, ?)")) {
            ps.setLong(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Cannot insert author: " + e.getMessage());
        }
    }

    private static void selectData(int ageLimit) {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL);
             PreparedStatement ps = conn.prepareStatement("select * from authors where age > ?")) {
            ps.setInt(1, ageLimit);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");
                System.out.printf("%s\t%d\t%s%n", name, age, phone);
            }
        } catch (SQLException e) {
            System.err.println("Cannot insert author: " + e.getMessage());
        }
    }
}
