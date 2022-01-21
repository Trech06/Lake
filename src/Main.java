import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] params = inputPerimeterAndCountVillage();
        int perimeter = params[0];
        int countVillages = params[1];

        //List<Integer> distanceFromNorth = inputDistanceFromNorth(perimeter, countVillage);
        List<Integer> distanceFromNorth =
                inputDistanceFromNorthAlternate(perimeter, countVillages);
        //System.out.println(distanceFromNorth);
        List<Integer> distanceBetweenVillages =
                calculateDistanceBetweenVillages(distanceFromNorth, perimeter);
        Collections.sort(distanceBetweenVillages);
        //System.out.println(distanceBetweenVillages);
        int minDistance = 0;
        for (int i = 0; i<distanceBetweenVillages.size()-1; i++) {
            minDistance += distanceBetweenVillages.get(i);
        }
        System.out.println(minDistance);
    }

    private static List<Integer> calculateDistanceBetweenVillages
            (List<Integer> distanceFromNorth, int perimeter) {

        List<Integer> distance = new ArrayList<>();
        for (int i = 0; i<distanceFromNorth.size()-1;i++) {
            distance.add(distanceFromNorth.get(i + 1) - distanceFromNorth.get(i));
        }
        distance.add(perimeter - distanceFromNorth.get(distanceFromNorth.size()-1)
                + distanceFromNorth.get(0));
        return distance;
    }

    public static List<Integer> inputDistanceFromNorthAlternate
            (int perimeter, int countVillages) {
        List<Integer> distanceFromNorth = new ArrayList<>();
        System.out.println("Введите расстояние от северной точки, " +
                "до каждой из " +countVillages+ " деревень");
        while (true) {
            int errors = 0;
            int lastInt = -1;
            Scanner scanner = new Scanner(System.in);
            String[] distancesStr = scanner.nextLine().split(" ");
            int[] distancesInt = new int[countVillages];

            if (distancesStr.length == countVillages) {
                for (int i =0; i < countVillages; i++) {
                    try {
                        distancesInt[i] = Integer.parseInt(distancesStr[i]);
                        if (distancesInt[i] < perimeter & distancesInt[i] >= 0
                                & lastInt < distancesInt[i]) {
                            distanceFromNorth.add(distancesInt[i]);
                            lastInt = distancesInt[i];
                        } else {
                            System.out.println("Введено число больше периметра," +
                                    " меньше нуля или не в порядке возрастания");
                            errors++;
                            distanceFromNorth.clear();
                            i=countVillages;
                        }
                    } catch (Exception e) {
                        System.out.println("Необходимо вводить числа от 0 до "
                                +perimeter + " в порядке возрастания");
                        errors++;
                        distanceFromNorth.clear();
                        i=countVillages;
                    }
                }
            } else {
                System.out.println("Необходимо вводить " + countVillages + " числа(-ел)");
                errors++;
            }
            if (errors == 0) {
                //System.out.println(distanceFromNorth);
                break;
            }
        }
        return distanceFromNorth;
    }

    public static List<Integer> inputDistanceFromNorth(int perimeter, int countVillages){
        List<Integer> distanceFromNorth = new ArrayList<>();
        System.out.println("Введите расстояние от северной точки, " +
                "до каждой из " +countVillages+ " деревень");
        int last =-1;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                for (int i = 0; i<countVillages; i++) {
                    int current = scanner.nextInt();
                    if (current < perimeter & current >= 0 & last < current) {
                        distanceFromNorth.add(current);
                        last = current;
                    } else {
                        System.out.println("Расстояние должно быть числом не превышающим " +
                                "периметра пруда,меньше нуля, расстояние между деревнями и " +
                                "северной точкой должно увеличиваться");
                        i--;
                    }
                }
                break;
            }
            catch (Exception e) {
                System.out.println("Расстояние должно быть числом не превышающим " +
                        "периметра пруда,меньше нуля, расстояние между деревнями и " +
                        "северной точкой должно увеличиваться");
            }
        }
        return distanceFromNorth;
    }

    public static int[] inputPerimeterAndCountVillage(){
        int countVillages;
        int perimeterLake;
        System.out.println("Введите диаметр пруда и количество деревень");
        while(true) {
            Scanner scannerInt = new Scanner(System.in);
            try {
                perimeterLake = scannerInt.nextInt();
                countVillages = scannerInt.nextInt();
                if (perimeterLake <= 1000000 & perimeterLake >= 2 &
                        countVillages <=100000 & countVillages >=2)  {
                    break;
                }
                else {
                    throw new Exception("Вводите данные удовлетворяющие условиям задачи");
                }
            } catch (Exception e) {
                System.out.println("Вводите данные удовлетворяющие условиям задачи");
            }
        }
        return new int[] {perimeterLake,countVillages};
    }
}
