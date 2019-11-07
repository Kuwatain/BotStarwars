package BotStarwars.Models;

import io.restassured.response.Response;
import io.restassured.RestAssured;

import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;

public class Planets {

    private String rotation_period;
    private String orbital_period;
    private String population;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String name;

    private List<String> residents;
    private List<String> films;

    public static void getPlanetInfo_Full(Planets planet) throws URISyntaxException {
        getPlanetInfo(planet);

        System.out.print("Фильмы: ");
        List<String> titles = planet.getFilms();
        titles.forEach(title ->
                System.out.print(title + (titles.indexOf(title)==titles.size()-1 ? ".\n" : ", ")));

        System.out.print("Жители: " );
        List<String> names = planet.getResident();
        names.forEach(name ->
                System.out.print(name + (names.indexOf(name)==names.size()-1 ? ".\n" : ", ")));

        System.out.println();
    }
    public static Planets getPlanet(Response response){
        Gson gson = new Gson();
        Object requested = response.getBody().jsonPath().get("results[0]");
        return gson.fromJson(gson.toJson(requested),Planets.class);
    }
    public static void getPlanetInfo(Planets planet){
        System.out.println("Название: " + planet.getName());
        System.out.println("Диаметр: " + planet.getDiameter());
        System.out.println("Гравитация: " + planet.getGravity());
        System.out.println("Орбитальный период: " + planet.getOrbital_period());
        System.out.println("Сутки: " + planet.getRotation_period()+ "часа");
        System.out.println("Климат: " + planet.getClimate());
        System.out.println("Территория: " + planet.getTerrain());
        System.out.println("Популяция: " + planet.getPopulation());
    }

    private List<String> getResident() throws URISyntaxException {
        List<String> names = new ArrayList<>();
        for (String resident : residents) {
            URI uri = new URI(resident);
            Response response = RestAssured.given()
                    .baseUri(uri.getScheme() + "://" + uri.getAuthority())
                    .basePath(uri.getPath())
                    .when()
                    .get();
            names.add(response.getBody().jsonPath().getString("name"));
        }
        return names;
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

    private String getRotation_period() {
        return rotation_period;
    }
    private String getOrbital_period() {
        return orbital_period;
    }
    private String getPopulation() {
        return population;
    }
    private String getDiameter() {
        return diameter;
    }
    private String getClimate() {
        return climate;
    }
    private String getGravity() {
        return gravity;
    }
    private String getTerrain() {
        return terrain;
    }
    public String getName() {
        return name;
    }
}
