package io.github.yas99en.clipshare.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class SettingDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField hostField;
    private JButton cancelButton;
    private JButton okButton;
    private JRadioButton serverMode;
    private JFormattedTextField portField;
    private JRadioButton clientMode;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            SettingDialog dialog = new SettingDialog();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public SettingDialog() {
        setTitle(Messages.getString("SettingDialog.title"));
        setBounds(100, 100, 282, 200);
        setLocationByPlatform(true);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        JLabel modeLabel = new JLabel(Messages.getString("SettingDialog.modeLabel"));
        
        serverMode = new JRadioButton(Messages.getString("SettingDialog.serverMode"));
        buttonGroup.add(serverMode);
        
        clientMode = new JRadioButton(Messages.getString("SettingDialog.clientMode"));
        buttonGroup.add(clientMode);
        
        JLabel hostLabel = new JLabel(Messages.getString("SettingDialog.hostLabel"));
        
        hostField = new JTextField();
        hostField.setToolTipText(Messages.getString("SettingDialog.hostTooltip")); //$NON-NLS-1$
        hostField.setColumns(10);
        
        JLabel portLabel = new JLabel(Messages.getString("SettingDialog.portLabel"));
        
        try {
            portField = new JFormattedTextField(new MaskFormatter("#####"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
            gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addComponent(modeLabel)
                            .addGap(18)
                            .addComponent(serverMode)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(clientMode))
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addComponent(hostLabel)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(hostField))
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addComponent(portLabel)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(portField)))
                    .addContainerGap(236, Short.MAX_VALUE))
        );
        gl_contentPanel.setVerticalGroup(
            gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(modeLabel)
                        .addComponent(serverMode)
                        .addComponent(clientMode))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(hostLabel)
                        .addComponent(hostField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(portLabel)
                        .addComponent(portField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(138, Short.MAX_VALUE))
        );
        gl_contentPanel.setAutoCreateGaps(true);
        gl_contentPanel.setAutoCreateContainerGaps(true);
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                okButton = new JButton(Messages.getString("SettingDialog.okButton"));
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                cancelButton = new JButton(Messages.getString("SettingDialog.cancelButton"));
                buttonPane.add(cancelButton);
            }
        }
    }
    public JButton getCancelButton() {
        return cancelButton;
    }
    public JButton getOkButton() {
        return okButton;
    }
    public JRadioButton getServerMode() {
        return serverMode;
    }
    public JTextField getHostField() {
        return hostField;
    }
    public JFormattedTextField getPortField() {
        return portField;
    }
    public JRadioButton getClientMode() {
        return clientMode;
    }
}
