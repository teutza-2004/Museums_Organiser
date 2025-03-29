package org.example;

import org.entities.*;
import org.excceptions.*;

import java.io.*;

public class AddMember extends Command {
    public void execute(Database db, String[] tokens, FactoryPerson factoryPerson, PrintStream ps) {
        Person member = factoryPerson.createPerson(tokens[3], tokens[1], tokens[2], tokens[8], Integer.parseInt(tokens[7]), tokens[6]);
        member.setAge(Integer.parseInt(tokens[4]));
        member.setEmail(tokens[5]);
        Group mainGroup = db.findGroup(Integer.parseInt(tokens[9]), tokens[10]);

        try {
            if (mainGroup != null) {
                if (mainGroup.getMembers().size() < 10) {
                    mainGroup.addMember(member);
                } else {
                    throw new GroupThresholdException("GroupThresholdException: Group cannot have more than 10 members.");
                }
            } else {
                throw new GroupNotExistsException("GroupNotExistsException: Group does not exist.");
            }
            ps.println(tokens[9] + " ## " + tokens[10] + " ## new member: " + member);
        } catch (Exception e) {
            ps.println(tokens[9] + " ## " + tokens[10] + " ## " + e.getMessage() + " ## (new member: " + member + ")");
        }
    }
}
