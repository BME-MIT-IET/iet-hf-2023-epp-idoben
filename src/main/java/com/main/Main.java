package com.main;

import gui.MainWindow;

import java.io.IOException;

public class Main {
    private static MainWindow window;

    //Elindítja a grafikát, ami elindítja a modellt
    public static void main(String[] args) throws IOException {
        System.out.println("Na csáááááááá");

        window = new MainWindow();
        window.setVisible(true);
    }
}
