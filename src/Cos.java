import java.io.Serializable;

public class Cos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int number;
	private String msg;
	
	public Cos(int number) throws DataFormatException  {
		if(number!=2)
			throw new DataFormatException("miala byc 2 lamusie");
		this.number = number;
		this.msg = "GITARA SIEMA";
	}
	
	//JavaBeans methods
	public Cos() {};
	public int getNumber() {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getMsg() {
		return this.msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
