package pro.x_way;


import pro.x_way.Service.NdsRequest2;
import pro.x_way.Service.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Main {


    public static final String ERROR_DATA = " - ошибка введенных данных";

    public static void main(String[] args) {

        UserInterface.printWelcome();

        //Создаем запрос
        ObjectFactory objectFactory = new ObjectFactory();
        NdsRequest2 ndsRequest2 = objectFactory.createNdsRequest2();

        // Проверяем параметры, если их нет то запускаем интерфейс
        if (args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].length() == 10) {
                    NdsRequest2.NP np = new NdsRequest2.NP();
                    np.setINN(args[i]);
                    np.setDT(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                    if (i + 1 < args.length) { // Если следующее значение существует и оно КПП
                        if (args[i + 1].length() == 9) {
                            np.setKPP(args[i + 1]);
                        }
                    }
                    ndsRequest2.getNP().add(np);
                } else if (args[i].length() != 9)
                    System.out.println(args[i] + ERROR_DATA);
            }

            if (ndsRequest2.getNP().isEmpty()) UserInterface.loadInterface(ndsRequest2);
        } else UserInterface.loadInterface(ndsRequest2);

        //Подготавливаем запрос
        FNSNDSCAWS2 fnsndscaws2 = new FNSNDSCAWS2();
        FNSNDSCAWS2Port fnsndscaws2Port = npIn -> fnsndscaws2.getFNSNDSCAWS2Port().ndsRequest2(npIn);


        //Отправляем запрос и получаем результат
        ResponseCode responseCode = new LegalPersonality();
        for (NdsResponse2.NP np1 : fnsndscaws2Port.ndsRequest2(ndsRequest2).getNP()) {
            UserInterface.printLine();
            if (np1.getKPP() == null) {
                System.out.println("| INN - " + np1.getINN() + "\n| Date - "
                        + np1.getDT() + " \n| Result (cod:" + np1.getState()+") - "
                        + responseCode.findStatus(np1.getState()));
            } else System.out.println("| INN - " + np1.getINN() + "\n| KPP - "
                    + np1.getKPP() + "\n| Date - "
                    + np1.getDT() + "\n| Result (cod:" + np1.getState()+") - "
                    + responseCode.findStatus(np1.getState()));
            UserInterface.printLine();
        }
    }

}
