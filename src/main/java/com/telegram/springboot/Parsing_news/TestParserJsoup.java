package com.telegram.springboot.Parsing_news;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestParserJsoup {
    public static void main(String[] args) throws IOException {

        List<DataParse> dataParses =new ArrayList<>();
        List<String> content =new ArrayList<>();

        Document doc = Jsoup.connect("http://priem.tti.sfedu.ru/").get();
        Elements liElements = doc.getElementsByAttributeValue("class","mod-articles-category-title ");

        Elements pElements=doc.getElementsByAttributeValue("class","mod-articles-category-introtext");

        liElements.forEach(liElement->{
            Element aElement=liElement;
            String url=aElement.attr("href");
            String title = aElement.text();
            dataParses.add(new DataParse(url,title));
        });

        pElements.forEach(pElement->{
            Element aElement =pElement;
            content.add(aElement.text());

        });

        dataParses.forEach(System.out::println);
        content.forEach(System.out::println);
    }




}
