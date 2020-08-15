package socket;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.sql.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.regex.Pattern;

public class BookingRoomUdpClient {

    String FinalBookInfo;
    private JFrame frame1, frame2, frame3, frame4;
    private JLabel BookInfoLabel1;
    private JTextField BookInfoField1, BookInfoField2, BookInfoField3;
    public static JTextArea showFrame;
    private JButton btnNext, btnExit;
    private static Socket socket;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookingRoomUdpClient window = new BookingRoomUdpClient();
                    window.frame1.setVisible(true);
                    window.frame2.setVisible(false);
                    window.frame3.setVisible(false);
                    window.frame4.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BookingRoomUdpClient() {
        UserInterface();
    }

    private void UserInterface() {

        /* Interface 1 */
        frame1 = new JFrame();
        frame1.setBounds(100, 100, 450, 300);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDesktopPane desktopPane1 = new JDesktopPane();
        frame1.getContentPane().add(desktopPane1, BorderLayout.CENTER);

        JLabel WelcomeTitle = new JLabel("WELCOME TO ROOM RENT BOOKING SYSTEM");
        WelcomeTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
        WelcomeTitle.setBounds(50, 60, 400, 100);
        desktopPane1.add(WelcomeTitle);

        btnNext = new JButton("Book Now");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame1.setVisible(false);
                frame2.setVisible(true);
            }
        });
        btnNext.setBounds(150, 200, 150, 23);
        desktopPane1.add(btnNext);
        /* Interface 1 */

        /* Interface 2 */
        frame2 = new JFrame();
        frame2.setBounds(100, 100, 500, 300);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDesktopPane desktopPane2 = new JDesktopPane();
        frame2.getContentPane().add(desktopPane2, BorderLayout.CENTER);

        JLabel UserInfo1 = new JLabel("Please Choose Room To Book");
        UserInfo1.setFont(new Font("Tahoma", Font.BOLD, 14));
        UserInfo1.setBounds(150, 0, 250, 100);
        desktopPane2.add(UserInfo1);

        btnNext = new JButton("Room 1");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame2.setVisible(false);
                frame3.setVisible(true);
                BookInfoField1.setText("Room 1");
            }
        });
        btnNext.setBounds(95, 100, 100, 40);
        desktopPane2.add(btnNext);

        btnNext = new JButton("Room 2");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame2.setVisible(false);
                frame3.setVisible(true);
                BookInfoField1.setText("Room 2");
            }
        });
        btnNext.setBounds(95, 150, 100, 40);
        desktopPane2.add(btnNext);

        btnNext = new JButton("Room 3");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame2.setVisible(false);
                frame3.setVisible(true);
                BookInfoField1.setText("Room 3");
            }
        });
        btnNext.setBounds(205, 100, 100, 40);
        desktopPane2.add(btnNext);

        btnNext = new JButton("Room 4");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame2.setVisible(false);
                frame3.setVisible(true);
                BookInfoField1.setText("Room 4");
            }
        });
        btnNext.setBounds(205, 150, 100, 40);
        desktopPane2.add(btnNext);

        btnNext = new JButton("Room 5");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame2.setVisible(false);
                frame3.setVisible(true);
                BookInfoField1.setText("Room 5");
            }
        });
        btnNext.setBounds(315, 100, 100, 40);
        desktopPane2.add(btnNext);

        btnNext = new JButton("Room 6");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame2.setVisible(false);
                frame3.setVisible(true);
                BookInfoField1.setText("Room 6");
            }
        });
        btnNext.setBounds(315, 150, 100, 40);
        desktopPane2.add(btnNext);
        /* Interface 2 */

        /* Interface 3 */
        frame3 = new JFrame();
        frame3.setBounds(100, 100, 550, 400);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDesktopPane desktopPane3 = new JDesktopPane();
        frame3.getContentPane().add(desktopPane3, BorderLayout.CENTER);

        JLabel UserInfo2 = new JLabel("BOOKING INFORMATION");
        UserInfo2.setFont(new Font("Tahoma", Font.BOLD, 14));
        UserInfo2.setBounds(150, 0, 250, 100);
        desktopPane3.add(UserInfo2);

        BookInfoLabel1 = new JLabel("Room Choosen :");
        BookInfoLabel1.setBounds(100, 90, 100, 30);
        desktopPane3.add(BookInfoLabel1);

        BookInfoLabel1 = new JLabel("Your Name :");
        BookInfoLabel1.setBounds(100, 125, 100, 30);
        desktopPane3.add(BookInfoLabel1);

        BookInfoLabel1 = new JLabel("Phone Number :");
        BookInfoLabel1.setBounds(100, 160, 100, 30);
        desktopPane3.add(BookInfoLabel1);
        
        BookInfoLabel1 = new JLabel("Payment Receipt :");
        BookInfoLabel1.setBounds(90, 200, 175, 30);
        desktopPane3.add(BookInfoLabel1);

        BookInfoField1 = new JTextField();
        BookInfoField1.setBounds(200, 90, 100, 30);
        desktopPane3.add(BookInfoField1);

        BookInfoField2 = new JTextField();
        BookInfoField2.setBounds(200, 125, 100, 30);
        desktopPane3.add(BookInfoField2);

        BookInfoField3 = new JTextField();
        BookInfoField3.setBounds(200, 160, 100, 30);
        desktopPane3.add(BookInfoField3);

        btnNext = new JButton("Upload");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Choose a receipt file: ");
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int returnValue = jfc.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    if (jfc.getSelectedFile().isFile()) {
                        System.out.println("You selected the directory: " + jfc.getSelectedFile());
                    }
                }
            }
        });
        btnNext.setBounds(200, 200, 100, 30);
        desktopPane3.add(btnNext);

        btnNext = new JButton("Submit Book Info");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                /*final process */
                if (BookInfoField2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your name", "Error !!!", JOptionPane.INFORMATION_MESSAGE);
                } else if (BookInfoField3.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter your phone number", "Error !!!", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        DatagramSocket clientSocket = new DatagramSocket();
                        InetAddress IPAddress = InetAddress.getByName("localhost");  

                        //insert into table
                        String room = BookInfoField1.getText(); 
                        String name = BookInfoField2.getText();  
                        String phone = BookInfoField3.getText();
                        Class.forName("com.mysql.jdbc.Driver");
                        String dburl = "jdbc:mysql://" +      
                        "localhost:" +             //host name
                        "3306/" +                  //port
                        "ZanaJava?" +              //db name
                        "useSSL=false&" +          //do not use ssl
                        "user=root&" +             //login
                        "password=";               //password

                        Connection dbcon = DriverManager.getConnection(dburl);
                        Statement stmt = dbcon.createStatement();

                        String insert1 = "INSERT INTO `booking` (`id`, `name`, `phone`, `room`, `type`) VALUES (NULL, '"+name+"', '"+phone+"', '"+room+"', 'Udp')";
                        stmt.executeUpdate(insert1);

                        //send data
                        byte[] sendData = new byte[1024];
                        String userbook = BookInfoField1.getText() + " | " + BookInfoField2.getText() + " | " + BookInfoField3.getText();
                        sendData = userbook.getBytes();
                        
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                        clientSocket.send(sendPacket);
                        
                        //receive data
                        byte[] receiveData = new byte[1024];
                        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                        clientSocket.receive(receivePacket);

                        String modifiedSentence = new String(receivePacket.getData());
                        clientSocket.close();
                        
                        //Proceed To next Frame
                        showFrame.append("Book Detail\n===================================\n");
                        showFrame.append("Name : "+ BookInfoField1.getText() + "\nPhone : " + BookInfoField2.getText() + "\nRoom : " + BookInfoField3.getText()+"\n\n"+modifiedSentence);
                        
                        frame3.setVisible(false);
                        frame4.setVisible(true);
                        
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
                /*final process */
            }
        });
        btnNext.setBounds(150, 240, 200, 30);
        desktopPane3.add(btnNext);
        /* Interface 3 */

        /* Interface 4 */
        frame4 = new JFrame();
        frame4.setBounds(100, 100, 500, 300);
        frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDesktopPane desktopPane4 = new JDesktopPane();
        frame4.getContentPane().add(desktopPane4, BorderLayout.CENTER);

        JLabel UserInfo3 = new JLabel("Booking Result");
        UserInfo3.setFont(new Font("Tahoma", Font.BOLD, 14));
        UserInfo3.setBounds(150, 0, 250, 100);
        desktopPane4.add(UserInfo3);
        
        showFrame = new JTextArea();
        showFrame.setBounds(150, 70, 300, 130);
        desktopPane4.add(showFrame);        

        btnExit = new JButton("Finish");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        btnExit.setBounds(185, 210, 89, 23);
        desktopPane4.add(btnExit);
        /* Interface 4 */
    }
}
