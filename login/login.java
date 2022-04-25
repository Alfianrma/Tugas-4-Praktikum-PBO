package login;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;



public class login extends JFrame{
    JLabel ljudul = new JLabel("LOGIN");
    JLabel lloguser = new JLabel("Username");
    JLabel llogpass = new JLabel("Password");
    JLabel lbuatuser = new JLabel("Username");
    JLabel lbuatpass = new JLabel("Password");

    JTextField tfloguser = new JTextField();
    JTextField tflogpass = new JTextField();
    JTextField tfbuatuser = new JTextField();
    JTextField tfbuatpass= new JTextField();

    JButton btnlogin = new JButton("Login");
    JButton btndaftar = new JButton("Daftar");

    koneksi Koneksi = new koneksi();
    String query;


    public login(){
        setSize(350,200);
        setLayout(null);

        add(ljudul);
        add(llogpass);
        add(lloguser);
        add(lbuatuser);
        add(lbuatpass);

        add(tfloguser);
        add(tflogpass);
        add(tfbuatuser);
        add(tfbuatpass);

        add(btnlogin);
        add(btndaftar);

        ljudul.setBounds(150,10,50,25);

        lloguser.setBounds(20,30,70,20);
        tfloguser.setBounds(20,50,100,20);
        llogpass.setBounds(20,70,70,20);
        tflogpass.setBounds(20,90,100,20);
        btnlogin.setBounds(35,120,70,20);

        lbuatuser.setBounds(200,30,70,20);
        tfbuatuser.setBounds(200,50,100,20);
        lbuatpass.setBounds(200,70,70,20);
        tfbuatpass.setBounds(200,90,100,20);
        btndaftar.setBounds(215,120,70,20);


        btndaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    query = "insert into users values ('" + getBuatuser() + "','" + getBuatpass() +"')";
                    Koneksi.statement = Koneksi.connect.createStatement();
                    Koneksi.statement.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Berhasil Mendaftarkan User");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Gagal Mendaftarkan User");
                }
            }
        });


        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    query = "select * from users where username = '"+getLoguser()+"'";
                    Koneksi.statement = Koneksi.connect.createStatement();
                    ResultSet resultSet = Koneksi.statement.executeQuery(query);
                    if(resultSet.next()){
                        if(getLoguser().equals(resultSet.getString("username")) && getLogpass().equals(resultSet.getString("password"))){
                            JOptionPane.showMessageDialog(null, "Berhasil Login");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Password Salah");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Anda Harus Terdaftar Untuk Login");
                    }
                } catch (Exception ex) {
                    if(ex.getMessage() == "empty String") {
                        JOptionPane.showMessageDialog(null, "Data tidak boleh kosong!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });

        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getLoguser(){
        return tfloguser.getText();
    }

    public String getLogpass(){
        return tflogpass.getText();
    }

    public String getBuatuser(){
        return tfbuatuser.getText();
    }

    public String getBuatpass(){
        return tfbuatpass.getText();
    }
}

