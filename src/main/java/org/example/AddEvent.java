package org.example;

import org.entities.*;

import java.io.*;

public class AddEvent extends Command{
    public void execute(Database db, String[] tokens, FactoryPerson factoryPerson, PrintStream ps) {
        try {
            Museum museum = db.findMuseum(Integer.parseInt(tokens[1]));
            if (museum != null) {
                museum.notifyObservers(db, tokens[2], ps);
            }
        } catch (Exception e) {
            ps.println(tokens[9] + " ## " + tokens[10] + " ## " + e.getMessage());
        }
    }
}
