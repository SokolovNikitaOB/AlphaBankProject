package main.CurrencyConversion;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-conversion", url = "${currencyconversion.url}")
public interface CurrencyConversionProxy {
    @GetMapping("/latest.json?app_id=${currencyconversion.api_id}&base={from}")
    public CurrencyConversionBean newExchangeValue(@PathVariable(name = "from") String from);

    @GetMapping("/historical/{date}.json?app_id=${currencyconversion.api_id}&base={from}")
    public CurrencyConversionBean oldExchangeValue(@PathVariable(name = "from") String from, @PathVariable(name = "date") String date);

}
