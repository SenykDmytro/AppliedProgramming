package com.senyk.laba3;

import com.senyk.laba3.droid.Droid;
import com.senyk.laba3.droid.exceptions.DroidDeadException;
import com.senyk.laba3.droid.exceptions.WrongDataValue;
import com.senyk.laba3.droid.model.Type;
import com.senyk.laba3.droid.status.Status;

import java.util.List;
import java.util.Scanner;

public class Application {
    ListOfDroids listOfDroids =new ListOfDroids();
    public void start(){
        System.out.println("Hello, gamer.");
        System.out.println("Now we will start a new game.");
        System.out.println("Choose the direction you want(print number of direction):");
        System.out.println("    1) create own droids");
        System.out.println("    2) load already created droids");
        System.out.println("    3) load previous game");
        Scanner scanner = new Scanner(System.in);
        int variant;
        try {
             variant= scanner.nextInt();
             if (variant<1||variant>3)throw new Exception();
             System.out.println("You choose "+variant+" option");
             switch (variant){
                 case 1:
                 case 2:
                 case 3:
                     createNewDroid();
             }
             OneVsOne();
        } catch (Exception exception) {
            scanner.close();
            System.out.println("You have entered incorrect data:"+".");
            System.out.println("Game will restart.");
            start();
            return;
        }
    }

    public void createNewDroid(){
        System.out.println("Set the parameters for the new droid");
        System.out.println("Range of values");
        System.out.println("of TANK");
        System.out.println(Type.TANK);
        System.out.println("of WARRIOR:");
        System.out.println(Type.WARRIOR);
        System.out.println("of UNIVERSAL:");
        System.out.println(Type.UNIVERSAL);
        Scanner scanner=new Scanner(System.in);
        Integer health;
        Integer damage;
        Integer defence;
        Integer department;
        try {
            String scan=scanner.nextLine();
            String type=scan;
            health=Integer.parseInt(scan);
            scan=scanner.nextLine();
            damage=Integer.parseInt(scan);
            scan=scanner.nextLine();
            defence=Integer.parseInt(scan);
            scan=scanner.nextLine();
            department=Integer.parseInt(scan);
            listOfDroids.setNewDroid(type,health,damage,defence,department);
        } catch (WrongDataValue wrongDataValue) {
            scanner.close();
            createNewDroid();
        } catch (NumberFormatException numberFormatException) {
            scanner.close();
            createNewDroid();
        }
        scanner.close();

    }

    public void OneVsOne(){
        int size =listOfDroids.getDroidList().size();
        Scanner scanner = new Scanner(System.in);
        Droid droidFirst=null;
        Droid droidSecond=null;
        try {
            System.out.println("choose first drone (from 0 to "+(size-1)+"):");
            int drone1= scanner.nextInt();
            System.out.println("choose second drone (from 0 to "+(size-1)+"):");
            int drone2= scanner.nextInt();
            if (drone1==drone2||drone1>=size||drone2>=size||drone2<0||drone1<0){
                scanner.close();
                System.out.println("wrong data input");
                OneVsOne();
            }
            droidFirst = listOfDroids.getDroidList().get(drone1);
            droidSecond = listOfDroids.getDroidList().get(drone2);
            System.out.println("Lets start:");
            droidFirst.printStatus();
            droidSecond.printStatus();
            scanner.close();
            int queue =1;
            while (true){
                if(queue==1){
                    droidFirst.attackAnotherDroid(droidSecond);
                    queue=2;
                }else if(queue==2){
                    droidSecond.attackAnotherDroid(droidFirst);
                    queue=1;
                }
            }
        } catch (DroidDeadException e) {
            if (droidFirst.getStatus()==Status.Dead){
                listOfDroids.getDroidList().remove(droidFirst);
                System.out.println("first is dead");
            }
            if (droidSecond.getStatus()==Status.Dead){
                listOfDroids.getDroidList().remove(droidSecond);
                System.out.println("second is dead");
            }
        }
    }

}
