package org.example;

import org.entities.*;
import org.excceptions.*;

import java.io.*;

public class FindGuide extends Command{
    public void execute(Database db, String[] tokens, FactoryPerson factoryPerson, PrintStream ps) {
        Person guide = factoryPerson.createPerson(tokens[3], tokens[1], tokens[2], tokens[8], Integer.parseInt(tokens[7]), tokens[6]);
        guide.setAge(Integer.parseInt(tokens[4]));
        guide.setEmail(tokens[5]);
        try {
            if (tokens[3].equals("student")) {
                throw new GuideTypeException("GuideTypeException: Guide must be a professor.");
            }
            Group mainGroup = db.findGroup(Integer.parseInt(tokens[9]), tokens[10]);

            if (mainGroup != null) {
                if (mainGroup.getGuide().personEquals(guide)) {
                    ps.println(tokens[9] + " ## " + tokens[10] + " ## guide found: " + guide);
                } else {
                    ps.println(tokens[9] + " ## " + tokens[10] + " ## guide not exists: " + guide);
                }
            } else {
                throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
            }
        } catch (Exception e) {
            ps.println(tokens[9] + " ## " + tokens[10] + " ## " + e.getMessage() + " ## (find guide: " + guide + ")");
        }
    }
}
