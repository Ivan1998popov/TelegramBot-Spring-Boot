package com.telegram.springboot.ParsingDirectionOfSpecialties;

import com.telegram.springboot.Parsing_news.DataParseNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.awt.SunHints;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestParserSpecialties {

    public static List<List<String>> data = new ArrayList<>();
    public static List<DataParsingSpecialties> dataParsingSpecialties = new ArrayList<>();
    public static List<DataParseNews> dataParses =new ArrayList<>();


    public TestParserSpecialties(){
        run();
    }

    public List<DataParsingSpecialties> getDataParsingSpecialties(){
        return dataParsingSpecialties;
    }

    public List<DataParseNews> getDataParses(){
        return dataParses;
    }

    void run(){


        List<String> content =new ArrayList<>();
        List<String> url =new ArrayList<>();
        List<String> name =new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect("http://priem.tti.sfedu.ru/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements liElements = doc.getElementsByAttributeValue("class","mod-articles-category-title ");

        Elements pElements=doc.getElementsByAttributeValue("class","mod-articles-category-introtext");
        liElements.forEach(liElement->{
            Element aElement=liElement;
            url.add(aElement.attr("href"));
            name.add(aElement.text());
        });

        pElements.forEach(pElement->{
            Element aElement =pElement;
            content.add(aElement.text());

        });

        for (int j = 0; j <content.size() ; j++) {
            dataParses.add(new DataParseNews(url.get(j),name.get(j),content.get(j)));
        }

        dataParses.forEach(System.out::println);


        //////////////////////////////////////////////////////////////////////////////////////////////////////////

        Document docsp = null;
        try {
            docsp = Jsoup.connect("http://priem.tti.sfedu.ru/index.php/kcp/bakalavriat-specialitet").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count=0;
        data.clear();
        for (Element table : docsp.select("table")) {
            for (Element row : table.select("tr")) {
                try {
                    count++;
                    if (row.select("td").eachText() != null&&count>=4&&count!=29&&count!=27)

                        data.add(row.select("td").eachText());

                } catch (Exception e) {
                    continue;
                }
            }
        }
        String id;
        dataParsingSpecialties.clear();
        for (int i = 3; i < data.size(); i++) {
            if(i==26||i==28){}else {
                if(data.get(i).get(0).charAt(0)=='*') {
                    id = data.get(i).get(0).substring(0, 9);
                }
                else {
                    id=data.get(i).get(0).substring(0,8);
                }
                dataParsingSpecialties.add(new DataParsingSpecialties(id,data.get(i).get(0), data.get(i).get(6),
                        data.get(i).get(7), data.get(i).get(10)));
            }
        }
       dataParsingSpecialties.forEach(System.out::println);
    }



}

