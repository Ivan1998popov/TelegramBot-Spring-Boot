package com.telegram.springboot.Parsing_news;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestParserNews {
    public static void main(String[] args) throws IOException {

        List<DataParseNews> dataParses =new ArrayList<>();
        List<String> content =new ArrayList<>();
        List<String> url =new ArrayList<>();
        List<String> name =new ArrayList<>();

        Document doc = Jsoup.connect("http://priem.tti.sfedu.ru/").get();
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

    }




}
