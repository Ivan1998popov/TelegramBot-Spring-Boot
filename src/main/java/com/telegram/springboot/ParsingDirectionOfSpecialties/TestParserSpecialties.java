package com.telegram.springboot.ParsingDirectionOfSpecialties;

import com.telegram.springboot.Parsing_news.DataParseNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestParserSpecialties {

    public static void main(String[] args) throws IOException {


        Document doc = Jsoup.connect("http://priem.tti.sfedu.ru/index.php/kcp/bakalavriat-specialitet").get();
        Elements trElements = doc.getElementsByAttributeValue("class","priemtable");
        Elements rows=trElements.select("tr");


        for (Element table:doc.select("table")) {
            for (Element row:table.select("tr")) {
                Elements tds=row.select("td");
                System.out.println(tds.get(0).text());
               // if(!row.select("td").text().equals(row.select("td.kcp").text()))
                //System.out.println(row.select("td").text());
            }

        }



//        trElements.forEach(trElement->{
//            Element aElement=trElement;
//            System.out.println(aElement.attr("href"));
//            System.out.println(aElement.text());
//        });

//        pElements.forEach(pElement->{
//            Element aElement =pElement;
//            content.add(aElement.text());
//
//        });

    }

}
