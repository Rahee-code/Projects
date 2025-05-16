package Dao;

import Pojo.UserPojo;

public interface UserDao {
	public boolean register(UserPojo userPojo);
	public boolean login(String name,int pin);

}
