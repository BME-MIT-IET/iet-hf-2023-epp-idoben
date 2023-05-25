package com.main;

import agent.*;
import equipment.*;
import field.*;
import resources.*;
import timer.Timer;

import java.util.Scanner;

public class Skeleton {
    static boolean enabled = false;
    static int level = 0;

    public static void runTests(){
        boolean run = true;
        while(run){
            System.out.println("Enter which test you want to run:\n 0) Exit\n 1) Virusdance rubbing\n 2) Protection/paralysis rubbing\n 3) Oblivion rubbing\n 4) Protsuit breaks\n 5) Move\n 6) Move while paralysed\n 7) Move randomly\n 8) Pickup equipment\n 9) FullProt rubbing on himself\n 10) Collect Resource With Sack\n 11) Can't Collect\n 12) Robbing From Stunned Fellow\n 13) Paralysis Disappearing\n 14) GetCode Endgame\n 15) GetCode Knownagents\n 16) GetCode\n 17) GetResource\n 18) Pickup eq, 3eq already\n 19) Agent rubbing, having Gloves\n 20) Agent rubbing, having FullProt\n 21) Agent rubbing, having ProtSuit\n");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            System.out.println("-------------------------------------------------------------------------------");
//            switch(num){
//                case 0:
//                    run = false;
//                    break;
//                case 1:
//                    useCase1();
//                    break;
//                case 2:
//                    useCase2();
//                    break;
//                case 3:
//                    useCase3();
//                    break;
//                case 4:
//                    useCase4();
//                    break;
//                case 5:
//                    useCase5();
//                    break;
//                case 6:
//                    useCase6();
//                    break;
//                case 7:
//                    useCase7();
//                    break;
//                case 8:
//                    useCase8();
//                    break;
//                case 9:
//                    useCase9();
//                    break;
//                case 10:
//                    useCase10();
//                    break;
//                case 11:
//                    useCase11();
//                    break;
//                case 12:
//                    useCase12();
//                    break;
//                case 13:
//                    useCase13();
//                    break;
//                case 14:
//                    useCase14();
//                    break;
//                case 15:
//                    useCase15();
//                    break;
//                case 16:
//                    useCase16();
//                    break;
//                case 17:
//                    useCase17();
//                    break;
//                case 18:
//                    useCase18();
//                    break;
//                case 19:
//                    useCase19();
//                    break;
//                case 20:
//                    useCase20();
//                    break;
//                case 21:
//                    useCase21();
//                    break;
//            }
            System.out.println("-------------------------------------------------------------------------------\n\n");
        }
    }

    public static void enable(){
        enabled = true;
    }

    public static void disable(){
        enabled = false;
    }

    public static void functionCall(String msg){
        if (enabled) {
            for(int p=0;p<level;p++){
                System.out.print("\t");
            }
            level++;
            System.out.println(msg);
        }
    }

    public static void functionReturn(String msg){
        if (enabled) {
            level--;
            for(int p=0;p<level;p++){
                System.out.print("\t");
            }
            System.out.println(msg);
        }
    }

//    /**
//     * Virusdance rubbing usecase tesztfüggvény
//     */
//    public static void useCase1(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        //Timer.getInstance().Start();
//        Timer.getInstance().Stop();
//        Simple field = new Simple("simpleField");
//        Virologist v1=new Virologist("Virologist1");
//        VirusDance virusDance = new VirusDance("virusDance", v1, 1,0);
//        v1.PickupResource(new Nucleotid());
//        Virologist v2=new Virologist("Virologist2");
//        field.Accept(v1);
//        field.Accept(v2);
//        v1.Accept(field);
//        v2.Accept(field);
//        Sack sack = new Sack(60, v2, "Sack");
//        v2.PickupEq(sack);
//        v1.LearnGenCode(virusDance);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v1.ThrowAgentOn(v2,virusDance);
//    }
//
//    /**
//     * Protection/paralysis rubbing usecase tesztfüggvény
//     */
//    public static void useCase2(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Stop();
//        Virologist v1=new Virologist("Virologist1");
//        Virologist v2=new Virologist("Virologist2");
//        Simple simple = new Simple("simple");
//        simple.Accept(v1);
//        simple.Accept(v2);
//        v1.Accept(simple);
//        v2.Accept(simple);
//        Paralysis paralysis = new Paralysis("paralysis", 10,v2, 1,0);
//        Sack sack = new Sack(10, v2, "sack");
//        v1.PickupResource(new Nucleotid());
//        v2.PickupEq(sack);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v1.ThrowAgentOn(v2,paralysis);
//    }
//
//    /**
//     * Oblivion rubbing usecase tesztfüggvény
//     */
//    public static void useCase3(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Stop();
//        Virologist v1=new Virologist("Virologist1");
//        Virologist v2=new Virologist("Virologist2");
//        Simple simple = new Simple("simple");
//        simple.Accept(v1);
//        simple.Accept(v2);
//        v1.Accept(simple);
//        v2.Accept(simple);
//        Oblivion oblivion = new Oblivion("oblivion",v1,1,0);
//        v1.PickupResource(new Nucleotid());
//        Sack sack = new Sack(10, v2, "sack");
//        v2.PickupEq(sack);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v1.ThrowAgentOn(v2,oblivion);
//    }
//
//    /**
//     * Protsuit breaks usecase tesztfüggvény
//     */
//    public static void useCase4(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Start();
//        Timer.getInstance().Stop();
//        Virologist v1=new Virologist("Virologist1");
//        ProtSuit protSuit = new ProtSuit(1,v1,"protSuit");
//        v1.PickupEq(protSuit);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        Timer.getInstance().Tick();
//    }
//
//    /**
//     * Move usecase tesztfüggvény
//     */
//    public static void useCase5(){
//        Timer.getInstance().Stop();
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Virologist v1=new Virologist("Virologist");
//        FullProt fullProt = new FullProt("fullProt",1,v1,1,0);
//        v1.AddEffect(fullProt);
//        SafeHouse safeHouse = new SafeHouse("safeHouse",new Sack(1,null,"sack"));
//        Simple simple = new Simple("simple");
//        safeHouse.AddNeighbour(0,simple);
//        simple.AddNeighbour(0,safeHouse);
//        safeHouse.Accept(v1);
//        v1.Accept(safeHouse);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v1.Move(0);
//    }
//
//    /**
//     * Move while paralysed usecase tesztfüggvény
//     */
//    public static void useCase6(){
//        Timer.getInstance().Stop();
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Stop();
//        Virologist v = new Virologist("v");
//        v.AddEffect(new Paralysis("p",10,v,0,0));
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v.Move(0);
//    }
//
//    /**
//     * Move randomly usecase tesztfüggvény
//     */
//    public static void useCase7(){
//        Field fields[][] = new Field[11][11];
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        for(int x=0;x<fields.length;x++) {
//            for (int y = 0; y < fields[x].length; y++) {
//                fields[x][y] = new Simple("x:" + x + ";" + "y:" + y);
//                if (x > 0) {
//                    fields[x][y].AddNeighbour(0, fields[x - 1][y]);
//                    fields[x - 1][y].AddNeighbour(1, fields[x][y]);
//                }
//                if (y > 0) {
//                    fields[x][y].AddNeighbour(2, fields[x][y - 1]);
//                    fields[x][y - 1].AddNeighbour(3, fields[x][y]);
//                }
//            }
//        }
//        Virologist v = new Virologist("v");
//        fields[5][5].Accept(v);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v.MoveRandom();
//    }
//
//    /**
//     * Pickup equipment usecase tesztfüggvény
//     */
//    public static void useCase8(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Gloves g = new Gloves(10,null,"glovesInSafeHouse");
//        Timer.getInstance().Stop();
//        SafeHouse s = new SafeHouse("sh", g);
//        Virologist v = new Virologist("v");
//        v.Accept(s);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v.Collect();
//    }
//
//    /**
//     * Fullprot rubbing on himself usecase tesztfüggvény
//     */
//    public static void useCase9(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        FullProt fp = new FullProt("fp",10,null,0,0);
//        Timer.getInstance().Stop();
//        Virologist v = new Virologist("v");
//        v.LearnGenCode(fp);
//        Simple f = new Simple("field");
//        f.Accept(v);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v.ThrowAgentOn(v,fp);
//    }
//
//    /**
//     * Collect Resource With Sack usecase tesztfüggvény
//     */
//    public static void useCase10(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Virologist v = new Virologist("v");
//        Timer.getInstance().Stop();
//        Sack sack = new Sack(10,null,"sackInSafeHose");
//        v.PickupEq(sack);
//        Nucleotid nucleo = new Nucleotid();
//        WareHouse s = new WareHouse(nucleo,"w");
//        s.Accept(v);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v.Collect();
//    }
//
//    /**
//     * Can't Collect usecase tesztfüggvény
//     */
//    public static void useCase11(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Virologist v = new Virologist("v");
//        Timer.getInstance().Stop();
//        v.PickupResource(new Aminoacid());
//        v.PickupResource(new Aminoacid());
//        v.PickupResource(new Aminoacid());
//        v.PickupResource(new Aminoacid());
//        v.PickupResource(new Aminoacid());
//        v.PickupResource(new Aminoacid());
//        WareHouse w = new WareHouse(new Aminoacid(),"w");
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        w.Collect(v);
//    }
//
//    /**
//     * Robbing from stunned fellow usecase tesztfüggvény
//     */
//    public static void useCase12(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Virologist v = new Virologist("v");
//        Virologist v2 = new Virologist("v2");
//        Simple simple =new Simple("simple");
//        simple.Accept(v);
//        simple.Accept(v2);
//        v.Accept(simple);
//        v2.Accept(simple);
//        Timer.getInstance().Stop();
//        ProtSuit e = new ProtSuit(3, v, "e");
//        SafeHouse s = new SafeHouse("s", e);
//        s.Collect(v);
//        Paralysis p = new Paralysis("1p", 3, v, 0, 0);
//        v.ReceiveEffectFrom(new Virologist("randomV"), p);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v2.StealEqFrom(v, e);
//    }
//
//    /**
//     * Paralysis Disappearing usecase tesztfüggvény
//     */
//    public static void useCase13(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Start();
//        Timer.getInstance().Stop();
//        Virologist v = new Virologist("v");
//        Paralysis p = new Paralysis("1p", 1, v, 0, 0);
//        v.ReceiveEffectFrom(new Virologist("randomV"), p);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        Timer.getInstance().Tick();
//    }
//
//    /**
//     * GetCode EndGame usecase tesztfüggvény
//     */
//    public static void useCase14(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Virologist v = new Virologist("v");
//        Timer.getInstance().Stop();
//        Paralysis p = new Paralysis("1p", 1, v, 0, 0);
//        VirusDance vd = new VirusDance("1vd", v, 0, 0);
//        FullProt fp = new FullProt("1fp", 1, v, 0, 0);
//        Oblivion o = new Oblivion("1ob", v, 0, 0);
//        v.LearnGenCode(p);
//        v.LearnGenCode(vd);
//        v.LearnGenCode(fp);
//        Lab lab = new Lab("lab", o);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        lab.Collect(v);
//    }
//    /**
//     * GetCode KnownAgents usecase tesztfüggvény
//     */
//    public static void useCase15(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Virologist v = new Virologist("v");
//        Timer.getInstance().Stop();
//        Paralysis p = new Paralysis("1p", 1, v, 0, 0);
//        Oblivion o = new Oblivion("1o", v,0,0);
//        Lab lab = new Lab("lab", o);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        lab.Collect(v);
//    }
//
//    /**
//     * Get Code usecase tesztfüggvény
//     */
//    public static void useCase16(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Stop();
//        Oblivion o = new Oblivion("oblivion", null, 3, 4);
//        Lab l = new Lab("lab", o);
//        Virologist v = new Virologist("virologist");
//        v.Accept(l);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v.Collect();
//    }
//
//    /**
//     * Get Resource usecase tesztfüggvény
//     */
//    public static void useCase17(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Stop();
//        Virologist v = new Virologist("virologist");
//        Aminoacid a = new Aminoacid();
//        WareHouse w = new WareHouse(a, "warehouse");
//        v.Accept(w);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v.Collect();
//    }
//
//    /**
//     * Új felszerlés felvétele, amikor már van 3 usecase tesztfüggvény
//     */
//    public static void useCase18(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Stop();
//        Virologist v = new Virologist("virologist");
//        Sack s1 = new Sack(60, v, "sack1");
//        Gloves g = new Gloves(60, v, "gloves");
//        ProtSuit p = new ProtSuit(60, v, "protsuit");
//        v.PickupEq(s1);
//        v.PickupEq(g);
//        v.PickupEq(p);
//        Sack s2 = new Sack(60, v, "sack2");
//        System.out.println("\t--INICIALIZALAS VEGE--");
//        v.PickupEq(s2);
//    }
//
//    /**
//     * Agent rubbing having gloves usecase tesztfüggvény
//     */
//    public static void useCase19(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Stop();
//        Simple s = new Simple("field");
//
//        Virologist v1 = new Virologist("virologist1");
//        Paralysis p = new Paralysis("paralysis", 60, v1, 0, 0);
//        v1.LearnGenCode(p);
//        s.Accept(v1);
//
//        Virologist v2 = new Virologist("virologist2");
//        Gloves g = new Gloves(60, v2, "gloves");
//        v2.PickupEq(g);
//        s.Accept(v2);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//
//        v1.ThrowAgentOn(v2, p);
//    }
//
//    /**
//     * Agent rubbing having fullprot usecase tesztfüggvény
//     */
//    public static void useCase20(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Stop();
//        Simple s = new Simple("field");
//
//        Virologist v1 = new Virologist("virologist1");
//        Paralysis p = new Paralysis("paralysis", 60, v1, 0, 0);
//        v1.LearnGenCode(p);
//        s.Accept(v1);
//
//        Virologist v2 = new Virologist("virologist2");
//        FullProt f = new FullProt("fullprot", 60, v2, 0, 0);
//        v2.AddEffect(f);
//        s.Accept(v2);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//
//        v1.ThrowAgentOn(v2, p);
//    }
//
//    /**
//     * Agent rubbing having protsuit usecase tesztfüggvény
//     */
//    public static void useCase21(){
//        System.out.println("\t--INICIALIZALAS KEZDETE--");
//        Timer.getInstance().Stop();
//        Simple s = new Simple("field");
//
//        Virologist v1 = new Virologist("virologist1");
//        Paralysis p = new Paralysis("paralysis", 60, v1, 0, 0);
//        v1.LearnGenCode(p);
//        s.Accept(v1);
//
//        Virologist v2 = new Virologist("virologist2");
//        ProtSuit pr = new ProtSuit(60, v2, "protsuit");
//        v2.PickupEq(pr);
//        s.Accept(v2);
//        System.out.println("\t--INICIALIZALAS VEGE--");
//
//        v1.ThrowAgentOn(v2, p);
//    }

}
