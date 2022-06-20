package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.KetNoiCSDL;
import entity.PhongBan;


public class PhongBan_DAO {
	
		public ArrayList<PhongBan> dspb(){
			ArrayList<PhongBan> pb=new ArrayList<PhongBan>();
			try {
					KetNoiCSDL.getinstance();
					Connection con=KetNoiCSDL.getConnection();
					String sql="Select* from PhongBan";
					Statement s=con.createStatement();
					ResultSet rs=s.executeQuery(sql);
					while(rs.next()) {
						String mapb=rs.getString(1);
						String tenpb=rs.getString(2);
						PhongBan p=new PhongBan(mapb, tenpb);
						pb.add(p);
					}
					
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
			return pb;
		}

}
