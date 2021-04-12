//public class Edge {
//}
//
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