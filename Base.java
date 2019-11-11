package BotStarwars;

import BotStarwars.Models.*;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import java.net.URISyntaxException;
import java.util.stream.Collectors;
import java.net.URI;
import java.util.*;

class  Base {

    static Endpoints endpoints = new Endpoints();
    static String URI = "https://swapi.co/";
    static Response response;

    static Starships starships;
    static Vehicles vehicles;
    static Planets planets;
    static Species species;
    static People people;
    static Films films;

    static HashMap<Object, String> hashMap = new HashMap<>();
    static Scanner in = new Scanner(System.in);
    static String input;


    static void getPlanetFullInfo(){
        while(true) {
            try {
                System.out.println("Показать полную информацию?");
                System.out.println("Y) Da     N) Net");
                Scanner in = new Scanner(System.in);
                input = in.nextLine();
                if(input.equals("Y") || input.equals("y")){
                    Planets.getPlanetInfo_Full(planets);
                    return;
                }
                if(input.equals("N") || input.equals("n")){
                    break;
                }
                throw new InputMismatchException("Неверный ввод");
            }
            catch (InputMismatchException | URISyntaxException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    static void getFilmFullInfo(){
        while(true) {
            try {
                System.out.println("Показать полную информацию?");
                System.out.println("Y) Da     N) Net");
                Scanner in = new Scanner(System.in);
                input = in.nextLine();
                if(input.equals("Y") || input.equals("y")){
                    Films.getFilmInfo_Full(films);
                    break;
                }
                if(input.equals("N") || input.equals("n")){
                    break;
                }
                throw new InputMismatchException("Неверный ввод");
            }
            catch (InputMismatchException | URISyntaxException ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    static void nextResponse(String string) throws URISyntaxException {
        if(response.getBody().jsonPath().getString(string)==null) {
            throw new InputMismatchException("Неверный ввод");
        }

        URI uri = new URI(response.getBody().jsonPath().getString(string));

        response = given()
                .baseUri(URI)
                .basePath(uri.getPath())
                .param(splitQuery(uri).get(0), splitQuery(uri).get(1))
                .when()
                .get();
    }
    static void getAttributes(Response response,String attribute){
        for(int i = 0; i < response.getBody().jsonPath().getList("results").size(); i++){
            System.out.println(i + " - " + response.getBody().jsonPath().getString("results."+attribute+"["+i+"]"));
            hashMap.put(Integer.toString(i),response.getBody().jsonPath().getString("results."+attribute+"["+i+"]"));
        }
    }
    private static List<String> splitQuery(URI uri){
        return Arrays.stream(uri.getQuery().split("=")).collect(Collectors.toList());
    }

    static void pageNavigation(){
        if (response.getBody().jsonPath().getString("next") == null &&
            response.getBody().jsonPath().getString("previous") == null){

            System.out.println("q) Categories");
            return;
        }
        if (response.getBody().jsonPath().getString("next") == null) {
            System.out.println("b) Back   q) Categories");
        }
        if (response.getBody().jsonPath().getString("previous") == null) {
            System.out.println("n) Next   q) Categories");
        } else {
            System.out.println("b) Back      n) Next     q) Categories");
        }

    }
    static void intro() {
        System.out.println("Добро пожаловать");
        System.out.println("1) Фильмы\n" +
                "2) Человеки\n" +
                "3) Планеты             quit) Закрыть бота       \n" +
                "4) Расы\n" +
                "5) Корабли\n" +
                "6) Транспорт");
    }
}
