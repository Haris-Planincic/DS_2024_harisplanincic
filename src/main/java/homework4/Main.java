package homework4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Loading...");
        File file = new File("C:\\Users\\Desktop\\social_network.csv");
        Scanner fileScanner = new Scanner(file);
        fileScanner.nextLine();
        SocialNetwork socialNetwork = new SocialNetwork(fileScanner);
        System.out.println("Network successfully loaded.");
        System.out.println("Total users: " + socialNetwork.getNumberOfUsers());
        System.out.println("Total friendships: " + socialNetwork.getNumberOfFriendships());

        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a name to recommend, or -1 to exit: ");
            String input = inputScanner.nextLine();
            if (input.equals("-1")) {
                break;
            }

            if (socialNetwork.userExists(input)) {
                ArrayList<FriendshipRecommendation> recommendations = socialNetwork.recommendFriends(input);
                System.out.println("Total recommendations: " + recommendations.size());
                System.out.println("Top ten recommendations: ");
                for (int i = 0; i < Math.min(10, recommendations.size()); i++) {
                    FriendshipRecommendation recommendation = recommendations.get(i);
                    System.out.println(recommendation.getUser() + ": " + recommendation.getRecommendationStrength());
                }
            } else {
                System.out.println("The user does not exist.");
            }
        }

        System.out.println("Thank you for using our friendship recommender algorithm.");
        inputScanner.close();
    }

}
