package io.github.yas99en.clipshare.view;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import io.github.yas99en.clipshare.model.ClipShareConfig;
import io.github.yas99en.clipshare.model.ClipShareContext;

public class SettingDialogPresenter {
    private final SettingDialog dialog = new SettingDialog();
    private final ClipShareContext context = ClipShareContext.getInstance();
    private final ClipShareConfig config = context.getConfig();

    public SettingDialogPresenter() {
        dialog.getOkButton().addActionListener(this::onOk);
        dialog.getCancelButton().addActionListener(e -> dialog.setVisible(false));
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.setVisible(false);
            }
        });
    }

    private void onOk(ActionEvent e) {
        if(dialog.getServerMode().isSelected()) {
            config.setServerMode(true);
        }
        if(dialog.getClientMode().isSelected()) {
            config.setServerMode(false);
        }

        config.setHost(dialog.getHostField().getText());
        config.setPort(Integer.parseInt(dialog.getPortField().getText()));

        dialog.setVisible(false);
    }

    public void show() {
        if(config.isServerMode()) {
            dialog.getServerMode().setSelected(true);
        } else {
            dialog.getClientMode().setSelected(true);
        }

        dialog.getHostField().setText(config.getHost());
        dialog.getPortField().setText(String.valueOf(config.getPort()));

        dialog.setVisible(true);
    }
}
