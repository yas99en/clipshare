package io.github.yas99en.clipshare.view;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.BackingStoreException;

import javax.websocket.DeploymentException;

import io.github.yas99en.clipshare.model.ClipShareConfig;
import io.github.yas99en.clipshare.model.ClipShareContext;

public class SettingDialogPresenter {
    private final SettingDialog dialog = new SettingDialog();
    private final ClipShareContext context = ClipShareContext.getInstance();
    private final ClipShareConfig config = context.getConfig();
    private final IconPresenter iconPresenter;

    public SettingDialogPresenter(IconPresenter iconPresenter) {
        dialog.getOkButton().addActionListener(this::onOk);
        dialog.getCancelButton().addActionListener(e -> dialog.setVisible(false));
        dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dialog.setVisible(false);
            }
        });
        this.iconPresenter = iconPresenter;
    }

    private void onOk(ActionEvent e) {
        dialog.setVisible(false);

        context.getServer().stop();
        context.getClient().stop();

        config.setHost(dialog.getHostField().getText());
        config.setPort(Integer.parseInt(dialog.getPortField().getText()));

        if(dialog.getServerMode().isSelected()) {
            config.setServerMode(true);
            try {
                context.getServer().start(config.getPort());
            } catch (DeploymentException e1) {
                iconPresenter.serverStartFailed();
            }
        } else {
            config.setServerMode(false);
            context.getClient().start(config.getHost(), config.getPort());
        }

        iconPresenter.update();

        try {
            config.flush();
        } catch (BackingStoreException ex) {
            iconPresenter.showMessage(ex.getLocalizedMessage());
        }
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
