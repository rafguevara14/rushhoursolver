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


    public void generate_neighbourstest(){


        ArrayList<Node> neighbours


    }


    //Pre-condition: needs the leftmost/upmost coordinate of the given car
    private int[] ROM(String currCar, int x, int y) {

//        0 O..P..
//        1 O..P..
//        2 OXXP..
//        3 ..AQQQ
//        4 ..A..B
//        5 ..RRRB

        //[min,max]
        int[] ROM = new int[2];

        int xtmp = x;

        int ytmp = y;

        //get to negative end of car (top/left of car)
        if(this.board.isVertical(currCar)){

            //travel up till first empty square
            while(this.board.isSameCar(currCar,xtmp,ytmp))
                ytmp--;

            //step back to get to car
            ytmp++;

        }else{

            //travel left till first empty square
            while(this.board.isSameCar(currCar,xtmp,ytmp))
                xtmp--;

            //step back to get to car (avoids out of bounds exception)
            xtmp++;
        }

        //Range of motion does not begin till after the first empty square
        ROM[0] = 1;

        //determine negative ROM
        do{

            ROM[0]--;

            if(this.board.isVertical(currCar))
                ytmp--;
            else
                xtmp--;

            //edge case
            if(!this.board.inBounds(currCar,xtmp,ytmp))
                break;

        }while(xtmp < 6 && ytmp < 6 && this.board.getSquare(xtmp,ytmp).equals("."));


        //reset coordinates
        xtmp = x;

        ytmp = y;

        //start at positive end of car (bottom/right of car)
        if(this.board.isVertical(currCar)) {

            //travel down till first empty square
            while(this.board.isSameCar(currCar,xtmp,ytmp))
                ytmp++;

            //step back to get to car
            ytmp--;

        }else{
            //travel right till first empty square
            while(this.board.isSameCar(currCar,xtmp,ytmp))
                xtmp++;

            //step back to get to car
            xtmp--;
        }

        //Range of motion does not begin till after the first empty square
        ROM[1] = -1;

        //determine positive ROM
        do{

            ROM[1]++;

            if(this.board.isVertical(currCar))
                ytmp++;
            else
                xtmp++;

            //edge case
            if(!this.board.inBounds(currCar,xtmp,ytmp))
                break;

        }while(xtmp < 6 && ytmp < 6 && this.board.getSquare(xtmp,ytmp).equals("."));

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
