package com.senyk.comprehensiveLaba.gems.app;

import com.senyk.comprehensiveLaba.gems.entity.Gem;
import com.senyk.comprehensiveLaba.gems.entity.Necklace;
import com.senyk.comprehensiveLaba.gems.entity.Sketch;
import com.senyk.comprehensiveLaba.gems.services.GemService;
import com.senyk.comprehensiveLaba.gems.services.NecklaceService;
import com.senyk.comprehensiveLaba.gems.services.SketchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.web.bind.annotation.RestController;


import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Scanner;
@RestController
public class ConsolApp implements CommandLineRunner {
    private Scanner scanner;
    @Autowired
    private GemService gemService;
    @Autowired
    private NecklaceService necklaceService;
    @Autowired
    private SketchService sketchService;
    public ConsolApp(GemService gemService,NecklaceService necklaceService, SketchService sketchService) {
        this.gemService = gemService;
        this.necklaceService = necklaceService;
        this.sketchService = sketchService;
    }

    public ConsolApp(){
    }
    @PostConstruct
    public void start(){
        scanner=new Scanner(System.in);
        System.out.println("Hello");
        configGem();
        while (menu()){}
        System.out.println("The end");

    }
    private boolean menu(){
        System.out.println("#####################################");
        System.out.println("It is head page\n"+
                           "Make your choice");
        System.out.println("#####################################");
        System.out.println("1-gem menu\n" +
                "2-sketch menu\n"+
                "3-necklace menu\n" +
                "0-exit");
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                while (menuGem());
                return true;
            case 2:
                while (menuSketch());
                return true;
            case 3:
                while (menuNecklace());
                return true;
            case 0:
                return false;
        }
        return false;
    }

    private boolean menuGem(){
        System.out.println("#####################################");
        System.out.println("It is gem menu page\n"+
                "Make your choice");
        System.out.println("#####################################");
        System.out.println("1-add gem\n" +
                "2-remove gem\n"+
                "3-update gem\n"+
                "4-get list of gem\n"+
                "0-exit");
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                addGem();
                return true;
            case 2:
                removeGem();
                return true;
            case 3:
                updateGem();
                return true;
            case 4:
                getListOfGems();
                return true;
            case 0:
                return false;
        }
        return false;
    }
    private void addGem(){
        System.out.println("#####################################");
        System.out.println("It is create gem page");
        System.out.println("#####################################");
        System.out.println("Add gem:");
        System.out.println("Name:");
        String name = scanner.next();
        System.out.println("Carat:");
        double carat =scanner.nextDouble();
        System.out.println("Price:");
        double price =scanner.nextDouble();
        System.out.println("Shape:");
        String shape= scanner.next();
        try {
            Gem gem =new Gem(name,carat,price,shape);
            gemService.addGem(gem);
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void removeGem(){
        System.out.println("#####################################");
        System.out.println("It is remove gem page");
        System.out.println("#####################################");
        System.out.println("Choose what gem you want remove");
        long choice= scanner.nextInt();
        try {
            gemService.removeGem(choice);
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void updateGem(){
        System.out.println("#####################################");
        System.out.println("It is update gem page");
        System.out.println("#####################################");
        System.out.println("Choose what gem you want update price");
        long choice= scanner.nextInt();
        System.out.println("Write new price");
        double price= scanner.nextDouble();
        try {
            gemService.updateGem(choice,price);
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void getListOfGems(){
        System.out.println("#####################################");
        System.out.println("It is gem-list page");
        System.out.println("#####################################");
        System.out.println("List of gems:");
        try {
            for (Gem g :gemService.getGems()) {
                System.out.println(g.toString());
            }
            System.out.println("_____________________________________");
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }

    private boolean menuSketch(){
        System.out.println("#####################################");
        System.out.println("It is sketch menu page\n"+
                "Make your choice");
        System.out.println("#####################################");
        System.out.println("1-create sketch\n" +
                "2-remove sketch\n"+
                "3-remove gem from sketch\n"+
                "4-add gem to sketch\n"+
                "5-get info about sketch\n" +
                "6-get list of sketch\n"+
                "0-exit");
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                createSketch();
                return true;
            case 2:
                removeSketch();
                return true;
            case 3:
                removeGemFromSketch();
                return true;
            case 4:
                addGemToSketch();
                return true;
            case 5:
                getInfoAboutSketch();
                return true;
            case 6:
                getListOfSketches();
                return true;
            case 0:
                return false;
        }
        return false;
    }
    private void createSketch(){
        System.out.println("#####################################");
        System.out.println("It is create sketch page");
        System.out.println("#####################################");
        System.out.println("Write name of new sketch");
        String choice= scanner.next();
        try {
            sketchService.createSketch(new Sketch(choice));
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void removeSketch(){
        System.out.println("#####################################");
        System.out.println("It is remove sketch page");
        System.out.println("#####################################");
        System.out.println("Choose what sketch you want remove");
        long choice= scanner.nextInt();
        try {
            sketchService.removeSketch(choice);
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void removeGemFromSketch(){
        System.out.println("#####################################");
        System.out.println("It is remove gem from sketch page");
        System.out.println("#####################################");
        System.out.println("Choose sketch");
        long choice= scanner.nextInt();
        System.out.println("Choose gem to remove from sketch");
        long choice2= scanner.nextInt();
        try {
            sketchService.removeGemFromSketch(choice,choice2);
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void addGemToSketch(){
        System.out.println("#####################################");
        System.out.println("It is add gem to sketch page");
        System.out.println("#####################################");
        System.out.println("Choose sketch");
        long choice= scanner.nextInt();
        System.out.println("Choose gem to add to sketch");
        long choice2= scanner.nextInt();
        try {
            sketchService.addGemToSketch(choice,choice2);
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void getInfoAboutSketch(){
        System.out.println("#####################################");
        System.out.println("It is sketch-info page");
        System.out.println("#####################################");

        System.out.println("Choose sketch you want to get info about");
        long choice= scanner.nextInt();
        try {
            double x=sketchService.getSketchPrice(choice);
            System.out.println("Price sketch:"+x);
            double x1=sketchService.getSketchCarat(choice);
            System.out.println("Carat sketch:"+x1);
            System.out.println("_____________________________________");
            System.out.println("List of gems of sketch:");
            sketchService.getSketchGems(choice);
            System.out.println("_____________________________________");

            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void getListOfSketches(){
        System.out.println("#####################################");
        System.out.println("It is sketch-list page");
        System.out.println("#####################################");
        System.out.println("List of sketch:");
        try {
            for (Sketch g :sketchService.getSketches()) {
                System.out.println(g.toString());
            }
            System.out.println("_____________________________________");
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }

    private boolean menuNecklace(){
        System.out.println("#####################################");
        System.out.println("It is necklace menu page\n"+
                "Make your choice");
        System.out.println("#####################################");
        System.out.println("1-implement sketch\n" +
                "2-reconstruct necklace\n"+
                "3-get info about necklace\n" +
                "4-get list of necklace\n"+
                "0-exit");
        int choice= scanner.nextInt();
        switch (choice){
            case 1:
                implementSketch();
                return true;
            case 2:
                reconstructNecklace();
                return true;
            case 3:
                getInfoAboutNecklace();
                return true;
            case 4:
                getListOfNecklaces();
                return true;
            case 0:
                return false;
        }
        return false;
    }
    private void implementSketch(){
        System.out.println("#####################################");
        System.out.println("It is implement sketch page");
        System.out.println("#####################################");
        System.out.println("Choose what sketch you want implement");
        long choice= scanner.nextInt();
        try {
            necklaceService.implementSketch(choice);
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void reconstructNecklace(){
        System.out.println("#####################################");
        System.out.println("It is reconstruct necklace page");
        System.out.println("#####################################");
        System.out.println("Choose what necklace you want reconstruct");
        long choice= scanner.nextInt();
        try {
            necklaceService.reconstructNecklace(choice);
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void getInfoAboutNecklace(){
        System.out.println("#####################################");
        System.out.println("It is necklace-info page");
        System.out.println("#####################################");

        System.out.println("Choose necklace you want to get info about");
        long choice= scanner.nextInt();
        try {
            double x=necklaceService.getNecklacePrice(choice);
            System.out.println("Price necklace:"+x);
            System.out.println("Carat necklace:"+necklaceService.getNecklaceCarat(choice));
            System.out.println("_____________________________________");
            System.out.println("List of gems of necklace:");
            necklaceService.getNecklaceGems1(choice);
            System.out.println("_____________________________________");

            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }
    private void getListOfNecklaces(){
        System.out.println("#####################################");
        System.out.println("It is necklace-list page");
        System.out.println("#####################################");
        System.out.println("List of necklaces:");
        try {
            for (Necklace g :necklaceService.getNecklaces()) {
                System.out.println(g.toString());
            }
            System.out.println("_____________________________________");
            System.out.println("all good");
        } catch (Exception e) {
            System.out.println("Something wrong...");
        }
    }

    @Override
    public void run(String... args) throws Exception {

    }

    private void configGem(){
        Gem gem1 =new Gem("Diamond",12.3,1200.2,"shape1");
        Gem gem2 =new Gem("Amber",1.3,5000.0,"ef");
        Gem gem3 =new Gem("Ruby",2.3,500.0,"shape1");
        Gem gem4 =new Gem("Emerald",0.3,100.0,"ef");
        Gem gem5 =new Gem("Sapphire",5.3,100000.0,"shape1");
        Gem gem6 =new Gem("Topaz",7.3,9000.0,"ef");
        for (Gem g :
                List.of(gem1, gem2,gem3,gem4,gem5,gem6)) {
            gemService.addGem(g);
        }
        Sketch sketch=new Sketch("test1_s");
        sketchService.createSketch(sketch);
        sketchService.addGemToSketch(1L,1L);
        sketchService.addGemToSketch(1L,2L);
    }

}
