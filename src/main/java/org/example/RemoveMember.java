package org.example;

import org.entities.*;
import org.excceptions.*;

import java.io.PrintStream;

public class RemoveMember extends Command {
    public void execute(Database db, String[] tokens, FactoryPerson factoryPerson, PrintStream ps) {
        Person member = factoryPerson.createPerson(tokens[3], tokens[1], tokens[2], tokens[8], Integer.parseInt(tokens[7]), tokens[6]);
        member.setAge(Integer.parseInt(tokens[4]));
        member.setEmail(tokens[5]);
        Group mainGroup = db.findGroup(Integer.parseInt(tokens[9]), tokens[10]);

        try {
            if (mainGroup != null) {
                Person toBeRemoved = mainGroup.findMember(member);
                if (toBeRemoved != null) {
                    mainGroup.removeMember(toBeRemoved);
                    ps.println(tokens[9] + " ## " + tokens[10] + " ## removed member: " + member);
                } else {
                    throw new PersonNotExistsException("PersonNotExistsException: Person was not found in the group. ## (");
                }
            } else {
                throw new GroupNotExistsException("GroupNotExistsException: Group does not exist. ## (removed member: ");
            }
        } catch (Exception e) {
            ps.println(tokens[9] + " ## " + tokens[10] + " ## " + e.getMessage() + member + ")");
        }
    }
}
