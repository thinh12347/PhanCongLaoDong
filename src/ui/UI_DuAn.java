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
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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

import dao.DuAn_DAO;
import dao.NhanVien_DAO;
import database.KetNoiCSDL;
import entity.DuAn;
import entity.NhanVien;
import entity.PhongBan;

public class UI_DuAn extends JFrame implements ActionListener, MouseListener, Runnable {
	private JTextField txtma;
	private JTextField txtten;
	private JButton btnthem;
	private JButton btnxt;
	private JButton btnx;
	private DefaultTableModel model;
	private JTable bang;
	private JButton btnsua;
	private JComboBox<String> cbbtim;
	private JButton btntim;
	private JTextField txttim;
	private JButton btnql;
	private DuAn_DAO nv_dao;

	public UI_DuAn(DuAn nv) {
		try {
			KetNoiCSDL.getinstance().Connection();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		nv_dao = new DuAn_DAO();
		setTitle("Du An");
		setSize(900, 550);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		JLabel lblTieuDe;
		JPanel pTieuDe = new JPanel();
		pTieuDe.add(lblTieuDe = new JLabel("THONG TIN DU AN"));
		Font font = new Font(Font.SERIF, Font.BOLD, 30);
		lblTieuDe.setForeground(Color.blue);
		lblTieuDe.setFont(font);

		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		p1.add(new JLabel("Ma du an:   "));

		p1.add(txtma = new JTextField(30));

		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		p2.add(new JLabel("Ten du an: "));

		p2.add(txtten = new JTextField(30));

		JPanel p9 = new JPanel();
		p9.add(btnthem = new JButton("Them"));
		try {
			BufferedImage imgcn = ImageIO.read(new File("image/add.png"));
			ImageIcon iccn = new ImageIcon(imgcn.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnthem.setIcon(iccn);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}

		p9.add(btnxt = new JButton("Xoa trang"));

		p9.add(btnx = new JButton("Xoa"));
		try {
			BufferedImage imgdel = ImageIO.read(new File("image/del.png"));
			ImageIcon icdel = new ImageIcon(imgdel.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnx.setIcon(icdel);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}

		p9.add(btnsua = new JButton("Cap nhat"));
		try {
			BufferedImage imgcn = ImageIO.read(new File("image/update.png"));
			ImageIcon iccn = new ImageIcon(imgcn.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnsua.setIcon(iccn);
		} catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}

		p9.add(new JLabel("Tim theo ma"));
		p9.add(cbbtim = new JComboBox<String>());

		p9.add(btntim = new JButton("Tim kiem nang cao"));
		try {

			BufferedImage imgs = ImageIO.read(new File("image/search.png"));
			ImageIcon ics = new ImageIcon(imgs.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btntim.setIcon(ics);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		p9.add(txttim = new JTextField(10));

		JPanel p13 = new JPanel();
		p13.add(btnql = new JButton("Quay lai"));
		try {

			BufferedImage imgql = ImageIO.read(new File("image/back.png"));
			ImageIcon icql = new ImageIcon(imgql.getScaledInstance(14, 14, Image.SCALE_SMOOTH));
			btnql.setIcon(icql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		JPanel p10 = new JPanel();
		p10.setLayout(new BoxLayout(p10, BoxLayout.X_AXIS));
		String[] cols = { "Ma du an", "Ten du an" };
		model = new DefaultTableModel(cols, 0);
		bang = new JTable(model);
		bang.setRowHeight(15);
		JScrollPane js;
		js = new JScrollPane(bang);
		js.setPreferredSize(new Dimension(550, 200));
		docdulieubang();
		updateComboboxData();
		p10.add(js);

		Box b = Box.createVerticalBox();
		b.add(pTieuDe);
		b.add(Box.createVerticalStrut(5));
		b.add(p1);
		b.add(Box.createVerticalStrut(5));
		b.add(p2);
		b.add(Box.createVerticalStrut(5));

		b.add(p9);
		b.add(Box.createVerticalStrut(5));
		b.add(p10);
		b.add(Box.createVerticalStrut(5));
		b.add(p13);
		b.add(Box.createVerticalStrut(5));

		JPanel p = new JPanel();
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
		DuAn nv = new DuAn();
		new UI_DuAn(nv).setVisible(true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DuAn nv = new DuAn();
		new UI_DuAn(nv).setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = bang.getSelectedRow();
		txtma.setText(model.getValueAt(row, 0).toString());
		txtma.setEnabled(false);
		txtten.setText(model.getValueAt(row, 1).toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnthem)) {
			if (ktdulieu()) {
				String ma = txtma.getText();
				String ten = txtten.getText();

				DuAn n = new DuAn(ma, ten);
				if (!nv_dao.InsertDuan(n)) {
					JOptionPane.showMessageDialog(this, "Trung ma");
				} else {
					model.addRow(new Object[] { n.getMada(), n.getTenda() });
				}
			}
		} else if (o.equals(btnx)) {
			int row = bang.getSelectedRow();
			if (row != -1) {
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Chac chan xoa? ", "Chu y",
						JOptionPane.YES_NO_OPTION);
				if (hoiNhac == JOptionPane.YES_OPTION) {

					model.removeRow(row);
					nv_dao.DeleteDuan((txtma.getText()));
					xoar();

				}
			} else {
				JOptionPane.showMessageDialog(this, "Chon dong can xoa");
			}

		} else if (o.equals(btnsua)) {
			if (ktdulieu()) {
				String ma = txtma.getText();
				String ten = txtten.getText();

				DuAn n = new DuAn(ma, ten);

				try {
					model.setValueAt(txtma.getText(), bang.getSelectedRow(), 0);
					model.setValueAt(txtten.getText(), bang.getSelectedRow(), 1);
					nv_dao.UpdateDuAn(n);

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, "that bai");
					ex.printStackTrace();
				}

			}
		} else if (o.equals(btnxt)) {
			xoar();
		} else if (o.equals(btnql)) {
			NhanVien nv = new NhanVien();
			UI_Admin ad = new UI_Admin(nv);
			this.setVisible(false);
			ad.setVisible(true);
		} else if (o.equals(cbbtim)) {
			String manv = (String) cbbtim.getSelectedItem();
			DuAn n = nv_dao.timKiem(manv);
			if (n != null) {
				xoabang();
				model.addRow(new Object[] { n.getMada(), n.getTenda() });
			}
		} else if (o.equals(btntim)) {
			List<DuAn> list = nv_dao.TimDa(txttim.getText());
			if (txttim.getText().equals("")) {
				xoabang();
				docdulieubang();
			} else if (list.size() == 0) {
				JOptionPane.showMessageDialog(this, "Khong tim thay");
			} else {
				xoabang();
				for (DuAn n : list) {
					String row[] = { n.getMada(), n.getTenda() };
					model.addRow(row);
				}
				bang.setModel(model);
			}
		}
	}

	private void xoar() {
		txtma.setText("");
		txtten.setText("");
		txtma.requestFocus();
		txtma.setEnabled(true);
	}

	private void updateComboboxData() {
		int n = nv_dao.count();
		String[] items = new String[n];
		int i = 0;
		for (DuAn s : nv_dao.getDsNhanvien()) {
			items[i] = s.getMada();
			i++;
		}
		cbbtim.setModel(new DefaultComboBoxModel<String>(items));
	}

	public boolean ktdulieu() {
		String ma = txtma.getText().trim();
		String ten = txtten.getText().trim();
		if (txtma.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập mã dự án");
			txtma.selectAll();
			txtma.requestFocus();
			return false;
		}

		if (txtten.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Chưa nhập tên dự án");
			txtten.selectAll();
			txtten.requestFocus();
			return false;
		}

		if (!(ma.length() > 0 && ma.matches("^DA_[0-9]+"))) {
			JOptionPane.showMessageDialog(this, "Ma nhap theo dang DA_ kem theo cac ki so");
			txtma.selectAll();
			txtma.requestFocus();
			return false;
		}
		if (!(ten.length() > 0 && ten.matches("[a-zA-Z0-9 ]+"))) {
			JOptionPane.showMessageDialog(this, "Ten khong nhap ki tu dac biet");
			txtten.selectAll();
			txtten.requestFocus();
			return false;
		}
		return true;
	}
	private void xoabang() {
		DefaultTableModel d=(DefaultTableModel) bang.getModel();
		d.getDataVector().removeAllElements();
	}
	private void docdulieubang() {
		for(int i=0;i<nv_dao.getSize();i++){
		DuAn n=nv_dao.getElement(i);
		model.addRow(new Object[] {
				n.getMada(),n.getTenda()
				
			});
			}
		}
}
