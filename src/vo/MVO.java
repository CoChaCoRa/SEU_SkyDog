package vo;

public class MVO {
	private String CName_C;
	private String CName_E;
	private String Intro;
	private String VeriType;
	private String CertiAdd;
	private String ID;
	
	public void setCName_C(String cname) {
		this.CName_C = cname;
	}
	public void setCName_E(String cname) {
		this.CName_E = cname;
	}
	public void setIntro(String intro) {
		this.Intro = intro;
	}
	public void setVeriType(String veritype) {
		this.VeriType = veritype;
	}
	public void setCertiAdd(String certiadd) {
		this.CertiAdd = certiadd;
	}
	public void setID(String id) {
		this.ID = id;
	}
	
	public String getCName_C() {
		return CName_C;
	}
	public String getCName_E() {
		return CName_E;
	}
	public String getIntro() {
		return Intro;
	}
	public String getVeriType() {
		return VeriType;
	}
	public String getCertiAdd() {
		return CertiAdd;
	}
	public String getID() {
		return ID;
	}
}
