import java.util.ArrayList;

public class Node {

    private ArrayList<String> visitedCars; //closed set
    private int H;
    private int G;
    private int F;
    fs

    Board board;

    private int[] ROM(String currCar,int x,int y,boolean isVertical) {dasmdsa

//saoidhsakds/dsad
//made a thing here

//        0 O..P..
//        1 O..P..
//        2 OXXP..
//        3 ..AQQQ
//        4 ..A..B
//        5 ..RRRB


        //[min,max,length]
        int[] ROM = new int[3];

        int xtmp = x;
        int ytmp = y;

        ROM[2] = 0;

        //traverse negative to bottom of car
        while(this.matrix[xtmp][ytmp] == currCar) {

            ROM[2]++;

            if(isVertical)
                ytmp++;
            else
                xtmp++;

        }

        //take away 'double dip'
        ROM[2]--;


        //determine negative ROM
        while(xtmp < 6 && ytmp < 6 && this.matrix[xtmp][ytmp] == ".") {


        }




        //traverse positive to top of car
        while(this.matrix[xtmp][ytmp] == currCar) {

            if (isVertical)
                ytmp--;
            else
                xtmp--;
        }





        xtmp = x;
        ytmp = y;








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

                    //determine orientation (constant to access element)
//                    boolean isVertical = (this.matrix[i][j+1] == currCar);

                    //determine ROM




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

