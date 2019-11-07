package BotStarwars;

import java.util.InputMismatchException;
import java.net.URISyntaxException;
import java.util.Scanner;

import io.restassured.RestAssured;
import BotStarwars.Models.*;

public class theBot extends Base {
    public static void main(String[] args) {
        daBot:
        while (true) {
            try {
                intro();
                in = new Scanner(System.in);
                input = in.nextLine();
                switch (input) {
                    case "1": {
                        System.out.println("Какой фильм нужен?");
                        response = RestAssured.given()
                                .baseUri(URI)
                                .basePath(endpoints.filmsPath)
                                .when()
                                .get();

                        filmsLoop:
                        while (true) {
                            System.out.println("Выбери цифру нужного фильма");
                            getAttributes(response, "title");
                            pageNavigation();
                            try {
                                in = new Scanner(System.in);
                                input = in.nextLine();
                                switch (input) {
                                    case "n":
                                        nextResponse("next");
                                        break;
                                    case "b":
                                        nextResponse("previous");
                                        break;
                                    case "q":
                                        break filmsLoop;
                                    default:
                                        if (hashMap.containsKey(input)) {
                                            response = RestAssured.given()
                                                    .baseUri(URI)
                                                    .basePath(endpoints.filmsPath)
                                                    .param("search",hashMap.get(input))
                                                    .when()
                                                    .get();
                                            films = Films.getFilms(response);
                                            Films.getFilmInfo(films);
                                            getFilmFullInfo();
                                            break filmsLoop;
                                        } else {
                                            throw new InputMismatchException("Неверный ввод");
                                        }
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println(ex.getMessage());
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                    case "2": {
                        System.out.println("Какой пацанчик нужен?");
                        response = RestAssured.given()
                                .baseUri(URI)
                                .basePath(endpoints.peoplePath)
                                .when()
                                .get();

                        peopleLoop:
                        while (true) {
                            System.out.println("Выбери цифру нужного пацанчика");
                            getAttributes(response, "name");
                            pageNavigation();
                            try {
                                in = new Scanner(System.in);
                                input = in.nextLine();
                                switch (input) {
                                    case "n":
                                        nextResponse("next");
                                        break;
                                    case "b":
                                        nextResponse("previous");
                                        break;
                                    case "q":
                                        break peopleLoop;
                                    default:
                                        if (hashMap.containsKey(input)) {
                                            response = RestAssured.given()
                                                    .baseUri(URI)
                                                    .basePath(endpoints.peoplePath)
                                                    .param("search",hashMap.get(input))
                                                    .when()
                                                    .get();
                                            people = People.getPeople(response);
                                            People.getPeopleInfo(people);
                                            break peopleLoop;
                                        } else {
                                            throw new InputMismatchException("Неверный ввод");
                                        }
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println(ex.getMessage());
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                    case "3": {
                        System.out.println("Какая планета нужна?");
                        response = RestAssured.given()
                                .baseUri(URI)
                                .basePath(endpoints.planetPath)
                                .when()
                                .get();

                        planetsLoop:
                        while (true) {
                            System.out.println("Выбери цифру нужной планеты");
                            getAttributes(response, "name");
                            pageNavigation();
                            try {
                                in = new Scanner(System.in);
                                input = in.nextLine();
                                switch (input) {
                                    case "n":
                                        nextResponse("next");
                                        break;
                                    case "b":
                                        nextResponse("previous");
                                        break;
                                    case "q":
                                        break planetsLoop;
                                    default:
                                        if (hashMap.containsKey(input)) {
                                            response = RestAssured.given()
                                                    .baseUri(URI)
                                                    .basePath(endpoints.planetPath)
                                                    .param("search",hashMap.get(input))
                                                    .when()
                                                    .get();
                                            planets = Planets.getPlanet(response);
                                            Planets.getPlanetInfo(planets);
                                            getPlanetFullInfo();
                                            break planetsLoop;
                                        } else {
                                            throw new InputMismatchException("Неверный ввод");
                                        }
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println(ex.getMessage());
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                    case "4": {
                        System.out.println("Какая раса нужна?");
                        response = RestAssured.given()
                                .baseUri(URI)
                                .basePath(endpoints.speciesPath)
                                .when()
                                .get();

                        speciesLoop:
                        while (true) {
                            System.out.println("Выбери цифру нужной расы");
                            getAttributes(response, "name");
                            pageNavigation();
                            try {
                                in = new Scanner(System.in);
                                input = in.nextLine();
                                switch (input) {
                                    case "n":
                                        nextResponse("next");
                                        break;
                                    case "b":
                                        nextResponse("previous");
                                        break;
                                    case "q":
                                        break speciesLoop;
                                    default:
                                        if (hashMap.containsKey(input)) {
                                            response = RestAssured.given()
                                                    .baseUri(URI)
                                                    .basePath(endpoints.speciesPath)
                                                    .param("search",hashMap.get(input))
                                                    .when()
                                                    .get();
                                            species = Species.getSpecie(response);
                                            Species.getSpeciesInfo(species);
                                            break speciesLoop;
                                        } else {
                                            throw new InputMismatchException("Неверный ввод");
                                        }
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println(ex.getMessage());
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                    case "5": {
                        System.out.println("Какой корабль нужен?");
                        response = RestAssured.given()
                                .baseUri(URI)
                                .basePath(endpoints.starshipsPath)
                                .when()
                                .get();

                        starshipsLoop:
                        while (true) {
                            System.out.println("Выбери цифру нужного корабля");
                            getAttributes(response, "name");
                            pageNavigation();
                            try {
                                in = new Scanner(System.in);
                                input = in.nextLine();
                                switch (input) {
                                    case "n":
                                        nextResponse("next");
                                        break;
                                    case "b":
                                        nextResponse("previous");
                                        break;
                                    case "q":
                                        break starshipsLoop;
                                    default:
                                        if (hashMap.containsKey(input)) {
                                            response = RestAssured.given()
                                                    .baseUri(URI)
                                                    .basePath(endpoints.starshipsPath)
                                                    .param("search",hashMap.get(input))
                                                    .when()
                                                    .get();
                                            starships = Starships.getStarship(response);
                                            Starships.getStarshipInfo(starships);
                                            break starshipsLoop;
                                        } else {
                                            throw new InputMismatchException("Неверный ввод");
                                        }
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println(ex.getMessage());
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                    case "6": {
                        System.out.println("Какой транспорт нужен?");
                        response = RestAssured.given()
                                .baseUri(URI)
                                .basePath(endpoints.vehiclesPath)
                                .when()
                                .get();

                        vehiclesLoop:
                        while (true) {
                            System.out.println("Выбери цифру нужного транспорта");
                            getAttributes(response, "name");
                            pageNavigation();
                            try {
                                in = new Scanner(System.in);
                                input = in.nextLine();
                                switch (input) {
                                    case "n":
                                        nextResponse("next");
                                        break;
                                    case "b":
                                        nextResponse("previous");
                                        break;
                                    case "q":
                                        break vehiclesLoop;
                                    default:
                                        if (hashMap.containsKey(input)) {
                                            response = RestAssured.given()
                                                    .baseUri(URI)
                                                    .basePath(endpoints.vehiclesPath)
                                                    .param("search",hashMap.get(input))
                                                    .when()
                                                    .get();
                                            vehicles = Vehicles.getVehicle(response);
                                            Vehicles.getVehicleInfo(vehicles);
                                            break vehiclesLoop;
                                        } else {
                                            throw new InputMismatchException("Неверный ввод");
                                        }
                                }
                            } catch (InputMismatchException ex) {
                                System.out.println(ex.getMessage());
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                    }
                    case "quit": {
                        System.out.println("ya powel, brat");
                        break daBot;
                    }
                    default: {
                        throw new InputMismatchException("Неверный ввод: " + input);
                    }
                }
            }
            catch (InputMismatchException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
