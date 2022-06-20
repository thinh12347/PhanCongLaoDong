package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.KetNoiCSDL;
import entity.DuAn;

public class DuAn_DAO {
	public Boolean InsertDuan(DuAn da) {
		KetNoiCSDL.getinstance();
		Connection con = KetNoiCSDL.getConnection();
		PreparedStatement ps = null;
		int n = 0;
		try {
			ps = con.prepareStatement("Insert into" + " DuAn values(?,?)");

			ps.setString(1, da.getMada());
			ps.setString(2, da.getTenda());

			n = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean UpdateDuAn(DuAn nv) throws Exception {
		KetNoiCSDL.getinstance();
		Connection con = KetNoiCSDL.getConnection();
		PreparedStatement ps = null;
		int n = 0;
		try {
			ps = con.prepareStatement("Update DuAn set  Tenda = ? where Mada = ?");
			ps.setString(2, nv.getMada());
			ps.setString(1, nv.getTenda());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return n > 0;
	}
	public ArrayList<DuAn> dsnv(){
		ArrayList<DuAn> dsnv=new ArrayList<DuAn>();
		try {
			KetNoiCSDL.getinstance();
	    	Connection con=KetNoiCSDL.getConnection();
				String sql="Select* from DuAn";
				Statement s=con.createStatement();
				ResultSet rs=s.executeQuery(sql);
				while(rs.next()) {
					String ma=rs.getString(1);
					String ten=rs.getString(2);
					
					DuAn nv=new DuAn(ma, ten);
					dsnv.add(nv);
				}
				
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return dsnv;
	}
	public void DeleteDuan(String mnv) {
		KetNoiCSDL.getinstance();
		Connection con = KetNoiCSDL.getConnection();
		PreparedStatement s = null;
		String sql = "Delete From DuAn where Mada = ?";
		try {
			s = con.prepareStatement(sql);
			s.setString(1, mnv);
			s.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public ArrayList<DuAn> TimDa(String name) {
		ArrayList<DuAn> dsnv = new ArrayList<DuAn>();
		KetNoiCSDL.getinstance();
		Connection con = KetNoiCSDL.getConnection();
		PreparedStatement s = null;
		try {
			String sql = "Select * from DuAn where Mada like N'%" + name + "%' " + "or Tenda like N'%" + name + "%'";

			s = con.prepareStatement(sql);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				String manv = rs.getString(1);
				String ten = rs.getString(2);

				DuAn nv = new DuAn(manv, ten);
				dsnv.add(nv);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return dsnv;
	}
	public DuAn getElement(int i) {
		if(i<0||i>dsnv().size())
			return null;
		return dsnv().get(i);
	}
	public int getSize() {
		return dsnv().size();
	}
	public ArrayList<DuAn> getDsNhanvien() {
		return dsnv();
	}
	public int count() {
		return dsnv().size();
	}
	public DuAn timKiem(String ma) {
		DuAn s = new DuAn(ma);
		if(dsnv().contains(s))
			return dsnv().get(dsnv().indexOf(s));
		return null;
	}
}
