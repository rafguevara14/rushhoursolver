import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

import java.util.*;

public class Solver {

    public static void solveFromFile(String inputPath,String outputPath) throws Exception {

        //reads from file
        Node initVertex = new Node(inputPath);

        //run A* algorithm
        Node solutionNode = Astar(initVertex);

        //output solution moves to textfile
        String moveList = createMoveList(solutionNode);
        sendtoFile(outputPath,moveList);

    }

    //    actual A* algo. takes initial node and returns solution node
    private static Node Astar(Node initVertex){

        //initialize data structures
        PriorityQueue<Node> OpenQueue = new PriorityQueue<Node>();

        HashMap<Integer,Node> ClosedSet = new HashMap<Integer,Node>();

        OpenQueue.add(initVertex);

        //A* algorithm
        while(!OpenQueue.isEmpty()){

            Node currentNode = OpenQueue.remove();

            if(ClosedSet.containsKey(currentNode.hashCode()))
                continue;

            //go through currentNode's neighbours
            for(Node neighbour : currentNode.generateNeighbours()){

                if(ClosedSet.containsKey(neighbour.hashCode()))
                    continue;

                //solved board
                if(neighbour.isSolved()) {

//                    neighbour.getBoard().print_matrix();

                    neighbour.setParent(currentNode);

//                    System.out.println("Found Solution!\n");

                    return neighbour;

                }else{

                    OpenQueue.add(neighbour);

                    neighbour.setParent(currentNode);
                }

            }// for loop

            //mark as visited
            ClosedSet.put(currentNode.hashCode(),currentNode);

        }//while loop

        System.out.println("NO SOLUTION FOUND :(");

        return null;
    }

    private static String createMoveList(Node node){

        String moveList = "";
        Stack<String> stack = new Stack<String>();

        //push moves from end to beginning onto stack
        while(node != null){

            stack.push(node.getMove());

            //traverse 'up' graph
            node = node.getParent();
        }


        //pop stack and concatenate to string
        while (!stack.isEmpty())
            moveList = moveList.concat(stack.pop());

        return moveList;
    }

    private static void sendtoFile(String outputPath,String moveList) {

        //code block referenced from: https://www.w3schools.com/java/java_files_create.asp
        try {
            FileWriter myWriter = new FileWriter(outputPath);
            myWriter.write(moveList);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /* static test functions */

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

    public static void moveListTest(String file) throws Exception {

        //createMoveList testing:
        Node initNode = new Node(file);

        System.out.println("Old Board\n");
        initNode.getBoard().print_matrix();

        System.out.println(Astar(initNode));
    }




} //Solver

