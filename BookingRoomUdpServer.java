package socket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.net.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

 
public class BookingRoomUdpServer{
    public static JFrame frame, frame2;
    public static JTextArea showFrame, showFrame2;
 
    public static void main(String[] args) throws IOException{
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JDesktopPane desktopPane = new JDesktopPane();
        frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
        
        frame2 = new JFrame();
        frame2.setBounds(100, 100, 450, 300);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JDesktopPane desktopPane2 = new JDesktopPane();
        frame2.getContentPane().add(desktopPane2, BorderLayout.CENTER);

        JLabel lblBookingRoomTcpServer = new JLabel("Room Rent Booking System Administrator");
        lblBookingRoomTcpServer.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblBookingRoomTcpServer.setBounds(10, 10, 350, 15);
        desktopPane.add(lblBookingRoomTcpServer);

        JLabel lblline = new JLabel("=============================================");
        lblline.setBounds(10, 25, 350, 15);
        desktopPane.add(lblline);
        
        JButton btnNext = new JButton("Show Book Record");
        btnNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                showFrame2.setText(null);
                showFrame2.append("Customer's Book Record\n========================================\n");
                
                try{
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

                    String select = "Select * from booking where isactive = 1 AND type = 'Udp' order by room asc";
                    ResultSet result = stmt.executeQuery(select);
                    while(result.next()){
                        showFrame2.append(result.getString(4)+" - "+result.getString(3)+" - "+result.getString(2)+" - ID:"+result.getString(1)+"\n");
                    }
		}catch(Exception err){
                    System.out.println("Error: " +err);
		}
            }
        });
        btnNext.setBounds(160, 70, 150, 20);
        desktopPane.add(btnNext);
        
        showFrame = new JTextArea();
        showFrame.setBackground(Color.white);
        showFrame.setBounds(10, 70, 400, 200);
        desktopPane.add(showFrame);
        showFrame.append("Incoming Booking\n========================================\n");
        
        showFrame2 = new JTextArea();
        showFrame2.setBackground(Color.white);
        showFrame2.setBounds(10, 290, 400, 400);
        desktopPane.add(showFrame2);
        
        BookingRoomUdpServer window = new BookingRoomUdpServer();
        window.frame.setVisible(true);
        
        //UPD code
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        int i = 0;
        while (true) {
            i++;
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData());
            showFrame.append("("+i+") "+sentence+"\n");
            
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            String capitalizedSentence = "Booking Accepted!";
            sendData = capitalizedSentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            serverSocket.send(sendPacket);
            
            
        }
        
    }
}
