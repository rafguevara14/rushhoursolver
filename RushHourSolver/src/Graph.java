import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

public class Graph {

    class Edge{


        String move;
        Node A;
        Node B;


        Edge(Node A, Node B,String move){

            this.A = A;

            this.B = B;

            this.move = move;
        }
        public Node getNode(Node o){

            if(o == this.A)
                return this.A;
            else
                return this.B;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return Objects.equals(move, edge.move) && Objects.equals(A, edge.A) && Objects.equals(B, edge.B);
        }

        @Override
        public int hashCode() {
            return Objects.hash(A, B);
        }

        //getters
        public String getMove(){
            return this.move;
        }

    }//Edge








    Hashtable<Integer,Edge> Edges;

    Graph(){

        this.Edges = new Hashtable<Integer,Edge>();
    }

    public Edge getEdge(Node A, Node B){

        return this.Edges.get(Objects.hash(A,B));
    }

    public String getMove(Node A, Node B){

        return this.Edges.get(Objects.hash(A,B)).getMove();
    }

    private void addEdge(Node A, Node B, String move){

        Edges.put(Objects.hash(A,B), new Edge(A,B,move));
    }

    //adds neighbours and returns nodes
    public Set<Node> addNeighbours(Node currentNode){

        Map<Node,String> neighbours = currentNode.generateNeighbours(); //key is the Node, value is move



        for(Node neighbour : neighbours.keySet()){
            //check if already in graph


            //add to graph
            addEdge(currentNode,neighbour,neighbours.get(neighbour));



        }

        return neighbours.keySet();
    }



    public static void testGraph() throws Exception {

        Graph testgraph = new Graph();

        Node initVertex = new Node("A00.txt");

        Set<Node> neighbours =  testgraph.addNeighbours(initVertex);

        for(Node neighbour : neighbours){

            System.out.println("\nONE SET OF MOVES\n");

            testgraph.getEdge(initVertex,neighbour).getNode(initVertex).print_matrix(initVertex);

            System.out.println("\nMove: " + testgraph.getMove(initVertex,neighbour));

            testgraph.getEdge(initVertex,neighbour).getNode(neighbour).print_matrix(neighbour);

            System.out.println();
        }





    }




}//Graph
