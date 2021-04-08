import java.util.ArrayList;

public class Node {

    private ArrayList<String> visitedCars; //closed set
    private int H;
    private int G;
    private int F;

    Board board;

    Node


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
            while(this.board.getSquare(xtmp,ytmp) == currCar) {
                ytmp--;
            }
        }
        else
            //travel left till first empty square
        {
            while(this.board.getSquare(xtmp,ytmp) == currCar) {
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
            while(this.board.getSquare(xtmp,ytmp) == currCar)
                ytmp++;
        else
            //travel right till first empty square
            while(this.board.getSquare(xtmp,ytmp) == currCar)
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


        ArrayList<Node> neighbours;
//
//        //go through entire board looking for NEW cars
//
        for(int i = 0; i < 6; i++) {
//
            for (int j = 0; j < 6; j++) {
//
//                //scan through till find a car
                if(this.board.getSquare(i,j) != "."){
//

//
                    String currCar = this.board.getSquare(i,j);
//
                    if(!visitedCars.contains(currCar)){

                        //mark as visited
                        visitedCars.add(currCar);

                        //determine ROM
                        int[] ROM = ROM(currCar,i,j);

                        //go through all moves for that car and generate a neighbour

                        for(int step = ROM[0]; step <= ROM[1]; step++){







                        }







                    }//new car


//                if(!visitedCars.contains(currCar)) {
//
//                    //add currCar to closed set
//                    visitedCars.add(currCar);
//
//
//
//                    //determine ROM
//                    int[] ROM = ROM(currCar,i,j,isVertical);
//
//                    //go through all moves given ROM
//                    for(int step = ROM[0]; step <= ROM[1]; step++){
//
//                        // makeMove(steps = i)
//
//                    }
                } //found a car
//
//

            }//j
        }//i

    }
//

}//Node
