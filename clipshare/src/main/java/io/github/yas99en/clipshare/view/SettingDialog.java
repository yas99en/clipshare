package io.github.yas99en.clipshare.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

public class SettingDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private JTextField hostField;
    private JButton cancelButton;
    private JButton okButton;

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
        setTitle("ClipShare Settings");
        setBounds(100, 100, 239, 200);
        setLocationByPlatform(true);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        
        JLabel modeLabel = new JLabel("mode");
        
        JRadioButton serverMode = new JRadioButton("server");
        buttonGroup.add(serverMode);
        
        JRadioButton clientMode = new JRadioButton("client");
        buttonGroup.add(clientMode);
        
        JLabel hostLabel = new JLabel("host");
        
        hostField = new JTextField();
        hostField.setColumns(10);
        
        JLabel portLabel = new JLabel("port");
        
        JFormattedTextField portField = new JFormattedTextField();
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
                okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
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
}
