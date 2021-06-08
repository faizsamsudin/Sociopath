/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sociopath;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Lidros
 */

public class TestSociopath {

    /**
     * @param args the command line arguments
     */
    private static SociopathGraph sociopath = new SociopathGraph();
    private static Student mc = new Student(); //mc of the story
    private static Random r = new java.util.Random();
    private static Student strangerEvent1;
    
    public static void main(String[] args) {
        //creating the students
        //Student(Double divingRate, Integer rep, Integer lunchTime, Integer lunchPeriod)
        //String[] nama = new String[]{"Abu", "Barb", "Chim", "Dar", "Edo", "Fad", "Gotye", "Hal", "Indie", "Jack"};
        
        for (Integer i = 1; i < 11; i++) {
            int rep = r.nextInt(11);    //generate from 0 to 10
            double diverate = 0 + (100 - 0) * r.nextDouble();    //generate from 0 to 100
            int lunchtime = ((r.nextInt(14 - 11) + 11)*100) + r.nextInt(59) ;    //generate from 1100 to 1400
            int lunchperiod = r.nextInt(60 - 5) + 5;    //generate from 5 to 60
            //sociopath.addVertex(new Student(nama[i], diverate, rep, lunchtime, lunchperiod));
            sociopath.addVertex(new Student(i.toString(), diverate, rep, lunchtime, lunchperiod));
        }
        
        pickMC();
        System.out.println("mc: " + mc.getName());
        System.out.println("");
        
//        System.out.println("The number of the vertices in graph: " + sociopath.getSize());
//        
//        System.out.println("Students and their vertices ");
//        for (int i = 0; i <= sociopath.getSize()-1; i++) {
//            System.out.println(i + ": " + sociopath.getStudent(i));
//        }
//        System.out.println("");
//        
//        System.out.println("11 in graph? " + sociopath.hasVertex("11"));
//        System.out.println("2 in graph? " + sociopath.hasVertex("2"));
//        System.out.println("");
//        
//        System.out.println("1 index: " + sociopath.getIndex("1"));
//        System.out.println("11 index: " + sociopath.getIndex("11"));
//        System.out.println("");
//        
        System.out.println("1 and 7 is now friends: " + sociopath.beFriends("1", "7", 4, 3));
        System.out.println("1 and 2 is now friends: " + sociopath.beFriends("1", "2", 5, 8));
        System.out.println("2 and 3 is now friends: " + sociopath.beFriends("2", "3", 5, 4));
        System.out.println("2 and 5 is now friends: " + sociopath.beFriends("2", "5", 6, 2));
        System.out.println("2 and 6 is now friends: " + sociopath.beFriends("2", "6", 9, 7));
        System.out.println("4 and 8 is now friends: " + sociopath.beFriends("4", "8", 7, 10));
        System.out.println("4 and 10 is now friends: " + sociopath.beFriends("4", "10", 7, 7));
        System.out.println("9 and 10 is now friends: " + sociopath.beFriends("9", "10", 5, 6));
        System.out.println("");
//        
//        System.out.println("has edge from 1 to 7: " + sociopath.isFriends("1", "7"));
//        System.out.println("has edge from 9 to 10: " + sociopath.isFriends("9", "10"));
//        System.out.println("");
//        
//        System.out.println(sociopath.getFriends("1"));
//        
//        System.out.println("");
        event1();
        System.out.println("");
        
        event2();
        System.out.println("");
        
        event3();
        System.out.println("");
        
        event4();
        System.out.println("");
    }
    
    /**
     * picking random MC
     */
    public static void pickMC(){
        mc = sociopath.getStudent(r.nextInt(10));
    }
    
    /**
     * MC teaching a stranger
     * gain 10 points for good teaching from the stranger
     * gain 2 points for bad teaching from the stranger
     * the stranger gain 2 points from MC
     * 1. determine who is the stranger to be teach
     * 2. determine bad/good teaching
     * 3. result of rep gain of MC and stranger
     * @param none
     * @return none
     */
    public static void event1(){    //for random stranger
        System.out.println("event 1 starts");
        // 1. determine who is the stranger to teach
        do {
            strangerEvent1 = sociopath.getStudent(r.nextInt(10)); //keep on changing the random student until it's stranger
            //System.out.println(sociopath.isFriends(mc.getName(), stranger.getName()));
        } while (sociopath.isFriends(mc.getName(), strangerEvent1.getName()) || mc.compareTo(strangerEvent1) == 0);
        System.out.println("mc: " + mc.getName());
        System.out.println("stranger: " + strangerEvent1.getName());
        
        //2. determine bad/good teaching
        int quality = r.nextInt(2);
        boolean good;
        if (quality == 0){   //3. bad
            good = false;
            System.out.println("mc done bad teaching");
            sociopath.beFriends(mc.getName(), strangerEvent1.getName(), 2, 2);    //mc gives 2 rep, stranger gives 2 rep
            System.out.println("rating " + mc.getName() + " from " + strangerEvent1.getName() + ": " + sociopath.getEdge(mc.getName(), strangerEvent1.getName()));
            System.out.println("rating " + strangerEvent1.getName() + " from " + mc.getName() + ": " + sociopath.getEdge(strangerEvent1.getName(), mc.getName()));
        }
        else{
            good = true;    //3. good
            System.out.println("mc done a good teaching");
            sociopath.beFriends(mc.getName(), strangerEvent1.getName(), 2, 10);   //mc gives 2 rep, stranger gives 10 rep
            System.out.println("rating " + mc.getName() + " from " + strangerEvent1.getName() + ": " + sociopath.getEdge(mc.getName(), strangerEvent1.getName()));
            System.out.println("rating " + strangerEvent1.getName() + " from " + mc.getName() + ": " + sociopath.getEdge(strangerEvent1.getName(), mc.getName()));
        }
    }
    
    /**
     * 1. identify gossiper = strangerEvent1
     * 2. identify kawan stranger
     * 3. determine good or bad talk (70% good 30% bad)
     * 4. buat edge aka beFriends
     */
    public static void event2(){  
        
        
        // 1.
        Student gossiper = strangerEvent1;
        
        //2.
        int index;
        Student listener;
        do {
            index = r.nextInt(10);
            listener = sociopath.getStudent(index);
            System.out.println("Gossiper: " + gossiper.getName());
            System.out.println("Listener: " + listener.getName());
            System.out.println("Gossiper & Listener friends: " + sociopath.isFriends(gossiper.getName(), listener.getName()));
        } while (!sociopath.isFriends(gossiper.getName(), listener.getName()) || listener == mc);
        
        //1st check mc kawan with listener
        System.out.println("1st check mc kawan with listener: " + sociopath.isFriends(mc.getName(), listener.getName()) + " " + sociopath.getEdge(mc.getName(), listener.getName()));
        
        
        // 3.
        int talk = r.nextInt(10);
        
        // 4.
        int rate = (sociopath.getEdge(mc.getName(), gossiper.getName()));
        if (talk < 7) { //klu cakap baik
            System.out.println(gossiper.getName() + " talk good, so +" + rate/2);
            sociopath.beFriends(mc.getName(), listener.getName(), 0, rate/2);
        }
        else{
            System.out.println(gossiper.getName() + " talk bad, so " + (rate-(2*rate)));
            sociopath.beFriends(mc.getName(), listener.getName(), 0, rate-(2*rate) );
        }
        
        //2nd check mc kawan with listener
        System.out.println("2nd check mc kawan with listener: " + sociopath.isFriends(mc.getName(), listener.getName()) + " " + sociopath.getEdge(mc.getName(), listener.getName()));
        System.out.println("rating " + mc.getName() + " from " + listener.getName() + ": " + sociopath.getEdge(mc.getName(), listener.getName()));
        System.out.println("rating " + listener.getName() + " from " + mc.getName() + ": " + sociopath.getEdge(listener.getName(), mc.getName()));
    }
    
    /**
     * 1. criteria of the lunch-with
     *  - friend of mc
     *  - lowest dive rate
     *  - clash
     * 2. effect after lunch
     *  - +1 rep from lunch-with
     */
    public static void event3(){
        System.out.println("Event 3: ");
        //1. determine lunch-with
        ArrayList<Student> friendsMC = sociopath.getFriends(mc.getName());
        System.out.println(friendsMC);
        ArrayList<Student> clash = new ArrayList<>();
        System.out.println(clash);
        Student lunchWith = new Student();
        
        
        for (int i = 0; i < friendsMC.size(); i++) {
            if (mc.clashLunch(friendsMC.get(i))) {
                //System.out.println("here");
                clash.add(friendsMC.get(i));
            }
            //System.out.println("mari2");
        }
        if (clash.size() == 1) {
            //System.out.println("here2");
            lunchWith  = clash.get(0);
        }
        else{
            for (int i = 0; i < clash.size()-1; i++) {
                if(clash.get(i).getDivingRate() > clash.get(i+1).getDivingRate()){   //get the lowest diving rate
                    lunchWith = clash.get(i);
                }
            }
        }
        System.out.println("lunchWith: " + lunchWith.getName());
        //System.out.println("kat sini");
        
        //2. effect after lunch(give rating)
        if (lunchWith.getName() != null) {  //if there is lunchWith
            sociopath.increaseRep(mc.getName(), lunchWith.getName(), 1);
            System.out.println("rating " + mc.getName() + " from " + lunchWith.getName() + ": " + sociopath.getEdge(mc.getName(), lunchWith.getName()));
            return;
        }
        return;
    }
    
    /**
     * 1. get the number to be generated
     * 2. generate integer (maximum 35cm) with integer count
     * 3. Stack part
     */
    public static void event4(){
        System.out.println("Event 4\n");
        Scanner s = new Scanner(System.in);
        System.out.print("Enter number of books: ");
        int num = s.nextInt();  //1. get the number to be generated
        ArrayList<Integer> listOfNum = new ArrayList<>();
        
//        Random r = new Random();
//        for (int i = 0; i < num; i++) {
//            listOfNum.add(r.nextInt(25)+10);
//        }
        listOfNum.add(3);
        listOfNum.add(1);
        listOfNum.add(10);
        listOfNum.add(7);
        listOfNum.add(3);
        listOfNum.add(5);
        listOfNum.add(6);
        listOfNum.add(6);
        
        System.out.println("Books' height: " + listOfNum);
        
        Stack<Integer> A = new Stack<>();
        Stack<Integer> B = new Stack<>();
        int counter = 0;
        
        //push into A all books' height
        for (int i = listOfNum.size()-1; i > -1; i--) {
            A.push(listOfNum.get(i));
        }
        
        //operation to discard bigger book
        ArrayList<Integer> toCheck = new ArrayList<>();
        while(A.peek() != null){
            System.out.println("push to B: "+A.peek());
            B.push(A.pop());
            System.out.println("A: "+A);
            System.out.println("B: "+B);
            if (A.peek() > B.peek()) {
                System.out.println("discard: "+A.peek());
                A.pop();
//                if (A.peek() != null) {
//                    toCheck.add(A.peek());
//                    System.out.println("push B: "+A.peek());
//                    B.push(A.pop());
//                }
            }
            counter++;
        }
//        while(true){
//            while(A.peek() != null){
//                B.push(A.pop());
//                System.out.println("A.peek(): "+A.peek());
//                System.out.println("B.peek(): "+B.peek());
//                if (A.peek() > B.peek()) {
//                    A.pop();
//                    toCheck.add(A.peek());
//                    B.push(A.pop());
//                }
//                counter++;
//            }
//            if (isIncreasing(toCheck)) {
//                while(B.peek() != null){
//                    A.push(B.pop());
//                }
//                toCheck.clear();
//            }
//            else
//                System.out.println("break");
//                break;
//        }
    }
    
    public static boolean isIncreasing(ArrayList<Integer> list){
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i)>list.get(i+1)) {
                return true;
            }
        }
        return false;
    }
}
