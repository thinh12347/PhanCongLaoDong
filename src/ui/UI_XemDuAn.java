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
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.ChiTietDuAn_DAO;
import database.KetNoiCSDL;
import entity.ChiTietDuAn;
import entity.NhanVien;



public class UI_XemDuAn extends JFrame implements ActionListener, Runnable {
	private ChiTietDuAn_DAO da_dao;
	private JTextField txtma;
	private NhanVien nhanvien;
	private DefaultTableModel model;
	private JTable bang;
	private JButton btnql;
	private JButton btntim;

	public UI_XemDuAn(NhanVien nv) {
		try {
			KetNoiCSDL.getinstance().Connection();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		da_dao=new ChiTietDuAn_DAO();
		nhanvien =new NhanVien(nv);
		setTitle("Du An Da Tham gia ");
		setSize(800,450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel pTieuDe= new JPanel();
		JLabel lblTieuDe;
		pTieuDe.add(lblTieuDe = new JLabel("DU AN DUOC PHAN CONG"));
		Font font = new Font (Font.SERIF, Font.BOLD , 30);
		lblTieuDe.setForeground(Color.green);
		lblTieuDe.setFont(font);
	
		JPanel p1=new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
		
		p1.add(btntim=new JButton("Tim du an"));
		try {
			
			BufferedImage imgs= ImageIO.read(new File("image/search.png"));
			ImageIcon ics= new ImageIcon(imgs.getScaledInstance(12, 12, Image.SCALE_SMOOTH));
			btntim.setIcon(ics);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		p1.add(txtma=new JTextField(20));
		txtma.setEnabled(false);
		
		JPanel p9=new JPanel();
		
		p9.setLayout(new BoxLayout(p9,BoxLayout.X_AXIS));
		String[] cols= {"Ma chi tiet du an","Ma du an","Dia chi","Ngay cap phep","Ngay khoi cong","Ngay hoan thanh","Ma nhan vien","Ngay cong"};
		model=new DefaultTableModel(cols,0);
		bang=new JTable(model);
		bang.setRowHeight(15);
		JScrollPane js;
		js=new JScrollPane(bang);
		js.setPreferredSize(new Dimension(780,300));
		
		p9.add(js); 
		
		JPanel p2=new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
		
		p2.add(btnql=new JButton("Quay lai"));
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
		b.add(p1);
		b.add(p9);
		b.add(p2);
		
		JPanel p=new JPanel();
		p.add(b);
		add(p);
		txtma.addActionListener(this);
		btnql.addActionListener(this);
		btntim.addActionListener(this);
		setData();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NhanVien nv=new NhanVien();
		new UI_XemDuAn(nv).setVisible(true);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		NhanVien nv=new NhanVien();
		new UI_XemDuAn(nv).setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o.equals(btntim)) {
			List<ChiTietDuAn> list=da_dao.TimDA(txtma.getText());
			 if(list.size()==0) {
				JOptionPane.showMessageDialog(this, "Khong tim thay");
			}else {
				for(ChiTietDuAn da:list) {
					String row[]= {da.getMactdu(),da.getMada().getMada(),da.getDiachi(),da.getNCP(),da.getNKC(),da.getNHT(),da.getManv().getManv(),da.getNgaycong()+""};
					model.addRow(row);
				}
				bang.setModel(model);
			}
		}else if(o.equals(btnql)) {
			UI_User u=new UI_User(nhanvien);
			this.setVisible(false);
            u.setVisible(true);
		}
	}
	private void setData(){
		txtma.setText(nhanvien.getManv());
    }

}
