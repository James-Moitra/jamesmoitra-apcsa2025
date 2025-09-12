package robot;

import kareltherobot.*;

public class Roomba implements Directions {

    // Main method to make this self-contained
    public static void main(String[] args) {
        String worldName = "robot/basicRoom.wld";
        World.setDelay(1);
        Roomba cleaner = new Roomba();
        cleaner.cleanRoom(worldName, 7, 7);
        System.out.println("Roomba cleaned up a total of " + cleaner.totalBeepers + " beepers.");
        System.out.println("The total area is " + (cleaner.totalArea+2) + " units");
        System.out.println("The largest pile of beepers was " + cleaner.largestPile + " beepers.");
        System.out.println("The average size of a pile is " + cleaner.getAveragePileSize() + " beepers.");
    }

    // declared here so it is visible in all the methods!
    private Robot roomba;
    private int totalBeepers = 0;
    public int totalArea = 0;
    private int largestPile = 0;
    private int numberOfPiles = 0;

    public void cleanRoom(String worldName, int startX, int startY) {
        // A new Robot should be constructed and assigned to the global (instance) variable named roomba that is declared above.
        // Make sure it starts at startX and startY location.
        World.readWorld(worldName);
        World.setVisible(true);
        roomba = new Robot(startX, startY, East, 0);

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
            totalArea += 1;
        }
        cleanCell();
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
            }
        }
    }

    private void moveToNextRow() {
        if (roomba.facingEast()) {
            roomba.turnLeft();
            if (roomba.frontIsClear()) {
                roomba.move();
                totalArea += 1;
            }
            roomba.turnLeft();
        } else {
            turnRight(roomba);
            if (roomba.frontIsClear()) {
                roomba.move();
                totalArea += 1;
            }
            turnRight(roomba);
        }
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

    public double getAveragePileSize() {
        if (numberOfPiles == 0) {
            return 0;
        }
        return (double) totalBeepers / numberOfPiles;
    }
}