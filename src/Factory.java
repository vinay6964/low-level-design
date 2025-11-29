interface Logistics {
    void Send ();
}

class Road implements Logistics {
    @Override
    public void Send () {
        System.out.println("Sending via Road");
    }
}

class Air implements Logistics {
    @Override
    public void Send () {
        System.out.println("Sending via Air");
    }
}

class Train implements Logistics {
    @Override
    public void Send () {
        System.out.println("Sending via Train");
    }
}

class LogisticsFactory {
    public static Logistics getLogistics(String mode) {
        if (mode == "Road") {
            return new Road();
        } else if (mode == "Air") {
            return new Air();
        }
        return new Train();
    }
}

class LogisticsService {
    public void Send (String mode) {
       Logistics defLogistics = LogisticsFactory.getLogistics(mode);
       defLogistics.Send();
    }
}

class Factory {
public static void main (String [] args) {
LogisticsService ls1 = new LogisticsService();
ls1.Send("Air");
ls1.Send("Road");
ls1.Send("Train");
}
}