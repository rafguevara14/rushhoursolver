import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Board {

    private String[][] matrix = new String[6][6];

    private final static Map<String, int[]> properties = new HashMap<>();

    public final static int LENGTH = 0;

    public final static int ISVERT = 1; //1 is vertical


    //testing methods

    public void print_properties() {

        properties.forEach((key, value) -> System.out.println(key + ":" + value[LENGTH] + ", " + value[ISVERT]));


    }


    public void print_matrix() {


        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 6; j++) {

//                System.out.print(i+  ","+ j + "   " + this.matrix[i][j]);

                System.out.print(this.matrix[i][j]);

            }

            System.out.println();
        }

    }


    //methods



    //getters
    public String getSquare(int x, int y) {

        if (x >= 6 || y >= 6 || x < 0 || y < 0)
            throw new IllegalArgumentException("out of bounds");

        //matrix is inverted
        return this.matrix[y][x];
    }

    public boolean isSameCar(String currCar, int x, int y){

        if (x >= 6 || y >= 6 || x < 0 || y < 0)
            return false;

        if(!properties.containsKey(currCar))
            throw new NoSuchElementException("That car does not exist in the board\n");

        return getSquare(x,y).equals(currCar);
    }

    public boolean inBounds(String currCar,int x,int y){

        return (x <= 5 && y <= 5 && x >= 0 && y >= 0);
    }


    private int getProperty(String key, int property){

        if(property != LENGTH && property != ISVERT)
            throw new NoSuchElementException("The given property does not exist\n");
        return properties.get(key)[property];

    }

    public int getLength(String key){

        if(!properties.containsKey(key))
            throw new NoSuchElementException("That car does not exist within properties array\n");

        return getProperty(key,LENGTH);

    }

    public boolean isVertical(String key){

        if(!properties.containsKey(key))
            throw new NoSuchElementException("That car does not exist within properties array\n");

        return (getProperty(key,ISVERT) == 1);
    }

    //setters
    public void setSquare(int x,int y,String value){
        if (x > 6 || y > 6)
            throw new IllegalArgumentException("out of bounds");

        this.matrix[y][x] = value;

    }

    Board(String currCar,int steps,Board oldBoard){

        //copy Matrix
        makeMove(currCar,steps,oldBoard);
    }


    //reads from file
    Board(String inputpath) throws FileAlreadyExistsException, FileNotFoundException {

        //creates the board
        File file = new File(inputpath);

        Scanner scanner = new Scanner(file);


        if (!file.exists())
            throw new FileAlreadyExistsException("Could not find file: " + inputpath);

        //iterate through file and store elements in 2D matrix

        String row;

        while (scanner.hasNext()) {

            for (int i = 0; i < 6; i++) {

                row = scanner.next();

                for (int j = 0; j < 6; j++) {

                    this.matrix[i][j] = String.valueOf(row.charAt(j));

                }//inner loop

            }//outer loop


        }//file iteration

        // System.out.println("Matrix \n\n\n");
        // System.out.println(this.matrix[0][1]);

//        int[] tmp_properties = new int[2];


        //scan through entire inverted matrix

        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 6; j++) {

                int[] tmp_properties = new int[2];

                //push car onto properties

                if (!properties.containsKey(getSquare(i, j)) && !getSquare(i, j).equals(".")) {
//                    properties.put(this.matrix[i][j],tmp_properties);


                    //check vertical

                    if (j == 5)

                        tmp_properties[ISVERT] = 0;

                    else if (i == 5)

                        tmp_properties[ISVERT] = 1;

                    else //check vertical
                        tmp_properties[ISVERT] = ((getSquare(i, j + 1).equals(getSquare(i, j))) ? 1 : 0);


                    //determine length

                    String carName = getSquare(i, j);

                    int ipos = i;

                    int jpos = j;

                    //positive end of car
                    while (ipos < 6 && jpos < 6) {

                        if (!getSquare(ipos, jpos).equals(carName))
                            break;

                        if (tmp_properties[ISVERT] == 0)
                            ipos++;
                        else
                            jpos++;


                    }

                    //step back to end of car
                    if (tmp_properties[ISVERT] == 0)
                        ipos--;
                    else
                        jpos--;


                    int ineg = i;

                    int jneg = j;

                    //negative end of car
                    while (ineg >= 0 && jneg >= 0) {

                        if (!getSquare(ineg, jneg).equals(carName))
                            break;


                        if (tmp_properties[ISVERT] == 0)
                            ineg--;
                        else
                            jneg--;
                    }

                    //step back to end of car
                    if (tmp_properties[ISVERT] == 0)
                        ineg++;
                    else
                        jneg++;

                    //horizontal
                    if (tmp_properties[ISVERT] == 0) {

                        tmp_properties[LENGTH] = (ipos - ineg) + 1;
                    } else
                        tmp_properties[LENGTH] = (jpos - jneg) + 1;


                    //finally, push car onto properties array

                    properties.put(getSquare(i, j), tmp_properties);

                }


            }//inner loop

        }//outer loop

    }

    //    checks if board is solved
    public boolean isSolved() {
        return (getSquare(2,5).equals("X") ) && (getSquare(2,4).equals("X"));
    }


    private int[] initCoordinates(String carName){

        int[] coord = new int[2];

        for(int x = 0; x < 6; x++){

            for(int y = 0; y < 6; y++) {

                if (getSquare(x, y).equals(carName)){

                    coord[0] = x;
                    coord[1] = y;

                    return coord;

                }

            }//inner for loop


        }
        throw new NoSuchElementException("Could not find Car in matrix");

    }



    //    returns a matrix with currCar moved steps steps. negative steps moves left or down. pos moves right or up
    private void makeMove(String currCar,int steps,Board oldBoard){
        copyMatrix(oldBoard.matrix);
        int[] propertyArr = properties.get(currCar);

        int carLength = propertyArr[0]; //for readability. doesn't have to be declared as variable. change later
        int carOrientation = propertyArr[1]; //for readability. doesn't have to be declared as variable. change later

        int[] initCoordinates = initCoordinates(currCar);



//        if car moves horizontally
        if (carOrientation == 0){
            for (int i=0; i< carLength; i++) {
                setSquare(initCoordinates[0] + steps + i,initCoordinates[1],currCar);
                setSquare(initCoordinates[0] + i,initCoordinates[1],".");
            }
        }


//         if car moves vertically
        if (carOrientation == 1){
            for (int j=0; j< carLength; j++) {
                setSquare(initCoordinates[0],initCoordinates[1] + steps + j,currCar);
                setSquare(initCoordinates[0],initCoordinates[1] + j,".");
            }
        }
    }


    //    receives a matrix and returns a duplicate of it
    private void copyMatrix(String[][] oldMatrix) {
        this.matrix = new String[6][6];
        for (int i=0; i<6; i++){
            for (int j=0; j<6; j++)
                this.matrix[i][j]=oldMatrix[i][j];
        }
    }





}//Board class

