import java.util.ArrayList;

public class Node {

    private ArrayList<String> visitedCars; //closed set
    private int H;
    private int G;
    private int F;

    Board board;

    private int[] ROM(String currCar,int x,int y,boolean isVertical) {dasmdsa

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


        //TODO:get to negative end of car-1 (negative_eoc)

        //start at negative end of car-1
        if(isVertical)

            ytmp = negative_eoc;

        else
            xtmp = negative_eoc;


        ROM[0] = 0;

        //determine negative ROM
        while(xtmp < 6 && ytmp < 6 && this.matrix[xtmp][ytmp] == ".") {

            ROM[0]--;

        }

        //TODO:get to positive end of car+1 (positive_eoc)


       //start at positive end of car+1
        if(isVertical)

            ytmp = positive_eoc;

        else
            xtmp = positive_eoc;

        ROM[1] = 0;

        //determine positive ROM
        while(xtmp < 6 && ytmp < 6 && this.matrix[xtmp][ytmp] == ".") {

            ROM[1]++;
        }


        return ROM;

    }


    public ArrayList<Node> generateNeighbours(){


        ArrayList<Node> neighbours;

        //go through entire board looking for NEW cars

        for(int i = 0; i < 6; i++) {

            for (int j = 0; j < 6; j++) {

                //scan through till find a car
                if(this.matrix[i][j] == ".")

                    continue;

                String currCar = this.matrix[i][j];

                if(!visitedCars.contains(currCar)) {

                    //add currCar to closed set
                    visitedCars.add(currCar);

                   

                    //determine ROM
                    int[] ROM = ROM(currCar,i,j,isVertical);

                    //go through all moves given ROM
                    for(int step = ROM[0]; step <= ROM[1]; step++){

                        // makeMove(steps = i)

                    }



                }//per car













            }//j


        }//i



        //set currCar to visited
        //determine orientation




        //determine ROM interval [a,b] (how??)

        //apply makeMove to the car with each step in ROM




    }




}

}

