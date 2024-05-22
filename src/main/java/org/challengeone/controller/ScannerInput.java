package org.challengeone.controller;

import org.challengeone.models.API_Response;
import org.challengeone.models.CurrencyInfo;

import java.util.*;
import java.util.function.Function;

public class ScannerInput {

    private static final Scanner input = new Scanner(System.in);
    private static String next;
    private static int option;
    private static CurrencyInfo baseCurrency;
    private static CurrencyInfo targetCurrency;
    private static double amount;

    public static void begin() {
        intro();
        start();
    }

    private static void intro() {
        System.out.println("**************************************************");
        System.out.println("*            CURRENCY CONVERTER v1.0             *");
        System.out.println("**************************************************");
    }

    private static void start() {
        showOptions("Select:",
                Arrays.asList("Do a conversion", "List all currencies", "Exit application"),
                "¡¡¡ SELECT ONE OF THE FOLLOWING OPTIONS !!!");
        switch (option) {
            case 1:
                typeBase();
                break;
            case 2:
                listAllCurrencies();
                start();
                break;
            case 3:
                break;
        }
    }

    private static void listAllCurrencies() {
        CurrencyInfo.CURRENCY_LIST.forEach(currencyInfo -> System.out.println(currencyInfo.toStringSpaced()));
    }

    private static void typeBaseOrTarget(boolean baseOrTarget) {
        typeWithCondition("Type the code, name or country of the currency to be the " +
                        (baseOrTarget ? "BASE" : "TARGET") + " (min. 3 characters)",
                (text) -> text.length() >= 3,
                "¡¡¡ MUST BE AT LEAST 3 CHARACTERS !!!");
        List<CurrencyInfo> result = CurrencyInfo.findCurrencies(next);
        if (!result.isEmpty()) {
            int selectedOption = 1;
            if (result.size() > 1) {
                List<String> options = new ArrayList<>(result.stream().map(CurrencyInfo::toStringSpaced).toList());
                options.add("Try again");
                showOptions("Several results were found, select one:",
                        options,
                        "¡¡¡ SELECT ONE AMONG THE FOLLOWING OPTIONS !!!");
                selectedOption = option;
                if (option == options.size()) {
                    typeBaseOrTarget(baseOrTarget);
                    return;
                }
            }
            showOptions("You select:\r\n> " + result.get(selectedOption - 1),
                    Arrays.asList("Confirm", "Try again"),
                    "¡¡¡ SELECT ONE OF THE FOLLOWING OPTIONS !!!");
            switch (next) {
                case "1":
                    if (baseOrTarget) {
                        baseCurrency = result.get(selectedOption - 1);
                        typeTarget();
                    } else {
                        targetCurrency = result.get(selectedOption - 1);
                        typeAmount();
                    }
                    break;
                case "2":
                    typeBaseOrTarget(baseOrTarget);
                    break;
            }
        } else {
            System.out.println("No result was found for: '" + next + "'");
            typeBaseOrTarget(baseOrTarget);
        }
    }

    private static void typeBase() {
        typeBaseOrTarget(true);
    }

    private static void typeTarget() {
        typeBaseOrTarget(false);
    }

    private static void typeAmount() {
        typeWithCondition("Type the AMOUNT of the currency to be converted",
                (text) -> {
                    try {
                        Double.parseDouble(text);
                        return true;
                    }
                    catch (NumberFormatException ignored) {
                    }
                    return false;
                },
                "¡¡¡ MUST BE A VALID DECIMAL NUMBER !!!");
        amount = Double.parseDouble(next);
        showResultOfConversion();
    }

    private static void showResultOfConversion() {
        API_Response response = API_Response.doConversion(baseCurrency, targetCurrency, amount);
        String titleMessage;
        if (!response.result().equalsIgnoreCase("error")) {
            System.out.println();
            System.out.println("CURRENCY CONVERSION:");
            System.out.println("Base Currency  =" + baseCurrency);
            System.out.println("Target Currency=" + targetCurrency);
            System.out.println("Exchange rate  =" + response.conversion_rate());
            System.out.println("FINAL RESULT   =" + response.conversion_result());
            titleMessage = "Thanks for using CURRENCY CONVERTER v1.0, what do you want to do now?";
        } else {
            titleMessage = "An error occurred while trying to do the conversion:\r\n> " + response.errorType();
        }
        showOptions(titleMessage,
                Arrays.asList("Start again", "List all currencies", "Exit application"),
                "¡¡¡ SELECT ONE OF THE FOLLOWING OPTIONS !!!");
        switch (option) {
            case 1:
                typeBase();
                break;
            case 2:
                listAllCurrencies();
                start();
                break;
            case 3:
                break;
        }
    }

    private static void showOptions(String titleMessage, List<String> options, String errorMessage) {
        boolean showError = false;
        option = 0;
        do {
            System.out.println();
            if (showError) {
                System.out.println(errorMessage);
            }
            System.out.println(titleMessage);
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i+1) + ".\t" + options.get(i));
            }
            next = input.nextLine().trim();
            try {
                option = Integer.parseInt(next);
            } catch (NumberFormatException ignored) {
            }
            showError = true;
        } while (option == 0 || option > options.size());
    }

    private static void typeWithCondition(String titleMessage, Function<String, Boolean> condition, String errorMessage) {
        boolean showError = false;
        do {
            System.out.println();
            if (showError) {
                System.out.println(errorMessage);
            }
            System.out.println(titleMessage);
            next = input.nextLine().trim();
            showError = true;
        } while (!condition.apply(next));
    }
}
