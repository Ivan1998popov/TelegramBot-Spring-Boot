package com.telegram.springboot.ParsingDirectionOfSpecialties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.awt.SunHints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestParserSpecialties {

    public static void main(String[] args) throws IOException {

        List<List<String>> data = new ArrayList<>();
        List<DataParsingSpecialties> dataParsingSpecialties = new ArrayList<>();

        Document doc = Jsoup.connect("http://priem.tti.sfedu.ru/index.php/kcp/bakalavriat-specialitet").get();

        int count=0;
        for (Element table : doc.select("table")) {
            for (Element row : table.select("tr")) {
                try {
                    count++;
                    if (row.select("td").eachText() != null&&count>=4&&count!=29&&count!=27)

                        data.add(row.select("td").eachText());

                   // System.out.println(row.select("td").eachText());

                } catch (Exception e) {
                    continue;
                }
            }
        }

        for (int i = 3; i < data.size(); i++) {
            if(i==26||i==28){}else {
                dataParsingSpecialties.add(new DataParsingSpecialties(data.get(i).get(0), data.get(i).get(6),
                        data.get(i).get(7), data.get(i).get(10)));
            }
           // System.out.println(data.get(i).get(0) + "  "+data.get(i).get(6)+" "+data.get(i).get(7)+" "+ data.get(i).get(10));}
            //System.out.println(data.get(i).get(7));
        }
       dataParsingSpecialties.forEach(System.out::println);
    }
}

