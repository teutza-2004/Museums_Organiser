package org.entities;

import java.io.PrintStream;

public interface Observer {
    void update(Database db, Integer code, String message, PrintStream ps);
}
