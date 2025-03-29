package org.entities;

/// Used Factory Method

public abstract class Person {
    private String surname;
    private String name;
    private String role;
    private int age;
    private String email;

    public Person(String surname, String name, String role) {
        this.surname = surname;
        this.name = name;
        this.role = role;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        if (this.email != null && this.email.equals("")) {
            this.email = null;
        }
        return "surname=" + this.surname + ", name=" + this.name + ", role=" + this.role + ", age=" + this.age + ", email=" + this.email;
    }
    public boolean personEquals(Person person) {
        if (this.surname != null && !this.surname.equals(person.surname)){ return false; }
        if (this.name != null && !this.name.equals(person.name)){ return false; }
        if (this.role != null && !this.role.equals(person.role)){ return false; }
        if (this.email != null && !this.email.equals(person.email)){ return false; }
        if (this.age != person.age){ return false; }
        return true;
    }
}
