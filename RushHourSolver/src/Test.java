import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws Exception {



//        //use deepHashCode instead of hashCode. Compares deep contents of array.
//       String[] x = new String[]{"a", "b"};
//
//       String[] y = new String[]{"a", "b"};
//
//
//       System.out.println(Arrays.deepHashCode(x) == Arrays.deepHashCode(y));



//        Node test = new Node();
//
//
//
//
//        String fileName = "A01.txt";
//            System.out.println(fileName + "\n");
////
////
//            Solver.solveFromFile(fileName,"");
//
//        Solver.solveFromFile("A02.txt","");
//

//

        for(Integer i = 0; i < 10; i++){

            if(i == 1){

                System.out.print("");
            }


            String fileName = "";



            fileName += "A0" + i.toString() + ".txt";

            System.out.println(fileName + "\n");


            Solver.solveFromFile(fileName,"");

        }

        String file = "A10.txt";

        System.out.println(file + "\n");


        Solver.solveFromFile(file,"");


        for(Integer i = 1; i < 10; i++){

            String fileName = "";



            fileName += "B1" + i.toString() + ".txt";

            System.out.println(fileName + "\n");


            Solver.solveFromFile(fileName,"");

        }

        String fileName = "B20.txt";

        System.out.println(fileName + "\n");


        Solver.solveFromFile(fileName,"");

        for(Integer i = 1; i < 10; i++){

            file = "";

            file += "C2" + i.toString() + ".txt";

            System.out.println(file + "\n");


            Solver.solveFromFile(file,"");

        }


        for(Integer i = 0; i < 6; i++){

            file = "";

            file += "D3" + i.toString() + ".txt";

            System.out.println(file + "\n");


            Solver.solveFromFile(file,"");

        }




//        Solver.testClosedSet();

//        Solver.testClosedMap();
//        Node.generate_neighbours_test("A00.txt");

//        //[0,0]
//        test.ROMtest("O",5,0,"A00.txt");
//
//        //[0,3] //3
//        test.ROMtest("X",0,2,"A00.txt");
//
//
//        //[0,3] //3 boards
//        test.ROMtest("A",0,0,"A00.txt");
//
//        //[-3,0] //3
//        test.ROMtest("Q",3,3,"A00.txt");
//
//        //[-4,0] //4
//        test.ROMtest("C",4,4,"A00.txt");
//
//        //[-2,1] //3
//        test.ROMtest("R",2,5,"A00.txt");
//
//
//        //should have 16 neighbours
//
//        //[0,0]
//        test.ROMtest("A",0,0,"B16.txt");
//
//        //[0,0]
//        test.ROMtest("B",2,0,"B16.txt");
//
//
//        //[0,2]
//        test.ROMtest("D",0,1,"B16.txt");
//
//
//        //[-1,0]
//        test.ROMtest("E",2,1,"B16.txt");
//
//        //[-1,1]
//        test.ROMtest("F",1,2,"B16.txt");
//
//        //[0,4]
//        test.ROMtest("G",0,5,"B16.txt");
//
//        //[0,1]
//        test.ROMtest("P",2,2,"B16.txt");
//
//
//        //[0,0]
//        test.ROMtest("Q",3,3,"B16.txt");
//
//
//        //[0,0]
//        test.ROMtest("X",3,2,"B16.txt");


//
//
//
//
//        Board testboard = new Board("A00.txt");
//
//        testboard.print_matrix();
//
//        testboard.print_properties();
//
//
//
//        System.out.println("\n\n");
//
//        Board nextstate = new Board("X",2,testboard);
//
//
//        nextstate.print_matrix();
//
//        nextstate.print_properties();



//        createMoveList testing:
        Node initNode = new Node("A00.txt");
        Node node2 = new Node("X", 2, initNode);
        Node node3 = new Node("X", 1, node2);

//        should return :
//        XR2
//        XR1
        Solver.moveListTest(node2);


//        toFile testing - with :

//        toFile testing - with moveList:



    }
}
;