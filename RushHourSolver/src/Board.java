import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {

    private String[][] matrix = new String[6][6];

    private Map<String,int[]> properties = new HashMap<>();


    private final int LENGTH = 0;

    private final int ISVERT = 1; //1 is vertical


    //testing methods

    public void print_properties(){

        properties.forEach((key, value) -> System.out.println(key + ":" + value[LENGTH] + ", " + value[ISVERT]));



    }


    public void print_matrix(){


        for(int i = 0; i < 6; i++){

            for(int j = 0; j <6; j++){

//                System.out.print(i+  ","+ j + "   " + this.matrix[i][j]);

                System.out.print(this.matrix[i][j]);

            }

            System.out.println();
        }

    }





    //methods


    //getters
    public String getSquare(int x, int y){

        if(x > 6 || y > 6)
            throw new IllegalArgumentException("out of bounds");

        //matrix is inverted
        return this.matrix[y][x];
    }

    Board(String inputpath) throws FileAlreadyExistsException, FileNotFoundException {

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

        // System.out.println("Matrix \n\n\n");
        // System.out.println(this.matrix[0][1]);

//        int[] tmp_properties = new int[2];


        //scan through entire inverted matrix

        for(int i = 0; i < 6; i++){

            for(int j = 0; j < 6; j++){

                int[] tmp_properties = new int[2];

                //push car onto properties

                if(!properties.containsKey(getSquare(i,j)) && !getSquare(i,j).equals(".")) {
//                    properties.put(this.matrix[i][j],tmp_properties);


                    //check vertical

                    if(j == 5)

                        tmp_properties[ISVERT] = 0;

                    else if(i == 5)

                        tmp_properties[ISVERT] = 1;

                    else //check vertical
                        tmp_properties[ISVERT] = ((getSquare(i,j+1).equals(getSquare(i,j))) ? 1 : 0);



                    //determine length

                    String carName = getSquare(i,j);

                    int ipos = i;

                    int jpos = j;

                    //positive end of car
                    while(ipos < 6 && jpos < 6){

                        if(!getSquare(ipos,jpos).equals(carName))
                            break;

                        if(tmp_properties[ISVERT] == 0)
                            ipos++;
                        else
                            jpos++;


                    }

                    //step back to end of car
                    if(tmp_properties[ISVERT] == 0)
                        ipos--;
                    else
                        jpos--;



                    int ineg = i;

                    int jneg = j;

                    //negative end of car
                    while(ineg >= 0 && jneg >= 0){

                        if(!getSquare(ineg,jneg).equals(carName))
                            break;


                        if(tmp_properties[ISVERT] == 0)
                            ineg--;
                        else
                            jneg--;
                    }

                    //step back to end of car
                    if(tmp_properties[ISVERT] == 0)
                        ineg++;
                    else
                        jneg++;

                    //horizontal
                    if(tmp_properties[ISVERT] == 0){

                        tmp_properties[LENGTH] = (ipos - ineg) + 1;
                    }else
                        tmp_properties[LENGTH] = (jpos - jneg) + 1;


                    //finally, push car onto properties array

                    properties.put(getSquare(i,j),tmp_properties);

                }


            }//inner loop

        }//outer loop

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
