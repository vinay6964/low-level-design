interface MatchingStrategy {
    void match(String riderLocation);
}

// ==============================
// Concrete Strategy: Nearest Driver
// ==============================
class NearestDriverStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching with the nearest available driver to " + riderLocation);
        // Distance-based matching logic
    }
}

// ==============================
// Concrete Strategy: Airport Queue
// ==============================
class AirportQueueStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching using FIFO airport queue for " + riderLocation);
        // Match first-in-line driver for airport pickup
    }
}

// ==============================
// Concrete Strategy: Surge Priority
// ==============================
class SurgePriorityStrategy implements MatchingStrategy {
    @Override
    public void match(String riderLocation) {
        System.out.println("Matching rider using surge pricing priority near " + riderLocation);
        // Prioritize high-surge zones or premium drivers
    }
}

class RideMatchingService {
    private MatchingStrategy requiredMatchingStrategy;

    RideMatchingService (MatchingStrategy requiredMatchingStrategy) {
        this.requiredMatchingStrategy = requiredMatchingStrategy;
    }

    // Setter injection for changing strategy dynamically
    public void setStrategy(MatchingStrategy strategy) {
        this.requiredMatchingStrategy = strategy;
    }

    public void matchRider (String location) {
        requiredMatchingStrategy.match(location);
    }
}


public class Strategy {
    public static void main (String [] args) {
        RideMatchingService service = new RideMatchingService(new NearestDriverStrategy());
        service.setStrategy(new AirportQueueStrategy());
        service.matchRider("Paldi");
    }
}
