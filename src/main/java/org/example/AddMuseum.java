package org.example;

import org.entities.*;

import java.io.*;

public class AddMuseum extends Command {
    public void execute(Database db, String[] tokens, FactoryPerson factoryPerson, PrintStream ps) {
        Location location = new Location.Builder(tokens[3], Integer.parseInt(tokens[7])).build();
        Museum museum = new Museum.Builder(tokens[2], Integer.parseInt(tokens[1]), Integer.parseInt(tokens[14]), location).build();
        db.addMuseum(museum, ps);
    }
}
