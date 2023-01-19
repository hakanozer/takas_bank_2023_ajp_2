package com.works.services;

import com.works.props.Currency;
import com.works.utils.REnum;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class XmlService {

    public ResponseEntity xml() {
        List<Currency> ls = new ArrayList<>();
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String stData = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString();
            Document doc = Jsoup.parse(stData, Parser.xmlParser());
            Elements elements = doc.getElementsByTag("Currency");
            for(Element item : elements) {
                String currencyName = item.getElementsByTag("CurrencyName").text();
                String forexBuying = item.getElementsByTag("ForexBuying").text();
                String forexSelling = item.getElementsByTag("ForexSelling").text();
                Currency currency = new Currency(currencyName, forexBuying, forexSelling);
                ls.add(currency);
            }
        }catch (Exception ex) {
            System.err.println("Xml Error : " + ex);
        }
        hm.put(REnum.status, true);
        hm.put(REnum.result, ls);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
