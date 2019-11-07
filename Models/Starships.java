package BotStarwars.Models;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;

public class Starships {

    private String max_atmosphering_speed;
    private String hyperdrive_rating;
    private String cost_in_credits;
    private String cargo_capacity;
    private String starship_class;
    private String manufacturer;
    private String consumables;
    private String passengers;
    private String length;
    private String model;
    private String MGLT;
    private String crew;
    private String name;

    private List<String> pilots;
    private List<String> films;

    public static void getStarshipInfo(Starships starships) throws URISyntaxException {

        System.out.println("Название: " + starships.getName());
        System.out.println("Модель: " + starships.getModel());
        System.out.println("Производитель: " + starships.getManufacturer());
        System.out.println("Цена в Галактич. кредитах: " + starships.getCost_in_credits());
        System.out.println("Длина: " + starships.getLength());
        System.out.println("Макс. скорость: " + starships.getMax_atmosphering_speed());
        System.out.println("Человек в команде: " + starships.getCrew());
        System.out.println("Пассажиры: " + starships.getPassengers());
        System.out.println("Грузоподъемность: " + starships.getCargo_capacity());
        System.out.println("Запас хода:" + starships.getConsumables());
        System.out.println("Рейтинг гиперпривода: " + starships.getHyperdrive_rating());
        System.out.println("Мегасвет в час: " + starships.getMGLT());
        System.out.println("Класс корабля: " + starships.getStarship_class());

        System.out.print("Фильмы: ");
        List<String> titles = starships.getFilms();
        titles.forEach(title ->
                System.out.print(title + (titles.indexOf(title)==titles.size()-1 ? ".\n" : ", ")));

        System.out.print("Пилоты: ");
        List<String> pilots = starships.getPilots();
        if(pilots.size() != 0) {
            pilots.forEach(pilot ->
                    System.out.print(pilot + (pilots.indexOf(pilot) == pilots.size() - 1 ? ".\n" : ", ")));
        }else{
            System.out.println("n/a");
        }
        System.out.println();
    }
    public static Starships getStarship(Response response){
        Gson gson = new Gson();
        Object requested = response.getBody().jsonPath().get("results[0]");
        return gson.fromJson(gson.toJson(requested),Starships.class);
    }

    private List<String> getPilots() throws URISyntaxException {
        List<String> pilot = new ArrayList<>();
        for (String p : pilots) {
            URI uri = new URI(p);
            Response response = RestAssured.given()
                    .baseUri(uri.getScheme() + "://" + uri.getAuthority())
                    .basePath(uri.getPath())
                    .when()
                    .get();
            pilot.add(response.getBody().jsonPath().getString("name"));
        }
        return pilot;
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

    private String getMax_atmosphering_speed() {
        return max_atmosphering_speed;
    }
    private String getHyperdrive_rating() {
        return hyperdrive_rating;
    }
    private String getCost_in_credits() {
        return cost_in_credits;
    }
    private String getCargo_capacity() {
        return cargo_capacity;
    }
    private String getStarship_class() {
        return starship_class;
    }
    private String getManufacturer() {
        return manufacturer;
    }
    private String getConsumables() {
        return consumables;
    }
    private String getPassengers() {
        return passengers;
    }
    private String getLength() {
        return length;
    }
    private String getModel() {
        return model;
    }
    private String getMGLT() {
        return MGLT;
    }
    private String getCrew() {
        return crew;
    }
    public String getName() {
        return name;
    }
}
