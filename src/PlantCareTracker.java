import java.util.ArrayList;
import java.util.Scanner;

abstract class Plant {
    String name;
    String type;
    int wateringFrequency;
    boolean isHealthy;

    public Plant(String name, String type, int wateringFrequency) {
        this.name = name;
        this.type = type;
        this.wateringFrequency = wateringFrequency;
        this.isHealthy = true;
    }

    public abstract void careInstructions();

    public String toString() {
        return "Name: " + name + ", Type: " + type +
                ", Watering Frequency: " + wateringFrequency + " days, Healthy: " + isHealthy;
    }
}

class Succulent extends Plant {
    public Succulent(String name, int wateringFrequency) {
        super(name, "Succulent", wateringFrequency);
    }

    public void careInstructions() {
        System.out.println(name + " needs minimal water and lots of sunlight.");
    }
}

class FloweringPlant extends Plant {
    public FloweringPlant(String name, int wateringFrequency) {
        super(name, "Flowering Plant", wateringFrequency);
    }

    public void careInstructions() {
        System.out.println(name + " needs regular watering and partial shade, but make sure to monitor health.");
    }
}

class Garden {
    private ArrayList<Plant> plants;

    public Garden() {
        plants = new ArrayList<>();
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
        System.out.println(plant.name + " has been added to the garden.");
    }

    public void removePlant(String name) {
        Plant toRemove = findPlant(name);
        if (toRemove != null) {
            plants.remove(toRemove);
            System.out.println(name + " has been removed from the garden.");
        } else {
            System.out.println("Plant with name " + name + " not found.");
        }
    }

    public Plant findPlant(String name) {
        for (Plant plant : plants) {
            if (plant.name.equalsIgnoreCase(name)) {
                return plant;
            }
        }
        return null;
    }

    public void listPlants() {
        if (plants.isEmpty()) {
            System.out.println("No plants in the garden.");
        } else {
            System.out.println("Plants in the garden:");
            for (Plant plant : plants) {
                System.out.println(plant);
            }
        }
    }
}

public class PlantCareTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Garden garden = new Garden();
        boolean running = true;

        while (running) {
            System.out.println("\n--- Plant Care Tracker Menu ---");
            System.out.println("1. Add a Plant");
            System.out.println("2. Remove a Plant");
            System.out.println("3. List All Plants");
            System.out.println("4. Find a Plant");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter plant name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter plant type (1 for Succulent, 2 for Flowering Plant): ");
                    int typeChoice = scanner.nextInt();
                    System.out.print("Enter watering frequency in days: ");
                    int frequency = scanner.nextInt();

                    Plant plant;
                    if (typeChoice == 1) {
                        plant = new Succulent(name, frequency);
                    } else {
                        plant = new FloweringPlant(name, frequency);
                    }
                    garden.addPlant(plant);
                    break;

                case 2:
                    System.out.print("Enter the name of the plant you would like to remove: ");
                    String removeName = scanner.nextLine();
                    garden.removePlant(removeName);
                    break;

                case 3:
                    garden.listPlants();
                    break;

                case 4:
                    System.out.print("Enter the name of the plant to find: ");
                    String findName = scanner.nextLine();
                    Plant foundPlant = garden.findPlant(findName);
                    if (foundPlant != null) {
                        System.out.println("Plant found: " + foundPlant);
                        foundPlant.careInstructions();
                    } else {
                        System.out.println("Plant not found.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting Plant Care Tracker. Goodbye and please take care of your plants :)!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
