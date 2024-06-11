package homework4;

import java.util.Objects;

public class FriendshipRecommendation implements Comparable<FriendshipRecommendation> {
    private String user;
    private double recommendationStrength;

    public FriendshipRecommendation(String user, double recommendationStrength) {
        this.user = user;
        this.recommendationStrength = recommendationStrength;
    }

    public String getUser() {
        return user;
    }

    public double getRecommendationStrength() {
        return recommendationStrength;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setRecommendationStrength(double recommendationStrength) {
        this.recommendationStrength = recommendationStrength;
    }

    @Override
    public int compareTo(FriendshipRecommendation o) {
        return Double.compare(this.recommendationStrength, o.recommendationStrength);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendshipRecommendation that = (FriendshipRecommendation) o;
        return Double.compare(that.recommendationStrength, recommendationStrength) == 0 &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, recommendationStrength);
    }

    @Override
    public String toString() {
        return "FriendshipRecommendation{" +
                "user='" + user + '\'' +
                ", recommendationStrength=" + recommendationStrength +
                '}';
    }
}
