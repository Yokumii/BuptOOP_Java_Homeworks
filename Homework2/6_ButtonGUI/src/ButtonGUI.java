import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * ButtonGUI - A GUI application demonstrating various button interactions
 * Uses GridBagLayout to arrange buttons in a grid as shown in the requirements
 */
public class ButtonGUI extends JFrame {
    
    // Declare buttons as class members for easy access
    private JButton[] buttons = new JButton[20]; // Array to hold all buttons
    private boolean button10Enabled = true; // Track button 10 state
    
    /**
     * Constructor - sets up the GUI
     */
    public ButtonGUI() {
        // Set up the frame
        setTitle("Button GUI Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        
        // Create main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Initialize buttons
        for (int i = 1; i <= 19; i++) {
            buttons[i] = new JButton(String.valueOf(i));
        }
        
        // Set up the GridBagLayout constraints
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(2, 2, 2, 2); // Add some padding
        
        // Create a panel for buttons 2, 3, 4 to ensure they have the same total width as column 5
        JPanel panel234 = new JPanel(new GridLayout(1, 3));
        panel234.add(buttons[2]);
        panel234.add(buttons[3]);
        panel234.add(buttons[4]);
        
        // Create a panel for buttons 7, 8, 9 to ensure they have the same total width as column 5
        JPanel panel789 = new JPanel(new GridLayout(1, 3));
        panel789.add(buttons[7]);
        panel789.add(buttons[8]);
        panel789.add(buttons[9]);
        
        // Rightmost column (column 5)
        // Button 1 (row 0)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(buttons[1], gbc);
        
        // Panel for buttons 2, 3, 4 (row 1)
        gbc.gridy = 1;
        mainPanel.add(panel234, gbc);
        
        // Button 5 (row 2)
        gbc.gridy = 2;
        mainPanel.add(buttons[5], gbc);
        
        // Button 6 (row 3)
        gbc.gridy = 3;
        mainPanel.add(buttons[6], gbc);
        
        // Panel for buttons 7, 8, 9 (row 4)
        gbc.gridy = 4;
        mainPanel.add(panel789, gbc);
        
        // Button 10 (row 5)
        gbc.gridy = 5;
        mainPanel.add(buttons[10], gbc);
        
        // Button 11 (row 6)
        gbc.gridy = 6;
        mainPanel.add(buttons[11], gbc);
        
        // Button 12 (row 7)
        gbc.gridy = 7;
        mainPanel.add(buttons[12], gbc);
        
        // Create a panel for buttons 13-17 to ensure they have the same height as buttons 18+19
        JPanel panelBottom = new JPanel(new GridLayout(1, 5));
        panelBottom.add(buttons[13]);
        panelBottom.add(buttons[14]);
        panelBottom.add(buttons[15]);
        panelBottom.add(buttons[16]);
        panelBottom.add(buttons[17]);
        
        // Create a panel for buttons 18 and 19 to ensure they have the same height as the bottom row
        JPanel panel1819 = new JPanel(new GridLayout(2, 1));
        panel1819.add(buttons[18]);
        panel1819.add(buttons[19]);
        
        // Bottom row
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.weightx = 5.0; // Make this panel take more horizontal space
        mainPanel.add(panelBottom, gbc);
        
        // Buttons 18 and 19
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        mainPanel.add(panel1819, gbc);
        
        // Add action listeners to buttons
        
        // Button 5: Replace button 6 with a text field
        buttons[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 将按钮6变成一个空白文本行
                JTextField textField = new JTextField();
                buttons[6].setLayout(new BorderLayout());
                buttons[6].removeAll(); // 移除按钮上的所有组件
                buttons[6].add(textField, BorderLayout.CENTER);
                buttons[6].revalidate();
                buttons[6].repaint();
            }
        });
        
        // Button 7: Add a text field to button 8
        buttons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在按钮8内部添加一个空白文本行，保留原有的数字8
                JPanel innerPanel = new JPanel(new BorderLayout());
                
                // 创建一个标签来显示原始的"8"文本
                JLabel originalLabel = new JLabel("8", JLabel.CENTER);
                originalLabel.setFont(buttons[8].getFont()); // 使用与按钮相同的字体
                
                // 创建一个空白文本行
                JTextField textField = new JTextField();
                
                // 将标签和文本框添加到内部面板
                innerPanel.add(originalLabel, BorderLayout.CENTER);
                innerPanel.add(textField, BorderLayout.SOUTH);
                
                // 设置按钮8的布局并添加内部面板
                buttons[8].setLayout(new BorderLayout());
                buttons[8].removeAll(); // 移除按钮上的所有组件
                buttons[8].add(innerPanel, BorderLayout.CENTER);
                
                // 刷新按钮显示
                buttons[8].revalidate();
                buttons[8].repaint();
            }
        });
        
        // Button 9: Toggle button 10's enabled state
        buttons[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button10Enabled = !button10Enabled;
                buttons[10].setEnabled(button10Enabled);
            }
        });
        
        // Create a shared action listener for buttons 10 and 11
        ActionListener colorChangeListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton sourceButton = (JButton) e.getSource();
                
                if (sourceButton == buttons[10]) {
                    // Button 10: Change button 15 to red background, green text
                    buttons[15].setBackground(Color.RED);
                    buttons[15].setForeground(Color.GREEN);
                } else if (sourceButton == buttons[11]) {
                    // Button 11: Change button 15 to blue background, red text
                    buttons[15].setBackground(Color.BLUE);
                    buttons[15].setForeground(Color.RED);
                }
                
                // Make sure the background color is visible
                buttons[15].setOpaque(true);
                buttons[15].setBorderPainted(false);
            }
        };
        
        // Add the shared listener to buttons 10 and 11
        buttons[10].addActionListener(colorChangeListener);
        buttons[11].addActionListener(colorChangeListener);
        
        // Add the main panel to the frame
        getContentPane().add(mainPanel);
        
        // Center the frame on the screen
        setLocationRelativeTo(null);
    }
    
    /**
     * Main method - creates and displays the GUI
     */
    public static void main(String[] args) {
        // Use the Event Dispatch Thread for Swing components
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ButtonGUI gui = new ButtonGUI();
                gui.setVisible(true);
            }
        });
    }
}