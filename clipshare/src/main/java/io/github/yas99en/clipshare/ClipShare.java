package io.github.yas99en.clipshare;

import java.awt.AWTException;
import java.io.IOException;

import javax.swing.SwingUtilities;

import io.github.yas99en.clipshare.view.IconPresenter;

public class ClipShare {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClipShare::run);
    }

    public static void run() {
        try {
            new IconPresenter();
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }
}
