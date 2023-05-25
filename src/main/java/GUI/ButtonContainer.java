package GUI;

import com.main.Game;
import field.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * Mezők gombját tároló grafikai elem
 */
public class ButtonContainer extends JComponent {
    private ArrayList<Triangle> buttons;
    private int size = 120;
    private ArrayList<Integer> virologistx = new ArrayList<Integer>();
    private ArrayList<Integer> virologisty = new ArrayList<Integer>();
    private ArrayList<String> virologistname = new ArrayList<String>();
    private MainWindow frame;

    /**
     * Létrehozza a panelt és bállítja a birtoklóját
     * @param frame birtokló ablak
     */
    public ButtonContainer(MainWindow frame) {
        buttons = new ArrayList<Triangle>();
        setOpaque(false);
        this.frame = frame;
    }

    /**
     * Kattintás érzékelése és gombok + virológusok kattintásának vizsgálata
     * @param x kattintás x koordinátája
     * @param y kattintás y koordinátája
     */
    public void clicked(int x, int y) {
        x = x - 33;
        y = y - 79;
        for (int i = 0; i < virologistx.size(); ++i){
            if (x > virologistx.get(i) && x < virologistx.get(i) + 22 && y > virologisty.get(i) - 22 && y < virologisty.get(i)){
                frame.virologistClicked(virologistname.get(i));
                return;
            }
        }
        for(Triangle b : buttons) {
            b.changeIfU(x, y);
        }
    }

    /**
     * Létrehozza a pálya grafikus mezőit
     * @param width pálya szélessége mezőkben
     * @param height pálya magassága mezőkben
     */
    public void createButtons(int width, int height) {
        if (Game.fields != null){
            buttons.clear();
            for (String f : Game.fields.keySet()){
                if (Game.fields.get(f).getClass() == Simple.class){
                    buttons.add(new Triangle(this, Game.fields.get(f), Game.fields.get(f).x % 2 == 0, size, (Game.fields.get(f).x / 2) * size, Game.fields.get(f).y * size, Game.fields.get(f).x / 2, Game.fields.get(f).y, true, new Color(251, 234, 166)));
                }
                if (Game.fields.get(f).getClass() == WareHouse.class){
                    buttons.add(new Triangle(this, Game.fields.get(f),Game.fields.get(f).x % 2 == 0, size, (Game.fields.get(f).x / 2) * size, Game.fields.get(f).y * size, Game.fields.get(f).x / 2, Game.fields.get(f).y, true, new Color(183, 61, 186)));
                }
                if (Game.fields.get(f).getClass() == SafeHouse.class){
                    buttons.add(new Triangle(this, Game.fields.get(f),Game.fields.get(f).x % 2 == 0, size, (Game.fields.get(f).x / 2) * size, Game.fields.get(f).y * size, Game.fields.get(f).x / 2, Game.fields.get(f).y, true, new Color(194, 253, 15)));
                }
                if (Game.fields.get(f).getClass() == Lab.class){
                    buttons.add(new Triangle(this, Game.fields.get(f),Game.fields.get(f).x % 2 == 0, size, (Game.fields.get(f).x / 2) * size, Game.fields.get(f).y * size, Game.fields.get(f).x / 2, Game.fields.get(f).y, true, new Color(150, 90, 57)));
                }
            }
            this.repaint();
        }
    }

    /**
     * Rajzolás felüldefiniálása
     * @param g grafika objektum
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Triangle b : buttons) {
            b.paintComponent(g);
        }
    }

    /**
     * Virolóus eltávolítása a grafikáról
     * @param name virológus azonosítója
     */
    void removeVirologist(String name){
        int i = 0;
        ArrayList<String> copy = new ArrayList<>(virologistname);
        for (String s : copy){
            if (s.equals(name)){
                virologistname.remove(i);
                virologistx.remove(i);
                virologisty.remove(i);
            }
            ++i;
        }
    }

    /**
     * Virológusok kirajzolása a megadott mező és név alapján
     * @param g grafika objektum
     * @param x mező x koordinátája
     * @param y mező y koordinátája
     * @param inverted mező állása
     * @param n hanyadik virológus a mezőn
     * @param name virológus kiírandó neve
     */
    void drawVirologist(Graphics g, int x, int y, boolean inverted, int n, String name){
        g.setFont(new Font("Helvetica", Font.BOLD, 18));
        if (inverted){
            g.drawString(name, x * size + n * 25 + 3, y * size + size - 5);
            virologistx.add(x * size + n * 25 + 3);
            virologisty.add(y * size + size - 5);
        }
        else {
            g.drawString(name, x * size + size - (n + 1) * 25 - 3, y * size + 20);
            virologistx.add(x * size + size - (n + 1) * 25 - 3);
            virologisty.add(y * size + 20);
        }
        virologistname.add(name);
    }

    /**
     * Mezőn lévő dolog kirajzolása a megadott mező és név alapján
     * @param g grafika objektum
     * @param x mező x koordinátája
     * @param y mező y koordinátája
     * @param inverted mező állása
     * @param name mezőn lévő dolog neve
     */
    void drawItem(Graphics g, int x, int y, boolean inverted, String name){
        g.setFont(new Font("Helvetica", Font.BOLD, 18));
        if (inverted){
            g.drawString(name, x * size + 3, y * size + 55);
        }
        else {
            g.drawString(name, x * size + size - 35, y * size + size - 45);
        }
    }
}
