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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import database.KetNoiCSDL;
import entity.NhanVien;
import entity.PhongBan;



public class UI_NhanVien extends JFrame implements ActionListener, MouseListener, Runnable {
	private NhanVien_DAO nv_dao;
	private PhongBan_DAO pb_dao;
	private JTextField txtma;
	private JTextField txtten;
	
	private JCheckBox check;
	private JTextField txtdc;
	private JComboBox cbbcv;
	private JComboBox<String> cbbpb;
	private JButton btnthem;
	private JButton btnxt;
	private JButton btnx;

	private JPasswordField txtpass;
	private DefaultTableModel model;
	private JTable bang;
	private JButton btnsua;
	private JDateChooser ns;
	private JComboBox<String> cbbtim;
	private JButton btntim;
	private JTextField txttim;
	private JButton btnql;
	
	
	public UI_NhanVien(NhanVien nv) {
		try {
			KetNoiCSDL.getinstance().Connection();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
	
		nv_dao=new NhanVien_DAO();
		pb_dao=new PhongBan_DAO();
		
		setTitle("Nhan vien");
		setSize(900, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel lblTieuDe;
		JPanel pTieuDe = new JPanel();
		pTieuDe.add(lblTieuDe = new JLabel("THONG TIN NHAN VIEN"));
		Font font = new Font (Font.SERIF, Font.BOLD , 30);
		lblTieuDe.setForeground(Color.blue);
		lblTieuDe.setFont(font);
		
		
		JPanel p1=new JPanel();
		p1.setLayout(new BoxLayout(p1,BoxLayout.X_AXIS));
		p1.add(new JLabel("Ma nhan vien:   "));
		p1.add(txtma=new JTextField(30));
		
		JPanel p2=new JPanel();
		p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
		p2.add(new JLabel("Ten nhan vien: "));
		p2.add(txtten=new JTextField(30));
		
		JPanel p3=new JPanel();
		p3.setLayout(new BoxLayout(p3,BoxLayout.X_AXIS));
		p3.add(new JLabel("Ngay sinh:         "));		
		p3.add(ns=new JDateChooser());
		
		p3.add(new JLabel("    Phai: "));
		p3.add(check=new JCheckBox());
		
		//JPanel p4=new JPanel();
		//p4.setLayout(new BoxLayout(p4,BoxLayout.X_AXIS));
		
		
		JPanel p5=new JPanel();
		p5.setLayout(new BoxLayout(p5,BoxLayout.X_AXIS));
		p5.add(new JLabel("Dia chi:              "));
		p5.add(txtdc=new JTextField(30));
		
		JPanel p6=new JPanel();
		p6.setLayout(new BoxLayout(p6,BoxLayout.X_AXIS));
		
		p6.add(new JLabel("Phong ban:       "));
		p6.add(cbbpb=new JComboBox<String>());
		cbbpb.setEditable(true);
		ArrayList<PhongBan> listpb=pb_dao.dspb();
		for(PhongBan p: listpb) {
			cbbpb.addItem(p.getMapb());
		}
		
		p6.add(new JLabel("   Chuc vu:   "));	
		p6.add(cbbcv=new JComboBox<>());
		cbbcv.addItem("Giam doc");
		cbbcv.addItem("Pho giam doc");
		cbbcv.addItem("Truong phong");
		cbbcv.addItem("Pho phong");
		cbbcv.addItem("Nhan vien");
		
		//JPanel p7=new JPanel();
		
		
		JPanel p8=new JPanel();
		p8.setLayout(new BoxLayout(p8,BoxLayout.X_AXIS));
		p8.add(new JLabel("Pass:                 "));
		p8.add(txtpass=new JPasswordField(30));
		
		JPanel p9=new JPanel();
		p9.add(btnthem=new JButton("Them"));
		try {
			BufferedImage imgcn= ImageIO.read(new File("image/add.png"));
			ImageIcon iccn= new ImageIcon(imgcn.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnthem.setIcon(iccn);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		p9.add(btnxt=new JButton("Xoa trang"));
		
		p9.add(btnx=new JButton("Xoa"));
		try {
			BufferedImage imgdel= ImageIO.read(new File("image/del.png"));
			ImageIcon icdel= new ImageIcon(imgdel.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnx.setIcon(icdel);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		p9.add(btnsua=new JButton("Cap nhat"));
		try {
			BufferedImage imgcn= ImageIO.read(new File("image/update.png"));
			ImageIcon iccn= new ImageIcon(imgcn.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnsua.setIcon(iccn);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		
		p9.add(new JLabel("Tim theo ma"));
		p9.add(cbbtim=new JComboBox<String>());
		
		p9.add(btntim=new JButton("Tim kiem nang cao"));
		try {
			
			BufferedImage imgs= ImageIO.read(new File("image/search.png"));
			ImageIcon ics= new ImageIcon(imgs.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btntim.setIcon(ics);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		p9.add(txttim=new JTextField(10));
		
		JPanel p13 = new JPanel();
		p13.add(btnql=new JButton("Quay lai"));
		try {
			
			BufferedImage imgql= ImageIO.read(new File("image/back.png"));
			ImageIcon icql= new ImageIcon(imgql.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnql.setIcon(icql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		
		
		
		JPanel p10=new JPanel();
		p10.setLayout(new BoxLayout(p10,BoxLayout.X_AXIS));
		String[] cols= {"Ma nhan vien","Ten nhan vien","Ngay sinh","Phai","Dia chi","Phong ban","Chuc vu","Mat khau"};
		model=new DefaultTableModel(cols,0);
		bang=new JTable(model);
		bang.setRowHeight(15);
		JScrollPane js;
		js=new JScrollPane(bang);
		js.setPreferredSize(new Dimension(550,200));
		docdulieubang();
		updateComboboxData();
		p10.add(js);
		
		
		
		Box b=Box.createVerticalBox();
		b.add(pTieuDe);
		b.add(Box.createVerticalStrut(5));
		b.add(p1);
		b.add(Box.createVerticalStrut(5));
		b.add(p2);
		b.add(Box.createVerticalStrut(5));
		b.add(p3);
		b.add(Box.createVerticalStrut(5));
		//b.add(p4);
		b.add(p5);
		b.add(Box.createVerticalStrut(5));
		b.add(p6);
		b.add(Box.createVerticalStrut(5));
		//b.add(p7);
		b.add(p8);
		b.add(Box.createVerticalStrut(5));
		b.add(p9);
		b.add(Box.createVerticalStrut(5));
		b.add(p10);
		b.add(Box.createVerticalStrut(5));
		b.add(p13);
		b.add(Box.createVerticalStrut(5));
		
		JPanel p=new JPanel();
		p.add(b);
		add(p);
		btnthem.addActionListener(this);
		btnxt.addActionListener(this);
		btnx.addActionListener(this);
		btnsua.addActionListener(this);
		bang.addMouseListener(this);
		cbbtim.addActionListener(this);
		btntim.addActionListener(this);
		btnql.addActionListener(this);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NhanVien nv=new NhanVien();
		new UI_NhanVien(nv).setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row=bang.getSelectedRow();
		txtma.setText(model.getValueAt(row, 0).toString());
		txtma.setEnabled(false);
		txtten.setText(model.getValueAt(row, 1).toString());
		try {
				Date date=new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(row, 2));
				ns.setDate(date);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex);
		}
		check.setSelected(model.getValueAt(row, 3).toString()=="Nu"? true:false);
		txtdc.setText(model.getValueAt(row, 4).toString());
		cbbpb.setSelectedItem(model.getValueAt(row, 5).toString());
		cbbcv.setSelectedItem(model.getValueAt(row, 6).toString());
		txtpass.setText(model.getValueAt(row, 7).toString());
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
			String ma=txtma.getText();
			String ten=txtten.getText();
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
			String date=s.format(ns.getDate());
			Boolean phai=check.isSelected();
			String dc=txtdc.getText();
			String phongban=cbbpb.getSelectedItem().toString();
			PhongBan pb=new PhongBan(phongban);
			String cv=cbbcv.getSelectedItem().toString();
			String pass=txtpass.getText();
			NhanVien n=new NhanVien(ma,ten,date,phai,dc,pb,cv,pass);
			if(!nv_dao.InsertNhanvien(n)) {
				JOptionPane.showMessageDialog(this, "Trung ma");
			}else {
				model.addRow(new Object[] {
						n.getManv(),n.getTennv(),n.getNgaysinh(),n.isPhai()?"Nu":"Nam",n.getDiachi(),n.getPb().getMapb(),n.getChucvu(),n.getPass()
						
				});
			}
			}
		}else if(o.equals(btnx)) {
			int row = bang.getSelectedRow();
			if(row!=-1) {
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Chac chan xoa? ", "Chu y", JOptionPane.YES_NO_OPTION);
				if(hoiNhac == JOptionPane.YES_OPTION){
					 if(this.txtma.getText().trim().toString().equals("Admin")){
			                JOptionPane.showMessageDialog(this,"Khong the xoa tai khoan Admin");
			           }else {
			        	   model.removeRow(row);
							nv_dao.DeleteNhanvien(txtma.getText());
							xoar();
			           }
				}
			}else {
					JOptionPane.showMessageDialog(this,"Chon dong can xoa");
				}
		
		}else if(o.equals(btnsua)) {
			if(ktdulieu()) {
			String ma=txtma.getText();
			String ten=txtten.getText();
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
			String date=s.format(ns.getDate());
			Boolean phai=check.isSelected();
			String dc=txtdc.getText();
			String phongban=cbbpb.getSelectedItem().toString();
			PhongBan pb=new PhongBan(phongban);
			String cv=cbbcv.getSelectedItem().toString();
			String pass=txtpass.getText();
			NhanVien n=new NhanVien(ma,ten,date,phai,dc,pb,cv,pass);
			
				try {
					model.setValueAt(txtma.getText(), bang.getSelectedRow(), 0);
					model.setValueAt(txtten.getText(), bang.getSelectedRow(),1);
					model.setValueAt(s.format(ns.getDate()), bang.getSelectedRow(), 2);
					model.setValueAt(check.isSelected()?"Nữ":"Nam", bang.getSelectedRow(),3);
					model.setValueAt(txtdc.getText(), bang.getSelectedRow(), 4);
					model.setValueAt(cbbpb.getSelectedItem(),  bang.getSelectedRow(),5);
					model.setValueAt(cbbcv.getSelectedItem(),  bang.getSelectedRow(),6);
					model.setValueAt(txtpass.getText(), bang.getSelectedRow(), 7);
	
					nv_dao.UpdateNhanvien(n);
						
				}catch(Exception ex) {
					JOptionPane.showMessageDialog(this, "that bai");
					ex.printStackTrace();
				}
			
			}
		}else if(o.equals(btnxt)) {
			xoar();
		}else if(o.equals(btnql)) {
			NhanVien nv=new NhanVien();
			UI_Admin ad=new UI_Admin(nv);
			this.setVisible(false);
	        ad.setVisible(true);
		}else if(o.equals(cbbtim)) {
			String manv = (String) cbbtim.getSelectedItem();
			NhanVien n = nv_dao.timKiem(manv);
			if(n !=null) {
				xoabang();
				model.addRow(new Object[] {
						n.getManv(),n.getTennv(),n.getNgaysinh(),n.isPhai()?"Nu":"Nam",n.getDiachi(),n.getPb().getMapb(),n.getChucvu(),n.getPass()
					
					});
		}
		}else if(o.equals(btntim)) {
			List<NhanVien> list=nv_dao.TimNV(txttim.getText());
			if(txttim.getText().equals("")) {
				xoabang();
				docdulieubang();
			}else if(list.size()==0) {
				JOptionPane.showMessageDialog(this, "Khong tim thay");
			}else {
				xoabang();
				for(NhanVien n:list) {
					String row[]= {n.getManv(),n.getTennv(),n.getNgaysinh(),n.isPhai()?"Nu":"Nam",n.getDiachi(),n.getPb().getMapb(),n.getChucvu(),n.getPass()};
					model.addRow(row);
				}
				bang.setModel(model);
			}
		}
	}
	private void docdulieubang() {
		for(int i=0;i<nv_dao.getSize();i++){
		NhanVien n=nv_dao.getElement(i);
		model.addRow(new Object[] {
				n.getManv(),n.getTennv(),n.getNgaysinh(),n.isPhai()?"Nu":"Nam",n.getDiachi(),n.getPb().getMapb(),n.getChucvu(),n.getPass()
				
			});
			}
		}
	private void xoar() {
		txtma.setText("");
		txtten.setText("");
		check.setSelected(false);
		txtdc.setText("");
		cbbcv.setSelectedItem(null);
		cbbpb.setSelectedItem(null);
		ns.setDate(null);
		txtpass.setText("");
		txtma.requestFocus();
		txtma.setEnabled(true);
	}
	public boolean ktdulieu() {
		String ma=txtma.getText().trim();
		String ten=txtten.getText().trim();
		String dc=txtdc.getText().trim();
		String pass=txtpass.getText().trim();
		SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd");
		String ngaysinh=s.format(ns.getDate());
		if(txtma.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mã nhân viên");
			txtma.selectAll();
			txtma.requestFocus();
			return false;
		}
		
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
		if(txtpass.getText().equals("")) {	
			JOptionPane.showMessageDialog(this, "Chưa mat khau nhân viên");
			txtpass.selectAll();
			txtpass.requestFocus();
			return false;
		}
		if(!(ma.length()>0 && ma.matches("^NV_[0-9]+"))) {
			JOptionPane.showMessageDialog(this,"Ma nhap theo dang NV_ kem theo cac ki so");
			txtma.selectAll();
			txtma.requestFocus();
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
		if(!(pass.length()>0 && pass.matches("[a-zA-Z0-9 ]{8,15}"))) {
			JOptionPane.showMessageDialog(this,"Mat khau gom 8-15 ki tu so hoac chu");
			txtpass.selectAll();
			txtpass.requestFocus();
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
		return true;
		}
	private void xoabang() {
		DefaultTableModel d=(DefaultTableModel) bang.getModel();
		d.getDataVector().removeAllElements();
	}
	private void updateComboboxData() {
		int n = nv_dao.count(); 
		String []items = new String[n];
		int i = 0;
		for(NhanVien s : nv_dao.getDsNhanvien()){
			items[i] = s.getManv();
			i++;
		}
		cbbtim.setModel(new DefaultComboBoxModel<String>(items));
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		NhanVien nv=new NhanVien();
		new UI_NhanVien(nv).setVisible(true);
	}
	
}
