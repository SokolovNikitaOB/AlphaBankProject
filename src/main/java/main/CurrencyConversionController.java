package main;

import main.CurrencyConversion.CurrencyConversionBean;
import main.CurrencyConversion.CurrencyConversionProxy;
import main.Giphy.GiphyBean;
import main.Giphy.GiphyProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionProxy proxy;

    @Autowired
    private GiphyProxy giphyProxy;

    /**
     * @param from базовая валюта
     * @param to валюта, чье изменение мы смотрим
     * @return страницу index.html
     */
    @GetMapping("/currency-converter/from/{from}/to/{to}")
    public String convertCurrency(@PathVariable String from, @PathVariable String to, Model model){

        CurrencyConversionBean newResponse = proxy.newExchangeValue(from);
        CurrencyConversionBean oldResponse = proxy.oldExchangeValue(from,getOldDate());
        //Изменение курса валют
        double difference = newResponse.getRates().get(to) - oldResponse.getRates().get(to);

        //Случайно число для выбора случайной gif
        int randomNumber = (int)(Math.random() * 4999);

        GiphyBean giphyBean;

        if(difference >= 0){
            giphyBean = giphyProxy.richGiphy(randomNumber);
        }else {
            giphyBean = giphyProxy.brokeGiphy(randomNumber);
        }

        model.addAttribute("gif", giphyBean.getData().get(0).getImages().getOriginal().getUrl());
        model.addAttribute("difference", difference);

        return "index";

    }

    /**
     * Этот метод предназначен для получения даты позавчерашнего дня.
     * Причина выбора даты не вчерашнего дня заключается в том, что
     * актуальные json файл валют может совпадать с json файлом валют
     * вчерашнего дня.
     */
    public static String getOldDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -2);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(cal.getTime());
    }
}
