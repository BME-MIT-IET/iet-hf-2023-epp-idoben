package gui;

import com.main.Game;
import com.main.Virologist;
import field.Field;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Triangle extends JComponent {
    ButtonContainer container;
    Field ref;
    boolean inverted;

    int size;
    int fieldX;
    int fieldY;
    boolean filled;
    boolean alive;
    boolean toBeAlive;
    Color c;

    ArrayList<Integer> xPositions;
    ArrayList<Integer> yPositions;

    /**
     * Háromszög mezőket létrehozó konstruktora
     * @param container tároló panel
     * @param ref a modellbeli elemre való hivatkozás
     * @param inverted milyen irányba áll a háromszög
     * @param size mező mérete
     * @param x x koordinátája
     * @param y y koordinátája
     * @param xID x koordinátája a modellben
     * @param yID y koordintája a modellben
     * @param filled ki van töltve
     * @param c szín
     */
    public Triangle(ButtonContainer container, Field ref, boolean inverted, int size, int x, int y, int xID, int yID, boolean filled, Color c){ // Háromszög
        this.container = container;
        this.ref = ref;
        this.size = size;
        this.fieldX = xID;
        this.fieldY = yID;
        this.filled = filled;
        alive = false;
        toBeAlive = false;
        this.c = c;

        this.inverted = inverted;
        xPositions = new ArrayList<Integer>();
        yPositions = new ArrayList<Integer>();
        xPositions.add(x);
        yPositions.add(y);
        xPositions.add(inverted ? x : x + size);
        yPositions.add(inverted ? y + size : y);
        xPositions.add(x + size);
        yPositions.add(y + size);
    }

    /**
     * Kattintás alapján, ha rá kattintottak akkor elvégzi a művelet
     * @param ix kattintás x koordinátája
     * @param iy kattintás y koordinátája
     */
    public void changeIfU(int ix, int iy) {
        if(inverted) {
            if(ix > fieldX * size && ix < fieldX * size + size && iy > fieldY * size && iy < fieldY * size + size && iy - fieldY * size > ix - fieldX * size) {
                if(!Game.gameEnded){
                    new Thread(()->Game.step(this.ref.getId())).start();
                }
            }
        }
        else {
            if(ix > fieldX * size && ix < fieldX * size + size && iy > fieldY * size && iy < fieldY * size + size && iy - fieldY * size < ix - fieldX * size) {
                if(!Game.gameEnded){
                    new Thread(()->Game.step(this.ref.getId())).start();
                }
            }
        }
    }

    /**
     * Rajzolás felülírása
     * @param g grafika objektum amire rajzolunk
     */
    @Override
    protected void paintComponent(Graphics g) {
        int[] xs = new int[3];
        int[] ys = new int[3];
        for(int i = 0; i < 3; i++) {
            xs[i] = xPositions.get(i);
            ys[i] = yPositions.get(i);
        }
        Polygon p = new Polygon(xs, ys, 3);
        g.setColor(c);
        g.fillPolygon(p);
        g.setColor(new Color(73, 73, 73));
        g.drawPolygon(p);
        int i = 0;
        container.drawItem(g, this.fieldX, this.fieldY, inverted, ref.mark());
        for (Virologist v : ref.virologist){
            container.removeVirologist(v.getId());
            container.drawVirologist(g, this.fieldX, this.fieldY, inverted, i++, v.getId());
        }
    }
}