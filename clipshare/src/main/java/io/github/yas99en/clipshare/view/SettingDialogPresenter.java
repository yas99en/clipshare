package io.github.yas99en.clipshare.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SettingDialogPresenter {
    private final SettingDialog dialog = new SettingDialog();

    public SettingDialogPresenter() {
        dialog.getOkButton().addActionListener(e -> dialog.setVisible(false));
        dialog.getCancelButton().addActionListener(e -> dialog.setVisible(false));
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.setVisible(false);
            }
        });
    }

    public void show() {
        dialog.setVisible(true);
    }
}
