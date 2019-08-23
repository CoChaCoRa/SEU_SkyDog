package vo;

public class DataDict {
	private String IndexType;
	private String Description;
	private int Code;
	private String CodeValue;
	
	public void setIndexType(String indextype) {
		this.IndexType = indextype;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	public void setCode(int code) {
		this.Code = code;
	}
	public void setCodeValue(String codevalue) {
		this.CodeValue = codevalue;
	}
	
	public String getIndexType() {
		return IndexType;
	}
	public String getDescription() {
		return Description;
	}
	public int getCode() {
		return Code;
	}
	public String getCodeValue() {
		return CodeValue;
	}
}
