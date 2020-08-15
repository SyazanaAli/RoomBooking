package socket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.sql.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.regex.Pattern;
 
public class BookingRoomTcpServer{
    private static Socket socket;
    public static JFrame frame,frame2;
    public static JTextArea showFrame, showFrame2;
    private static int dialogButton;
    
 
    public static void main(String[] args){
        
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

                    String select = "Select * from booking where isactive = 1 AND type = 'Tcp' order by room asc";
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
        
        BookingRoomTcpServer window = new BookingRoomTcpServer();
        window.frame.setVisible(true);
        window.frame2.setVisible(false);
        
        try{
            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port);

            //Server is running always. This is done using this while(true) loop
            int i = 0;
            while(true){
                i++;
                //Reading the message from the client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String booking = br.readLine();
                
                //return message
                String returnMessage;
                
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                
                int dialogResult = JOptionPane.showConfirmDialog (null, "Booking Detail : "+booking+"\nAccept this booking?","Warning",dialogButton);                
                if(dialogResult == JOptionPane.YES_OPTION){
                    returnMessage = String.valueOf("Accepted") + "\n";
                    bw.write(returnMessage);

                    //insert into table
                    String[] book = booking.split(Pattern.quote("|"));
                    String room = book[0]; 
                    String name = book[1];  
                    String phone = book[2];

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

                        String insert1 = "INSERT INTO `booking` (`id`, `name`, `phone`, `room`) VALUES (NULL, '"+name+"', '"+phone+"', '"+room+"')";
                        stmt.executeUpdate(insert1);

                    }catch(Exception err){
                        System.out.println("Error: " +err);
                    }
                }else{
                    returnMessage = String.valueOf("Rejected") + "\n";
                    bw.write(returnMessage);
                }
                //append in box
                showFrame.append("("+i+") "+booking+" | Status : "+returnMessage+"\n");
                bw.flush();
                socket.close();
               
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
      
}
