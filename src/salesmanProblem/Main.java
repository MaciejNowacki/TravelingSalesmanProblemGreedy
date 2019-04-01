package salesmanProblem;

import java.util.ArrayList;

public class Main {

    private static ArrayList<City> listOfCities = new ArrayList<City>();
    private static ArrayList<City> bestOfRoute = new ArrayList<City>();
    private static City startPoint;

    public static void main(String[] args) {
        startPoint = new City(0, 0, "Pabianice");
        listOfCities.add(new City(1, 1, "Lodz"));
        listOfCities.add(new City(0, -2, "Belchatow"));
        listOfCities.add(new City(-2, 0, "Lask"));

        findShortestRoad();
        printBestRoad();
        System.out.println(": " + calculateRoute());
    }

    public static void findShortestRoad() {
        double distance = Double.MAX_VALUE;
        int indexNearestCity = Integer.MAX_VALUE;
        double tmpDistance;
        bestOfRoute.add(startPoint);
        while (listOfCities.size() > 0) {
            distance = getDist(bestOfRoute.get(bestOfRoute.size() - 1), listOfCities.get(0));
            indexNearestCity = 0;

            for (int i = 0; i < listOfCities.size(); i++) {
                tmpDistance = getDist(bestOfRoute.get(bestOfRoute.size() - 1), listOfCities.get(i));
                if (tmpDistance < distance) {
                    distance = tmpDistance;
                    indexNearestCity = i;
                }
            }
            bestOfRoute.add(listOfCities.get(indexNearestCity));
            listOfCities.remove(indexNearestCity);
        }
        bestOfRoute.add(startPoint);
    }

    public static double calculateRoute() {
        double route = 0;
        for (int i = 0; i < bestOfRoute.size() - 1; i++) {
            route += getDist(bestOfRoute.get(i), bestOfRoute.get(i + 1));
        }
        return route;
    }

    public static void printBestRoad() {
        for (int i = 0; i < bestOfRoute.size() - 1; i++) {
            System.out.print(bestOfRoute.get(i)
                                        .getName()
                             + " => ");
        }
        System.out.print(bestOfRoute.get(bestOfRoute.size() - 1)
                                    .getName());
    }

    public static double getDist(City firstCity, City secondCity) {
        return Math.sqrt(Math.pow((firstCity.getPosX() - secondCity.getPosX()), 2)
                         + Math.pow((firstCity.getPosY() - secondCity.getPosY()), 2));
    }
}
