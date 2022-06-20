package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import entity.NhanVien;
import entity.PhongBan;



public class UI_Login extends JFrame implements ActionListener , Runnable{

	private static final long serialVersionUID = 1L;
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	private JLabel lblTieuDe,lblID,lblPass;
	private JTextField txtID;
	private JPasswordField txtPass;
	private JButton btnLogin;
	private JButton btnExit;
	
	public UI_Login() {
		setTitle("Quan ly lao dong");
		setSize(350,350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		
		JPanel pTheme=new JPanel();
		JLabel lblTheme = new JLabel();
		pTheme.add(lblTheme=new JLabel());
		try {
			BufferedImage imgtheme= ImageIO.read(new File("image/theme.gif"));
			ImageIcon icuser= new ImageIcon(imgtheme.getScaledInstance(200, 160, Image.SCALE_SMOOTH));
			lblTheme.setIcon(icuser);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		JLabel lblTieuDe;
		JPanel pTieuDe = new JPanel();
		pTieuDe.add(lblTieuDe = new JLabel("LOGIN"));
		Font font = new Font (Font.SANS_SERIF, Font.BOLD , 20);
		lblTieuDe.setForeground(Color.red);
		lblTieuDe.setFont(font);
		
		JPanel p1=new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
		p1.add(new JLabel("User:      "));
		p1.add(txtID=new JTextField(20));
		
		JPanel p2=new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
		p2.add(new JLabel("Pass:      "));
		p2.add(txtPass=new JPasswordField(20));
		
		
		JPanel p3=new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
		p3.add(btnLogin=new JButton("Dang nhap"));
		p3.add(Box.createRigidArea(new Dimension(20,0)));
		p3.add(btnExit=new JButton("Thoat"));
		
		Box b=Box.createVerticalBox();
		b.add(pTheme);
		b.add(pTieuDe);
		
		b.add(p1);
		b.add(Box.createVerticalStrut(5));
		b.add(p2);
		b.add(Box.createVerticalStrut(20));
		b.add(p3);
		
		JPanel p=new JPanel();
		p.add(b);
		add(p);
		btnLogin.addActionListener(this);
		btnExit.addActionListener(this);
		connection();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new UI_Login().setVisible(true);
	}
	
	
	 private void connection(){
	        try {
	            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	            con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Baitaplon;username=sa;password =123");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o=e.getSource();
		if(o.equals(btnLogin)) {
			  String sql = "SELECT * FROM NhanVien WHERE Manv=? and Pass=?";
		        if(ktdl())
		        try{
		            pst=con.prepareStatement(sql);
		            pst.setString(1, this.txtID.getText());
		            pst.setString(2, String.valueOf(this.txtPass.getPassword()));
		           
		            rs=pst.executeQuery();
		            if(rs.next()){
		            	  NhanVien d = new NhanVien(rs.getString("Manv").trim(),rs.getString("Tennv").trim(),rs.getString("Ngaysinh").trim(),rs.getBoolean("Phai"),rs.getString("Diachi").trim(), new PhongBan(rs.getString("Mapb").trim()),rs.getString("Chucvu").trim(),rs.getString("Pass"));
		            	  if(rs.getString("Manv").trim().toString().equals("Admin")){ 
		            	  UI_Admin home=new UI_Admin(d);
		                    this.setVisible(false);
		                    home.setVisible(true);
		                }
		                else{
		                	UI_User home=new UI_User(d);
		                    this.setVisible(false);
		                    home.setVisible(true);
		                }
		            }
		            else{
		                JOptionPane.showMessageDialog(this,"Sai ten dang nhap hoac mat khau!");
		            }
		        }
		        catch(Exception ex){
		            ex.printStackTrace();
		        }
		}
		else if(o.equals(btnExit)) {
			System.exit(0);
		}
		
	}
	
	private boolean ktdl() {
		if(txtID.getText().equals("")) {
		    JOptionPane.showMessageDialog(this, "Chua nhap user");
		    		return false;
		}
		if(txtPass.getText().equals("")) {
		   JOptionPane.showMessageDialog(this, "Chua nhap pass");
		   		return false;
		}
		    		return true;
	}	        
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		new UI_Login().setVisible(true);
	}
	
	
}

