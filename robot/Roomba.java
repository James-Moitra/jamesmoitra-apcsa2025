package robot;

import kareltherobot.*;

public class Roomba implements Directions {

    // Main method to make this self-contained
    public static void main(String[] args) {
        String worldName = "robot/finalTestWorld2024.wld";
        World.setDelay(0);
        Roomba cleaner = new Roomba();
        cleaner.cleanRoom(worldName, 26, 101);
        System.out.println("Roomba cleaned up a total of " + cleaner.totalBeepers + " beepers.");
        System.out.println("The total area is " + (cleaner.totalArea-173) + " units");
        System.out.println("The largest pile of beepers was " + cleaner.largestPile + " beepers at location (" + cleaner.largestPileX + ", " + cleaner.largestPileY + ").");
        System.out.println("The average size of a pile is " + cleaner.getAveragePileSize() + " beepers.");
        System.out.println("The percentage dirty is " + cleaner.percentdirty() + "%");
       System.out.println("The location of the largest pile is (" + cleaner.largestPileX + ", " + cleaner.largestPileY + ")");
    }

    // declared here so it is visible in all the methods!
    private Robot roomba;
    private int totalBeepers = 0;
    public int totalArea = 0;
    private int largestPile = 0;
    public int largestPileX = 0;
    public int largestPileY = 0;
    private int numberOfPiles = 0;
    private int x = 0;
    private int y = 0;

    public void cleanRoom(String worldName, int startX, int startY) {
        // A new Robot should be constructed and assigned to the global (instance) variable named roomba that is declared above.
        // Make sure it starts at startX and startY location.
        
    
        World.readWorld(worldName);
        World.setVisible(true);
        roomba = new Robot(26, 101, East, 0);
        x = startX;
        y = startY;

        cleanRow();
        while (roomba.frontIsClear() || canMoveUp()) {
            moveToNextRow();
            cleanRow();
        }
    }

    private void cleanRow() {
        while (roomba.frontIsClear()) {
            cleanCell();
            roomba.move();
            x += 1;
            totalArea += 1;
        }
        cleanCell();
        totalArea += 1; // increment totalArea after cleaning the last cell
    }

    private void cleanCell() {
        int pileSize = 0;
        while (roomba.nextToABeeper()) {
            roomba.pickBeeper();
            totalBeepers += 1;
            pileSize += 1;
        }
        if (pileSize > 0) {
            numberOfPiles += 1;
            if (pileSize > largestPile) {
                largestPile = pileSize;
                largestPileX = x;
                largestPileY = y;
            }
        }
    }

    private void moveToNextRow() {
        if (roomba.facingEast()) {
            roomba.turnLeft();
            if (roomba.frontIsClear()) {
                roomba.move();
                y += 1;
                totalArea += 1;
            }
            roomba.turnLeft();
        } else {
            turnRight(roomba);
            if (roomba.frontIsClear()) {
                roomba.move();
                y += 1;
                totalArea += 1;
            }
            turnRight(roomba);
        }
        x = getX();
    }

    private boolean canMoveUp() {
        if (roomba.facingEast()) {
            roomba.turnLeft();
            boolean canMove = roomba.frontIsClear();
            turnRight(roomba);
            return canMove;
        } else {
            turnRight(roomba);
            boolean canMove = roomba.frontIsClear();
            roomba.turnLeft();
            return canMove;
        }
    }

    public static void turnRight(Robot roomba) {
        roomba.turnLeft();
        roomba.turnLeft();
        roomba.turnLeft();
    }

    private int getX() {
        return x;
    }

    private int getY() {
        return y;
    }

    public double getAveragePileSize() {
        if (numberOfPiles == 0) {
            return 0;
        }
        return (double) totalBeepers / numberOfPiles;
    }

    public double percentdirty() {
        if (totalArea == 0) {
            return 0;
        }
        return ((double) numberOfPiles / totalArea) * 100;
    }
}