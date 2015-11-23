package io.github.yas99en.clipshare.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;

public class SettingDialog extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private JRadioButton rdbtnNewRadioButton;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private final ButtonGroup buttonGroup = new ButtonGroup();

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
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            {
                rdbtnNewRadioButton = new JRadioButton("New radio button");
                buttonGroup.add(rdbtnNewRadioButton);
                panel.add(rdbtnNewRadioButton);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            {
                JLabel lblAaaa = new JLabel("aaaa");
                panel.add(lblAaaa);
            }
            {
                textField = new JTextField();
                panel.add(textField);
                textField.setColumns(10);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            {
                JLabel lblNewLabel = new JLabel("New label");
                panel.add(lblNewLabel);
            }
            {
                textField_1 = new JTextField();
                panel.add(textField_1);
                textField_1.setColumns(10);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
            {
                JLabel lblNewLabel_1 = new JLabel("New label");
                panel.add(lblNewLabel_1);
            }
            {
                textField_2 = new JTextField();
                panel.add(textField_2);
                textField_2.setColumns(10);
            }
        }
        {
            JPanel panel = new JPanel();
            contentPanel.add(panel);
            {
                JLabel lblNewLabel_2 = new JLabel("New label");
                panel.add(lblNewLabel_2);
            }
            {
                textField_3 = new JTextField();
                panel.add(textField_3);
                textField_3.setColumns(10);
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancel");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
        }
    }
}
