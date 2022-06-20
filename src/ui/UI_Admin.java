package ui;

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

import entity.DuAn;
import entity.NhanVien;

public class UI_Admin extends JFrame implements ActionListener, Runnable {

	private JButton btnnv;
	private JButton btnda;
	private JButton btndx;
	private JButton btntk;

	public UI_Admin(NhanVien nv) {
		setTitle("Admin");
		setSize(600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		JPanel p1 = new JPanel();
		p1.add(btnnv = new JButton("Quan ly nhan vien"));
		try {
			BufferedImage imgnv = ImageIO.read(new File("image/tt.png"));
			ImageIcon icnv = new ImageIcon(imgnv.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnnv.setIcon(icnv);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}

		p1.add(btnda = new JButton("Phan cong lao dong"));
		try {
			BufferedImage imgda = ImageIO.read(new File("image/duan.gif"));
			ImageIcon icda = new ImageIcon(imgda.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnda.setIcon(icda);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}

		p1.add(btndx = new JButton("Dang xuat"));
		try {
			BufferedImage imgdx = ImageIO.read(new File("image/logout.png"));
			ImageIcon icdx = new ImageIcon(imgdx.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btndx.setIcon(icdx);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}

		JPanel p2 = new JPanel();
		JLabel lblUser = new JLabel();
		p2.add(lblUser = new JLabel());
		try {
			BufferedImage imguser = ImageIO.read(new File("image/admin.gif"));
			ImageIcon icuser = new ImageIcon(imguser.getScaledInstance(550, 400, Image.SCALE_SMOOTH));
			lblUser.setIcon(icuser);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}

		p1.add(btntk = new JButton("Quan ly du an"));
		try {
			BufferedImage imgtk = ImageIO.read(new File("image/static.gif"));
			ImageIcon ictk = new ImageIcon(imgtk.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btntk.setIcon(ictk);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}

		Box b = Box.createVerticalBox();
		b.add(p2);
		b.add(p1);

		JPanel p = new JPanel();
		p.add(b);
		add(p);
		btnnv.addActionListener(this);
		btnda.addActionListener(this);
		btndx.addActionListener(this);
		btntk.addActionListener(this);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NhanVien nv = new NhanVien();
		new UI_Admin(nv).setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		NhanVien nv = new NhanVien();
		new UI_Admin(nv).setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnnv)) {
			NhanVien n = new NhanVien();
			UI_NhanVien nv = new UI_NhanVien(n);
			this.setVisible(false);
			nv.setVisible(true);
		} else if (o.equals(btnda)) {

			UI_ChiTietDuAn da = new UI_ChiTietDuAn();
			this.setVisible(false);
			da.setVisible(true);
		} else if (o.equals(btndx)) {
			int Click = JOptionPane.showConfirmDialog(null, "Ban co muon dang xuat khoi he thong khong?", "Thong bao",
					2);
			if (Click == JOptionPane.YES_OPTION) {
				UI_Login login = new UI_Login();
				this.setVisible(false);
				login.setVisible(true);
			}
		} else if (o.equals(btntk)) {

			DuAn n = new DuAn();
			UI_DuAn nv = new UI_DuAn(n);
			this.setVisible(false);
			nv.setVisible(true);
		}
	}
}
