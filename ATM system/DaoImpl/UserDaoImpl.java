package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Dao.UserDao;
import Helper.Connect;
import Pojo.UserPojo;

public class UserDaoImpl implements UserDao{
	Connection con=Connect.getConnection();
	PreparedStatement ps;

	@Override
	public boolean login(String name, int pin) {
		try 
		{
			String q="select*from data where name=? and pin=?";
			ps=con.prepareStatement(q);
			ps.setString(1, name);
			ps.setInt(2, pin);
			ResultSet rt=ps.executeQuery();
			if(rt.next())
			{
				return true;		
			}
			else
			{
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean register(UserPojo userPojo) {
		// TODO Auto-generated method stub
		try {
			String q="insert into data(name,accNo,pin)values(?,?,?)";
			ps=con.prepareStatement(q);
			ps.setString(1,userPojo.getName());
			ps.setInt(2,userPojo.getAccNo());
			ps.setInt(3,userPojo.getPin());
			int x=ps.executeUpdate();
			if(x>0) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	

}
