package org.entities;

import java.io.*;
import java.util.*;

/// Used Singleton

public class Database {
    private static Database database;
    private Set<Museum> museums;
    private Set<Group> groups;

    private Database() {
        museums = new HashSet<>();
        groups = new HashSet<>();
    }

    public static Database getInstance() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public void addMuseum(Museum museum, PrintStream ps) {
        this.museums.add(museum);
        ps.println(museum);
    }
    public void addMuseums(Set<Museum> museums) {
        this.museums.addAll(museums);
    }
    public void addGroup(Group group) {
        this.groups.add(group);
    }
    public void addGroups(Set<Group> groups) {
        this.groups.addAll(groups);
    }

    public Group findGroup(Integer museumCode, String timetable) {
        for (Group group : groups) {
            if (group.getMuseumCode().equals(museumCode) && group.getTimetable().equals(timetable)) {
                return group;
            }
        }
        return null;
    }
    public Museum findMuseum(long code) {
        for (Museum museum : museums) {
            if (museum.getCode() == code) {
                return museum;
            }
        }
        return null;
    }
}
