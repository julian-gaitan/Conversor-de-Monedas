package org.challengeone.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public record CurrencyInfo(String code, String name, String country) {

    public static final List<CurrencyInfo> CURRENCY_LIST = initCurrencies();

    private static List<CurrencyInfo> initCurrencies() {
        List<CurrencyInfo> currencyList = new ArrayList<>();
        try {
            File myFile = new File("Currencies Info");
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String[] currencyText = myReader.nextLine().split("\t");
                currencyList.add(new CurrencyInfo(currencyText[0], currencyText[1], currencyText[2]));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return currencyList;
    }

    private String toStringWithSpace(int space) {
        String first = "code:'" + code + "', " +
                       "currency:'" + name + "', ";
        return first + " ".repeat(Math.max(space - first.length(), 0)) + "country:'" + country + "'";
    }

    @Override
    public String toString() {
        return toStringWithSpace(0);
    }

    public String toStringSpaced() {
        final int maxLengthFirst = 54;
        return toStringWithSpace(maxLengthFirst);
    }

    public static List<CurrencyInfo> findCurrencies(String value) {
        List<CurrencyInfo> currencies = new ArrayList<>();
        if (value != null && !value.isBlank()) {
            if (value.length() == 3) {
                CURRENCY_LIST.stream().filter(c -> c.code.equalsIgnoreCase(value)).findFirst().ifPresent(currencies::add);
            }
            if (currencies.isEmpty()) {
                currencies = CURRENCY_LIST.stream().filter(c ->
                                c.name.toLowerCase().contains(value.toLowerCase()) || c.country.toLowerCase().contains(value.toLowerCase())
                        ).collect(Collectors.toList());
            }
        }
        return currencies;
    }
}
