package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.KetNoiCSDL;
import entity.ChiTietDuAn;
import entity.DuAn;
import entity.NhanVien;

public class ChiTietDuAn_DAO {
	  public Boolean InsertDuan(ChiTietDuAn d){
	    	KetNoiCSDL.getinstance();
	    	Connection con = KetNoiCSDL.getConnection();
	    	PreparedStatement ps=null;
	    	int n=0;
	        try {
	        	  ps =con.prepareStatement( "Insert into" +" ChiTietDuAn values(?,?,?,?,?,?,?,?)");
	        	  ps.setString(1, d.getMactdu());
	              ps.setString(2, d.getMada().getMada());
	              ps.setString(3,d.getDiachi());
	              ps.setString(4,d.getNCP());
	              ps.setString(5, d.getNKC());
	              ps.setString(6, d.getNHT());
	              ps.setString(7, d.getManv().getManv());
	              ps.setInt(8, d.getNgaycong());
	              n=ps.executeUpdate();
	        } catch (Exception e) {
	          e.printStackTrace();
	        }finally {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
	        return n>0;
	    }
	    public boolean UpdateDuan(ChiTietDuAn d) throws Exception{
	    	KetNoiCSDL.getinstance();
	    	Connection con= KetNoiCSDL.getConnection();
	    	PreparedStatement ps=null;
	    	int n=0;
	        try {
	            ps =con.prepareStatement("Update ChiTietDuAn set  Mada = ?,"
	                    +"Diachi = ?,NCP=? , NKC=? , NHT=? , Manv=? , Ngaycong=? where Mactdu = ?");
	            ps.setString(8, d.getMactdu());
	            ps.setString(1, d.getMada().getMada());
	            ps.setString(2, d.getDiachi());
	            ps.setString(3, d.getNCP());
	            ps.setString(4,d.getNKC() );
	            ps.setString(5,d.getNHT());
	            ps.setString(6, d.getManv().getManv());
	            ps.setInt(7, d.getNgaycong());
	            return ps.executeUpdate() > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }finally {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
	        return n>0;
	    }
	    public void DeleteDuan(String mda){
	    	KetNoiCSDL.getinstance();
	    	Connection con = KetNoiCSDL.getConnection();
	    	PreparedStatement s=null;
	    	String sql="Delete From ChiTietDuAn where Mactdu = ?";
	        try {
	           s=con.prepareStatement(sql);
	            s.setString(1, mda);
	            s.executeUpdate();
	        } catch (SQLException e) {
	           e.printStackTrace();
	        }finally {
	        	try {
					s.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
	        }
	    }
		public ArrayList<ChiTietDuAn> dsda(){
			ArrayList<ChiTietDuAn> dsda=new ArrayList<ChiTietDuAn>();
			try {
					KetNoiCSDL.getinstance();
					Connection con = KetNoiCSDL.getConnection();
					String sql="Select* from ChiTietDuAn";
					Statement s=con.createStatement();
					ResultSet rs=s.executeQuery(sql);
					while(rs.next()) {
						String ma=rs.getString(1);
						DuAn duan=new DuAn(rs.getString(2));
						String dc=rs.getString(3);
						String ncp=rs.getString(4);
						String nkc=rs.getString(5);
						String nht=rs.getString(6);
						NhanVien manv=new NhanVien(rs.getString(7));
						int nc=rs.getInt(8);
						ChiTietDuAn da=new ChiTietDuAn(ma,duan,dc,ncp,nkc,nht,manv,nc);
						dsda.add(da);
					}
					
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return dsda;
		}
		public ArrayList<ChiTietDuAn> TimDA(String da){
			ArrayList<ChiTietDuAn> dsda=new ArrayList<ChiTietDuAn>();
			KetNoiCSDL.getinstance();
	    	Connection con= KetNoiCSDL.getConnection();
	    	PreparedStatement s=null;
			try {
				String sql = "Select * from ChiTietDuAn where Mactdu like N'%"+da+"%'"
			            + "or Mada like N'%"+da+"%'"
			            + "or Diachi like N'%"+da+"%'"
			            + "or NCP like N'%"+da+"%'"
			            + "or NKC like N'%"+da+"%'"
			            + "or NHT like N'%"+da+"%'"
			            + "or Manv like N'%"+da+"%'";
			                   
				s =con.prepareStatement(sql);
				ResultSet rs=s.executeQuery();
					while(rs.next()) {
						String ma=rs.getString(1);
						DuAn duan=new DuAn(rs.getString(2));
						String dc=rs.getString(3);
						String ncp=rs.getString(4);
						String nkc=rs.getString(5);
						String nht=rs.getString(6);
						NhanVien manv=new NhanVien(rs.getString(7));
						int nc=rs.getInt(8);
						ChiTietDuAn d=new ChiTietDuAn(ma,duan,dc,ncp,nkc,nht,manv,nc);
						dsda.add(d);
					}
					
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return dsda;
		}
		
		public ChiTietDuAn getElement(int i) {
			if(i<0||i>dsda().size())
				return null;
			return dsda().get(i);
		}
		public int getSize() {
			return dsda().size();
		}
		public ArrayList<ChiTietDuAn> getDsDa() {
			return dsda();
		}
		public int count() {
			return dsda().size();
		}
		public ChiTietDuAn timKiem(String ma) {
			ChiTietDuAn s = new ChiTietDuAn(ma);
			if(dsda().contains(s))
				return dsda().get(dsda().indexOf(s));
			return null;
		}
}
