package BotStarwars.Models;

import com.google.gson.Gson;

import io.restassured.response.Response;
import io.restassured.RestAssured;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;

public class People {

    private String hair_color;
    private String skin_color;
    private String birth_year;
    private String eye_color;
    private String height;
    private String gender;
    private String name;
    private String mass;

    private List<String> starships;
    private List<String> vehicles;
    private List<String> species;
    private List<String> films;


    public static void getPeopleInfo(People people) throws URISyntaxException {

        System.out.println();
        System.out.println();

        System.out.println("Имя: " + people.getName());
        System.out.println("Дата рождения: " + people.getBirth_year());
        System.out.println("Цвет глаз: " + people.getEye_color());
        System.out.println("Пол: " + people.getGender());
        System.out.println("Цвет волос: " + people.getHair_color());
        System.out.println("Рост: " + people.getHeight());
        System.out.println("Вес: " + people.getMass());
        System.out.println("Цвет кожи: " + people.getSkin_color());

        System.out.print("Фильмы: ");
        List<String> titles = people.getFilms();
        titles.forEach(title ->
                System.out.print(title + (titles.indexOf(title)==titles.size()-1 ? ".\n" : ", ")));

        System.out.println("Вид: " + people.getSpecies().get(0));

        System.out.print("Космич. Корабли: ");
        if(people.getStarships().size() != 0) {
            List<String> starships = people.getStarships();
            starships.forEach(starship ->
                    System.out.print(starship + (starships.indexOf(starship) == starships.size() - 1 ? ".\n" : ", ")));
        }else{
            System.out.println("n/a");
        }
        System.out.print("Траспорт: ");
        if (people.getVehicles().size() != 0) {
            List<String> vehicles = people.getVehicles();
            vehicles.forEach(vehicle ->
                    System.out.print(vehicle + (vehicles.indexOf(vehicle) == vehicles.size() - 1 ? ".\n" : ", ")));
        }else{
            System.out.println("n/a");
        }
        System.out.println();
    }
    public static People getPeople(Response response){
        Gson gson = new Gson();
        Object requested = response.getBody().jsonPath().get("results[0]");
        return gson.fromJson(gson.toJson(requested),People.class);
    }


    private List<String> getStarships() throws URISyntaxException {
        List<String> starship = new ArrayList<>();
        for (String s : starships) {
            URI uri = new URI(s);
            Response response = RestAssured.given()
                    .baseUri(uri.getScheme() + "://" + uri.getAuthority())
                    .basePath(uri.getPath())
                    .when()
                    .get();
            starship.add(response.getBody().jsonPath().getString("name"));
        }
        return starship;
    }
    private List<String> getVehicles() throws URISyntaxException {
        List<String> vehicle = new ArrayList<>();
        for (String s : vehicles) {
            URI uri = new URI(s);
            Response response = RestAssured.given()
                    .baseUri(uri.getScheme() + "://" + uri.getAuthority())
                    .basePath(uri.getPath())
                    .when()
                    .get();
            vehicle.add(response.getBody().jsonPath().getString("name"));
        }
        return vehicle;
    }
    private List<String> getSpecies() throws URISyntaxException {
        List<String> specie = new ArrayList<>();
        for (String s : species) {
            URI uri = new URI(s);
            Response response = RestAssured.given()
                    .baseUri(uri.getScheme() + "://" + uri.getAuthority())
                    .basePath(uri.getPath())
                    .when()
                    .get();
            specie.add(response.getBody().jsonPath().getString("name"));
        }
        return specie;
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

    private String getHair_color() {
        return hair_color;
    }
    private String getSkin_color() {
        return skin_color;
    }
    private String getBirth_year() {
        return birth_year;
    }
    private String getEye_color() {
        return eye_color;
    }
    private String getGender() {
        return gender;
    }
    private String getHeight() {
        return height;
    }
    private String getName() {
        return name;
    }
    private String getMass() {
        return mass;
    }
}


