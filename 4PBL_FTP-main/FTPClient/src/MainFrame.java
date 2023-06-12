import java.awt.*;
import java.awt.event.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/*
 * Created by JFormDesigner on Fri Nov 11 11:44:51 ICT 2022
 */



/**
 * @author lee_kayn
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        initComponents();
    }

    private void toggleButton1(ActionEvent e) {
        // TODO add your code here
        if(toggleButton1.isSelected()){

            JOptionPane.showMessageDialog(this,"send (Sends a file)\nreceive (Receives a file)\nlist (Lists all files in current working directiry)/"
                    + "\ncd (change working directory)\nmkdir (creates a new directory)\nrmdir (deletes a directory if it's empty)\ndelete (delete's a file)" , "List of Comments", JOptionPane.INFORMATION_MESSAGE);

            toggleButton1.doClick();
        }
    }


    //Execute
    private void button1(ActionEvent e) {
        // TODO add your code here
        Thread queryThread = new Thread() {
            public void run() {
                button1.setEnabled(false);
                try {
                    executeCommands();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        queryThread.start();
    }


    //Disconnect
    private void button2(ActionEvent e) {
        // TODO add your code here
        try {
            obj_client.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] args={"as"};
        Client_Form.main(args);
        this.setVisible(false);
    }

    private void button3(ActionEvent e) {
        // TODO add your code here
        int returnVal = fileChooser2.showOpenDialog(this);
        if (returnVal == fileChooser1.APPROVE_OPTION){
            String path = fileChooser2.getSelectedFile().getAbsolutePath();
            System.out.println(path);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        toggleButton1 = new JToggleButton();
        button1 = new JButton();
        button2 = new JButton();
        progressBar1 = new JProgressBar();
        label5 = new JLabel();
        fileChooser1 = new JFileChooser();
        fileChooser2 = new JFileChooser();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("Port");
        contentPane.add(label1);
        label1.setBounds(10, 25, 35, label1.getPreferredSize().height);

        //---- textField1 ----
        textField1.setEnabled(false);
        contentPane.add(textField1);
        textField1.setBounds(70, 20, 90, textField1.getPreferredSize().height);

        //---- label2 ----
        label2.setText("UserName");
        contentPane.add(label2);
        label2.setBounds(180, 25, label2.getPreferredSize().width, 20);

        //---- textField2 ----
        textField2.setEnabled(false);
        contentPane.add(textField2);
        textField2.setBounds(245, 20, 95, textField2.getPreferredSize().height);

        //---- label3 ----
        label3.setText("PWD");
        contentPane.add(label3);
        label3.setBounds(new Rectangle(new Point(10, 80), label3.getPreferredSize()));

        //---- textField3 ----
        textField3.setEnabled(false);
        contentPane.add(textField3);
        textField3.setBounds(70, 75, 265, textField3.getPreferredSize().height);

        //---- label4 ----
        label4.setText("CMD");
        contentPane.add(label4);
        label4.setBounds(new Rectangle(new Point(10, 130), label4.getPreferredSize()));
        contentPane.add(textField4);
        textField4.setBounds(70, 125, 270, 30);

        //---- toggleButton1 ----
        toggleButton1.setText("Help");
        toggleButton1.addActionListener(e -> toggleButton1(e));
        contentPane.add(toggleButton1);
        toggleButton1.setBounds(new Rectangle(new Point(360, 125), toggleButton1.getPreferredSize()));

        //---- button1 ----
        button1.setText("Execute");
        button1.addActionListener(e -> button1(e));
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(170, 180), button1.getPreferredSize()));

        //---- button2 ----
        button2.setText("Disconnect");
        button2.addActionListener(e -> button2(e));
        contentPane.add(button2);
        button2.setBounds(new Rectangle(new Point(345, 20), button2.getPreferredSize()));

        //---- progressBar1 ----
        progressBar1.setPreferredSize(new Dimension(146, 10));
        contentPane.add(progressBar1);
        progressBar1.setBounds(345, 75, 90, 25);

        //---- label5 ----
        label5.setIcon(new ImageIcon(getClass().getResource("/FTPImage.jpeg")));
        contentPane.add(label5);
        label5.setBounds(315, 160, 135, 130);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());

        //---- fileChooser2 ----
        fileChooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setDefaultCloseOperation(3);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JToggleButton toggleButton1;
    private JButton button1;
    private JButton button2;
    private JProgressBar progressBar1;
    private JLabel label5;
    private JFileChooser fileChooser1;
    private JFileChooser fileChooser2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    String userName,Port,pwd,Host;
    String Comand;
    ClientFTP obj_client = new ClientFTP();

    public MainFrame(String args[]) throws Exception {
        Host=args[0];
        Port=args[1];
        userName = args[2];
        initComponents();
        myInitComponents();
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    obj_client.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                e.getWindow().dispose();
            }
        });
    }

    public void myInitComponents() throws Exception {
        textField1.setText(Port);
        textField2.setText(userName);
        pwd= obj_client.getPWD();
        textField3.setText("PWD: "+pwd);
    }

    public String getChoice() {
        String choice =null;
        int n;
        n=JOptionPane.showConfirmDialog(this,"Do you want to overwrite?","File Already Exists",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(n==0)
        {
            choice ="Y";
        }
        else {
            choice="N";
        }
        return choice;
    }

    private void executeCommands() throws IOException{
        Comand=textField4.getText();
        Comand=Comand.toLowerCase();
        if (Comand.compareTo("send")==0){

            int returnVal = fileChooser1.showOpenDialog(this);
            if (returnVal == fileChooser1.APPROVE_OPTION) {
                File file = fileChooser1.getSelectedFile();

                ClientFTP.dout.writeUTF("SEND");
                ClientFTP.datasoc=new Socket(Host,ClientFTP.dataPort);
                ClientFTP.datadin=new DataInputStream(ClientFTP.datasoc.getInputStream());
                ClientFTP.dataout=new DataOutputStream(ClientFTP.datasoc.getOutputStream());
                String filename=file.getName();
                String path=file.getAbsolutePath();

                ClientFTP.dout.writeUTF(filename);

                String msgFromServer=ClientFTP.din.readUTF();
                if(msgFromServer.compareTo("File Already Exists")==0)
                {
                    String Option;
                    MainFrame obj_main=new MainFrame();
                    Option=obj_main.getChoice();
                    if(Option=="Y")
                    {
                        ClientFTP.dout.writeUTF("Y");
                    }
                    else
                    {
                        ClientFTP.dout.writeUTF("N");
                        return;
                    }
                }

                System.out.println("Sending File ...");
                FileInputStream fin=new FileInputStream(file);
                double filelength=file.length();
                double updatelength=filelength/1000;

                int ch,count=0;
                do
                {
                    if(count >= updatelength){
                        double progValue=((updatelength/filelength)*100);
                        System.out.println("size" + (int) progValue);
                        updatelength+=updatelength;
                        progressBar1.setValue((int) progValue);
                        progressBar1.update(progressBar1.getGraphics());
                    }
                    count++;
                    ch=fin.read();

                    ClientFTP.dataout.writeUTF(String.valueOf(ch));
                }
                while(ch!=-1);
                fin.close();
                System.out.println(ClientFTP.din.readUTF());
                progressBar1.setValue(100);
                //return true;
            }


            else {
                System.out.println("File access cancelled by user.");
            }

        }
        else if (Comand.compareTo("receive")==0){

            ArrayList<String> fileList= new ArrayList<String>();
            try {
                fileList=obj_client.getFiles();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //String fileList[] = new String[30];
            String[] inputlist = new String[fileList.size()];
            inputlist = fileList.toArray(inputlist);
            System.out.println("In receive before pane");
            String fileName = (String) JOptionPane.showInputDialog(this, "Choose a File to Download/Receive", "Input", JOptionPane.QUESTION_MESSAGE,
                    null, inputlist, "Titan");
            System.out.println("In receive after pane");
            System.out.println(fileName);
            ClientFTP.dout.writeUTF("GET");
            ClientFTP.datasoc=new Socket(Host,ClientFTP.dataPort);
            ClientFTP.datadin=new DataInputStream(ClientFTP.datasoc.getInputStream());
            ClientFTP.dataout=new DataOutputStream(ClientFTP.datasoc.getOutputStream());
            ClientFTP.dout.writeUTF(fileName);
            String msgFromServer=ClientFTP.din.readUTF();

            if(msgFromServer.compareTo("File Not Found")==0)
            {
                System.out.println("File not found on Server ...");
                return;
            }
            else if(msgFromServer.compareTo("READY")==0)
            {
                System.out.println("Receiving File ...");
                File f=new File(fileName);
                if(f.exists())
                {
                    String Option;
                    System.out.println("File Already Exists. Want to OverWrite (Y/N) ?");
                    //Option=br.readLine();
                    MainFrame obj_main=new MainFrame();
                    Option=obj_main.getChoice();
                    if(Option=="N")
                    {
                        ClientFTP.dout.flush();
                        return;
                    }
                }
                FileOutputStream fout=new FileOutputStream(f);
                int ch;
                String temp;
                int count=0;
                double receivelength,filelength;
                filelength=ClientFTP.din.readDouble();
                receivelength=filelength/1000;

                do
                {
                    if (count > receivelength){
                        //System.out.println("in client" + updatelength);

                        double progValue=((receivelength/filelength)*100);
                        System.out.println("size" + (int) progValue);
                        receivelength+=receivelength;
                        progressBar1.setValue((int) progValue);
                        progressBar1.update(progressBar1.getGraphics());

                    }
                    count++;
                    temp=ClientFTP.datadin.readUTF();
                    ch=Integer.parseInt(temp);
                    if(ch!=-1)
                    {
                        fout.write(ch);
                    }
                }while(ch!=-1);
                fout.close();
                System.out.println(ClientFTP.din.readUTF());
                progressBar1.setValue(100);

            }
        }
        else if(Comand.compareTo("list")==0){
            ArrayList<String> fileList= new ArrayList<String>();
            try {
                fileList= obj_client.getList();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] inputlist = new String[fileList.size()];
            inputlist = fileList.toArray(inputlist);

            JOptionPane.showMessageDialog(this, inputlist, "List of Files/Folders", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (Comand.compareTo("cd")==0){
            String status="false";
            String selection = (String) JOptionPane.showInputDialog(this, "Enter a Path:", "Input", JOptionPane.QUESTION_MESSAGE,
                    null, null,null);
            System.out.println(selection);
            if (selection.isEmpty()){
                JOptionPane.showMessageDialog(this,"You didn't enter any path" , "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{

                try {
                    status= obj_client.setCD(selection);
                    myInitComponents();
                    if (status.compareTo("false")==0){
                        JOptionPane.showMessageDialog(this,"Directory not Exist" , "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if (Comand.compareTo("delete")==0){
            ArrayList<String> fileList= new ArrayList<String>();
            try {
                fileList=obj_client.getFiles();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //String fileList[] = new String[30];
            String[] inputlist = new String[fileList.size()];
            inputlist = fileList.toArray(inputlist);

            String selection = (String) JOptionPane.showInputDialog(this, "Choose a File to delete", "Input", JOptionPane.QUESTION_MESSAGE,
                    null, inputlist, "Titan");
            System.out.println(selection);
            try {
                obj_client.deleteFile(selection);
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (Comand.compareTo("mkdir")==0){
            String status="false";
            String selection = (String) JOptionPane.showInputDialog(this, "Enter a Directory name:", "Input", JOptionPane.QUESTION_MESSAGE,
                    null, null,null);
            System.out.println(selection);
            if (selection.isEmpty()){
                JOptionPane.showMessageDialog(this,"You didn't enter any input" , "Error", JOptionPane.ERROR_MESSAGE);
            }
            else{

                try {
                    status=obj_client.setNewDir(selection);
                    if (status.compareTo("false")==0){
                        JOptionPane.showMessageDialog(this,"Directory not created" , "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else if (Comand.compareTo("rmdir")==0){
            String status="false";
            ArrayList<String> fileList= new ArrayList<String>();
            try {
                fileList=obj_client.getDir();
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //String fileList[] = new String[30];
            String[] inputlist = new String[fileList.size()];
            inputlist = fileList.toArray(inputlist);

            String selection = (String) JOptionPane.showInputDialog(this, "Choose a Folder to delete", "Input", JOptionPane.QUESTION_MESSAGE,
                    null, inputlist, "Titan");
            System.out.println(selection);
            try {
                status=obj_client.deleteFolder(selection);
                if (status.compareTo("false")==0){
                    JOptionPane.showMessageDialog(this,"Directory contains Files!!!!" , "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"Invalid Command Click Help Button" , "Error", JOptionPane.ERROR_MESSAGE);
        }
        textField4.setText("");
        button1.setEnabled(true);
        progressBar1.setValue(0);

    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MainFrame(args).setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
