package com.example.utils;

import org.openqa.selenium.WebElement;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class practiceFormulae {

    public String countOfAtoms(String formula) {

//        formulae=HNO || HeNi2O3
//        formulae=HNO3
//        formulae=H2O
//        formulae=K4(ON(SO3)2)2

        int cnt=0;
        Map<String, Integer> elementCnt = new HashMap<>();
        for(int i=0; i<=formula.length(); i++){

            if(Character.isUpperCase(formula.charAt(i)) && !elementCnt.containsKey(String.valueOf(formula.charAt(i)))){
                String eleShort;
                int eleCnt;
                if(Character.isLowerCase(formula.charAt(i+1))){
                    eleShort= String.valueOf(formula.charAt(i)+formula.charAt(i+1));
                }else{
                    eleShort= String.valueOf(formula.charAt(i));
                }
                elementCnt.keySet().add(eleShort);
                if(Character.isDigit(formula.charAt(i+1))){
                    elementCnt.values().add((int) formula.charAt(i+1));
                }else if(Character.isDigit(formula.charAt(i+2))){
                    elementCnt.values().add((int) formula.charAt(i+1));
                }else{
                    elementCnt.values().add(1);
                }

            }

        }


        return "";
    }

}
