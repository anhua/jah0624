package jah0624;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {
	private List<Tool> tools;
	private final String dateFormat = "M/d/yy";

	public Shop() {
		// init the tools, hard coded values for demo purpose
		tools = new ArrayList<Tool>();
		tools.add(new Tool("Chainsaw", "CHNS", "Stihl", 1.49f, true, false, true));
		tools.add(new Tool("Ladder", "LADW", "Werner", 1.99f, true, true, false));
		tools.add(new Tool("Jackhammer", "JAKD", "DeWalt", 2.99f, true, false, false));
		tools.add(new Tool("Jackhammer", "JAKR", "Ridgid", 2.99f, true, false, false));
	}

	private LocalDate getIndependenceDay(LocalDate checkoutDate) {
		LocalDate holiday = checkoutDate.withMonth(7).withDayOfMonth(4);
		DayOfWeek dayOfWeek = holiday.getDayOfWeek();
		if (dayOfWeek == DayOfWeek.SATURDAY)
			return holiday.minusDays(1);
		else if (dayOfWeek == DayOfWeek.SUNDAY)
			return holiday.plusDays(1);
		return holiday;
	}

	private LocalDate getLaborDay(LocalDate checkoutDate) {
		LocalDate holiday = checkoutDate.withMonth(9).withDayOfMonth(1);
		DayOfWeek dayOfWeek = holiday.getDayOfWeek();

		if (dayOfWeek == DayOfWeek.MONDAY)
			return holiday;
		else
			return holiday.plusDays(8 - dayOfWeek.getValue());
	}

	private int getWeekendDaysForRental(LocalDate checkoutDate, int days) {
		DayOfWeek dayOfWeek = checkoutDate.getDayOfWeek();
		int weekendDays = 0;
		int weeks = days / 7;
		weekendDays += weeks * 2;
		days = days - weeks * 7;
		// days=0 to 6
		if (days > 0) {
			int d = dayOfWeek.getValue() + days;// 2-13

			if (dayOfWeek.getValue() <= 5)// 2-11
				weekendDays += d / 6 + d / 7;
			else if (dayOfWeek.getValue() == 6)// 7-12, Sunday to Friday
				weekendDays += d / 7;
			else if (dayOfWeek.getValue() == 7)// 8-13, Monday to Saturday
				weekendDays += d / 13;
		}
		return weekendDays;
	}

	public RentalAgreement checkOut(String code, int days, float discount, String date) throws Exception {
		RentalAgreement agreement = new RentalAgreement();
		if (days < 1) {
			throw new Exception("The rental needs to be at least one day.");
		}
		if (discount < 0 || discount > 100) {
			throw new Exception("The allowed discount needs to be 0-100");
		}

		for (int i = 0; i < tools.size(); i++) {
			if (tools.get(i).getToolCode().compareTo(code) == 0) {
				LocalDate checkOutDate;
				try {
					checkOutDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
				} catch (DateTimeParseException e) {
					System.out.println("The date format should be 'mm/dd/yy', pls check your input: " + date);
					return null;
				}
				LocalDate dueDate = checkOutDate.plusDays(days);
				// Get charge days
				int chargeDays = rentalChargeDays(tools.get(i), checkOutDate, dueDate, days);
				if (chargeDays > 0) {
					// prepare the agreement even if the charge days = 0.
					agreement.setChargeDays(chargeDays);
					agreement.setTool(tools.get(i));
					agreement.setCheckoutDate(date);
					agreement.setDiscountPercent(discount);
					agreement.setDiscountAmount(
							Math.round(tools.get(i).getDailyCharge() * chargeDays * discount) / 100.0f);
					agreement.setDueDate(dueDate.format(DateTimeFormatter.ofPattern(dateFormat)));
					agreement.setRentalDays(days);
					agreement.setPreDiscountCharge(chargeDays * tools.get(i).getDailyCharge());
					agreement.setFinalCharge(agreement.getPreDiscountCharge() - agreement.getDiscountAmount());
					return agreement;
				} else {
					System.out.println("Please add few more days and try again.");
					return null;
				}
			}
		}
		System.out.println(
				"The toole code : '" + code + "' you provided is not in the list. Please check and try again.");
		return null;
	}

	private int rentalChargeDays(Tool tool, LocalDate checkOutDate, LocalDate dueDate, int days) {
		// get holidays
		LocalDate independenceDay = this.getIndependenceDay(checkOutDate);
		LocalDate laborDay = this.getLaborDay(checkOutDate);

		// Since weekday is always charged, no need to check it.
		// calculate charge days according to tool type, weekend days and holidays
		int chargeDays = days;
		if (!tool.isWeekendDayCharged()) {
			chargeDays -= this.getWeekendDaysForRental(checkOutDate, days);
		}
		if (!tool.isHoliDayCharged()) {
			if (independenceDay.equals(dueDate)
					|| (independenceDay.isAfter(checkOutDate) && independenceDay.isBefore(dueDate)))
				chargeDays = chargeDays - 1;

			if (laborDay.equals(dueDate) || (laborDay.isAfter(checkOutDate) && laborDay.isBefore(dueDate)))
				chargeDays = chargeDays - 1;
		}
		return chargeDays;
	}

	public static void main(String[] args) {
		Shop shop = new Shop();
		while (true) {
			System.out
					.println("Type 'code rentalDays discountPercent checkoutDate' then enter to check the agreement.");
			System.out.println("Enter without any other keys to quit the application\n");
			Scanner in = new Scanner(System.in);

			String s = in.nextLine();
			String[] array = s.split(" ");
			if (array.length == 4) {
				RentalAgreement agreement;
				try {
					agreement = shop.checkOut(array[0], Integer.parseInt(array[1]), Float.parseFloat(array[2]),
							array[3]);
					if (agreement != null)
						agreement.printAgreement();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("Good Bye.");
				return;
			}
		}
	}
}
