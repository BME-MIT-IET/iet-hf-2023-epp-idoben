package com.main;

import GUI.MainWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static com.main.Game.*;

public class Main {
    private static MainWindow window;

    //Elindítja a grafikát, ami elindítja a modellt
    public static void main(String[] args) throws IOException {
        System.out.println("Na csáááááááá");

        window = new MainWindow();
        window.setVisible(true);
    }
}
