package service;

import model.Faction;
import model.Factory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimulationTest {
  @Test
  void testSimulation() {
    Phaser phaser = new Phaser(1);

    Factory factory = new Factory(phaser);
    Faction world = new Faction("World", factory, phaser);
    Faction wednesday = new Faction("Wednesday", factory, phaser);

    try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
      for (int i = 0; i < 100; ++i) {
        System.out.println("day " + (i + 1));
        executorService.submit(factory);
        phaser.arriveAndAwaitAdvance();

        executorService.submit(world);
        executorService.submit(wednesday);
        phaser.arriveAndAwaitAdvance();
      }

      int worldRobots = world.getRobotCount();
      int wednesdayRobots = wednesday.getRobotCount();

      if (worldRobots > wednesdayRobots) {
        System.out.println("World wins");
      } else if (worldRobots < wednesdayRobots) {
        System.out.println("Wednesday wins");
      } else {
        System.out.println("Tie");
      }
    }
  }
}
