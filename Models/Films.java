package BotStarwars.Models;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;

public class Films {

    private String opening_crawl;
    private String release_date;
    private String episode_id;
    private String director;
    private String producer;
    private String title;

    private List<String> characters;
    private List<String> starships;
    private List<String> vehicles;
    private List<String> planets;
    private List<String> species;


    public static void getFilmInfo_Full(Films films) throws URISyntaxException {
        System.out.println();
        System.out.println();

        System.out.println("Название фильма: " + films.getTitle());
        System.out.println("Эпизод: " + films.getEpisode_id());
        System.out.println("Дата выхода: " + films.getRelease_date());
        System.out.println("Продюссер: " + films.getProducer());
        System.out.println("Режиссер: " + films.getDirector());
        System.out.println("Титры в начале: \n" + films.getOpening_crawl() + "\n");

        System.out.print("Планеты: " );
        List<String> planets = films.getPlanets();
        planets.forEach(planet ->
                System.out.print(planet + (planets.indexOf(planet)==planets.size()-1 ? ".\n" : ", ")));

        System.out.println("Виды: ");
        List<String> species = films.getSpecies();
        species.forEach(specie ->
                System.out.print(specie + (species.indexOf(specie)==species.size()-1 ? ".\n" : ", ")));

        System.out.print("Космич. Корабли: ");
        if(films.getStarships().size() != 0) {
            List<String> starships = films.getStarships();
            starships.forEach(starship ->
                    System.out.print(starship + (starships.indexOf(starship) == starships.size() - 1 ? ".\n" : ", ")));
        }else{
            System.out.println("n/a");
        }

        System.out.print("Траспорт: ");
        if (films.getVehicles().size() != 0) {
            List<String> vehicles = films.getVehicles();
            vehicles.forEach(vehicle ->
                    System.out.print(vehicle + (vehicles.indexOf(vehicle) == vehicles.size() - 1 ? ".\n" : ", ")));
        }else{
            System.out.println("n/a");
        }

        System.out.println("Персонажи: ");
        List<String> characters = films.getCharacters();
        characters.forEach(character ->
                System.out.print(character + (characters.indexOf(character)==characters.size()-1 ? ".\n" : ", ")));


    }
    public static Films getFilms(Response response){
        Gson gson = new Gson();
        Object requested = response.getBody().jsonPath().get("results[0]");
        return gson.fromJson(gson.toJson(requested),Films.class);
    }
    public static void getFilmInfo(Films films){
        System.out.println();
        System.out.println();

        System.out.println("Название фильма: " + films.getTitle());
        System.out.println("Эпизод: " + films.getEpisode_id());
        System.out.println("Дата выхода: " + films.getRelease_date());
        System.out.println("Продюссер: " + films.getProducer());
        System.out.println("Режиссер: " + films.getDirector());
    }

    private List<String> getCharacters() throws URISyntaxException {
        List<String> character = new ArrayList<>();
        for (String s : characters) {
            URI uri = new URI(s);
            Response response = RestAssured.given()
                    .baseUri(uri.getScheme() + "://" + uri.getAuthority())
                    .basePath(uri.getPath())
                    .when()
                    .get();
            character.add(response.getBody().jsonPath().getString("name"));
        }
        return character;
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
    private List<String> getPlanets() throws URISyntaxException {
        List<String> planet = new ArrayList<>();
        for (String s : planets) {
            URI uri = new URI(s);
            Response response = RestAssured.given()
                    .baseUri(uri.getScheme() + "://" + uri.getAuthority())
                    .basePath(uri.getPath())
                    .when()
                    .get();
            planet.add(response.getBody().jsonPath().getString("name"));
        }
        return planet;
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

    private String getOpening_crawl() {
        return opening_crawl;
    }
    private String getRelease_date() {
        return release_date;
    }
    private String getEpisode_id() {
        return episode_id;
    }
    private String getDirector() {
        return director;
    }
    private String getProducer() {
        return producer;
    }
    private String getTitle() {
        return title;
    }
}
