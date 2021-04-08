import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

public class Node {

    private ArrayList<String> visitedCars; //closed set
    private int H;
    private int G;
    private int F;

    Board board;


    Node(){

        this.board = null;

        this.H = 0;

        this.G = 0;

        this.F = 0;

    }

    //creates a new Node from old board with one move executed
    Node(String currCar, int steps,Board oldBoard){

        //create new Board
        this.board = new Board(currCar,steps,oldBoard);

        //update H

        //update G

        //update F

    }

    public void ROMtest(String currCar, int x, int y, String file) throws FileAlreadyExistsException, FileNotFoundException {

        this.board = new Board(file);

        int ROM[] = ROM(currCar,x,y);

        System.out.println("ROM of " + currCar + " is [" + ROM[0] + "," + ROM[1] + "]\n");


    }


    private int[] ROM(String currCar, int x, int y) {

//        0 O..P..
//        1 O..P..
//        2 OXXP..
//        3 ..AQQQ
//        4 ..A..B
//        5 ..RRRB

//        public final int LENGTH = 0;

//        public final int ISVERT = 1; //1 is vertical

        //[min,max]
        int[] ROM = new int[2];
//
        int xtmp = x;
        int ytmp = y;
//
//
        //TODO:get to negative end of car-1 (negative_eoc)

        //start at negative end of car-1 (top/left of car)

        if(this.board.isVertical(currCar))

            //travel up till first empty square
        {
            while(this.board.getSquare(xtmp,ytmp).equals(currCar)){
                ytmp--;
            }
        }
        else
            //travel left till first empty square
        {
            while(this.board.getSquare(xtmp,ytmp).equals(currCar)){
                xtmp--;
            }
        }

        ROM[0] = 0;

        //determine negative ROM
        while(xtmp < 6 && ytmp < 6 && this.board.getSquare(xtmp,ytmp).equals("."))

            ROM[0]--;

        //start at negative end of car-1 (bottom/right of car)
        if(this.board.isVertical(currCar))

            //travel down till first empty square
            while(this.board.getSquare(xtmp,ytmp).equals(currCar))
                ytmp++;
        else
            //travel right till first empty square
            while(this.board.getSquare(xtmp,ytmp).equals(currCar))
                xtmp++;

        //TODO:get to positive end of car+1 (positive_eoc)

        ROM[1] = 0;

        //determine positive ROM
        while(xtmp < 6 && ytmp < 6 && this.board.getSquare(xtmp,ytmp).equals("."))
            ROM[1]++;

        return ROM;

    }

    //return all possible neighbours as an ArrayList of Nodes
    public ArrayList<Node> generateNeighbours() {

        ArrayList<Node> neighbours = null;

        //go through entire board looking for NEW cars
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {

                //found a car
                if (!this.board.getSquare(i, j).equals(".")){

                    String currCar = this.board.getSquare(i, j);

                    //found a NEW car
                    if (!visitedCars.contains(currCar)) {

                        //mark as visited
                        visitedCars.add(currCar);

                        //determine ROM
                        int[] ROM = ROM(currCar, i, j);

                        //go through all moves for that car and generate a neighbour
                        for (int step = ROM[0]; step <= ROM[1]; step++)

                            neighbours.add(new Node(currCar, step, this.board));

                    }//new car
                }//car found
            }//j
        }//i (main for loop)

        return neighbours;

    }//generate neighbours function

}//Node
