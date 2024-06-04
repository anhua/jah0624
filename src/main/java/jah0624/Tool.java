package jah0624;

public class Tool extends ToolType {

	private String toolCode;
	private String toolBrand;

	public Tool(String type, String code, String brand, float dailyCharge, boolean weekDayCharged,
			boolean weekenDayCharged, boolean holidayCharged) {
		super(type, dailyCharge, weekDayCharged, weekenDayCharged, holidayCharged);
		this.toolCode = code;
		this.toolBrand = brand;
	}

	public String getToolCode() {
		return toolCode;
	}

	public void setToolCode(String toolCode) {
		this.toolCode = toolCode;
	}

	public String getToolBrand() {
		return toolBrand;
	}

	public void setToolBrand(String toolBrand) {
		this.toolBrand = toolBrand;
	}	
}
