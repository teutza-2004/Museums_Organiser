package org.example;

import org.entities.*;
import org.excceptions.*;

import java.io.*;
import java.util.*;

public class AddGuide extends Command {
    public void execute(Database db, String[] tokens, FactoryPerson factoryPerson, PrintStream ps) {
        Person guide = factoryPerson.createPerson(tokens[3], tokens[1], tokens[2], tokens[8], Integer.parseInt(tokens[7]), tokens[6]);
        guide.setAge(Integer.parseInt(tokens[4]));
        guide.setEmail(tokens[5]);
        Group mainGroup = db.findGroup(Integer.parseInt(tokens[9]), tokens[10]);

        try {
            if (tokens[3].equals("student")) {
                throw new GuideTypeException("GuideTypeException: Guide must be a professor.");
            } else if (mainGroup != null) {
                if (mainGroup.getGuide() != null) {
                    guide = mainGroup.getGuide();
                    throw new GuideExistsException("GuideExistsException: Guide already exists.");
                } else {
                    mainGroup.addGuide((Professor) guide);
                }
            } else {
                mainGroup = new Group(new ArrayList<>(), (Professor) guide, Integer.parseInt(tokens[9]), tokens[10]);
                db.addGroup(mainGroup);
                Museum museum = db.findMuseum(Integer.parseInt(tokens[9]));
                if (museum != null) {
                    museum.addObserver(mainGroup);
                }
            }
            ps.println(tokens[9] + " ## " + tokens[10] + " ## new guide: " + guide);
        } catch (Exception e) {
            ps.println(tokens[9] + " ## " + tokens[10] + " ## " + e.getMessage() + " ## (new guide: " + guide + ")");
        }
    }
}
