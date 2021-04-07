public class Test {

    public static void main(String[] args) throws Exception {

        Board testboard = new Board("A00.txt");

        testboard.print_matrix();

        testboard.print_properties();




        System.out.println("\n\n");

        Board nextstate = new Board("X",2,testboard);


        nextstate.print_matrix();

        nextstate.print_properties();


    }



}
