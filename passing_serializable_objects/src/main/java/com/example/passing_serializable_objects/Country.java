package com.example.passing_serializable_objects;

import java.io.Serializable;

/*created using Android Studio (Beta) 0.8.14
www.101apps.co.za*/


public class Country implements Serializable {

    private String name;
    private String capital;
    private int population;


    public Country(String name, String capital, int population) {
        this.name = name;
        this.capital = capital;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
