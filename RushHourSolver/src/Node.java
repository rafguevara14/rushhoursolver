import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

public class Node implements Comparable<Node>{

    private int H;
    private int G;
    private int F;

    private Node parent;


//    private static HashSet<Node> allNodes = new HashSet<Node>();

    Board board;

    Node(){

        this.board = null;

        this.H = 0;

        this.G = 0;

        this.F = 0;

        this.parent = null;

    }

    //read from file
    Node(String file) throws FileAlreadyExistsException, FileNotFoundException {

        this.board = new Board(file);

        this.H = 0;

        this.G = 0;

        this.F = 0;

        this.parent = null;

    }


    //creates a new Node from old board with one move executed
    Node(String currCar, int steps,Node oldNode){

        //create new Board
        this.board = new Board(currCar,steps, oldNode.board);

        //update H
        this.H = 0; //for now...

        //update G
        this.G = oldNode.getG()+1;

        //update F
        this.F = this.H + this.G;

        this.parent = oldNode;



    }

    //getters

    public int getG() {
        return this.G;
    }

    public int getF() {
        return this.F;
    }

    public int getH() {
        return this.H;
    }

//    public Set<Node> getNeighbours(){
//        return this.neighbours.keySet();
//    }


    public void ROMtest(String currCar, int x, int y, String file) throws FileAlreadyExistsException, FileNotFoundException {

        this.board = new Board(file);

        int ROM[] = ROM(currCar,x,y);

        System.out.println("ROM of " + currCar + " is [" + ROM[0] + "," + ROM[1] + "]\n");


    }

    public void print_matrix(Node test){

        this.board.print_matrix();



    }
    public void print_neighbours(Node test){

        this.board.print_matrix();

      Set<Node> neighbours = test.generateNeighbours().keySet();

        for(Node neighbour : neighbours){

            neighbour.board.print_matrix();

            System.out.println(neighbour.board.getMove());


            System.out.println("\n");
        }


        System.out.println("Number of Neighbours: " + neighbours.size());


    }


    public static void generate_neighbours_test(String file) throws FileAlreadyExistsException, FileNotFoundException {

        //some node to generate neighbours...read from file

        Node test = new Node(file);

        test.print_neighbours(test);



    }

    public boolean isSolved(){

        return this.board.isSolved();
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


        //leftmost/upmost coordinate of car
        int xtmp = x;

        int ytmp = y;

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


        //reset coordinates (leftmost/upmost coordinate of car)
        xtmp = x;
        ytmp = y;

        //get to right/downmost part of car
        if(this.board.isVertical(currCar))

            ytmp += this.board.getLength(currCar)-1;
        else
            xtmp += this.board.getLength(currCar)-1;


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

    }//ROM function

    //return all possible neighbours as an ArrayList of Nodes
    public Map<Node,String> generateNeighbours() {

        ArrayList<String> visitedCars = new ArrayList<String>(); //closed set

        Map<Node,String> neighbours = new HashMap<Node,String>(); //key is the Node, value is move

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
                        for (int step = ROM[0]; step <= ROM[1]; step++) {

                            //don't include moves with zero steps
                            if(step == 0)
                                continue;


                            Node neighbour = new Node(currCar, step,this);

                            String move = neighbour.board.getMove();

                            neighbours.put(neighbour,move);
                        }


                    }//new car
                }//car found
            }//j
        }//i (main for loop)

//        this.neighbours = neighbours;

        return neighbours;

    }//generate neighbours function

    @Override
    public int compareTo(Node o) {
        return 0;
    }
}//Node
