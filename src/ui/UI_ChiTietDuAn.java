package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.ChiTietDuAn_DAO;
import dao.DuAn_DAO;
import dao.NhanVien_DAO;
import database.KetNoiCSDL;
import entity.ChiTietDuAn;
import entity.DuAn;
import entity.NhanVien;


public class UI_ChiTietDuAn extends JFrame implements ActionListener, MouseListener,Runnable {
	private NhanVien_DAO nv_dao;
	private ChiTietDuAn_DAO da_dao;
	private JTextField txtma;
	
	private JTextField txtdc;
	private JDateChooser ncp;
	private JDateChooser nkc;
	private JDateChooser nht;
	private JComboBox<String> cbbnv;
	private JTextField txtnc;
	private DefaultTableModel model;
	private JTable bang;
	private JButton btnthem;
	private JButton btnxt;
	private JButton btnsua;
	private JButton btnx;
	private JComboBox<String> cbbtim;
	private JComboBox<String> cbbda;
	private JButton btnload;
	private JButton btnql;
	private JTextField txttim;
	private DuAn_DAO d_dao;

	public UI_ChiTietDuAn() {
		try {
			KetNoiCSDL.getinstance().Connection();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		d_dao=new DuAn_DAO();
		da_dao=new ChiTietDuAn_DAO();
		nv_dao=new NhanVien_DAO();
		setTitle("Du An");
		setSize(900,600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JLabel lblTieuDe;
		JPanel pTieuDe = new JPanel();
		pTieuDe.add(lblTieuDe = new JLabel("THONG TIN DU AN"));
		Font font = new Font (Font.SERIF, Font.BOLD , 30);
		lblTieuDe.setForeground(Color.GREEN);
		lblTieuDe.setFont(font);
		
		
		JPanel p1=new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
		p1.add(new JLabel("Ma chi tiet du an:            "));
		p1.add(txtma=new JTextField(10));
		
		JPanel p2=new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
		
		p2.add(new JLabel("Ma du an:     "));
		
		p2.add(cbbda=new JComboBox<String>());
		cbbda.setEditable(true);
		ArrayList<DuAn> listnv=d_dao.dsnv();
		for(DuAn p: listnv) {
			cbbda.addItem(p.getMada());
		}
		
		JPanel p3=new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
		p3.add(new JLabel("Dia chi:                "));
		p3.add(txtdc=new JTextField(10));
		
		JPanel p4=new JPanel();
		p4.setLayout(new BoxLayout(p4,BoxLayout.X_AXIS));
		
		p4.add(new JLabel("Ngay cap phep:  "));
		p4.add(ncp=new JDateChooser());
		
		p4.add(new JLabel("  Ngay khoi cong: "));
		p4.add(nkc=new JDateChooser());
		
		p4.add(new JLabel("  Ngay hoan thanh: "));
		p4.add(nht=new JDateChooser());
		
		
		
		
		JPanel p7=new JPanel();
		p7.setLayout(new BoxLayout(p7,BoxLayout.X_AXIS));
		p7.add(new JLabel("Ma nhan vien:     "));
		p7.add(cbbnv=new JComboBox<String>());
		cbbnv.setEditable(true);
		ArrayList<NhanVien> listnvv=nv_dao.dsnv();
		for(NhanVien p: listnvv) {
			cbbnv.addItem(p.getManv());
		}
	
		p7.add(new JLabel("         Ngay cong:             "));
		p7.add(txtnc=new JTextField(10));
		
		
		
		JPanel p10=new JPanel();
		
		p10.add(btnthem=new JButton("Them"));
		try {
			BufferedImage imgadd= ImageIO.read(new File("image/add.png"));
			ImageIcon icadd= new ImageIcon(imgadd.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnthem.setIcon(icadd);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		
		p10.add(btnxt=new JButton("Xoa trang"));
		
		p10.add(btnx=new JButton("Xoa"));
		try {
			BufferedImage imgdel= ImageIO.read(new File("image/del.png"));
			ImageIcon icdel= new ImageIcon(imgdel.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnx.setIcon(icdel);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		p10.add(btnsua=new JButton("Cap nhat"));
		try {
			BufferedImage imgcn= ImageIO.read(new File("image/update.png"));
			ImageIcon iccn= new ImageIcon(imgcn.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnsua.setIcon(iccn);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		
		JPanel p9=new JPanel();
		p9.setLayout(new BoxLayout(p9,BoxLayout.X_AXIS));
		
		JPanel p8=new JPanel();
		p8.setLayout(new BoxLayout(p8,BoxLayout.X_AXIS));
		p8.add(new JLabel("Tim theo ma:       "));
		p8.add(cbbtim=new JComboBox<String>());
		
		ArrayList<ChiTietDuAn> listda=da_dao.dsda();
		for(ChiTietDuAn p: listda) {
			cbbtim.addItem(p.getMactdu());;
		}
		
		p8.add(Box.createRigidArea(new Dimension(30,0)));
	
		p8.add(btnload=new JButton("Tim kiem nang cao"));
		try {
			
			BufferedImage imgs= ImageIO.read(new File("image/search.png"));
			ImageIcon ics= new ImageIcon(imgs.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnload.setIcon(ics);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}	
		p8.add(Box.createRigidArea(new Dimension(40,0)));
		p8.add(txttim=new JTextField(10));
		
	
		JPanel p13=new JPanel();
		p13.add(btnql=new JButton("Quay lai"));
		try {
			
			BufferedImage imgql= ImageIO.read(new File("image/back.png"));
			ImageIcon icql= new ImageIcon(imgql.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnql.setIcon(icql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		String[] cols= {"Ma Chi tiet du an","Ma Du An","Dia chi","Ngay cap phep","Ngay khoi cong","Ngay hoan thanh","Ma nhan vien","Ngay cong"};
		model=new DefaultTableModel(cols,0);
		bang=new JTable(model);
		bang.setRowHeight(15);
		JScrollPane js;
		js=new JScrollPane(bang);
		js.setPreferredSize(new Dimension(770,220));
		docdulieubang();
		p9.add(js);
		
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
		//b.add(p5);
		//
		b.add(p7);
		b.add(Box.createVerticalStrut(5));
		b.add(p8);
		b.add(Box.createVerticalStrut(5));
		b.add(p10);
		b.add(Box.createVerticalStrut(5));
		b.add(p9);
		b.add(Box.createVerticalStrut(5));
		b.add(p13);
		b.add(Box.createVerticalStrut(5));
		
		JPanel p=new JPanel();
		p.add(b);
		add(p);
		btnthem.addActionListener(this);
		btnx.addActionListener(this);
		btnxt.addActionListener(this);
		btnsua.addActionListener(this);
		bang.addMouseListener(this);
		cbbtim.addActionListener(this);
		btnload.addActionListener(this);
		btnql.addActionListener(this);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new UI_ChiTietDuAn().setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int row=bang.getSelectedRow();
		txtma.setText(model.getValueAt(row, 0).toString());
		txtma.setEnabled(false);
		cbbda.setSelectedItem(model.getValueAt(row, 1).toString());
		txtdc.setText(model.getValueAt(row, 2).toString());
		try {
				Date date=new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(row, 3));
				ncp.setDate(date);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(row, 4));
			nkc.setDate(date);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(row, 5));
			nht.setDate(date);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		cbbnv.setSelectedItem(model.getValueAt(row, 6).toString());
		txtnc.setText(model.getValueAt(row, 7).toString());
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o.equals(btnthem)) {
			if(ktdulieu()) {
			String mada=txtma.getText();
			String duan=cbbda.getSelectedItem().toString();
			DuAn da=new DuAn(duan);
			String dc=txtdc.getText();
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
			String ngaycapphep=s.format(ncp.getDate());
			String ngaykhoicong=s.format(nkc.getDate());
			String ngayhoanthanh=s.format(nht.getDate());
			String nhanvien=cbbnv.getSelectedItem().toString();
			NhanVien nv=new NhanVien(nhanvien);
			int ngaycong=Integer.parseInt(txtnc.getText());
			ChiTietDuAn dad=new ChiTietDuAn(mada, da, dc,ngaycapphep,ngaykhoicong,ngayhoanthanh, nv, ngaycong);
			if(!da_dao.InsertDuan(dad)) {
				JOptionPane.showMessageDialog(this, "Trung ma");
			}else {
				model.addRow(new Object[] {
						dad.getMactdu(),dad.getMada().getMada(),dad.getDiachi(),dad.getNCP(),dad.getNKC(),dad.getNHT(),dad.getManv().getManv(),dad.getNgaycong()
						
				});
			}
			
		}
		}else if(o.equals(btnx)) {
			int row = bang.getSelectedRow();
			if(row!=-1) {
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Chac chan xoa khong? ", "Chu y", JOptionPane.YES_NO_OPTION);
				if(hoiNhac == JOptionPane.YES_OPTION){
					
					model.removeRow(row);
					da_dao.DeleteDuan(txtma.getText());
					xoar();
				}
			}else {
					JOptionPane.showMessageDialog(this,"Chon dong can xoa");
				}

		}else if(o.equals(btnsua)) {
			if(ktdulieu()) {
			String mada=txtma.getText();
			String duan=cbbnv.getSelectedItem().toString();
			DuAn dad=new DuAn(duan);
			String dc=txtdc.getText();
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
			String ngaycapphep=s.format(ncp.getDate());
			String ngaykhoicong=s.format(nkc.getDate());
			String ngayhoanthanh=s.format(nht.getDate());
			String nhanvien=cbbnv.getSelectedItem().toString();
			NhanVien nv=new NhanVien(nhanvien);
			int ngaycong=Integer.parseInt(txtnc.getText());
			ChiTietDuAn da=new ChiTietDuAn(mada, dad, dc,ngaycapphep,ngaykhoicong,ngayhoanthanh, nv, ngaycong);
			try {
				model.setValueAt(txtma.getText(), bang.getSelectedRow(), 0);
				model.setValueAt(cbbda.getSelectedItem(),  bang.getSelectedRow(),1);
				model.setValueAt(txtdc.getText(), bang.getSelectedRow(), 2);
				model.setValueAt(s.format(ncp.getDate()), bang.getSelectedRow(), 3);
				model.setValueAt(s.format(nkc.getDate()), bang.getSelectedRow(), 4);
				model.setValueAt(s.format(nht.getDate()), bang.getSelectedRow(), 5);
				model.setValueAt(cbbnv.getSelectedItem(),  bang.getSelectedRow(),6);
				model.setValueAt(txtnc.getText(), bang.getSelectedRow(), 7);
				
				da_dao.UpdateDuan(da);
					
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(this, "that bai");
				ex.printStackTrace();
			}
		}
		}else if(o.equals(btnxt)) {
			xoar();
		}else if(o.equals(cbbtim)) {
			String mada = (String) cbbtim.getSelectedItem();
			ChiTietDuAn da = da_dao.timKiem(mada);
			if(da !=null) {
				xoabang();
				model.addRow(new Object[] {
						da.getMactdu(),da.getMada().getMada(),da.getDiachi(),da.getNCP(),da.getNKC(),da.getNHT(),da.getManv().getManv(),da.getNgaycong()
					});
			}
		}else if(o.equals(btnload)) {
			List<ChiTietDuAn> list=da_dao.TimDA(txttim.getText());
			if(txttim.getText().equals("")) {
				xoabang();
				docdulieubang();
			}else if(list.size()==0) {
				JOptionPane.showMessageDialog(this, "Khong tim thay");
			}else {
				xoabang();
				for(ChiTietDuAn da:list) {
					String row[]= {da.getMactdu(),da.getMada().getMada(),da.getDiachi(),da.getNCP(),da.getNKC(),da.getNHT(),da.getManv().getManv(),da.getNgaycong()+""};
					model.addRow(row);
				}
				bang.setModel(model);
			}
		}else if(o.equals(btnql)) {
			NhanVien nv=new NhanVien();
			UI_Admin ad=new UI_Admin(nv);
			 this.setVisible(false);
	            ad.setVisible(true);
		}
	}
	private void xoar() {
		txtma.setText("");
		cbbnv.setSelectedItem(null);
		txtdc.setText("");
		txtnc.setText("");
		cbbnv.setSelectedItem(null);
		ncp.setDate(null);
		nkc.setDate(null);
		nht.setDate(null);
		txtma.requestFocus();
		txtma.setEnabled(true);
	}
	private void docdulieubang() {
		for(int i=0;i<da_dao.getSize();i++){
		ChiTietDuAn da=da_dao.getElement(i);
		model.addRow(new Object[] {
				
				da.getMactdu(),da.getMada().getMada(),da.getDiachi(),da.getNCP(),da.getNKC(),da.getNHT(),da.getManv().getManv(),da.getNgaycong()
				
			});
			}
		}
	private void xoabang() {
		DefaultTableModel d=(DefaultTableModel) bang.getModel();
		d.getDataVector().removeAllElements();
	}
	public boolean ktdulieu() {
		String ma=txtma.getText().trim();
		String dc=txtdc.getText().trim();
		String nc=txtnc.getText().trim();
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		String ngaycapphep=s.format(ncp.getDate());
		String ngaykhoicong=s.format(nkc.getDate());
		String ngayhoanthanh=s.format(nht.getDate());
		if(txtma.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chua nhap ma du an");
			txtma.selectAll();
			txtma.requestFocus();
			return false;
		}
		
		if(txtdc.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chua nhap dia chi du an");
			txtdc.selectAll();
			txtdc.requestFocus();
			return false;
		}
		if(txtnc.getText().equals("")) {	
			JOptionPane.showMessageDialog(this, "Chua nhap ngay cong");
			txtnc.selectAll();
			txtnc.requestFocus();
			return false;
		}
		if(!(ma.length()>0 && ma.matches("^CTDA_[0-9]+"))) {
			JOptionPane.showMessageDialog(this,"Ma nhap theo dang CTDA_ kem theo cac ki so");
			txtma.selectAll();
			txtma.requestFocus();
			return false;
		}
	
		if(!(dc.length()>0 && dc.matches("[a-zA-Z0-9 ]+"))) {
			JOptionPane.showMessageDialog(this,"Dia chi khong nhap ki tu dac biet");
			txtdc.selectAll();
			txtdc.requestFocus();
			return false;
		}
		if(ngaycapphep.length()>0) {
			try {
					LocalDate x=LocalDate.parse(ngaycapphep);
					LocalDate y=LocalDate.now();
					if(!(y.isBefore(x))) {
						JOptionPane.showMessageDialog(this, "Ngay cap phep >now");
						return false;
					}
			}catch(NumberFormatException ex) {
				ex.printStackTrace();
				return false;
			}
		}
		if(ngaycapphep.length()>0) {
			try {
					LocalDate x=LocalDate.parse(ngaycapphep);
					LocalDate y=LocalDate.parse(ngaykhoicong);
					if(!(x.isBefore(y))) {
						JOptionPane.showMessageDialog(this, "Ncp<nkc");
						return false;
					}
			}catch(NumberFormatException ex) {
				ex.printStackTrace();
				return false;
			}
			if(ngaykhoicong.length()>0) {
				try {
						LocalDate x=LocalDate.parse(ngaykhoicong);
						LocalDate y=LocalDate.parse(ngayhoanthanh);
						if(!(x.isBefore(y))) {
							JOptionPane.showMessageDialog(this, "nkc<nht");
							return false;
						}
				}catch(NumberFormatException ex) {
					ex.printStackTrace();
					return false;
				}
			}
		}
		if(nc.length()>0) {
			try {
					int i=Integer.parseInt(nc);
					if(!(i>0 &&i<=31)) {
						JOptionPane.showMessageDialog(this,"Ngay cong 0<nc<=31");
						return false;
					}
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(this,"Ngay cong chi nhap so nguyen duong");
				return false;
			}
		}
		return true;
		}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		new UI_ChiTietDuAn().setVisible(true);
	}
	
}
