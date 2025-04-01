import org.fusesource.jansi.Ansi;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import static org.fusesource.jansi.Ansi.ansi;

class Date implements Comparable<Date> {
    int day;
    int month;
    int year;

    Date(int day, int month, int year) {
        if (isValidDate(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new IllegalArgumentException("Invalid date entered!");
        }
    }

    boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12 || day < 1 || day > 31) {
            return false;
        }
        int daysInMonth = daysInMonth(month, year);
        return day <= daysInMonth;
    }

    boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    int daysInMonth(int month, int year) {
        int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (month == 2 && isLeapYear(year)) {
            return 29;
        }
        return days[month - 1];
    }

    void updateDate(int d, int m, int y) {
        if (isValidDate(d, m, y)) {
            this.day = d;
            this.month = m;
            this.year = y;
        } else {
            System.out.println("Invalid date");
        }
    }

    String getDayOfWeek() {
        int d = this.day;
        int m = this.month;
        int y = this.year;

        if (m < 3) {
            m += 12;
            y--;
        }

        int k = y % 100;
        int j = y / 100;

        int dayOfWeek = (d + (13 * (m + 1)) / 5 + k + (k / 4) + (j / 4) + (5 * j)) % 7;
        String[] days = { "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };

        return days[dayOfWeek];
    }

    int calculateDifference(Date otherDate) {
        return Math.abs(toDays() - otherDate.toDays());
    }

    int toDays() {
        int days = this.year * 365 + this.day;

        for (int i = 1; i < this.month; i++) {
            days += daysInMonth(i, this.year);
        }

        for (int i = 1; i < this.year; i++) {
            if (isLeapYear(i)) {
                days++;
            }
        }

        return days;
    }

    void printDate() {
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        System.out.println(months[this.month - 1] + " " + this.day + ", " + this.year);
    }

    public int compareTo(Date other) {
        if (this.year != other.year) {
            return this.year - other.year;
        }
        if (this.month != other.month) {
            return this.month - other.month;
        }
        return this.day - other.day;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Date> dates = new ArrayList<>();

        System.out.println(ansi().fg(Ansi.Color.BLUE).a("Welcome!").reset());
        System.out.println(ansi().fg(Ansi.Color.CYAN).a("Enter the number of dates: ").reset());

        int n;
        while (true) {
            try {
                n = scanner.nextInt();
                if (n > 0) break;
                System.out.println(ansi().fg(Ansi.Color.RED).a("Please enter a positive number!").reset());
            } catch (Exception e) {
                System.out.println(ansi().fg(Ansi.Color.RED).a("Invalid input! Enter a valid number.").reset());
                scanner.next();
            }
        }

        for (int i = 0; i < n; i++) {
            while (true) {
                try {
                    System.out.println(ansi().fg(Ansi.Color.YELLOW).a("Enter day, month, year (separated by space): ").reset());
                    int day = scanner.nextInt();
                    int month = scanner.nextInt();
                    int year = scanner.nextInt();

                    dates.add(new Date(day, month, year));
                    break;
                } catch (Exception e) {
                    System.out.println(ansi().fg(Ansi.Color.RED).a("Invalid date! Try again.").reset());
                    scanner.nextLine();
                }
            }
        }

        System.out.println(ansi().fg(Ansi.Color.MAGENTA).a("\nEntered dates:").reset());
        for (Date d : dates) {
            d.printDate();
            System.out.println(ansi().fg(Ansi.Color.MAGENTA).a("Day of the week: " + d.getDayOfWeek()).reset());
        }

        Collections.sort(dates);
        System.out.println(ansi().fg(Ansi.Color.GREEN).a("\nSorted dates:").reset());
        for (Date d : dates) {
            d.printDate();
        }

        if (dates.size() > 1) {
            System.out.println(ansi().fg(Ansi.Color.GREEN).a("\nDifferences between all date combinations:").reset());
            for (int i = 0; i < dates.size(); i++) {
                for (int j = i + 1; j < dates.size(); j++) {
                    System.out.println(ansi().fg(Ansi.Color.GREEN).a("Difference between " + dates.get(i).day + "/" + dates.get(i).month + "/" + dates.get(i).year + " and " + dates.get(j).day + "/" + dates.get(j).month + "/" + dates.get(j).year + ": " + dates.get(i).calculateDifference(dates.get(j)) + " days").reset());
                }
            }
        }

        scanner.close();
    }
}
