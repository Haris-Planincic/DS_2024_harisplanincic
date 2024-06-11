package homework4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;


public class SocialNetwork {
    private int V;
    private int E;
    private HashMap<String, ArrayList<Friendship>> adj;

    public SocialNetwork() {
        this.V = 0;
        this.E = 0;
        this.adj = new HashMap<>();
    }

    public SocialNetwork(Scanner in) {
        this();
        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (!line.isEmpty()) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String friend1 = parts[0];
                    String friend2 = parts[1];
                    int strength = Integer.parseInt(parts[2]);
                    Friendship f = new Friendship(friend1, friend2, strength);
                    addFriendship(f);
                }
            }
        }
    }

    public void addUser(String user) {
        if (!adj.containsKey(user)) {
            adj.put(user, new ArrayList<>());
            V++;
        }
    }

    public void addFriendship(Friendship f) {
        String friend1 = f.getFriend1();
        String friend2 = f.getFriend2();
        addUser(friend1);
        addUser(friend2);
        adj.get(friend1).add(f);
        adj.get(friend2).add(f);
        E++;
    }

    private ArrayList<String> getFriends(String user) {
        ArrayList<String> friends = new ArrayList<>();
        if (adj.containsKey(user)) {
            for (Friendship f : adj.get(user)) {
                if (f.getFriend1().equals(user)) {
                    friends.add(f.getFriend2());
                } else {
                    friends.add(f.getFriend1());
                }
            }
        }
        return friends;
    }

    public ArrayList<FriendshipRecommendation> recommendFriends(String user) {
        ArrayList<FriendshipRecommendation> recommendations = new ArrayList<>();
        if (!adj.containsKey(user)) return recommendations;

        ArrayList<String> friends = getFriends(user);
        HashMap<String, Double> recommendationStrengths = new HashMap<>();

        for (String friend : friends) {
            int userFriendStrength = getFriendshipStrength(user, friend);
            ArrayList<String> friendsOfFriend = getFriends(friend);
            for (String fof : friendsOfFriend) {
                if (!fof.equals(user) && !friends.contains(fof)) {
                    int friendFofStrength = getFriendshipStrength(friend, fof);
                    double strengthToAdd = Math.min(userFriendStrength, friendFofStrength);
                    recommendationStrengths.put(fof, recommendationStrengths.getOrDefault(fof, 0.0) + strengthToAdd);
                }
            }
        }

        for (String potentialFriend : recommendationStrengths.keySet()) {
            recommendations.add(new FriendshipRecommendation(potentialFriend, recommendationStrengths.get(potentialFriend)));
        }

        Collections.sort(recommendations, Collections.reverseOrder());
        return recommendations;
    }

    private int getFriendshipStrength(String user1, String user2) {
        if (adj.containsKey(user1)) {
            for (Friendship f : adj.get(user1)) {
                if ((f.getFriend1().equals(user1) && f.getFriend2().equals(user2)) ||
                        (f.getFriend1().equals(user2) && f.getFriend2().equals(user1))) {
                    return f.getFriendshipStrength();
                }
            }
        }
        return 0;
    }
    public int getNumberOfUsers() {
        return V;
    }

    public int getNumberOfFriendships() {
        return E;
    }

    public boolean userExists(String user) {
        return adj.containsKey(user);
    }
}
