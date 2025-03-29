package org.example;

import org.entities.*;
import org.excceptions.*;

import java.io.*;

public class RemoveGuide extends Command{
    public void execute(Database db, String[] tokens, FactoryPerson factoryPerson, PrintStream ps) {
        Person guide = factoryPerson.createPerson(tokens[3], tokens[1], tokens[2], tokens[8], Integer.parseInt(tokens[7]), tokens[6]);
        guide.setAge(Integer.parseInt(tokens[4]));
        guide.setEmail(tokens[5]);
        Group mainGroup = db.findGroup(Integer.parseInt(tokens[9]), tokens[10]);

        try {
            if (mainGroup != null) {
                mainGroup.resetGuide();
                ps.println(tokens[9] + " ## " + tokens[10] + " ## removed guide: " + guide);
            } else {
                throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
            }
        } catch (Exception e) {
            ps.println(tokens[9] + " ## " + tokens[10] + " ## " + e.getMessage() + " ## (removed guide: " + guide + ")");
        }
    }
}
