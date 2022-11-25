package com.senyk.laba3;

import com.senyk.laba3.droid.Droid;
import com.senyk.laba3.droid.exceptions.DroidDeadException;
import com.senyk.laba3.droid.exceptions.WrongDataValue;
import com.senyk.laba3.droid.model.Type;
import com.senyk.laba3.droid.status.Status;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application1 {
    ListOfDroids listOfDroids =new ListOfDroids();
    File fileURL = new File("src/main/resources/game.txt");
    FileWriter fileWriter;
    Scanner scanner = new Scanner(System.in);

    /**
     * start game
     */
    public void start(){
        System.out.println("Hello, gamer.");
        System.out.println("Now we will start a new game.");
        System.out.println("Choose the direction you want(print number of direction):");
        System.out.println("    1) start new game");
        System.out.println("    2) load previous game");
        System.out.println("    3 or another number) end game");
        int variant= scanner.nextInt();
        System.out.println("You choose "+variant+" option");
        switch (variant){
            case 1:
                startNewGame();
                break;
            case 2:
                loadPreviousGame();
                break;
            case 3:
                scanner.close();
                System.out.println("Your game end");
                return;
        }
        scanner.close();
        if(fileWriter!=null){
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }}
        System.out.println("Your game end");
    }

    /**
     * load previous game
     */
    private void loadPreviousGame(){
        try {
            scanner =new Scanner(fileURL);
            while (scanner.hasNext()){
                String data =scanner.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * start new match
     */
    private void startNewGame(){
        if (listOfDroids.getDroidList().size()<2){
            System.out.println("Choose what you want(print number):");
            System.out.println("    1) load droids");
            System.out.println("    2) create droids");
            int variant= scanner.nextInt();
            System.out.println("You choose "+variant+" option");
            switch (variant){
                case 1:
                    loadDroids();
                    break;
                case 2:
                    createNewDroids();
                    break;
            }
            startNewGame();
            return;
        }
        else {
            System.out.println("Choose what you want(print number):");
            System.out.println("    1) One Vs One");
            System.out.println("    2) Team Vs Team");
            int variant= scanner.nextInt();
            System.out.println("You choose "+variant+" option");
            switch (variant){
                case 1:
                    OneVsOne();
                    break;
                case 2:
                    System.out.println("2");
                    TeamVsTeam();
                    break;
            }
        }
        System.out.println("Would you like star new game?");
        System.out.println("    1) Yes");
        System.out.println("    2) No");
        int variant= scanner.nextInt();
        System.out.println("You choose "+variant+" option");
        switch (variant){
            case 1:
                startNewGame();
                break;
            case 2:
                return;
        }

    }

    /**
     * option for match team vs team
     */
    private void TeamVsTeam(){
        if (listOfDroids.getDroidList().size()<4){
            System.out.println("Choose what you want(print number):");
            System.out.println("    1) load droids");
            System.out.println("    2) create droids");
            int variant= scanner.nextInt();
            System.out.println("You choose "+variant+" option");
            switch (variant){
                case 1:
                    loadDroids();
                    break;
                case 2:
                    createNewDroids();
                    break;
            }
            TeamVsTeam();
            return;
        }
        else {
            Team teamFirst = new Team("Milk");
            Team teamSecond = new Team("Juice");
            createTeams(teamFirst,teamSecond);
            System.out.println("Lets start:");
            System.out.println("First team:"+teamFirst.getName());
            for (Droid droid :teamFirst.getTeam()) {
                droid.printStatus();
            }
            System.out.println("Second team:"+teamSecond.getName());
            for (Droid droid :teamSecond.getTeam()) {
                droid.printStatus();
            }
            int queue =1;
            Integer round=1;
            try {
                roundTeamVsTeam(teamFirst,teamSecond,queue,round);
            } catch (DroidDeadException e) {
                e.printStackTrace();
            }
            if (!teamFirst.isStatus()){
                System.out.println("Second team win");
            }
            else System.out.println("first team win");
        }
    }

    /**
     * support method of TeamVsTeam
     * @param team1
     * @param team2
     * @param queue
     * @param round
     * @return
     * @throws DroidDeadException
     */
    private int roundTeamVsTeam(Team team1, Team team2, int queue,Integer round) throws DroidDeadException {
        if (!team1.isStatus()|| !team2.isStatus()){
            return 0;
        }
        else {
            if(queue%2==1){
                System.out.println("the first team attacks");
                System.out.println("choose who will attack: 0 - "+(team1.getTeam().size()-1));
                int x1 = scanner.nextInt();
                System.out.println("choose who will be attacked: 0 - "+(team2.getTeam().size()-1));
                int x2 = scanner.nextInt();
                if(team1.getTeam().get(x1).getStatus()==Status.Dead){
                    System.out.println("attacker is dead");
                }
                else if (team2.getTeam().get(x2).getStatus()==Status.Dead){
                    System.out.println("defender is dead");
                }
                else {
                    team1.getTeam().get(x1).attackAnotherDroid(team2.getTeam().get(x2));
                }
            }
            else {
                System.out.println("the second team attacks");
                System.out.println("choose who will attack: 0 - "+(team2.getTeam().size()-1));
                int x1 = scanner.nextInt();
                System.out.println("choose who will be attacked: 0 - "+(team1.getTeam().size()-1));
                int x2 = scanner.nextInt();
                if(team2.getTeam().get(x1).getStatus()==Status.Dead){
                    System.out.println("attacker is dead");
                }
                else if (team1.getTeam().get(x2).getStatus()==Status.Dead){
                    System.out.println("defender is dead");
                }
                else {
                    team2.getTeam().get(x1).attackAnotherDroid(team1.getTeam().get(x2));
                }
            }
            roundTeamVsTeam(team1,team2,queue+1,round+1);
        }
        return 0;
    }

    /**
     * create teams
     * @param team1
     * @param team2
     */
    private void createTeams(Team team1,Team team2){
        for (int i = 0; i < listOfDroids.getDroidList().size(); i++) {
            if(i%2==0) team1.addDroidToTeam(listOfDroids.getDroidList().get(i));
            else team2.addDroidToTeam(listOfDroids.getDroidList().get(i));
        }
    }

    /**
     * option for match one vs one
     */
    private void OneVsOne(){
        int size =listOfDroids.getDroidList().size();
        Droid droidFirst=null;
        Droid droidSecond=null;
        String data="";
        try {
            System.out.println("choose first drone (from 0 to "+(size-1)+"):");
            int drone1= scanner.nextInt();
            System.out.println("choose second drone (from 0 to "+(size-1)+"):");
            int drone2= scanner.nextInt();
            if (drone1==drone2||drone1>=size||drone2>=size||drone2<0||drone1<0){
                System.out.println("wrong data input");
                OneVsOne();
                return;
            }
            droidFirst = listOfDroids.getDroidList().get(drone1);
            droidSecond = listOfDroids.getDroidList().get(drone2);
            System.out.println("Lets start:");
            droidFirst.printStatus();
            droidSecond.printStatus();
            int queue =1,x=1;
            try {
                fileWriter=new FileWriter(fileURL);
            } catch (IOException e) {
                e.printStackTrace();
            }
            {
                 data ="Game:One vs One:\n" +
                        "first droid:"+droidFirst.getSerialNumber()+"\n"+
                        "second droid:"+droidSecond.getSerialNumber()+"\n"+
                        "Start:\n";
            }
            while (true){
                if (droidFirst.getStatus()==Status.Dead||droidSecond.getStatus()==Status.Dead)
                    throw new DroidDeadException();
                if(queue==1){
                    {
                        data+="attacker:first droid\n";
                    }
                    int damage=droidFirst.attackAnotherDroid(droidSecond);
                    {
                        data+="second droid get "+damage+"damage\n";
                    }
                    System.out.println("Round: "+x);
                    x++;
                    queue=2;
                }else if(queue==2){
                    {
                        data+="attacker:second droid\n";
                    }
                    int damage= droidSecond.attackAnotherDroid(droidFirst);
                    {
                        data+="first droid get "+damage+"damage\n";
                    }
                    queue=1;
                }
            }
        } catch (DroidDeadException e) {
            {
               data+="End of match\n";
            }
            droidFirst.printStatus();
            droidSecond.printStatus();
            if (droidFirst.getStatus()==Status.Dead){
                {
                    data+="second droid is winner\n";
                }
                listOfDroids.getDroidList().remove(droidFirst);
                System.out.println("first is dead");
            }
            if (droidSecond.getStatus()==Status.Dead){
                {
                    data+="first droid is winner\n";
                }
                listOfDroids.getDroidList().remove(droidSecond);
                System.out.println("second is dead");
            }
        }
        try {
            fileWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create new droids
     */
    private void createNewDroids(){
        System.out.println("How many droids you want create?(min 2)");
        int droids= scanner.nextInt();
        if (droids<2){
            createNewDroids();
            return;
        }
        for (int i = 0; i < droids; i++) {
            createNewDroid();
        }
    }
    private void createNewDroid(){
        System.out.println("Set the parameters for the new droid");
        System.out.println("Range of values");
        System.out.println("of TANK");
        System.out.println(Type.TANK);
        System.out.println("of WARRIOR:");
        System.out.println(Type.WARRIOR);
        System.out.println("of UNIVERSAL:");
        System.out.println(Type.UNIVERSAL);
        Integer health;
        Integer damage;
        Integer defence;
        Integer department;
        try {
            System.out.println("type:");
            String type=scanner.next();
            System.out.println("health:");

            health=scanner.nextInt();
            System.out.println("damage:");
            damage=scanner.nextInt();
            System.out.println("defence:");
            defence=scanner.nextInt();
            System.out.println("department:");
            department=scanner.nextInt();
            listOfDroids.setNewDroid(type,health,damage,defence,department);
        } catch (WrongDataValue wrongDataValue) {
            createNewDroid();
        } catch (NumberFormatException numberFormatException) {
            createNewDroid();
        } catch (InputMismatchException inputMismatchException){
            System.out.println("wrong data type input");
            createNewDroid();
        }

    }

    /**
     * load droids
     */
    private void loadDroids(){
        try {
            listOfDroids=new ListOfDroids();
            listOfDroids.setNewDroid("tank",1000,60,20,1);
            listOfDroids.setNewDroid("tank",901,60,15,1);
            listOfDroids.setNewDroid("UNIVERSAL",700,61,9,1);
            listOfDroids.setNewDroid("UNIVERSAL",760,90,7,2);
            listOfDroids.setNewDroid("WARRIOR",490,100,4,3);
            listOfDroids.setNewDroid("WARRIOR",400,110,5,2);
        } catch (WrongDataValue e) {
            e.printStackTrace();
        }
    }

}
