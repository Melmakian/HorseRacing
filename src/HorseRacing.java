import java.util.ArrayList;
import java.util.List;

public class HorseRacing {
    public static int countHorses = 10;

    public static void main(String[] args) throws InterruptedException {
        List<Horse> horses = prepareHorsesSndStart();
        while (calculatedHorsesFinished(horses) != countHorses) {

        }
    }

    public static int calculatedHorsesFinished(List<Horse> horses) throws InterruptedException {
        int countFinished = 0;
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).isFinished()) {
                countFinished++;
            } else if (horses.get(i).isFinished() != true) {
                System.out.println("Waiting for " + horses.get(i).getName());
                horses.get(i).join();
            }

        }
        return countFinished;
    }

    public static class Horse extends Thread {
        public Horse(String name) {
            super(name);
        }

        private boolean isFinished;

        public boolean isFinished() {
            return isFinished;
        }

        @Override
        public void run() {
            String s = "";
            for (int i = 0; i < 1001; i++) {
                s += new String("" + i);
                if (i == 1000) {
                    s = " is finished!";
                    System.out.println(getName() + s);
                    isFinished = true;
                }
            }
        }
    }

    public static List<Horse> prepareHorsesSndStart() {
        List<Horse> horses = new ArrayList<>(countHorses);
        String number;
        for (int i = 0; i < countHorses + 1; i++) {
            number = i < 10 ? ("0" + i) : "" + i;
            horses.add(new Horse("Horse_ " + number));
        }
        for (int i = 0; i < countHorses; i++) {
            horses.get(i).start();
        }
        return horses;
    }
}
