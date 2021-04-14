import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solver {


    public static void testClosedSet()throws Exception{

        HashSet<Node> ClosedSet = new HashSet<Node>();


        Node B = new Node("A00.txt");

        ClosedSet.add(B);

        System.out.println("B");


        B.getBoard().print_matrix();

        System.out.println();

        Node A = new Node("A",2,B);


        System.out.println("A");


        System.out.println("Hash code of A: " + A.hashCode());

        A.getBoard().print_matrix();

        System.out.println();

        ClosedSet.add(A);


        Node middleman = new Node("A",1,A);

        System.out.println("MiddleMan");

        middleman.getBoard().print_matrix();


        System.out.println();

        ClosedSet.add(middleman);


        Node A2 = new Node("A",-1,middleman);

        System.out.println("A2");


        System.out.println("Hash code of A2: " + A2.hashCode());


        A2.getBoard().print_matrix();

        System.out.println();


        //should both be true
        System.out.println("Is A2 already in the closed set? " + ClosedSet.contains(A2));

        System.out.println("Is A2 equal to A? " + A.equals(A2));


        //A and A2 have the same boards, but traversed in different ways
        //hashCode should be the same







    }


    private String createMoveList(Node endNode){

        String moveList;

        //use recursion and a stack to get to original Node

        //push onto stack to reverse list



        //recursion to pop off stack

//        moveList += pop stack




        return "";



    }

    private String toFile(Node endNode){


//        String moveList = createMoveList(endNode)
//
//
//        //output to file
//
//        String fileName;
//
//        return fileName;

        return "";
    }




    private static String Astar(Node initVertex){

        //initialize data structures
        PriorityQueue<Node> OpenQueue = new PriorityQueue<Node>(); //override comparator for heuristic implementation

        HashSet<Node> ClosedSet = new HashSet<Node>();

        OpenQueue.add(initVertex);

        //A* algorithm
        while(!OpenQueue.isEmpty()){

            Node currentNode = OpenQueue.remove();

            if(ClosedSet.contains(currentNode))
                continue;


            for(Node neighbour : currentNode.generateNeighbours()){



                //solved board
                if(neighbour.isSolved()) {

                    neighbour.print_neighbours(currentNode);


                    return "";
//                  return createFilePath(neighbour);

                }else{

                    OpenQueue.add(neighbour);

                }


            }// for loop

            ClosedSet.add(currentNode); //consider where to add stuff to the closed set

        }//while loop

        return "";
    }

    //methods
    public static void solveFromFile(String inputPath,String outputPath) throws Exception {
        //instantiate relevant classes


        //reads from file
        Node initVertex = new Node(inputPath);

        outputPath = Astar(initVertex);





    }//solveFromFile




    } //Solver

