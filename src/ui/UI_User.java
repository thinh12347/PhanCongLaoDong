package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import entity.NhanVien;

public class UI_User extends JFrame implements ActionListener, Runnable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btndx;
	private JButton btnnv;
	private NhanVien nhanvien;
	private JButton btnda;

	public UI_User(NhanVien nv) {
		setTitle("Nhan Vien");
		setSize(500, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		nhanvien=new NhanVien(nv);
		
		JPanel pTieuDe = new JPanel();
		JLabel lblTieuDe;
		pTieuDe.add(lblTieuDe = new JLabel("THONG TIN CA NHAN"));
		Font font = new Font (Font.SERIF, Font.BOLD , 30);
		lblTieuDe.setForeground(Color.black);
		lblTieuDe.setFont(font);
		
		JPanel p1=new JPanel();
		p1.add(btnnv=new JButton("Xem Thong Tin"));
		try {
			BufferedImage imgnv= ImageIO.read(new File("image/tt.png"));
			ImageIcon icnv= new ImageIcon(imgnv.getScaledInstance(12, 12, Image.SCALE_SMOOTH));
			btnnv.setIcon(icnv);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		
		p1.add(btnda=new JButton("Du An Duoc Phan Cong"));
		try {
			BufferedImage imgda= ImageIO.read(new File("image/duan.gif"));
			ImageIcon icda= new ImageIcon(imgda.getScaledInstance(12, 12, Image.SCALE_SMOOTH));
			btnda.setIcon(icda);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		p1.add(btndx=new JButton("Dang Xuat"));
		try {
			BufferedImage imgdx= ImageIO.read(new File("image/logout.png"));
			ImageIcon icdx= new ImageIcon(imgdx.getScaledInstance(12, 12, Image.SCALE_SMOOTH));
			btndx.setIcon(icdx);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		
		JPanel p9=new JPanel();
		JLabel lblUser = new JLabel();
		p9.add(lblUser=new JLabel());
		try {
			BufferedImage imguser= ImageIO.read(new File("image/user.gif"));
			ImageIcon icuser= new ImageIcon(imguser.getScaledInstance(400, 400, Image.SCALE_SMOOTH));
			lblUser.setIcon(icuser);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		
		Box b=Box.createVerticalBox();
		b.add(pTieuDe);
		b.add(p9);
		b.add(p1);
		
		
		JPanel p=new JPanel();
		p.add(b);
		add(p);
		
		
		btndx.addActionListener(this);
		btnnv.addActionListener(this);
		btnda.addActionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NhanVien nv=new NhanVien();
		new UI_User(nv).setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		NhanVien nv=new NhanVien();
		new UI_User(nv).setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o.equals(btndx)) {
			int Click = JOptionPane.showConfirmDialog(null, "Ban co muong dang xuat khoi he thong khong?", "Thong bao",2);
	        if(Click ==JOptionPane.YES_OPTION){
	            UI_Login login = new UI_Login(); 
	            this.setVisible(false);
	            login.setVisible(true);
	        }
		}else if(o.equals(btnnv)) {
			UI_XemTT tt=new UI_XemTT(nhanvien);
			 this.setVisible(false);
	            tt.setVisible(true);
		}else if(o.equals(btnda)) {
			UI_XemDuAn da = new UI_XemDuAn(nhanvien);
			 this.setVisible(false);
	            da.setVisible(true);
		}
	}
}
