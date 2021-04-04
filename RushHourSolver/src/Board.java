import java.util.HashMap;
import java.util.Map;

public class Board {

    private String[][] matrix = new String[6][6];

    private Map<String,int[]> properties = new HashMap<>();

    //methods

    //sets matrix
//    private void readFromFile();

    Board(String inputpath){

        //creates the board
//        readFromFile();

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

}
