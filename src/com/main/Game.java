package com.main;

import GUI.MainWindow;
import agent.*;
import equipment.*;
import field.*;
import resources.Aminoacid;
import resources.Nucleotid;
import resources.Resource;
import timer.*;
import timer.Timer;

import javax.swing.*;
import java.io.*;
import java.util.*;


public class Game {

    public static boolean random = false;
    static public Map<String, Field> fields;
    static Map<String, Virologist> virologists;
    static Map<String, Agent> codes;
    static public int width, height;
    static public Virologist currentRound;
    static public int remainingSteps;
    public static boolean gameEnded = true;
    static MainWindow gui;
    static boolean enabled = true;


    /**
     * Elindítja a játékot
     */
    public static void startGame(){
        com.main.Skeleton.functionCall("Game.StartGame()");
    }


    public static void setGui(MainWindow g){gui = g;}


    /**
     * ez betölti a játékot
     * @param fileName a fájl neve
     * @throws IOException kivétel ha sikertelen
     */
    public static void loadGame(String fileName) throws IOException {
        if(!enabled)
            return;
        File f = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(f));

        //Mezők inicializálása
        fields = new HashMap<>();
        virologists = new HashMap<>();
        codes = new HashMap<>();
        codes.put("Co", new Oblivion("Co", null, 5,5));
        codes.put("Cp", new Paralysis("Cp", 5,null, 5,5));
        codes.put("Cv", new VirusDance("Cv", null, 5,5));
        codes.put("Cf", new FullProt("Cf", 5,null, 5,5));

        String line;
        line = br.readLine();

        boolean updateNeeded = false;

        //Pálya méretének beolvasása
        if(line.contains("×")) {
            width = Integer.parseInt(line.split("×")[0]);
            height = Integer.parseInt(line.split("×")[1]);
        }else{
            width = Integer.parseInt(line.split("x")[0]);
            height = Integer.parseInt(line.split("x")[1]);
        }

        //Pálya beolvasása
        Field [] prevRow = new Field[width];
        String [] afaszomat = new String[width];
        for(int p=0;p<height;p++){
            line = br.readLine();
            String [] tmp = line.split(" ");
            Field prevField = null;
            String prevFieldName = null;
            for(int q=0;q<width;q++){
                Field newField = null;
                String currentName = tmp[q].split("\\(")[0];
                //Különböző mezők létrehozása
                //Virológus elhelyezése rajta
                switch(tmp[q].charAt(0)){
                    //A sima mező
                    case 'S':
                        newField = new Simple(currentName, q, p);
                        fields.put(currentName, newField);
                        if(tmp[q].split("\\(")[1].split("\\)").length==0)
                            break;
                        String [] thingsOnField = tmp[q].split("\\(")[1].split("\\)")[0].split(";");
                        for(String s : thingsOnField){
                            Virologist v = new Virologist(s);
                            virologists.put(s, v);
                            newField.Accept(v);
                        }
                        break;
                        //Labor mező
                    case 'L':
                        if(tmp[q].split("\\(")[1].split("\\)").length==0){
                            newField = new Lab( currentName, null, false, q, p);
                            fields.put(currentName, newField);
                            break;
                        }
                        thingsOnField = tmp[q].split("\\(")[1].split("\\)")[0].split(";");
                        Optional<String> o = Arrays.stream(thingsOnField).filter((s)-> s.equals("Co")||s.equals("Cp")||s.equals("Cv")||s.equals("Cf")||s.equals("Cb")).findFirst();
                        String thing = "";
                        if(o.isPresent())
                            thing = o.get();
                        newField = new Lab(currentName, codes.get(thing), thing.equals("Cb"), q, p);
                        fields.put(currentName, newField);
                        for(String s : thingsOnField){
                            if(!s.equals(thing)) {
                                Virologist v = new Virologist(s);
                                virologists.put(s, v);
                                newField.Accept(v);
                            }
                        }
                        break;
                        //Raktár mező
                    case 'W':
                        if(tmp[q].split("\\(")[1].split("\\)").length==0){
                            newField = new WareHouse( null, currentName, q, p);
                            fields.put(currentName, newField);
                            break;
                        }
                        thingsOnField = tmp[q].split("\\(")[1].split("\\)")[0].split(";");
                        o = Arrays.stream(thingsOnField).filter((s)-> s.startsWith("Ra")||s.startsWith("Rn")).findFirst();
                        thing = "";
                        if(o.isPresent())
                            thing = o.get();
                        Resource r = null;
                        if(thing.startsWith("Ra")){
                            r = new Aminoacid();
                        }else if(thing.startsWith("Rn")){
                            r = new Nucleotid();
                        }
                        newField = new WareHouse( r, currentName, q, p);
                        fields.put(currentName, newField);
                        for(String s : thingsOnField){
                            if(!s.equals(thing)) {
                                Virologist v = new Virologist(s);
                                virologists.put(s, v);
                                newField.Accept(v);
                            }
                        }
                        break;
                        //Óvóhely mező
                    case 'H':
                        if(tmp[q].split("\\(")[1].split("\\)").length==0){
                            newField = new SafeHouse( currentName, null, q, p);
                            fields.put(currentName, newField);
                            break;
                        }
                        thingsOnField = tmp[q].split("\\(")[1].split("\\)")[0].split(";");
                        o = Arrays.stream(thingsOnField).filter((s)-> s.startsWith("Ea")||s.startsWith("Ep")||s.startsWith("Eg")||s.startsWith("Es")).findFirst();
                        thing = "";
                        if(o.isPresent())
                            thing = o.get();
                        Equipment e;
                        switch (thing.substring(0, 2)) {
                            case "Ea":
                                e = new Axe(null, thing);
                                break;
                            case "Ep":
                                e = new ProtSuit(5, null, thing);
                                break;
                            case "Eg":
                                e = new Gloves(-1, 3, null, thing);
                                break;
                            case "Es":
                                e = new Sack(5, null, thing);
                                break;
                            default:
                                e = null;
                                break;
                        }
                        newField = new SafeHouse( currentName, e, q, p);
                        fields.put(currentName, newField);
                        for(String s : thingsOnField){
                            if(!s.equals(thing)) {
                                Virologist v = new Virologist(s);
                                virologists.put(s, v);
                                newField.Accept(v);
                            }
                        }
                }
                //Szomszédok beállítása
                if(prevField!=null){
                    prevField.AddNeighbour(currentName, newField);
                    newField.AddNeighbour(prevFieldName, prevField);
                }

                if(p!=0){
                    if(q%2==1){
                        Field upperField = prevRow[q-1];
                        upperField.AddNeighbour(currentName, newField);
                        newField.AddNeighbour(afaszomat[q-1], upperField);

                        prevRow[q-1] = prevField;
                        afaszomat[q-1] = prevFieldName;
                        prevRow[q] = newField;
                        afaszomat[q] = currentName;
                    }else if(q==width-1){
                        prevRow[q] = newField;
                        afaszomat[q] = currentName;
                    }
                }else {
                    prevRow[q] = newField;
                    afaszomat[q] = currentName;
                }
                prevField = newField;
                prevFieldName = currentName;
            }
        }

        //Virológusok inicializálása
        while((line = br.readLine())!=null){
            String [] tmp = line.split(" ");
            Virologist selected = virologists.get(tmp[0]);
            if(!tmp[1].equals("-")){
                String [] eq = tmp[1].split(";");
                for(String s : eq){
                    String name = s.split("\\(")[0];
                    int tt = Integer.parseInt(s.split("\\(")[1].split("\\)")[0]);
                    Equipment e;
                    //Equipment init
                    switch (name.substring(0, 2)) {
                        case "Ea":
                            e = new Axe(selected, name);
                            break;
                        case "Ep":
                            e = new ProtSuit(tt, selected, name);
                            break;
                        case "Eg":
                            e = new Gloves(-1, tt, selected, name);
                            break;
                        case "Es":
                            e = new Sack(tt, selected, name);
                            break;
                        default:
                            e = null;
                            break;
                    }
                    selected.PickupEq(e);
                }
            }
            if(!tmp[2].equals("-")){
                String [] eq = tmp[2].split(";");
                for(String s : eq){
                    String name = s.split("\\(")[0];
                    int tt = Integer.parseInt(s.split("\\(")[1].split("\\)")[0]);
                    Effect e;
                    //Effect init
                    switch (name.substring(0, 2)) {
                        case "Co":
                            e = new Oblivion(name, selected, 5, 5);
                            break;
                        case "Cp":
                            e = new Paralysis(name, tt, selected, 5, 5);
                            break;
                        case "Cv":
                            e = new VirusDance(name, selected, 5, 5);
                            break;
                        case "Cf":
                            e = new FullProt(name, tt, selected, 5, 5);
                            break;
                        case "Cb":
                            e = new BearVirus(name, selected);
                            break;
                        default:
                            e = null;
                            break;
                    }
                    selected.AddEffect(e);
                }
            }
            //Megtanult kódok elhelyezése
            if(!tmp[3].equals("-")){
                String [] eq = tmp[3].split(";");
                for(String s : eq){
                    selected.LearnGenCode(codes.get(s));
                }
            }
            //Nyersanyagok init
            int max = Integer.parseInt(tmp[4].split(";")[0]);
            for(int p=0; p<max; p++){
                selected.PickupResource(new Aminoacid());
            }
            max = Integer.parseInt(tmp[4].split(";")[1]);
            for(int p=0; p<max; p++){
                selected.PickupResource(new Nucleotid());
            }
            //Hátralévő lépések init
            int rounds = Integer.parseInt(tmp[5]);
            if(rounds!=0){
                currentRound = selected;
                remainingSteps = rounds;
            }
            if(rounds==5){
                updateNeeded=true;
            }
        }

        br.close();

        gameEnded = false;

        if(updateNeeded || currentRound.HasBearVirus()){
            Timer.getInstance().Tick(currentRound);
        }

        gui.gameLoaded();
    }


    public static void enableActions(boolean action){
        enabled = action;
    }

    /**
     * Frissíti a játékot, minden virológus köre elején meghívódik
     */
    public static void updateGame(){
        ArrayList<String> list1 = new ArrayList<>(virologists.keySet());
        boolean allBear = true;
        for(String s : list1){
            Virologist v = virologists.get(s);
            if(!v.HasBearVirus()){
                allBear = false;
                break;
            }
        }
        if(allBear){
            gameEnded = true;
            remainingSteps--;
            gui.gameEndedMessage("Everybody has become a bear");
            return;
        }
        remainingSteps--;
        if(remainingSteps==0) {
            ArrayList<String> list = new ArrayList<>(virologists.keySet());
            Collections.sort(list);
            int i = list.indexOf(currentRound.getId());
            i=(i+1)% list.size();
            currentRound=virologists.get(list.get(i));
            remainingSteps=5;
            Timer.getInstance().Tick(currentRound);
        }
        gui.updateGui();
    }


    /**
     * Kör átugrása
     */
    public static void skip(){
        if(!enabled)
            return;
        remainingSteps = 1;
        updateGame();
        gui.updateGui();
    }

    /**
     * a soron lévő virológus felveszi az adott mezőn lévő nyersanyagot/felszerelést/megtanulja a geneteikai kódot
     */

    public static void pickup(){
        if(!enabled)
            return;
        currentRound.Collect();
        Game.updateGame();
        gui.updateGui();
    }


    public static void rob(String s, int i){
        if(!enabled)
            return;
        i--;
        Virologist v = virologists.get(s);
        currentRound.StealEqFrom(v, v.getEquipment(i));
        Game.updateGame();
        gui.updateGui();
    }

    public static Virologist getVirologist(String s){
        return virologists.get(s);
    }

    /**
     * Ágens kenése virológusra
     * @param s szó
     * @param sa ágens
     */
    public static void rub(String s, String sa){
        if(!enabled)
            return;
        Virologist v = virologists.get(s);
        Agent a = currentRound.getAgent(sa);
        if(a!=null)
            currentRound.ThrowAgentOn(v,a);
        Game.updateGame();
        gui.updateGui();
    }

    /**
     * Ha a felhasználó szomszédos mezőt adott meg, a virológus átlép a megadott mezőre
     * @param f - a mező, amire a virológust léptetni akarjuk
     */
    public static void step(String f){
        if(!enabled)
            return;
        Game.updateGame();
        currentRound.Move(f);
        gui.updateGui();
    }

    /**
     * megöli a paraméterként kapott virológust
     * @param s
     */
    public static void kill(String s){
        if(!enabled)
            return;
        Virologist v=virologists.get(s);
        currentRound.Kill(v);
        Game.updateGame();
        gui.updateGui();
    }

    /**
     * Játék vége
     */
    public static void EndGame(String winner){
        com.main.Skeleton.functionCall("Game.EndGame()");
        gameEnded = true;
        gui.updateGui();
        gui.gameEndedMessage(winner);
    }

    /**
     * Map kirajzolása
     */
    public static void showMap(){
        System.out.println(width + "×" + height);
        for(int y = 0; y<height; y++)
        {
            String out="";
            for(int x = 0; x<width;x++){
                for(String s : fields.keySet()) {
                    if (fields.get(s).x == x && fields.get(s).y == y) {
                        out += (s + "(" + fields.get(s).toString() + ") ");
                    }
                }
            }
            if(out.length()>0)
                System.out.println(out);
        }
        ArrayList<String> list = new ArrayList<>(virologists.keySet());
        Collections.sort(list);
        for(String s : list){
            String out="";
            out+=s;
            out+=" ";
            Virologist v = virologists.get(s);
            out += v.toString();
            if(v.equals(currentRound))
                out+=remainingSteps;
            else out+=0;
            System.out.println(out);
        }
        if(gameEnded){
            System.out.println("GAME OVER");
            return;
        }
    }

    /**
     * map mentése fájlba
     */
    public static void saveMap(String fileName) throws IOException {
        if(!enabled)
            return;
            FileWriter fw = new FileWriter(fileName);
            fw.write(width + "×" + height+"\n");
            for(int y = 0; y<height; y++)
            {
                String out="";
                for(int x = 0; x<width;x++){
                    for(String s : fields.keySet()) {
                        if (fields.get(s).x == x && fields.get(s).y == y) {
                            out += (s + "(" + fields.get(s).toString() + ") ");
                        }
                    }
                }
                if(out.length()>0)
                    fw.write(out+"\n");
            }
            ArrayList<String> list = new ArrayList<>(virologists.keySet());
            Collections.sort(list);
            for(String s : list){
                String out="";
                out+=s;
                out+=" ";
                Virologist v = virologists.get(s);
                out += v.toString();
                if(v.equals(currentRound))
                    out+=remainingSteps;
                else out+=0;
                fw.write(out+"\n");
            }
            fw.close();
    }


}
