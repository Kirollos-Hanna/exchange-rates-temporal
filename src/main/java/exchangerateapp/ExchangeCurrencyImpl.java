package exchangerateapp;

import org.json.simple.JSONObject;

public class ExchangeCurrencyImpl implements ExchangeCurrency{

    private final String[] currencySymbols = new String[]{"USD", "GBP", "EUR"};

    @Override
    public String convertEGPToForeign(JSONObject currencies) {
        StringBuilder EGPToForeign = new StringBuilder();
        for (String currency : currencySymbols) {
            EGPToForeign
                    .append("EGP to ")
                    .append(currency)
                    .append(": ")
                    .append(String.format("%.3f", (double) currencies.get(currency)))
                    .append(" ");
        }

        return EGPToForeign.toString();
    }

    @Override
    public String convertForeignToEGP(JSONObject currencies){
        StringBuilder ForeignToEGP = new StringBuilder();
        for (String currency : currencySymbols) {
            ForeignToEGP
                    .append(currency)
                    .append(" to EGP")
                    .append(": ")
                    .append(String.format("%.3f", 1 / (double) currencies.get(currency)))
                    .append(" ");
        }

        return ForeignToEGP.toString();
    }
}
