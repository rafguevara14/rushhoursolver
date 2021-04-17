import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws Exception {


//
//        HashMap<Integer, Byte> properties = new HashMap<Integer, Byte>();
//
//        properties.put()

//        Byte x = 0b1000;
//
//        x = (byte) (x | (byte) ((5 - 3) + 1));
//
//        int length = (int) (0b0111 & x);
//
//
//        System.out.println(length);
//
//
////        System.out.println((x & 0b0011));
//
//        if((x&1000) != 0)
//            System.out.println(true);
//        else
//
//            System.out.println(false);
//






        //java functions test

//        String hello = "hello";
//
//        hello.concat(" world\n");
//
//
//
//
//        //movelist test
//
//        Solver.moveListTest("A00.txt");
//
//



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


//        String fileName = "A01.txt";
//            System.out.println(fileName + "\n");
////
////
//            Solver.solveFromFile(fileName,"");
//
//        Solver.solveFromFile("A02.txt","");
//
//


        String fileName;

        for(Integer i = 0; i < 10; i++){

            fileName = "A0" + i.toString() + ".txt";

            System.out.println(fileName + "\n");

            String outpath = "ourA0" + i.toString() + ".sol";


            Solver.solveFromFile(fileName,outpath);

        }

        String file = "A10.txt";

        System.out.println(file + "\n");


        Solver.solveFromFile(file,"ourA10.sol");


        for(Integer i = 1; i < 10; i++){

            fileName = "";


            fileName += "B1" + i.toString() + ".txt";

            System.out.println(fileName + "\n");


            String outpath = "ourB1" + i.toString() + ".sol";


            Solver.solveFromFile(fileName,outpath);

        }

        fileName = "B20.txt";

        System.out.println(fileName + "\n");


        Solver.solveFromFile(fileName,"ourB20.sol");

        for(Integer i = 1; i < 10; i++){

            file = "";

            file += "C2" + i.toString() + ".txt";

            System.out.println(file + "\n");


            String outpath = "ourC2" + i.toString() + ".sol";

            Solver.solveFromFile(file,outpath);

        }


        for(Integer i = 0; i < 6; i++){

            file = "";

            file += "D3" + i.toString() + ".txt";

            System.out.println(file + "\n");

            String outpath = "ourD3" + i.toString() + ".sol";



            Solver.solveFromFile(file,outpath);

        }


        for(Integer i = 0; i < 5; i++){

            file = "";

            file += "F0" + i.toString() + ".txt";

            System.out.println(file + "\n");


            String outpath = "ourF0" + i.toString() + ".sol";

            Solver.solveFromFile(file,outpath);

        }



//
//        String file = "";
//
//            file += "D35.txt";
//
//            System.out.println(file + "\n");
//
////
//            Solver.solveFromFile(file,"ourD35.sol");
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


//
////        createMoveList testing:
//        Node initNode = new Node("A00.txt");
//
//
////        Node node2 = new Node("X", 2, initNode);
////        Node node3 = new Node("X", 1, node2);
//
////        should return :
////        XR2
////        XR1
//        Solver.moveListTest(node2);


//        toFile testing - with :

//        toFile testing - with moveList:



    }
}
;