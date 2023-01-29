import za.co.sunesh.javapicli.Controller;
import za.co.sunesh.javapicli.utils.AddressType;

import java.util.Arrays;
import java.util.Scanner;

public class PiCliApplication implements Runnable {

    public static void printMenu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("\nChoose your option : ");
    }

    private static String[] options = {"1 - Calculate Highest Common Factor of 2 or more numbers ",
            "2 - Pretty print all addresses",
            "3 - Print address of type",
            "4 - Validate Addresses",
            "5 or any other number to Exit",
    };

    private void calculateHcf(Scanner scanner, Controller controller) {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
        System.out.println("Please enter a list of comma separated numbers with no spaces 1(eg. 1,10,3,50,20,5): ");
        int index = 0;
        String input = scanner.next();
        int[] numbers = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.format("The highest common factor is: %s%n%n%n%n", controller.highestCommonFactor(numbers));
    }

    private void printAddressesOfType(Scanner scanner, Controller controller) {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
        System.out.println("Please enter 1 - Physical Address or 2 - Business Address or 3 - Postal Address");
        int index = 0;
        String input = scanner.next();
        String addressType = AddressType.getDescriptionFromOption(Integer.parseInt(input));
        controller.printAddressOfType(addressType);
        System.out.format("%n%n%n%n");
    }

    public static void main(String... args) {
        PiCliApplication piCliApplication = new PiCliApplication();
        piCliApplication.run();
    }

    public void run() {
        Controller controller = new Controller();
        Scanner scanner = new Scanner(System.in);
        int option = 1;
        boolean endApplication = false;
        while (!endApplication){
            printMenu(options);
            option = scanner.nextInt();
            switch (option){
                case 1:
                    calculateHcf(scanner, controller);
                    break;
                case 2:
                    System.out.println(new String(new char[50]).replace("\0", "\r\n"));
                    controller.printAllAddressesFromJsonFile();
                    System.out.format("%n%n%n%n");
                    break;
                case 3:
                    printAddressesOfType(scanner, controller);
                    break;
                case 4:
                    System.out.println(new String(new char[50]).replace("\0", "\r\n"));
                    controller.validateJsonFileAddresses();
                    System.out.format("%n%n%n%n");
                    break;
                default:
                    endApplication = true;
                    break;
            }
        }
    }
}
