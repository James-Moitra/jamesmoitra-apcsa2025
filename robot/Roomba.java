package robot;

import kareltherobot.*;

public class Roomba implements Directions {

// Main method to make this self-contained
public static void main(String[] args) {
// LEAVE THIS ALONE!!!!!!
String worldName = "robot/basicRoom.wld";
World.setDelay(1);
Roomba cleaner = new Roomba();
int totalBeepers = cleaner.cleanRoom(worldName, 7, 7);
System.out.println("Roomba cleaned up a total of " + totalBeepers + " beepers.");
}

// declared here so it is visible in all the methods!
private Robot roomba;

// You will need to add many variables!!


public int cleanRoom(String worldName, int startX, int startY) {

// A new Robot should be constructed and assigned to the global (instance) variable named roomba that is declared above.
// Make sure it starts at startX and startY location.

World.readWorld(worldName);
World.setVisible(true);
roomba = new Robot(startX, startY, East, 0);

while(roomba.frontIsClear()){
roomba.move();
while(roomba.nextToABeeper()){
roomba.pickBeeper();
}
}
roomba.turnLeft();
roomba.move();
roomba.turnLeft();

while(roomba.frontIsClear()){
roomba.move();
while(roomba.nextToABeeper()){
roomba.pickBeeper();
}
}

turnRight(roomba);
roomba.move();
turnRight(roomba);

while(roomba.frontIsClear()){
roomba.move();
while(roomba.nextToABeeper()){
roomba.pickBeeper();
}
}

roomba.turnLeft();
roomba.move();
roomba.turnLeft();

while(roomba.frontIsClear()){
roomba.move();
while(roomba.nextToABeeper()){
roomba.pickBeeper();
}
}

turnRight(roomba);
roomba.move();
turnRight(roomba);

while(roomba.frontIsClear()){
roomba.move();
while(roomba.nextToABeeper()){
roomba.pickBeeper();
//pile size
for ( int 1 = 0)
 
}
}



/** This section will have all the logic that takes the Robot to every location
* and cleans up all piles of beepers. Think about ways you can break this
* large, complex task into smaller, easier to solve problems.
*/

// the line below causes a null pointer exception
// what is that and why are we getting it?


int totalBeepers = 20;

return totalBeepers;}

// Need to move this somewhere else.
// This method should return the total number of beepers cleaned up.


public static void turnRight(Robot roomba){
roomba.turnLeft();
roomba.turnLeft();
roomba.turnLeft();
}
}




