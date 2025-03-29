package org.example;

import org.entities.*;

import java.io.*;
import java.util.*;

public class Main {
    static Database db = Database.getInstance();

    private static void processMuseums(String filePath) {
        PrintStream ps = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(filePath + ".out");
            ps = new PrintStream(fos);

            Scanner scanner = new Scanner(new File(filePath + ".in"));
            String params = scanner.nextLine();
            FactoryPerson fp = new FactoryPerson();

            String line = null;
            while (scanner.hasNextLine()) {
                try {
                    line = scanner.nextLine();
                    String[] tokens = line.split("\\|");

                    AddMuseum am = new AddMuseum();
                    am.execute(db, tokens, fp, ps);
                } catch (Exception e) {
                    ps.println("Exception: Data is broken. ## (" + line + ")");
                }
            }
            scanner.close();
        } catch (Exception e) {
            ps.println(e.getMessage());
        }
        ps.close();
    }

    private static void processGroups(String filePath) {
        PrintStream ps = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(filePath + ".out");
            ps = new PrintStream(fos);

            Scanner scanner = new Scanner(new File(filePath + ".in"));
            String params = scanner.nextLine();
            FactoryPerson fp = new FactoryPerson();

            String line = null;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] tokens = line.split("\\|");

                switch (tokens[0]) {
                    case "ADD GUIDE": {
                        AddGuide ag = new AddGuide();
                        ag.execute(db, tokens, fp, ps);
                        break;
                    }
                    case "FIND GUIDE": {
                        FindGuide fg = new FindGuide();
                        fg.execute(db, tokens, fp, ps);
                        break;
                    }
                    case "REMOVE GUIDE": {
                        RemoveGuide rg = new RemoveGuide();
                        rg.execute(db, tokens, fp, ps);
                        break;
                    }
                    case "ADD MEMBER": {
                        AddMember am = new AddMember();
                        am.execute(db, tokens, fp, ps);
                        break;
                    }
                    case "FIND MEMBER": {
                        FindMember fm = new FindMember();
                        fm.execute(db, tokens, fp, ps);
                        break;
                    }
                    case "REMOVE MEMBER": {
                        RemoveMember rm = new RemoveMember();
                        rm.execute(db, tokens, fp, ps);
                        break;
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (ps != null) {
            ps.close();
        }
    }

    private static void processEvents(String filePath) {
        PrintStream ps = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(filePath + ".out");
            ps = new PrintStream(fos);

            Scanner scanner = new Scanner(new File(filePath + ".in"));
            String params = scanner.nextLine();
            FactoryPerson fp = new FactoryPerson();

            String line = null;
            while (scanner.hasNextLine()) {
                try {
                    line = scanner.nextLine();
                    String[] tokens = line.split("\\|");

                    AddEvent ae = new AddEvent();
                    ae.execute(db, tokens, fp, ps);
                } catch (Exception e) {
                    ps.println("Exception: Data is broken. ## (" + line + ")");
                }
            }
            scanner.close();
        } catch (Exception e) {
            ps.println(e.getMessage());
        }
        ps.close();
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            PathTypes type = PathTypes.getType(args[0]);
            String filePath = args[1];

            switch (type) {
                case MUSEUMS:
                    processMuseums(filePath);
                    break;
                case GROUPS:
                    processGroups(filePath);
                    break;
                case LISTENER:
                    processEvents(filePath);
                    break;
                default:

            }
        } else if (args.length == 4) {
            PathTypes type = PathTypes.getType(args[0]);
            String museumsPath = args[1];
            String groupsPath = args[2];
            String eventsPath = args[3];

            processMuseums(museumsPath);
            processGroups(groupsPath);
            processEvents(eventsPath);
        }
    }
}
