import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class Editor extends JFrame implements ActionListener {

    private JTextArea textArea = new JTextArea();
    private JFrame frame = new JFrame("Text Editor");

    Editor(){
        JMenu menu = new JMenu("File");

        JMenuItem menuItemOne = new JMenuItem("New");
        JMenuItem menuItemTwo = new JMenuItem("Open");
        JMenuItem menuItemThree = new JMenuItem("Save");
        JMenuItem menuItemFour = new JMenuItem("Print");

        menuItemOne.addActionListener(this);
        menuItemTwo.addActionListener(this);
        menuItemThree.addActionListener(this);
        menuItemFour.addActionListener(this);

        menu.add(menuItemOne);
        menu.add(menuItemTwo);
        menu.add(menuItemThree);
        menu.add(menuItemFour);

        JMenu menuTwo = new JMenu("Edit");

        JMenuItem menuItemFive = new JMenuItem("Cut");
        JMenuItem menuItemSix = new JMenuItem("Copy");
        JMenuItem menuItemSeven = new JMenuItem("Paste");

        menuItemFive.addActionListener(this);
        menuItemSix.addActionListener(this);
        menuItemSeven.addActionListener(this);

        menuTwo.add(menuItemFive);
        menuTwo.add(menuItemSix);
        menuTwo.add(menuItemSeven);

        JMenuItem menuClose = new JMenuItem("Close");
        menuClose.addActionListener(this);

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menuBar.add(menuTwo);
        menuBar.add(menuClose);

        //JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        frame.setJMenuBar(menuBar);
        frame.add(textArea);
        frame.setSize(250, 400);
        frame.setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String stringCommand = e.getActionCommand();

        switch (stringCommand) {
            case "Cut":
                textArea.cut();
                break;
            case "Copy":
                textArea.copy();
                break;
            case "Paste":
                textArea.paste();
                break;
            case "Save":
                JFileChooser fileChooser = new JFileChooser("C:/Users/drago/Desktop/output.txt");

                int a = fileChooser.showSaveDialog(null);

                if (a == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                    try {
                        FileWriter fileWriter = new FileWriter(file, false);

                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                        bufferedWriter.write(textArea.getText());

                        bufferedWriter.flush();
                        bufferedWriter.close();
                    } catch (Exception event) {
                        JOptionPane.showMessageDialog(frame, event.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "The user har cancelled the operation");
                }
                break;
            case "Print":
                try {
                    textArea.print();
                } catch (Exception event) {
                    JOptionPane.showMessageDialog(frame, event.getMessage());
                }
                break;
            case "Open":
                JFileChooser fileChooserTwo = new JFileChooser("C:");

                int b = fileChooserTwo.showOpenDialog(null);

                if (b == JFileChooser.APPROVE_OPTION) {
                    File fileTwo = new File(fileChooserTwo.getSelectedFile().getAbsolutePath());

                    try {
                        String stringOne;
                        new StringBuilder();
                        StringBuilder stringLine;

                        FileReader fileReader = new FileReader(fileTwo);

                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        stringLine = new StringBuilder(bufferedReader.readLine());

                        while ((stringOne = bufferedReader.readLine()) != null) {
                            stringLine.append("\n").append(stringOne);
                        }

                        textArea.setText(stringLine.toString());
                    } catch (Exception event) {
                        JOptionPane.showMessageDialog(frame, event.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "The user har cancelled the operation");
                }
                break;
        }
    }
}