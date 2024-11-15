import java.util.Scanner;

public class AgeDOBCalculator {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter DOB or AGE (e.g., DOB=28-02-2001 or AGE=19-10-27): ");
        String input = scanner.nextLine();
        
        System.out.print("Enter the reference date or type 'today' for the current date (e.g., 27-02-2024): ");
        String refDateInput = scanner.nextLine();
      
        System.out.print("Enter the date format (e.g., DDdlcMMdlcYYYY): ");
        String dateFormat = scanner.nextLine();
    
        System.out.print("Enter the delimiter character (e.g., -): ");
        String delimiter = scanner.nextLine();

        try {
        
            if (input.startsWith("DOB=")) {
                
                String dobStr = input.split("=")[1];
                String[] dobParts = parseDate(dobStr, delimiter, dateFormat);
                int dobDay = Integer.parseInt(dobParts[0]);
                int dobMonth = Integer.parseInt(dobParts[1]);
                int dobYear = Integer.parseInt(dobParts[2]);

                String[] refParts = parseDate(refDateInput, delimiter, dateFormat);
                int refDay = Integer.parseInt(refParts[0]);
                int refMonth = Integer.parseInt(refParts[1]);
                int refYear = Integer.parseInt(refParts[2]);

                int ageYears = refYear - dobYear;
                int ageMonths = refMonth - dobMonth;
                int ageDays = refDay - dobDay;

                if (ageDays < 0) {
                    ageMonths--;
                    ageDays += getDaysInMonth(refMonth - 1, refYear);
                }

                if (ageMonths < 0) {
                    ageYears--;
                    ageMonths += 12;
                }

                System.out.println("Age is " + ageYears + " years, " + ageMonths + " months, " + ageDays + " days");
            } 
            else if (input.startsWith("AGE=")) {
                
                String ageStr = input.split("=")[1];
                String[] ageParts = parseDate(ageStr, delimiter, dateFormat);
                int ageYears = Integer.parseInt(ageParts[0]);
                int ageMonths = Integer.parseInt(ageParts[1]);
                int ageDays = Integer.parseInt(ageParts[2]);

                String[] refParts = parseDate(refDateInput, delimiter, dateFormat);
                int refDay = Integer.parseInt(refParts[0]);
                int refMonth = Integer.parseInt(refParts[1]);
                int refYear = Integer.parseInt(refParts[2]);

                
                int dobYear = refYear - ageYears;
                int dobMonth = refMonth - ageMonths;
                int dobDay = refDay - ageDays;

                if (dobDay <= 0) {
                    dobMonth--;
                    dobDay += getDaysInMonth(refMonth - 1, refYear);
                }

                if (dobMonth <= 0) {
                    dobYear--;
                    dobMonth += 12;
                }

                
                System.out.println("DOB is " + dobDay + delimiter + dobMonth + delimiter + dobYear);
            } 
            else {
                System.out.println("Invalid input format. Please use DOB= or AGE=.");
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid date provided. Please check the DOB or reference date format.");
        }

        scanner.close();
    }

    
    static String[] parseDate(String dateStr, String delimiter, String dateFormat) {
        String[] parts = null;

        if (dateFormat.equals("DDdlcMMdlcYYYY")) {
            
            parts = dateStr.split(delimiter);
        } else if (dateFormat.equals("YYYYdlcMMdlcDD")) {
            
            parts = new String[3];
            String[] splitParts = dateStr.split(delimiter);
            parts[0] = splitParts[2]; 
            parts[1] = splitParts[1]; 
            parts[2] = splitParts[0]; 
        } else if (dateFormat.equals("MMdlcDDdlcYYYY")) {
            
            parts = new String[3];
            String[] splitParts = dateStr.split(delimiter);
            parts[0] = splitParts[1]; 
            parts[1] = splitParts[0]; 
            parts[2] = splitParts[2]; 
        } else {
            System.out.println("Error: Invalid date format.");
        }

        return parts;
    }

    
    static int getDaysInMonth(int month, int year) {
        if (month <= 0) {
            return 0; 
        }

        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;  
            case 4: case 6: case 9: case 11:
                return 30;  
            case 2:
                
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;  
                } else {
                    return 28;  
                }
            default:
                return 0;  
        }
    }
}
