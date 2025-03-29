package org.example;

import org.entities.*;

import java.io.*;

public abstract class Command {
    public abstract void execute(Database db, String[] tokens, FactoryPerson factoryPerson, PrintStream ps);
}
