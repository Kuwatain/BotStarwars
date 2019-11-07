package BotStarwars.Models;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;

public class Vehicles {

    private String max_atmosphering_speed;
    private String cost_in_credits;
    private String cargo_capacity;
    private String vehicle_class;
    private String manufacturer;
    private String consumables;
    private String passengers;
    private String length;
    private String model;
    private String crew;
    private String name;

    private List<String> films;
    private List<String> pilots;


    public static void getVehicleInfo(Vehicles vehicles) throws URISyntaxException {

        System.out.println("Название: " + vehicles.getName());
        System.out.println("Модель: " + vehicles.getModel());
        System.out.println("Класс транспорта: " + vehicles.getVehicle_class());
        System.out.println("Производитель: " + vehicles.getManufacturer());
        System.out.println("Длина: " + vehicles.getLength());
        System.out.println("Цена в Галактич. кредитах: " + vehicles.getCost_in_credits());
        System.out.println("Человек в команде: " + vehicles.getCrew());
        System.out.println("Пассажиры: " + vehicles.getPassengers());
        System.out.println("Макс. скорость: " + vehicles.getMax_atmosphering_speed());
        System.out.println("Грузоподъемность: " + vehicles.getCargo_capacity());
        System.out.println("Запас хода:" + vehicles.getConsumables());

        System.out.print("Фильмы: ");
        List<String> titles = vehicles.getFilms();
        titles.forEach(title ->
                System.out.print(title + (titles.indexOf(title)==titles.size()-1 ? ".\n" : ", ")));

        System.out.print("Пилоты: ");
        List<String> pilots = vehicles.getPilots();
        if(pilots.size() != 0) {
            pilots.forEach(pilot ->
                    System.out.print(pilot + (pilots.indexOf(pilot) == pilots.size() - 1 ? ".\n" : ", ")));
        }else{
            System.out.println("n/a");
        }
        System.out.println();
    }
    public static Vehicles getVehicle(Response response){
        Gson gson = new Gson();
        Object requested = response.getBody().jsonPath().get("results[0]");
        return gson.fromJson(gson.toJson(requested),Vehicles.class);
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
    private String getCost_in_credits() {
        return cost_in_credits;
    }
    private String getCargo_capacity() {
        return cargo_capacity;
    }
    private String getVehicle_class() {
        return vehicle_class;
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
    private String getCrew() {
        return crew;
    }
    public String getName() {
        return name;
    }

}
