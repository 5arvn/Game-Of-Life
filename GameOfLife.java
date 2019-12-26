/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.of.life;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author varun
 */
public class GameOfLife {
    private ColonyFrame currentFrame;
    private HashMap<String, Colony> colonies;
    private HashMap<String, ColonyFrame> cf;
    private HashMap<String, parallelEvolveTask> tasks;
    private Colony currentColony;
    private int size;
    private int p;

    public GameOfLife() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Thou hast requested the presence of the Keeper of Cells.");
        System.out.println("What wishest thou today, good sire?");
        tasks = new HashMap<>();
        cf = new HashMap<>();
        driver(scan);
    }

    public void addColony(String name) {
        currentColony = new Colony(size, name);
        colonies.put(name, currentColony);

    }

    public void help() {
        System.out.println("Options for game of life:");
        System.out.println("q-- quit the program");
        System.out.println("h-- print out this help menu");
        System.out.println("a xcor ycor-- set cell at xcor, ycor alive");
        System.out.println("d xcor ycor-- set cell at xcor, ycor dead");
        System.out.println("e numGen-- evolve colony");
        System.out.println("r-- reset colony");
        System.out.println("i-- print out basic information of the colonies");
        System.out.println("p numMillisec-- set delay between evolutions");
        System.out.println("c size name-- create a new colony with a name and "
                + "size");
        System.out.println("u n-- use colony");
        System.out.println("s-- toggle silent evolution");
        System.out.println("remove n-- remove a colony");
    }

    public void summary() {
        for (Map.Entry<String, ColonyFrame> entry : cf.entrySet()) {
            ColonyFrame c = entry.getValue();
            if (entry.getValue() == currentFrame) {
                System.out.print("* ");
            }
            System.out.println("name: " + c.colony.getName() + " size: " 
                    + c.colony.getColonySize() + " # alive: " 
                    + c.colony.numberAlive());
        }
    }

    public void setCurrent(String name) {
        currentFrame = cf.get(name);
        currentColony = currentFrame.getColony();
    }

    private void driver(Scanner scan) {
        String command = scan.next();
        while (!command.equals("q")) {
            switch (command) {
                case "h":
                    help(); break;
                case "a":
                    currentColony.setCellAlive(scan.nextInt(), scan.nextInt());
                    currentFrame.repaint(); break;
                case "d":
                    currentColony.setCellDead(scan.nextInt(), scan.nextInt());
                    currentFrame.repaint(); break;
                case "e":
                    tasks.get(currentColony.getName()).moveIt(scan.nextInt(), 
                            tasks.get(currentColony.getName()).getDelay());
                    break;
                case "r":
                    currentColony.resetColony();
                    currentFrame.repaint();
                    break;
                case "i":
                    summary(); break;
                case "p":
                    tasks.get(currentColony.getName()).setDelay(scan.nextInt());
                    break;
                case "c":
                    currentColony = new Colony(scan.nextInt(), scan.next());
                    System.out.println("GOL: colony " + currentColony.getName() 
                            + " created!");
                    currentFrame = new ColonyFrame(currentColony);
                    cf.put(currentColony.getName(), currentFrame);
                    tasks.put(currentColony.getName(), 
                            new parallelEvolveTask(currentFrame)); break;
                case "u":
                    setCurrent(scan.next());
                    System.out.println("GOL: colony set to " 
                            + currentColony.getName()); break;
                case "s":
                   tasks.get(currentColony.getName()).setSilent(true);
                    break;
                case "remove":
                    cf.remove(scan.next());
                    break;
                default:
                    System.out.println("Thy input is invalid. Try again, "
                            + "if it pleases you.");
                    break;
            }
            command = scan.next();
        }
        scan.close();
        System.out.println("Fare thee well, good sire.");
        System.exit(0);
    }

   
    public static void main(String[] args) {
        GameOfLife gol = new GameOfLife();
        
    }

}
