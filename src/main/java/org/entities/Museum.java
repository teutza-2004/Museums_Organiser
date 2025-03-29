package org.entities;

import java.io.*;
import java.util.*;

/// Used Builder + Observer

public class Museum {
    private String name;
    private long code;
    private long supervisorCode;
    private Location location;
    private Person manager;
    private Integer foundingYear;
    private String email;

    public Set<Observer> observers = new HashSet<>();

    public static class Builder {
        private String name;
        private long code;
        private long supervisorCode;
        private Location location;
        private Person manager = null;
        private Integer foundingYear = null;
        private String email = "";

        public Builder(String name, long code, long supervisorCode, Location location) {
            this.name = name;
            this.code = code;
            this.supervisorCode = supervisorCode;
            this.location = location;
        }

        public Builder setManager(Person manager) {
            this.manager = manager;
            return this;
        }
        public Builder setFoundingYear(Integer foundingYear) {
            this.foundingYear = foundingYear;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public Museum build() {
            return new Museum(this);
        }
    }

    public Museum(Builder builder) {
        this.name = builder.name;
        this.code = builder.code;
        this.supervisorCode = builder.supervisorCode;
        this.location = builder.location;
        this.manager = builder.manager;
        this.foundingYear = builder.foundingYear;
        this.email = builder.email;
    }

    public long getCode() {
        return this.code;
    }
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.code + ": " + this.name;
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }
    public void notifyObservers(Database db, String message, PrintStream ps) {
        for (Observer observer : observers) {
            observer.update(db, Integer.valueOf((int) this.code), message, ps);
        }
    }
}
