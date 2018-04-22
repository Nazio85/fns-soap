package pro.x_way;

public class LegalPersonality implements ResponseCode {
    private static final String RESPONSE_0 = "Налогоплательщик зарегистрирован в ЕГРН и имел статус действующего в указанную дату";
    private static final String RESPONSE_1 = "Налогоплательщик зарегистрирован в ЕГРН, но не имел статус действующего в указанную дату";
    private static final String RESPONSE_2 = "Налогоплательщик зарегистрирован в ЕГРН";
    private static final String RESPONSE_3 = "Налогоплательщик с указанным ИНН зарегистрирован в ЕГРН, КПП не соответствует ИНН или не указан";
    private static final String RESPONSE_4 = "Налогоплательщик с указанным ИНН не зарегистрирован в ЕГРН";
    private static final String RESPONSE_5 = "Некорректный ИНН";
    private static final String RESPONSE_6 = "Недопустимое количество символов ИНН";
    private static final String RESPONSE_7 = "Недопустимое количество символов КПП";
    private static final String RESPONSE_8 = "Недопустимые символы в ИНН";
    private static final String RESPONSE_9 = "Недопустимые символы в КПП";
    private static final String RESPONSE_11 = "некорректный формат даты";
    private static final String RESPONSE_12 = "некорректная дата (ранее 01.01.1991 или позднее текущей даты)";
    private static final String ERROR = "Пожалуйста обратитесь к поставщику приложения";


    @Override
    public String findStatus(String code) {
        switch (code) {
            case "0":
                return RESPONSE_0;
            case "1":
                return RESPONSE_1;
            case "2":
                return RESPONSE_2;
            case "3":
                return RESPONSE_3;
            case "4":
                return RESPONSE_4;
            case "5":
                return RESPONSE_5;
            case "6":
                return RESPONSE_6;
            case "7":
                return RESPONSE_7;
            case "8":
                return RESPONSE_8;
            case "9":
                return RESPONSE_9;
            case "11":
                return RESPONSE_11;
            case "12":
                return RESPONSE_12;
            default:
                return ERROR;
        }
    }
}
