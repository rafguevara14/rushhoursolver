//import java.util.*;
//
//public class Graph {
//
//
//
//
//    class Edge{
//
//
//        String move;
//        Node A;
//        Node B;
//
//
//        Edge(Node A, Node B,String move){
//
//            this.A = A;
//
//            this.B = B;
//
//            this.move = move;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Edge edge = (Edge) o;
//            return Objects.equals(move, edge.move) && Objects.equals(A, edge.A) && Objects.equals(B, edge.B);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(A, B);
//        }
//
//        //getters
//        public String getMove(){
//            return this.move;
//        }
//
//    }//Edge
//
//    Hashtable<Integer,Edge> Edges = new Hashtable<Integer,Edge>();
//
//    public Edge getEdge(Node A, Node B){
//
//        return this.Edges.get(Objects.hash(A,B));
//    }
//
//    private void addEdge(Node A, Node B, String move){
//
//        Edges.put(Objects.hash(A,B), new Edge(A,B,move));
//    }
//
//    //adds neighbours and returns nodes
//    public ArrayList<Node>  addNeighbours(Node currentNode){
//
//        ArrayList<Node> neighbours = currentNode.generateNeighbours();
//
//
//
//        for(Node neighbour : neighbours){
//            //check if already in graph
//
//
//            //add to graph
//
//            currentNode
//
//
//
//        }
//
//
//
//
//    }
//
//
//}//Graph
