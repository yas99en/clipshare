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
    private JTextField textField;
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
        
        JLabel lblMode = new JLabel("mode");
        
        JRadioButton rdbtnServer = new JRadioButton("server");
        buttonGroup.add(rdbtnServer);
        
        JRadioButton rdbtnClient = new JRadioButton("client");
        buttonGroup.add(rdbtnClient);
        
        JLabel lblHost = new JLabel("host");
        
        textField = new JTextField();
        textField.setColumns(10);
        
        JLabel lblPort = new JLabel("port");
        
        JFormattedTextField formattedTextField = new JFormattedTextField();
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
            gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addComponent(lblMode)
                            .addGap(18)
                            .addComponent(rdbtnServer)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(rdbtnClient))
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addComponent(lblHost)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(textField))
                        .addGroup(gl_contentPanel.createSequentialGroup()
                            .addComponent(lblPort)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(formattedTextField)))
                    .addContainerGap(236, Short.MAX_VALUE))
        );
        gl_contentPanel.setVerticalGroup(
            gl_contentPanel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_contentPanel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblMode)
                        .addComponent(rdbtnServer)
                        .addComponent(rdbtnClient))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblHost)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblPort)
                        .addComponent(formattedTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
