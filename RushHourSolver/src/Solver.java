import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solver {

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

        HashSet<Node> ClosedSet = new HashSet<Node>(){

            @Override
            public boolean equals(Object o) {

                //implement hashCode for two boards look the same
                return false;
            }


        }; //implement equals method


        OpenQueue.add(initVertex);

        //Astar algorithm

        while(OpenQueue.isEmpty()){

            Node currentNode = OpenQueue.remove();

            if(ClosedSet.contains(currentNode))
                continue;


            for(Node neighbour : currentNode.getNeighbours()){



                //solved board
                if(neighbour.isSolved()) {
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

