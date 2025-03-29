package org.entities;

public class Professor extends Person {
    private int exprerience;
    private String school;

    public Professor(String surname, String name, String role, int exprerience, String school) {
        super(surname, name, role);
        this.exprerience = exprerience;
        this.school = school;
    }

    @Override
    public String toString() {
        return super.toString() + ", school=" + school + ", experience=" + exprerience;
    }
}
