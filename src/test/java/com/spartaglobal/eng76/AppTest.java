package com.spartaglobal.eng76;


import com.spartaglobal.eng76.framework.URLBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppTest 
{

    @Test
    @DisplayName("testing")
    void testing(){
        System.out.println(URLBuilder.ofCity("Bucharest", "fc40ec94b702609da7483ea4672bb0e6"));
        System.out.println(URLBuilder.ofCities(new int[]{2643743,683506}, "fc40ec94b702609da7483ea4672bb0e6"));
        System.out.println(URLBuilder.ofCity(683506, "fc40ec94b702609da7483ea4672bb0e6"));
        System.out.println(URLBuilder.ofCitiesInRectangle(12,32,15,37,10, "fc40ec94b702609da7483ea4672bb0e6"));
    }
}
