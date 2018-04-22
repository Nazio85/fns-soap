package pro.x_way;

import pro.x_way.Service.NdsRequest2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserInterface {
    public static void printLine() {
        System.out.println(" -------------------------------------");
    }

    public static void printWelcome() {
        printLine();
        System.out.println("|  Web-сервис \"Проверка контрагента\"  |");
        printLine();
    }

    private static int stringToInt(String string) {
        int result;
        try {
            result = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }

    private static long stringToLong(String string) {
        long result;
        try {
            result = Long.parseLong(string);
        } catch (NumberFormatException e) {
            result = -1;
        }
        return result;
    }

    public static void loadInterface(NdsRequest2 ndsRequest2) {
        BufferedReader bufferedReader = null;
        try {

            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            int choice;

            do {
                System.out.println();
                System.out.println("Выберите пункт меню:");
                System.out.println("1 - Проверить по ИНН");
                System.out.println("2 - Проверить по ИНН и КПП");
                System.out.println("3 - Test");
                System.out.println("4 - Выход");
                choice = stringToInt(bufferedReader.readLine());

            } while (choice < 1 | choice > 4);

            long inn = 0;
            long kpp = 0;
            switch (choice) {
                case 1:
                    inn = readINN(bufferedReader, inn);
                    checkINN(ndsRequest2, inn, 0);
                    break;
                case 2:
                    inn = readINN(bufferedReader, inn);
                    kpp = readKPP(bufferedReader, kpp);
                    checkINN(ndsRequest2, inn, kpp);
                    break;
                case 3:
                    test(ndsRequest2);
                    break;
                case 4:
                    System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static long readINN(BufferedReader bufferedReader, long inn) throws IOException {
        do {
            System.out.println();
            System.out.println("Введите ИНН или 0 для выхода:");
            if (inn == -1) System.out.println("Некорректный ИНН");
            inn = stringToLong(bufferedReader.readLine());
            if (inn == 0) System.exit(0);
        } while (inn > 9999999999L | inn <= 999999999L);
        return inn;
    }

    private static long readKPP(BufferedReader bufferedReader, long kpp) throws IOException {
        do {
            System.out.println();
            System.out.println("Введите КПП или 0 для выхода:");
            if (kpp == -1) System.out.println("Некорректный КПП");
            kpp = stringToLong(bufferedReader.readLine());
            if (kpp == 0) System.exit(0);
        } while (kpp > 999999999L | kpp <= 99999999L);
        return kpp;
    }

    private static void checkINN(NdsRequest2 ndsRequest2, long inn, long kpp) {
        NdsRequest2.NP np = new NdsRequest2.NP();
        np.setINN(String.valueOf(inn));
        if (kpp != 0) np.setKPP(String.valueOf(kpp));
        np.setDT(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        ndsRequest2.getNP().add(np);
    }

    private static void test(NdsRequest2 ndsRequest2) {
        NdsRequest2.NP np = new NdsRequest2.NP();
        np.setINN("2723192097");
        np.setDT(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        ndsRequest2.getNP().add(np);

        NdsRequest2.NP np2 = new NdsRequest2.NP();
        np2.setINN("2723192097");
        np.setKPP("272301001");
        np2.setDT(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        ndsRequest2.getNP().add(np2);
    }
}
