import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

import static javax.swing.text.html.HTML.Tag.OPTION;

public class MyNotePad implements ActionListener {
    JFrame jframe;
    JTextArea jTextArea;
    JMenuBar jMenuBar;
    JMenu file;
    JMenu edit;
    JMenu close;
    JMenuItem New;
    JMenuItem Open;
    JMenuItem Save;
    JMenuItem Print;
    JMenuItem Cut;
    JMenuItem Copy;
    JMenuItem Paste;
    JMenuItem Closeeditor;

    MyNotePad(){
        jframe = new JFrame("NotePad");
        jframe.setBounds(0,0,800,1000);
        jTextArea = new JTextArea("Welcome to my notepad");
        jMenuBar = new JMenuBar();
        file = new JMenu("File");
        file.addActionListener(this);
        edit = new JMenu("Edit");
        edit.addActionListener(this);
        close = new JMenu("Close");
        close.addActionListener(this);
        jMenuBar.add(file);
        jMenuBar.add(edit);
        jMenuBar.add(close);
        Open = new JMenuItem("Open");
        Open.addActionListener(this);

        Save = new JMenuItem("Save");
        Save.addActionListener(this);

        New  = new JMenuItem("New");
        New.addActionListener(this);

        Print = new JMenuItem("Print");
        Print.addActionListener(this);
        Cut = new JMenuItem("Cut");
        Cut.addActionListener(this);

        Copy = new JMenuItem("Copy");
        Copy.addActionListener(this);
        Paste = new JMenuItem("Paste");
        Paste.addActionListener(this);
        Closeeditor = new JMenuItem("Close");
        Closeeditor.addActionListener(this);
        file.add(New);
        file.add(Open);
        file.add(Save);
        file.add(Print);
        edit.add(Cut);
        edit.add(Copy);
        edit.add(Paste);
        close.add(Closeeditor);





        jframe.setJMenuBar(jMenuBar);



        jframe.add(jTextArea);

        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setVisible(true);
    }
    public static void main(String[] args) {
     MyNotePad m = new MyNotePad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if(s.equals("Copy")){
            jTextArea.copy();
        } else if (s.equals("Cut")) {
            jTextArea.cut();
        } else if (s.equals("Paste")) {
            jTextArea.paste();
        } else if (s.equals("Close")) {

            System.exit(1);
        } else if (s.equals("New")) {
            jTextArea.setText("");
        } else if (s.equals("Print")) {
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }

        } else if (s.equals("Open")) {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if(ans == jFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1 = "",s2 = "";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while ((s1 = bufferedReader.readLine())!= null){
                        s2+=s1+"\n";
                    }
                    jTextArea.setText(s2);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }

        } else if (s.equals("Save")) {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showSaveDialog(null);
            if(ans == jFileChooser.APPROVE_OPTION){
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file));
                    writer.write((jTextArea.getText()));
                    writer.flush();
                    writer.close();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
