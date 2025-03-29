package org.entities;

import java.io.*;
import java.util.*;

///  Used Observer

public class Group implements Observer{
    private List<Person> members;
    private Professor guide;
    private Integer museumCode;
    private String timetable;

    public Group() {
        this.members = new ArrayList<>();
    }
    public Group(ArrayList<Person> members, Professor guide, Integer musumCode, String timetable) {
        this.members = members;
        this.guide = guide;
        this.museumCode = musumCode;
        this.timetable = timetable;
    }

    public List<Person> getMembers() {
        return this.members;
    }
    public Integer getMuseumCode() {
        return this.museumCode;
    }
    public String getTimetable() {
        return this.timetable;
    }
    public Professor getGuide() {
        return this.guide;
    }

    public void addGuide(Professor professor) {
        this.guide = professor;
    }
    public void addMember(Person person) {
        this.members.add(person);
    }
    public void removeMember(Person person) {
        members.remove(person);
    }
    public void resetGuide() {
        this.guide = null;
    }

    public Person findMember(Person person) {
        for (Person member : members) {
            if (member.personEquals(person)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public void update(Database db, Integer code, String message, PrintStream ps) {
        Museum museum = db.findMuseum(code);
        ps.println("To: " + this.guide.getEmail() + " ## Message: " + museum.getName() + " (" + museum.getCode() + ") " + message);
    }
}
