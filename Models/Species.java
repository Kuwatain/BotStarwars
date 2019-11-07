package BotStarwars.Models;

import io.restassured.response.Response;
import io.restassured.RestAssured;

import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;

public class Species {

    private String average_lifespan;
    private String average_height;
    private String classification;
    private String designation;
    private String hair_colors;
    private String skin_colors;
    private String eye_colors;
    private String homeworld;
    private String language;
    private String name;

    private List<String> people;
    private List<String> films;



    public static void getSpeciesInfo(Species specie) throws URISyntaxException {
        System.out.println("Название: " + specie.getName());
        System.out.println("Средний рост: " + specie.getAverage_height());
        System.out.println("Средний срок жизни: " + specie.getAverage_lifespan());
        System.out.println("Классификация: " + specie.getClassification());
        System.out.println("Обозначение: " + specie.getDesignation());
        System.out.println("Цвет глаз: " + specie.getEye_colors());
        System.out.println("Цвет волос: " + specie.getHair_colors());
        System.out.println("Цвет кожи: " + specie.getSkin_colors());
        System.out.println("Родная планета: " + specie.getHomeworld());
        System.out.println("Язык: " + specie.getLanguage());

        System.out.print("Персонажи: ");
        List<String> characters = specie.getPeople();
        characters.forEach(character ->
                System.out.print(character + (characters.indexOf(character)==characters.size()-1 ? ".\n" : ", ")));

        System.out.print("Фильмы: ");
        List<String> titles = specie.getFilms();
        titles.forEach(title ->
                System.out.print(title + (titles.indexOf(title)==titles.size()-1 ? ".\n" : ", ")));

        System.out.println();
    }
    public static Species getSpecie(Response response){
        Gson gson = new Gson();
        Object requested = response.getBody().jsonPath().get("results[0]");
        return gson.fromJson(gson.toJson(requested),Species.class);
    }

    private List<String> getPeople() throws URISyntaxException {
        List<String> peopl = new ArrayList<>();
        for (String s : people) {
            URI uri = new URI(s);
            Response response = RestAssured.given()
                    .baseUri(uri.getScheme() + "://" + uri.getAuthority())
                    .basePath(uri.getPath())
                    .when()
                    .get();
            peopl.add(response.getBody().jsonPath().getString("name"));
        }
        return peopl;
    }
    private List<String> getFilms() throws URISyntaxException {
        List<String> titles = new ArrayList<>();
        for (String film : films) {
            URI uri = new URI(film);
            Response response = RestAssured.given()
                    .baseUri(uri.getScheme() + "://" + uri.getAuthority())
                    .basePath(uri.getPath())
                    .when()
                    .get();
            titles.add(response.getBody().jsonPath().getString("title"));
        }
        return titles;
    }

    private String getAverage_lifespan() {
        return average_lifespan;
    }
    private String getHomeworld() throws URISyntaxException {
        URI uri = new URI(homeworld);
        Response response = RestAssured.given()
                .baseUri(uri.getScheme()+"://" + uri.getAuthority())
                .basePath(uri.getPath())
                .when()
                .get();
        return response.getBody().jsonPath().getString("name");
    }
    private String getAverage_height() {
        return average_height;
    }
    private String getClassification() {
        return classification;
    }
    private String getDesignation() {
        return designation;
    }
    private String getHair_colors() {
        return hair_colors;
    }
    private String getSkin_colors() {
        return skin_colors;
    }
    private String getEye_colors() {
        return eye_colors;
    }
    private String getLanguage() {
        return language;
    }
    public String getName() {
        return name;
    }
}
