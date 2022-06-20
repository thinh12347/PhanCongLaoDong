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
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import dao.NhanVien_DAO;
import database.KetNoiCSDL;
import entity.NhanVien;
import entity.PhongBan;

public class UI_XemTT extends JFrame implements ActionListener, Runnable {
	private JTextField txtma;
	private JTextField txtten;
	private JDateChooser ns;
;
	private JCheckBox check;
	private JButton btnsua;
	private JButton btnql;
	private NhanVien_DAO nv_dao;
	private NhanVien nhanvien;
	private JPasswordField txtpass;
	private JTextField txtdc;
	private JTextField txtpb;
	private JTextField txtcv;
	
	

	public UI_XemTT(NhanVien nv)  {
		try {
			KetNoiCSDL.getinstance().Connection();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		nhanvien=new NhanVien(nv);
		nv_dao=new NhanVien_DAO();
		setTitle("Nhan Vien");
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel pTieuDe = new JPanel();
		JLabel lblTieuDe;
		pTieuDe.add(lblTieuDe = new JLabel("CAP NHAT THONG TIN"));
		Font font = new Font (Font.SANS_SERIF, Font.BOLD , 40);
		lblTieuDe.setForeground(Color.blue);
		lblTieuDe.setFont(font);
		
		
		JLabel lblUser = new JLabel();
		pTieuDe.add(lblUser=new JLabel());
		try {
			BufferedImage imguser= ImageIO.read(new File("image/user.gif"));
			ImageIcon icuser= new ImageIcon(imguser.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
			lblUser.setIcon(icuser);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		
		
		JPanel p1=new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
		p1.add(new JLabel("Ma nhan vien:     "));
		p1.add(txtma=new JTextField(20));
		txtma.setEnabled(false);
		
		JPanel p2=new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
		p2.add(new JLabel("Ten nhan vien:    "));
		p2.add(txtten=new JTextField(20));
		
		JPanel p3=new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
		p3.add(new JLabel("Ngay sinh:            "));
		p3.add(ns=new JDateChooser());
		
		p3.add(new JLabel("   Phai:  "));
		p3.add(check=new JCheckBox());
		
		JPanel p4=new JPanel();
	
		
		JPanel p5=new JPanel();
		p5.setLayout(new BoxLayout(p5,BoxLayout.X_AXIS));
		p5.add(new JLabel("Dia chi:                "));
		p5.add(txtdc=new JTextField(20));
		
		JPanel p6=new JPanel();
		p6.setLayout(new BoxLayout(p6,BoxLayout.X_AXIS));
		
		p6.add(new JLabel("Phong ban:         "));
		p6.add(txtpb=new JTextField(20));
		txtpb.setEnabled(false);
		
		p6.add(new JLabel("   Chuc vu:      "));
		p6.add(txtcv=new JTextField(20));
		txtcv.setEnabled(false);
		
		JPanel p7=new JPanel();
		
		
		JPanel p8=new JPanel();
		p8.setLayout(new BoxLayout(p8,BoxLayout.X_AXIS));
		p8.add(new JLabel("Pass:                   "));
		p8.add(txtpass=new JPasswordField(20));
		
		JPanel p9=new JPanel();
		
		p9.add(btnsua=new JButton("Cap nhat"));
		try {
			BufferedImage imgcn= ImageIO.read(new File("image/update.png"));
			ImageIcon iccn= new ImageIcon(imgcn.getScaledInstance(12, 12, Image.SCALE_SMOOTH));
			btnsua.setIcon(iccn);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		p9.add(Box.createRigidArea(new Dimension(20,0)));
		
		p9.add(btnql=new JButton("Quay lai"));
		try {
			
			BufferedImage imgql= ImageIO.read(new File("image/back.png"));
			ImageIcon icql= new ImageIcon(imgql.getScaledInstance(12, 12, Image.SCALE_SMOOTH));
			btnql.setIcon(icql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Box b=Box.createVerticalBox();
		b.add(pTieuDe);
		b.add(Box.createVerticalStrut(5));
		b.add(p1);
		b.add(Box.createVerticalStrut(5));
		b.add(p2);
		b.add(Box.createVerticalStrut(5));
		b.add(p3);
		b.add(Box.createVerticalStrut(5));
		b.add(p4);
		b.add(Box.createVerticalStrut(5));
		b.add(p5);
		b.add(Box.createVerticalStrut(5));
		b.add(p6);
		b.add(Box.createVerticalStrut(5));
		b.add(p7);
		b.add(Box.createVerticalStrut(5));
		b.add(p8);
		b.add(Box.createVerticalStrut(5));
		b.add(p9);
		b.add(Box.createVerticalStrut(5));
		
		JPanel p=new JPanel();
		p.add(b);
		add(p);
		setData();
		btnql.addActionListener(this);
		btnsua.addActionListener(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NhanVien nv=new NhanVien();
		new UI_XemTT(nv).setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		NhanVien nv=new NhanVien();
		new UI_XemTT(nv).setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o.equals(btnql)) {
			UI_User ad=new UI_User(nhanvien);
			this.setVisible(false);
	        ad.setVisible(true);
		}else if(o.equals(btnsua)) {
			if(ktdulieu()) {
				try {
						NhanVien nv=new NhanVien();
						nv.setManv(txtma.getText());
						nv.setTennv(txtten.getText());
						nv.setDiachi(txtdc.getText());
						SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
						nv.setNgaysinh(s.format(ns.getDate()));
						nv.setPhai(check.isSelected());
						nv.setPb(new PhongBan(txtpb.getText()));
						nv.setChucvu(txtcv.getText());
						nv.setPass(txtpass.getText());
						nv_dao.UpdateNhanvien(nv);
						JOptionPane.showMessageDialog(this, "Cap nhat thành cong");
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(this, "That bai");
					ex.printStackTrace();
				}			
			}
		}
	}
	private void setData(){
		txtma.setText(nhanvien.getManv());
        txtten.setText(nhanvien.getTennv());
        ns.setDate(Date.valueOf(nhanvien.getNgaysinh()));
        check.setSelected(nhanvien.isPhai());
        txtdc.setText(nhanvien.getDiachi());
        txtcv.setText(nhanvien.getChucvu());
        txtpb.setText(nhanvien.getPb().getMapb());
        txtpass.setText(nhanvien.getPass());
    }
	public boolean ktdulieu() {
		String ten=txtten.getText().trim();
		String dc=txtdc.getText().trim();
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		String ngaysinh=s.format(ns.getDate());
		String pass=txtpass.getText().trim();
			if(txtten.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Chưa nhập tên nhân viên");
				txtten.selectAll();
				txtten.requestFocus();
				return false;
		}
		
		if(txtdc.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa dia chi nhân viên");
			txtdc.selectAll();
			txtdc.requestFocus();
			return false;
		}
		
		if(!(ten.length()>0 && ten.matches("[a-zA-Z ]+"))) {
			JOptionPane.showMessageDialog(this,"Ten khong nhap so va ki tu dac biet");
			txtten.selectAll();
			txtten.requestFocus();
			return false;
		}
		if(!(dc.length()>0 && dc.matches("[a-zA-Z0-9 ]+"))) {
			JOptionPane.showMessageDialog(this,"Dia chi khong nhap ki tu dac biet");
			txtdc.selectAll();
			txtdc.requestFocus();
			return false;
		}
		
		if(ngaysinh.length()>0) {
			try {
					LocalDate x=LocalDate.parse(ngaysinh);
					LocalDate y=LocalDate.of(2001, 1, 1);
					LocalDate z=LocalDate.of(1956, 1, 1);
					if(!(x.isBefore(y)&&z.isBefore(x))) {
						JOptionPane.showMessageDialog(this, "Ngay sinh lon 20-56 tuoi");
						return false;
					}
			}catch(NumberFormatException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		if(!(pass.length()>0 && pass.matches("[a-zA-Z0-9 ]{8,15}"))) {
			JOptionPane.showMessageDialog(this,"Mat khau gom 8-15 ki tu so hoac chu");
			txtpass.selectAll();
			txtpass.requestFocus();
			return false;
		}
		return true;
		}

}
