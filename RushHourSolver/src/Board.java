import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {

    private String[][] matrix = new String[6][6];

    private Map<String,int[]> properties = new HashMap<>();


    private final int LENGTH = 0;

    private final int ISVERT = 1; //1 is vertical

    //methods

    //sets matrix
    Board(String inputpath) throws FileAlreadyExistsException {

        //creates the board
        File file = new File(inputpath);

        Scanner scanner = new Scanner(file);


        if(!file.exists())
            throw new FileAlreadyExistsException("Could not find file: " + inputpath);

        //iterate through file and store elements in 2D matrix

        String row;

        while(scanner.hasNext()){

            for(int i = 0; i < 6; i++){

                row = scanner.next();

                for(int j = 0; j < 6; j++){

                    this.matrix[i][j] = String.valueOf(row.charAt(j));

                }//inner loop

            }//outer loop


        }//file iteration


//        int[] tmp_properties = new int[2];


        //scan through entire matrix

        for(int i = 0; i < 6; i++){

            for(int j = 0; j < 6; j++){

                int[] tmp_properties = new int[2];

                //push car onto properties

                if(!properties.containsKey(this.matrix[i][j]) && !this.matrix[i][j].equals(".")) {
//                    properties.put(this.matrix[i][j],tmp_properties);


                    //edge cases

                    //determine orientation

                    //check a direction that is within board
                    int pivot = 1;

                    //at edge of board, pivot to check negative direction instead
                    if (j == 5)
                        pivot *= -1;
                    if (i == 5)
                        pivot *= -1;


                    //check vertical

                    tmp_properties[ISVERT] = (this.matrix[i][j + pivot].equals(this.matrix[i][j])) ? 1 : 0;



                    //determine length

                    String carName = this.matrix[i][j];

                    int ipos = i;

                    int jpos = j;

                    //positive end of car
                    while(this.matrix[ipos][jpos] == carName){

                        if(tmp_properties[ISVERT] == 0)
                            ipos++;
                        else
                            jpos++;


                    }

                    int ineg = i;

                    int jneg = j;

                    //negative end of car
                    while(this.matrix[ineg][jneg] == carName){

                        if(tmp_properties[ISVERT] == 0)
                            ineg--;
                        else
                            jneg--;
                    }

                    //horizontal
                    if(tmp_properties[ISVERT] == 0){

                        tmp_properties[LENGTH] = (ipos - ineg) + 1;
                    }else
                        tmp_properties[LENGTH] = (jpos - jneg) + 1;


                    //finally, push car onto properties array

                    properties.put(this.matrix[i][j],tmp_properties);



                }


            }//inner loop

        }//outer loop





        //gets length of every car




        //gets orientation

    }

    //getters
    public String getSquare(int x, int y){

        if(x > 6 || y > 6)
            throw new IllegalArgumentException("out of bounds");

        return this.matrix[x][y];
    }

    //checks if X car in solved spot
//    public boolean isSolved();

//    public String[][] makeMove(String currCar,int steps);

//    public String[][] copyMatrix();

//

    public static void main(String[] args) {
        //Write your code here
    }

}
