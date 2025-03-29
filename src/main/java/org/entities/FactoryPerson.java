package org.entities;

public class FactoryPerson {
    public Person createPerson(String title, String surname, String name, String role, int year, String school) {
        switch (title) {
            case "student":
                return new Student(surname, name, role, school, year);
            case "profesor":
                return new Professor(surname, name, role, year, school);
            default:
                return null;
        }
    }
}
