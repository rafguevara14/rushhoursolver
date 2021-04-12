//import java.util.*;
//
//public class Graph {
//
//    public static void testAddEdge(){
//
////
////        A -. D .- E ..- F
////         \   |   /     |
////       ....  .  ..     .
////           \ | /      /
////             B  ---- G
////            ...       /
////             I      ...
////            . .    /
////           H   FINAL
//
//        Graph testGraph = new Graph();
//
//
//
//        //AD
//        testGraph.addEdge(new Node(),new Node(),".");
//
//
//        //DE
//        testGraph.addEdge(new Node(),new Node(),".");
//
//
//        //AD
//        testGraph.addEdge(new Node(),new Node(),".");
//
//
//        //AD
//        testGraph.addEdge(new Node(),new Node(),".");
//
//
//        //AD
//        testGraph.addEdge(new Node(),new Node(),".");
//
//
//        //AD
//        testGraph.addEdge(new Node(),new Node(),".");
//
//
//
//
//
//
//    }
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
//    public void addEdge(Node A, Node B, String move){
//
//        Edges.put(Objects.hash(A,B), new Edge(A,B,move));
//    }
//
//}//Graph
