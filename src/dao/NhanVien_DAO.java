package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.KetNoiCSDL;
import entity.NhanVien;
import entity.PhongBan;


public class NhanVien_DAO {
	  public Boolean InsertNhanvien(NhanVien nv){
	    	KetNoiCSDL.getinstance();
	    	Connection con=KetNoiCSDL.getConnection();
	    	PreparedStatement ps=null;
	    	int n=0;
	        try {
	        	  ps =con.prepareStatement( "Insert into" +" NhanVien values(?,?,?,?,?,?,?,?)");
	    
	        	  ps.setString(1, nv.getManv());
	              ps.setString(2, nv.getTennv());
	              ps.setString(3,nv.getNgaysinh());
	              ps.setBoolean(4,nv.isPhai());
	              ps.setString(5, nv.getDiachi());
	              ps.setString(6, nv.getPb().getMapb());
	              ps.setString(7, nv.getChucvu());
	              ps.setString(8, nv.getPass());
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
	    
	    public boolean UpdateNhanvien(NhanVien nv) throws Exception{
	    	KetNoiCSDL.getinstance();
	    	Connection con= KetNoiCSDL.getConnection();
	    	PreparedStatement ps=null;
	    	int n=0;
	        try {
	            ps =con.prepareStatement("Update NhanVien set  Tennv = ?,"
	                    +"Ngaysinh=?, Phai=?, Diachi = ?,Mapb=?,Chucvu=?,Pass=? where Manv = ?");
	            ps.setString(8, nv.getManv());
	            ps.setString(1, nv.getTennv());
	            ps.setString(2,nv.getNgaysinh());
	            ps.setBoolean(3, nv.isPhai());
	            ps.setString(4, nv.getDiachi());
	            ps.setString(5, nv.getPb().getMapb());
	            ps.setString(6, nv.getChucvu());
	            ps.setString(7, nv.getPass());
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
	    
	    public void DeleteNhanvien(String mnv){
	    	KetNoiCSDL.getinstance();
	    	Connection con = KetNoiCSDL.getConnection();
	    	PreparedStatement s=null;
	    	String sql="Delete From NhanVien where Manv = ?";
	        try {
	           s=con.prepareStatement(sql);
	            s.setString(1, mnv);
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
		public ArrayList<NhanVien> dsnv(){
			ArrayList<NhanVien> dsnv=new ArrayList<NhanVien>();
			try {
				KetNoiCSDL.getinstance();
		    	Connection con=KetNoiCSDL.getConnection();
					String sql="Select* from NhanVien";
					Statement s=con.createStatement();
					ResultSet rs=s.executeQuery(sql);
					while(rs.next()) {
						String ma=rs.getString(1);
						String ten=rs.getString(2);
						boolean phai=rs.getBoolean(4);
						String ngaysinh=rs.getString(3);
						String dc=rs.getString(5);
						PhongBan pb=new PhongBan(rs.getString(6));
						String cv=rs.getString(7);
						String pass=rs.getString(8);
						NhanVien nv=new NhanVien(ma,ten,ngaysinh,phai,dc, pb,cv,pass);
						dsnv.add(nv);
					}
					
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return dsnv;
		}
		public NhanVien getElement(int i) {
			if(i<0||i>dsnv().size())
				return null;
			return dsnv().get(i);
		}
		public int getSize() {
			return dsnv().size();
		}
		public ArrayList<NhanVien> TimNV(String name){
			ArrayList<NhanVien> dsnv=new ArrayList<NhanVien>();
			KetNoiCSDL.getinstance();
	    	Connection con= KetNoiCSDL.getConnection();
	    	PreparedStatement s=null;
			try {	
					String sql = "Select * from NhanVien where Manv like N'%"+name+"%' "
				            + "or Tennv like N'%"+name+"%'"
				            + "or Ngaysinh like N'%"+name+"%'"
				            + "or Diachi like N'%"+name+"%'"
				            + "or Mapb like N'%"+name+"%'"
				            + "or Chucvu like N'%"+name+"%'";          
					s =con.prepareStatement(sql);
					ResultSet rs=s.executeQuery();
					while(rs.next()) {
						String manv=rs.getString(1);
						String ten=rs.getString(2);
						boolean phai=rs.getBoolean(4);
						String ngaysinh=rs.getString(3);
						String dc=rs.getString(5);
						PhongBan pb=new PhongBan(rs.getString(6));
						String cv=rs.getString(7);
						String pass=rs.getString(8);
						NhanVien nv=new NhanVien(manv,ten,ngaysinh,phai,dc, pb,cv,pass);
						dsnv.add(nv);
					}
					
			}catch(SQLException ex) {
				ex.printStackTrace();
			}finally {
				try {
					s.close();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
			}
			return dsnv;
		}
		public ArrayList<NhanVien> getDsNhanvien() {
			return dsnv();
		}
		public int count() {
			return dsnv().size();
		}
		public NhanVien timKiem(String ma) {
			NhanVien s = new NhanVien(ma);
			if(dsnv().contains(s))
				return dsnv().get(dsnv().indexOf(s));
			return null;
		}
}

