import java.util.concurrent.Semaphore;

public class BusStop {
    private int maxBusesPerStop;
    private String[] stopNames;
    private int[] currentBuses;
    private Semaphore[] semaphores;

    public BusStop(String[] stopNames, int maxBusesPerStop) {
        this.stopNames = stopNames;
        this.maxBusesPerStop = maxBusesPerStop;
        this.currentBuses = new int[stopNames.length];
        this.semaphores = new Semaphore[stopNames.length];

        for (int i = 0; i < stopNames.length; i++) {
            this.currentBuses[i] = 0;
            this.semaphores[i] = new Semaphore(maxBusesPerStop);
        }
    }

    public void arrive(int stopIndex, String busName) throws InterruptedException {
        semaphores[stopIndex].acquire();
        System.out.println(busName + " прибыл на остановку " + stopNames[stopIndex]);
        synchronized (this) {
            currentBuses[stopIndex]++;
        }
    }

    public void depart(int stopIndex, String busName) throws InterruptedException {
        synchronized (this) {
            currentBuses[stopIndex]--;
        }
        System.out.println(busName + " отправился с остановки " + stopNames[stopIndex]);
        semaphores[stopIndex].release();
    }

    public static void main(String[] args) {
        String[] stopNames = {"Площадь Ленина", "Улица Гагарина", "Железнодорожный вокзал", "Парк им. Горького", "Улица Карла Маркса"};
        int maxBusesPerStop = 2;

        BusStop busStop = new BusStop(stopNames, maxBusesPerStop);

        Bus bus1 = new Bus("Автобус 1", busStop, 0, stopNames.length);
        Bus bus2 = new Bus("Автобус 2", busStop, 1, stopNames.length);
        Bus bus3 = new Bus("Автобус 3", busStop, 1, stopNames.length);
        Bus bus4 = new Bus("Автобус 4", busStop, 2, stopNames.length);

        bus1.start();
        bus2.start();
        bus3.start();
        bus4.start();
    }
}

class Bus extends Thread {
    private String busName;
    private BusStop busStop;
    private int currentStop;
    private int numStops;

    public Bus(String busName, BusStop busStop, int startStop, int numStops) {
        this.busName = busName;
        this.busStop = busStop;
        this.currentStop = startStop;
        this.numStops = numStops;
    }

    public void run() {
        try {
            int k = 0;
            while (k<3) {
                Thread.sleep((long) (Math.random() * 10000));
                busStop.arrive(currentStop, busName);
                Thread.sleep((long) (Math.random() * 10000));
                busStop.depart(currentStop, busName);

                int nextStop;
                do {
                    nextStop = (int) (Math.random() * numStops);
                } while (nextStop == currentStop);

                currentStop = nextStop;
                k++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}