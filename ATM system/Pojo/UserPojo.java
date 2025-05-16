package Pojo;

public class UserPojo {
	
	private int id;
	private int accNo;
	private String name;
	private int pin;
	
	public UserPojo(int accNo,String name, int pin) {
		super();
		this.accNo=accNo;
		this.name = name;
		this.pin = pin;
	}
	public UserPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccNo() {
		return accNo;
	}
	public void setAccNo(int accNo) {
		this.accNo = accNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "UserPojo [id=" + id + ", accNo=" + accNo + ", name=" + name + ", pin=" + pin + "]";
	}
	
	
	
}
