package GUI;

import com.main.Game;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

/**
 * A főablakot megjelenítő swing grafikus osztály
 */
public class MainWindow  extends JFrame{
    /**
     * A menüsáv navigációs gombjai
     */
    private JMenuBar menuBar = new JMenuBar();
    private JMenuItem newGameButton = new JMenuItem("New game");
    private JMenuItem loadGameButton = new JMenuItem("Load game");
    private JMenuItem saveGameButton = new JMenuItem("Save game");

    /**
     * Játék irányító gombok
     */
    private JButton robButton = new JButton("Rob");
    private JButton rubButton = new JButton("Rub");
    private JButton pickupButton = new JButton("Pickup");
    private JButton killButton = new JButton("Kill");
    private JButton skipRound = new JButton("→");

    /**
     * Soronlévő virológus adatai
     */
    private JLabel currentRound = new JLabel("V");
    private JLabel learntCodes = new JLabel("C");
    private JLabel amino = new JLabel("0");
    private JLabel nucleo = new JLabel("0");
    private JLabel equipment = new JLabel("E");
    private JLabel remainingSteps = new JLabel("0");
    private JLabel activeEffects = new JLabel("C");

    private boolean kill = false;
    private boolean rub = false;
    private boolean rob = false;

    ButtonContainer fields;


    /**
     * Egységes listener minden képernyőn lévő gombhoz
     */
    class Listener implements ActionListener{
        Frame frame;

        public Listener(Frame f){
            frame = f;
        }

        /**
         * Automatikus hívódó eseménykezelő függvény, lekezeli, hogy melyik gomb hívta
         * @param e Esemény ami bekövetkezett
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            rub=false;
            rob=false;
            kill=false;
            //Új játék betöltése
            if(e.getSource()==newGameButton){
                    new Thread(()-> {
                        try {
                            Game.loadGame("nagy_teszt.txt");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }).start();
            }
            //Mentett fájl betöltése
            if(e.getSource()==loadGameButton){
                String result = (String)JOptionPane.showInputDialog(
                        frame,
                        "Enter file name",
                        "",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );
                if(result!=null){
                        new Thread(()-> {
                            try {
                                Game.loadGame(result);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }).start();
                }
            }
            //Játék mentése fájlba
            if(e.getSource()==saveGameButton){
                String result = (String)JOptionPane.showInputDialog(
                        frame,
                        "Enter file name",
                        "",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null
                );
                if(result!=null){
                    try {
                        Game.saveMap(result);
                    } catch (IOException ex) {
                        System.out.println("Megpusztult");
                    }
                }
            }
            //Kör átugrása
            if(e.getSource()==skipRound && !Game.gameEnded){
                new Thread(()->Game.skip()).start();
            }
            //Tárgy felvétele
            if(e.getSource()==pickupButton && !Game.gameEnded){
                new Thread(()->Game.pickup()).start();
            }
            //Ágens kenése
            if(e.getSource()==rubButton && !Game.gameEnded){
                rub = true;
            }
            //Rablás
            if(e.getSource()==robButton && !Game.gameEnded){
                rob = true;
            }
            //Gyilkolászás
            if(e.getSource()==killButton && !Game.gameEnded){
                kill = true;
            }
        }
    }


    /**
     * Virológus kiválasztás érzékelése, majd a megfelelő játékfüggvény hívása
     * @param id virológus id-ja
     */
    public void virologistClicked(String id){
        if(!Game.gameEnded) {
            if (rub) {
                JPanel panel = new JPanel();
                panel.add(new JLabel("Select code to rub: "));
                DefaultComboBoxModel model = new DefaultComboBoxModel();
                for (String value : Game.currentRound.getCodes()) {
                    model.addElement(value);
                }
                JComboBox comboBox = new JComboBox(model);
                panel.add(comboBox);

                int iResult = JOptionPane.showConfirmDialog(null, panel, "Rub", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                switch (iResult) {
                    case JOptionPane.OK_OPTION:
                        new Thread(()->Game.rub(id, (String) comboBox.getSelectedItem())).start();
                        break;
                }
            }
            if (rob) {
                JPanel panel = new JPanel();
                panel.add(new JLabel("Select equipment to rob: "));
                DefaultComboBoxModel model = new DefaultComboBoxModel();
                for (String value : Game.getVirologist(id).getEquipments()) {
                    model.addElement(value);
                }
                JComboBox comboBox = new JComboBox(model);
                panel.add(comboBox);

                int iResult = JOptionPane.showConfirmDialog(null, panel, "Rob", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                switch (iResult) {
                    case JOptionPane.OK_OPTION:
                        int index = Game.getVirologist(id).getEquipments().indexOf((String) comboBox.getSelectedItem()) + 1;
                        new Thread(()->Game.rob(id, index)).start();
                        break;
                }
            }
            if (kill) {
                new Thread(()->Game.kill(id)).start();
            }
        }
        rub = false;
        rob = false;
        kill = false;
    }


    /**
     * Grafikus felület frissítése, ha megváltozott a modell
     */
    public void updateGui(){
        currentRound.setText(Game.currentRound.getId());
        String [] tmp = Game.currentRound.toString().split(" ");
        learntCodes.setText(tmp[2]);
        equipment.setText(tmp[0]);
        activeEffects.setText(tmp[1]);
        amino.setText(tmp[3].split(";")[0]);
        nucleo.setText(tmp[3].split(";")[1]);
        remainingSteps.setText(String.valueOf(Game.remainingSteps));

        fields.repaint();
        this.repaint();
    }


    /**
     * Játék vége felirat kiírása
     * @param msg üzenet amit kiír
     */
    public void gameEndedMessage(String msg){
        JOptionPane.showMessageDialog(this,
                msg,
                "Game ended",
                JOptionPane.WARNING_MESSAGE);
    }


    /**
     * Játék betöltésekor játék kezdeti kirajzolása
     */
    public void gameLoaded(){
        fields.createButtons(Game.width, Game.height);

        updateGui();
    }


    /**
     * Egérkattintást kezelő osztály
     */
    public class ClickListener implements MouseListener {
        JFrame window;
        public ClickListener(JFrame window){
            this.window = window;
        }
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        /**
         * Egérkattintást kezelő függvény
         * @param e
         */
        @Override
        public void mousePressed(MouseEvent e) {
            fields.clicked(e.getX(), e.getY());
            window.repaint();
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }
    }


    /**
     * Konstruktor, ami elrendezi a főablakot és inicializálja a rajta lévő elemeket
     */
    public MainWindow() {
        this.setTitle("Projlab <3");
        this.setSize(800, 800);
        this.setResizable(true);
        this.setLayout(new BorderLayout());
        Image icon = Toolkit.getDefaultToolkit().getImage(new File("goldi.jpg").getAbsolutePath());
        this.setIconImage(icon);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Game.setGui(this);

        Listener listener = new Listener(this);


        this.addMouseListener(new ClickListener(this));

        //Alul a szövegek amik kiírják a soronlévő virológus adatait
        Color orange = new Color(255,103,0);
        Font labelFont = new Font("Arial", Font.PLAIN, 25);
        currentRound.setFont(labelFont);
        learntCodes.setFont(labelFont);
        remainingSteps.setFont(labelFont);
        amino.setFont(labelFont);
        nucleo.setFont(labelFont);
        equipment.setFont(labelFont);
        activeEffects.setFont(labelFont);
        skipRound.setForeground(Color.red);
        skipRound.setFont(new Font("Arial", Font.BOLD, 40));
        skipRound.setBackground(Color.CYAN);
        skipRound.setBorderPainted(false);
        skipRound.setFocusPainted(false);
        skipRound.setBackground(new Color(0,160,255));
        skipRound.setPreferredSize(new Dimension(120,60));
        skipRound.addActionListener(listener);

        //Gombok mindenol...
        newGameButton.addActionListener(listener);
        loadGameButton.addActionListener(listener);
        saveGameButton.addActionListener(listener);
        menuBar.add(newGameButton);
        menuBar.add(loadGameButton);
        menuBar.add(saveGameButton);
        this.setJMenuBar(menuBar);

        //Mező téglalap
        JPanel green = new JPanel();
        green.setBorder(BorderFactory.createLineBorder(orange, 6));
        green.setBackground(Color.gray);
        green.setLayout(new BorderLayout());

        //...még több gomb
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(10, 80));
        buttonPanel.setBackground(Color.gray);
        buttonPanel.add(robButton);
        robButton.setPreferredSize(new Dimension(120,60)); robButton.setFocusPainted(false); robButton.setBorderPainted(false);
        rubButton.setPreferredSize(new Dimension(120,60));  rubButton.setFocusPainted(false); rubButton.setBorderPainted(false);
        killButton.setPreferredSize(new Dimension(120,60)); killButton.setFocusPainted(false); killButton.setBorderPainted(false);
        pickupButton.setPreferredSize(new Dimension(160,60)); pickupButton.setFocusPainted(false); pickupButton.setBorderPainted(false);
        rubButton.setFont(new Font("Arial", Font.PLAIN, 34));
        robButton.setFont(new Font("Arial", Font.PLAIN, 34));
        killButton.setFont(new Font("Arial", Font.PLAIN, 34));
        pickupButton.setFont(new Font("Arial", Font.PLAIN, 34));
        robButton.setForeground(Color.red);
        rubButton.setForeground(Color.red);
        killButton.setForeground(Color.red);
        pickupButton.setForeground(Color.red);
        robButton.setBackground(new Color(0,160,255));
        rubButton.setBackground(new Color(0,160,255));
        killButton.setBackground(new Color(0,160,255));
        pickupButton.setBackground(new Color(0,160,255));
        robButton.addActionListener(listener);
        rubButton.addActionListener(listener);
        killButton.addActionListener(listener);
        pickupButton.addActionListener(listener);
        buttonPanel.add(rubButton);
        buttonPanel.add(killButton);
        buttonPanel.add(pickupButton);
        buttonPanel.add(skipRound);
        green.add(buttonPanel, BorderLayout.PAGE_END);

        //Pályát magéba foglaló téglalap
        JPanel fieldPanel = new JPanel();
        fieldPanel.setPreferredSize(new Dimension(10, 100));
        fieldPanel.setBackground(Color.gray);
        fieldPanel.setLayout(new BorderLayout());
        fields = new ButtonContainer(this);
        fieldPanel.add(fields, BorderLayout.CENTER);
        fieldPanel.repaint();
        green.add(fieldPanel, BorderLayout.CENTER);


        //Alsó adat téglalap
        JPanel data = new JPanel();
        data.setPreferredSize(new Dimension(10, 100));
        data.setBackground(Color.CYAN);
        data.setLayout(new BorderLayout());

        //Sok szöveg
        JLabel j1 = new JLabel("Current round: "); j1.setForeground(orange); j1.setFont(labelFont);
        JLabel j2 = new JLabel(" Learnt codes: "); j2.setForeground(orange); j2.setFont(labelFont);
        JLabel j3 = new JLabel(" Remaining steps: "); j3.setForeground(Color.red); j3.setFont(labelFont);
        JLabel j4 = new JLabel("Amino: "); j4.setForeground(orange); j4.setFont(labelFont);
        JLabel j5 = new JLabel(" Nucleo: "); j5.setForeground(orange); j5.setFont(labelFont);
        JLabel j6 = new JLabel(" Equipments: "); j6.setForeground(orange); j6.setFont(labelFont);
        JLabel j7 = new JLabel(" Active effects: "); j7.setForeground(orange); j7.setFont(labelFont);
        JPanel topData = new JPanel();
        topData.setBackground(Color.CYAN);
        topData.add(j1); topData.add(currentRound);
        topData.add(j2); topData.add(learntCodes);
        topData.add(j3); topData.add(remainingSteps);
        JPanel bottomData = new JPanel();
        bottomData.setPreferredSize(new Dimension(10, 50));
        bottomData.setBackground(Color.CYAN);
        bottomData.add(j4); bottomData.add(amino);
        bottomData.add(j5); bottomData.add(nucleo);
        bottomData.add(j6); bottomData.add(equipment);
        bottomData.add(j7); bottomData.add(activeEffects);

        data.add(topData, BorderLayout.CENTER);
        data.add(bottomData, BorderLayout.PAGE_END);

        //Paddingek
        JPanel topPadding = new JPanel();
        topPadding.setPreferredSize(new Dimension(80, 20));
        topPadding.setBackground(Color.CYAN);
        JPanel leftPadding = new JPanel();
        leftPadding.setPreferredSize(new Dimension(20, 200));
        leftPadding.setBackground(Color.CYAN);
        JPanel rightPadding = new JPanel();
        rightPadding.setPreferredSize(new Dimension(20, 200));
        rightPadding.setBackground(Color.CYAN);

        //Dolgok hozzáadása az ablakhoz
        this.repaint();
        this.getContentPane().add(green, BorderLayout.CENTER);
        this.getContentPane().add(data, BorderLayout.PAGE_END);
        this.getContentPane().add(topPadding, BorderLayout.PAGE_START);
        this.getContentPane().add(leftPadding, BorderLayout.LINE_START);
        this.getContentPane().add(rightPadding, BorderLayout.LINE_END);

    }
}