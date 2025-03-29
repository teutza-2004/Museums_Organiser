package org.example;

import org.entities.*;
import org.excceptions.*;

import java.io.*;

public class FindMember extends Command{
    public void execute(Database db, String[] tokens, FactoryPerson factoryPerson, PrintStream ps) {
        Person member = factoryPerson.createPerson(tokens[3], tokens[1], tokens[2], tokens[8], Integer.parseInt(tokens[7]), tokens[6]);
        member.setAge(Integer.parseInt(tokens[4]));
        member.setEmail(tokens[5]);
        Group mainGroup = db.findGroup(Integer.parseInt(tokens[9]), tokens[10]);

        try {
            if (mainGroup != null) {
                if (mainGroup.findMember(member) != null) {
                    ps.println(tokens[9] + " ## " + tokens[10] + " ## member found: " + member);
                } else {
                    ps.println(tokens[9] + " ## " + tokens[10] + " ## member not exists: " + member);
                }
            } else {
                throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
            }
        } catch (Exception e) {
            ps.println(tokens[9] + " ## " + tokens[10] + " ## " + e.getMessage() + " ## (find member: " + member + ")");
        }
    }
}
