import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Board {

    private String[][] matrix = new String[6][6];

    private static Map<String, int[]> properties;


    public final static int LENGTH = 0;

    public final static int ISVERT = 1; //1 is vertical

    private String move  = "";

    /* getters */
    public String[][] getMatrix(){
        return this.matrix;
    }

    public String getSquare(int x, int y) {

        if (x >= 6 || y >= 6 || x < 0 || y < 0)
            throw new IllegalArgumentException("out of bounds");

        //matrix is inverted
        return this.matrix[y][x];
    }

    public String getMove(){
        return this.move;
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

    public boolean inBounds(String currCar,int x,int y){

        return (x <= 5 && y <= 5 && x >= 0 && y >= 0);
    }

    public boolean isVertical(String key){

        if(!properties.containsKey(key))
            throw new NoSuchElementException("That car does not exist within properties array\n");

        return (getProperty(key,ISVERT) == 1);
    }

    public boolean isSolved() {
        return (getSquare(5,2).equals("X") ) && (getSquare(4,2).equals("X"));
    }

    /* setters */

    public void setSquare(int x,int y,String value){
        if (x > 6 || y > 6)
            throw new IllegalArgumentException("out of bounds");

        this.matrix[y][x] = value;
    }

    /* constructors */

    //constructs copy of old board with one move made
    Board(String currCar,int steps,Board oldBoard){

        //copy Matrix
        makeMove(currCar,steps,oldBoard);
    }

    //reads from file
    Board(String inputpath) throws FileAlreadyExistsException, FileNotFoundException {

        //overwrite old properties; are in a new board
        properties = new HashMap<String,int[]>();

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

        //scan through entire inverted matrix

        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 6; j++) {

                int[] tmp_properties = new int[2];

                //push car onto properties
                if (!properties.containsKey(getSquare(i, j)) && !getSquare(i, j).equals(".")) {

                    //check edge cases (ie at edge of board)
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

    /* main methods */

    //set this matrix with currCar moved 'steps' steps. negative steps moves left or down. pos moves right or up
    private void makeMove(String currCar,int steps,Board oldBoard){

        String direction = "";

        //find direction of move

        if(isVertical(currCar)){

            direction += steps > 0 ? "D" : "U";

        }else {
            direction += steps > 0 ? "R" : "L";
        }

        //set move
        this.move += currCar + direction + Math.abs(steps) + "\n";

        //set matrix to be copy of oldMatrix
        this.copyMatrix(oldBoard.matrix);

        //coordinates at the left/upmost part of car
        int[] initCoordinates = initCoordinates(currCar);

        int itmp = 0;

        //Pre-condition: initCoordinates should be "pushing the car"

        //if car moves horizontally
        if (!isVertical(currCar)) {

            //if moving left, "push" from other side of car
            if (steps < 0) {
                initCoordinates[0] += getLength(currCar) - 1;
            }

            for (int i = 0; i < getLength(currCar); i++) {

                //if moving in negative direction, set i to be negative
                itmp = i;
                if (steps < 0) {
                    itmp *= -1;
                }

                //move car
                setSquare(initCoordinates[0] + steps + itmp, initCoordinates[1], currCar);
            }

            //clean up path (with dots)
            for (int i = 0; i < Math.abs(steps); i++) {

                //if moving in negative direction, set i to be negative
                itmp = i;
                if (steps < 0)
                    itmp *= -1;

                setSquare(initCoordinates[0] + itmp, initCoordinates[1], ".");
            }

        }//outer if statement

        itmp = 0;

//         if car moves vertically
        if (isVertical(currCar)){

            //if moving up, "push" from other side of car
            if(steps < 0)
                initCoordinates[1] += getLength(currCar)-1;

            for (int i=0; i < getLength(currCar); i++) {

                //if moving in negative direction, set i to be negative
                itmp = i;
                if (steps < 0) {
                    itmp *= -1;
                }

                setSquare(initCoordinates[0],initCoordinates[1] + steps + itmp,currCar);
            }

            //clean up path (with dots)
            itmp = 0;

            for (int i = 0; i < Math.abs(steps); i++) {

                //if moving in negative direction, set i to be negative
                itmp = i;
                if (steps < 0)
                    itmp *= -1;

                setSquare(initCoordinates[0], initCoordinates[1] + itmp, ".");
            }

        }//outer if statement

    }//makeMove

    //find coordinates of top leftmost part of car
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

    //creates a copy of oldMatrix and sets to 'this.matrix'
    private void copyMatrix(String[][] oldMatrix) {
        this.matrix = new String[6][6];
        for (int i=0; i<6; i++){
            for (int j=0; j<6; j++)
                this.matrix[i][j]=oldMatrix[i][j];
        }
    }

    /* test functions */

    public void print_properties() {

        properties.forEach((key, value) -> System.out.println(key + ":" + value[LENGTH] + ", " + value[ISVERT]));
    }

    public void print_matrix() {

        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 6; j++) {
                System.out.print(this.matrix[i][j]);
            }
            System.out.println();
        }
    }

}//Board class

