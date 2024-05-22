package org.challengeone.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    @Override
    public String toString() {
        return "CurrencyInfo{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
