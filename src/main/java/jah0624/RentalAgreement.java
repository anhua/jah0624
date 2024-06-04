package jah0624;

public class RentalAgreement {
	private Tool tool;
	private int rentalDays;
	private String checkoutDate;
	private String dueDate;
	private int chargeDays;
	private float preDiscountCharge;
	private float discountPercent;
	private float discountAmount;
	private float finalCharge;

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public String getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(String checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public int getChargeDays() {
		return chargeDays;
	}

	public void setChargeDays(int chargeDays) {
		this.chargeDays = chargeDays;
	}

	public float getPreDiscountCharge() {
		return preDiscountCharge;
	}

	public void setPreDiscountCharge(float preDiscountCharge) {
		this.preDiscountCharge = preDiscountCharge;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public float getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(float discountAmount) {
		this.discountAmount = discountAmount;
	}

	public float getFinalCharge() {
		return finalCharge;
	}

	public void setFinalCharge(float finalCharge) {
		this.finalCharge = finalCharge;
	}

	public void printAgreement() {
		System.out.println("\n----------------------------");
		System.out.println("     Rental Agreement\n");
		System.out.println("Tool code: " + tool.getToolCode());
		System.out.println("Tool type: " + tool.getToolType());
		System.out.println("Tool brand: " + tool.getToolBrand());
		System.out.println("Rental days: " + rentalDays);
		System.out.println("Checkout Date: " + checkoutDate);
		System.out.println("Due date: " + dueDate);
		System.out.println("Daily rental charge: " + tool.getDailyCharge());
		System.out.println("Charge days: " + chargeDays);
		System.out.printf("Pre-discount charge: %.2f\n", this.preDiscountCharge);
		System.out.println("Discount percent: " + discountPercent);
		System.out.printf("Discount amount: %.2f\n", this.discountAmount);
		System.out.printf("Final charge: %.2f\n" , this.finalCharge);
		System.out.println("----------------------------\n");
	}
}
