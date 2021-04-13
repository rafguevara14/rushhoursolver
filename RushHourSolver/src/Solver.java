import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Solver {


    private static String createMoveList(Node node){

        String moveList = "";
        Stack<String> stack = new Stack<>();
        Node parent = node.getParent();


//        while node has parent, get the move and store it in stack
        while (parent != null) {
//            stack.push(getMove(node, parent));
            node = parent;
            node.setParent(node.getParent());
        }


//        pop from stack and concatenate to a String
        while (!stack.isEmpty())
            moveList.concat(stack.pop()).concat("\n");


        return moveList.substring(0, moveList.length() - 1);  //to get rid of the last newline character

    }

    private String toFile(Node endNode){


        String moveList = createMoveList(endNode);
        
//        //output to file
//
//        String fileName;
//
//        return fileName;

        return "";
    }

    public static void moveListTest(Node testNode){
        System.out.println(createMoveList(testNode));
    }



//    actual A* algo. takes initial node and returns output path
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

//          for every node in currentNode's neighbours
            for(Node neighbour : currentNode.generateNeighbours()){



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

        //makes an empty output file called outputPath
        File outputFile = new File(outputPath);

        outputPath = Astar(initVertex);

    }//solveFromFile




    } //Solver

