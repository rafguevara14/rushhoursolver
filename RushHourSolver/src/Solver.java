import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;

import java.util.*;

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


    public static void testClosedMap()throws Exception{

        HashMap<Integer,Node> ClosedSet = new HashMap<Integer,Node>();


        Node B = new Node("A00.txt");

        ClosedSet.put(B.hashCode(),B);

        System.out.println("B");


        B.getBoard().print_matrix();

        System.out.println();

        Node A = new Node("A",2,B);


        System.out.println("A");


        System.out.println("Hash code of A: " + A.hashCode());

        A.getBoard().print_matrix();

        System.out.println();

        ClosedSet.put(A.hashCode(),A);


        Node middleman = new Node("A",1,A);

        System.out.println("MiddleMan");

        middleman.getBoard().print_matrix();


        System.out.println();

        ClosedSet.put(middleman.hashCode(),middleman);


        Node A2 = new Node("A",-1,middleman);

        System.out.println("A2");


        System.out.println("Hash code of A2: " + A2.hashCode());


        A2.getBoard().print_matrix();

        System.out.println();


        //should both be true
        System.out.println("Is A2 already in the closed set? " + ClosedSet.containsKey(A2.hashCode()));

        System.out.println("Is A2 equal to A? " + A.equals(A2));


        //A and A2 have the same boards, but traversed in different ways
        //hashCode should be the same

    }

    private static String createMoveList(Node node){

        String moveList = "";
        Stack<String> stack = new Stack<>();
        Node parent = node.getParent();

        //push onto stack to reverse list

        //recursion to pop off stack

//        moveList += pop stack



//        while node has parent, get the move and store it in stack
        while (parent != null) {
            stack.push(node.getMove());
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

//        String[][] answer={ {".",".",".",".","A","A"},
//                            {".",".",".",".",".","."},
//                            {".",".",".",".","X","X"},
//                            {".",".","Q","Q","Q","O"},
//                            {"C","C",".",".",".","O"},
//                            {".","R","R","R",".","O"},
//
//
//                        };


//        ....AA
//        ......
//        ....XX
//        ..QQQO
//        CC...O
//        .RRR.O

//        int count= 0;

        //initialize data structures
        PriorityQueue<Node> OpenQueue = new PriorityQueue<Node>(); //override comparator for heuristic implementation

//        HashSet<Node> ClosedSet = new HashSet<Node>();

        HashMap<Integer,Node> ClosedSet = new HashMap<Integer,Node>();

        OpenQueue.add(initVertex);

        //A* algorithm
        while(!OpenQueue.isEmpty()){

            Node currentNode = OpenQueue.remove();

//            if(OpenQueue.isEmpty()){
//
//                System.out.print("almost done!\n");
//            }
//
//            if(Arrays.deepEquals(currentNode.getBoard().getMatrix(), answer)){
//
//                System.out.println("Solution found!");
//            }

            if(ClosedSet.containsKey(currentNode.hashCode()))
                continue;

            //go through currentNode's neighbours

            for(Node neighbour : currentNode.generateNeighbours()){

                if(ClosedSet.containsKey(neighbour.hashCode()))
                    continue;


                //solved board
                if(neighbour.isSolved()) {

                    neighbour.getBoard().print_matrix();

                    neighbour.setParent(currentNode);

                    return "Found Solution!";
//                  return createFilePath(neighbour);

                }else{


                    OpenQueue.add(neighbour);

                    neighbour.setParent(currentNode);
                }


//                count++;

            }// for loop

//            if(count == 38494){
//
//                currentNode.getBoard().print_matrix();
//
//
//
//                System.out.println("Hi");
//            }

            //mark as visited
            ClosedSet.put(currentNode.hashCode(),currentNode); //consider where to add stuff to the closed set

        }//while loop


//        System.out.println("Count: " + count);
        return "No solution found :(";
    }

    //methods
    public static void solveFromFile(String inputPath,String outputPath) throws Exception {
        //instantiate relevant classes


        //reads from file
        Node initVertex = new Node(inputPath);


//        outputPath = Astar(initVertex);

          //testing code
//        System.out.println(Astar(initVertex));

        //makes an empty output file called outputPath
//        File outputFile = new File(outputPath);


//        outputPath = Astar(initVertex);

    }//solveFromFile




    } //Solver

