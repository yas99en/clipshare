package io.github.yas99en.clipshare;

import javax.swing.SwingUtilities;

public class ClipShare {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("run");
        });
    }
}
