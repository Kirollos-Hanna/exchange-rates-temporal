package exchangerateapp;

import org.json.simple.JSONObject;

public class ExchangeCurrencyImpl implements ExchangeCurrency{

    @Override
    public String convertEGPToForeign(JSONObject currencies) {
        double GBPVal = (double) currencies.get("GBP");
        double USDVal = (double) currencies.get("USD");
        double EURVal = (double) currencies.get("EUR");

        return "EGP to USD: " + String.format("%.3f", USDVal)
                + " EGP to GBP: " + String.format("%.3f", GBPVal)
                + " EGP to EUR: " + String.format("%.3f", EURVal);
    }

    @Override
    public String convertForeignToEGP(JSONObject currencies){
//        int EGPVal = (int) currencies.get("EGP");
        double GBPVal = 1 / (double) currencies.get("GBP");
        double USDVal = 1 / (double) currencies.get("USD");
        double EURVal = 1 / (double) currencies.get("EUR");

        return "USD to EGP: " + String.format("%.3f", USDVal)
                + " GBP to EGP: " + String.format("%.3f", GBPVal)
                + " EUR to EGP: " + String.format("%.3f", EURVal);
    }
}
