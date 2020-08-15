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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.sql.*;

public class BookingRoomTcpClient {

    String FinalBookInfo;
    private JFrame frame1, frame2, frame3, frame4, frame5, frame6;
    private JLabel BookInfoLabel1, BookEditLabel1;
    private JTextField BookInfoField1, BookInfoField2, BookInfoField3, BookEditField0, BookEditField1, BookEditField2, BookEditField3;
    public static JTextArea showFrame, showFrame5;
    private JButton btnNext, btnExit, btnEdit, btnSave;
    private static Socket socket;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BookingRoomTcpClient window = new BookingRoomTcpClient();
                    window.frame1.setVisible(true);
                    window.frame2.setVisible(false);
                    window.frame3.setVisible(false);
                    window.frame4.setVisible(false);
                    window.frame5.setVisible(false);
                    window.frame6.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public BookingRoomTcpClient() {
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
                        String host = "localhost";
                        int port = 25000;
                        InetAddress address = InetAddress.getByName(host);
                        socket = new Socket(address, port);

                        //Send the message to the server
                        OutputStream os = socket.getOutputStream();
                        OutputStreamWriter osw = new OutputStreamWriter(os);      
                        BufferedWriter bw = new BufferedWriter(osw);

                        String number = BookInfoField1.getText() + " | " + BookInfoField2.getText() + " | " + BookInfoField3.getText();

                        String sendMessage = number + "\n";
                        bw.write(sendMessage);
                        bw.flush();

                        //Get the return message from the server
                        InputStream is = socket.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        String message = br.readLine();
                        socket.close();
                        
                        //Proceed To next Frame
                        showFrame.append("Book Detail\n===================================\n");
                        showFrame.append("Room : "+ BookInfoField1.getText() + "\nName : " + BookInfoField2.getText() + "\nPhone : " + BookInfoField3.getText()+"\n\nYour booking has been "+message);
                        
                        showFrame5.append("Book Detail\n===================================\n");
                        showFrame5.append("Room : "+ BookInfoField1.getText() + "\nName : " + BookInfoField2.getText() + "\nPhone : " + BookInfoField3.getText()+"\n\nYour booking has been "+message);
                        
                        switch(message){
                            case "Accepted": 
                                
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

                                String select = "SELECT * FROM booking WHERE id = (SELECT max(id) FROM booking)";
                                ResultSet result = stmt.executeQuery(select);
                                while(result.next()){
                                    BookEditField0.setText(result.getString(1)); 
                                    BookEditField1.setText(result.getString(2)); 
                                    BookEditField2.setText(result.getString(3)); 
                                    BookEditField3.setText(result.getString(4)); 
                                }
                                
                                frame3.setVisible(false);
                                frame5.setVisible(true); 
                            break;
                            case "Rejected": 
                                frame3.setVisible(false);
                                frame4.setVisible(true);
                            break;
                        }

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
        
        /* Interface 5 */
        frame5 = new JFrame();
        frame5.setBounds(100, 100, 500, 400);
        frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDesktopPane desktopPane5 = new JDesktopPane();
        frame5.getContentPane().add(desktopPane5, BorderLayout.CENTER);

        JLabel UserInfo4 = new JLabel("Booking Result");
        UserInfo4.setFont(new Font("Tahoma", Font.BOLD, 14));
        UserInfo4.setBounds(150, 0, 250, 100);
        desktopPane5.add(UserInfo4);
        
        showFrame5 = new JTextArea();
        showFrame5.setBounds(150, 70, 300, 130);
        desktopPane5.add(showFrame5);        

        btnEdit = new JButton("Modify");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame6.setVisible(true);
                frame5.setVisible(false); 
            }
        });
        btnEdit.setBounds(100, 210, 89, 23);
        desktopPane5.add(btnEdit);
        
        btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
        btnExit.setBounds(150, 250, 89, 23);
        desktopPane5.add(btnExit);
        
        btnExit = new JButton("Cancel Book");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
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
                    
                    String select = "SELECT * FROM booking WHERE id = (SELECT max(id) FROM booking)";
                    ResultSet result = stmt.executeQuery(select);
                    while(result.next()){
                        String sql = "UPDATE booking SET isactive = ? WHERE id = ?";
                        PreparedStatement pstmt = dbcon.prepareStatement(sql);
                        pstmt.setInt (1,0);
                        pstmt.setString(2,result.getString(1));
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(frame6, "Book Cancelled");
                    }
                    System.exit(0);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        btnExit.setBounds(195, 210, 120, 23);
        desktopPane5.add(btnExit);
        /* Interface 5 */
        /* Interface 6 */
        frame6 = new JFrame();
        frame6.setBounds(100, 100, 500, 300);
        frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JDesktopPane desktopPane6 = new JDesktopPane();
        frame6.getContentPane().add(desktopPane6, BorderLayout.CENTER);

        JLabel UserInfo5 = new JLabel("Modify Personal Info");
        UserInfo5.setFont(new Font("Tahoma", Font.BOLD, 14));
        UserInfo5.setBounds(150, 0, 250, 100);
        desktopPane6.add(UserInfo5);
        
        BookEditLabel1 = new JLabel("Book ID :");
        BookEditLabel1.setBounds(100, 63, 100, 30);
        desktopPane6.add(BookEditLabel1);
        
        BookEditLabel1 = new JLabel("Your Name :");
        BookEditLabel1.setBounds(100, 90, 100, 30);
        desktopPane6.add(BookEditLabel1);

        BookEditLabel1 = new JLabel("Phone Number :");
        BookEditLabel1.setBounds(100, 125, 100, 30);
        desktopPane6.add(BookEditLabel1);
        
        BookInfoLabel1 = new JLabel("Room :");
        BookInfoLabel1.setBounds(100, 160, 100, 30);
        desktopPane6.add(BookInfoLabel1);
        
        BookEditField0 = new JTextField();
        BookEditField0.setBounds(200, 63, 100, 24);
        BookEditField0.setEditable(false);
        desktopPane6.add(BookEditField0);

        BookEditField1 = new JTextField();
        BookEditField1.setBounds(200, 90, 100, 30);
        desktopPane6.add(BookEditField1);

        BookEditField2 = new JTextField();
        BookEditField2.setBounds(200, 125, 100, 30);
        desktopPane6.add(BookEditField2);
        
        BookEditField3 = new JTextField();
        BookEditField3.setBounds(200, 160, 100, 30);
        BookEditField3.setEditable(false);
        desktopPane6.add(BookEditField3);

        btnEdit = new JButton("Back");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame6.setVisible(false);
                frame5.setVisible(true); 
            }
        });
        btnEdit.setBounds(100, 210, 89, 23);
        desktopPane6.add(btnEdit);
        
        btnSave = new JButton("Save Changes");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try
		{
                    Class.forName("com.mysql.jdbc.Driver");
                    String dburl = "jdbc:mysql://" +      
                    "localhost:" +             //host name
                    "3306/" +                  //port
                    "ZanaJava?" +              //db name
                    "useSSL=false&" +          //do not use ssl
                    "user=root&" +             //login
                    "password=";               //password
                    Connection dbcon = DriverManager.getConnection(dburl);
                    
                    String sql = "UPDATE booking SET name = ?, phone = ? WHERE id = ?";
                    PreparedStatement pstmt = dbcon.prepareStatement(sql);
                    pstmt.setString (1,BookEditField1.getText());
                    pstmt.setString(2,BookEditField2.getText());
                    pstmt.setString(3,BookEditField0.getText());
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(frame6, "Record saved");
			
		}catch(Exception err){
                    System.out.println("Error: " +err);
		}
            }
        });
        btnSave.setBounds(195, 210, 120, 23);
        desktopPane6.add(btnSave);
        /* Interface 6 */
    }
}
