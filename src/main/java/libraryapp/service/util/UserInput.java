package libraryapp.service.util;

import java.util.Scanner;

/**
 * AIT-TR, cohort 42.1, Java Basic, Project1
 *
 * @author Anton Gorbovyi
 * @version 22-Apr-24
 */

public class UserInput {

    private static final Scanner scanner = new Scanner(System.in);

    public static String getText(String message) {

        String val = "";
        System.out.print(message);

        do {
            val = scanner.nextLine();

            if ( val.length() < 2 )
            {
                System.out.println("Error: empty or too short value");
                val = "";
            }

        } while (val.isEmpty());

        return val;
    }

    public static Integer getInt(String message) {

        int val = -1;
        System.out.print(message);

        do {
            val = scanner.nextInt();

            if ( val < 0 )
            {
                System.out.println("Error: invalid value");
            }

        } while ( val < 0 );

        return val;
    }
}



