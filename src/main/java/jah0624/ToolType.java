package jah0624;

public class ToolType {
	private String toolType;

	public String getToolType() {
		return toolType;
	}

	public void setToolType(String toolType) {
		this.toolType = toolType;
	}

	private boolean isWeekDayCharged;
	private boolean isWeekendDayCharged;
	private boolean isHoliDayCharged;
	private float dailyCharge;

	public ToolType(String type) {
		this.toolType = type;
	}

	public ToolType(String type, float dailyCharge, boolean weekDayCharged, boolean weekenDayCharged,
			boolean holidayCharged) {
		this.toolType = type;
		this.dailyCharge = dailyCharge;
		this.isWeekDayCharged = weekDayCharged;
		this.isHoliDayCharged = holidayCharged;
		this.isWeekendDayCharged = weekenDayCharged;
	}

	public float getDailyCharge() {
		return dailyCharge;
	}

	public void setDailyCharge(float dailyCharge) {
		this.dailyCharge = dailyCharge;
	}

	public boolean isWeekDayCharged() {
		return isWeekDayCharged;
	}

	public void setWeekDayCharged(boolean isWeekDayCharged) {
		this.isWeekDayCharged = isWeekDayCharged;
	}

	public boolean isWeekendDayCharged() {
		return isWeekendDayCharged;
	}

	public void setWeekendDayCharged(boolean isWeekendDayCharged) {
		this.isWeekendDayCharged = isWeekendDayCharged;
	}

	public boolean isHoliDayCharged() {
		return isHoliDayCharged;
	}

	public void setHoliDayCharged(boolean isHoliDayCharged) {
		this.isHoliDayCharged = isHoliDayCharged;
	}
}
