package service;

import model.Faction;
import model.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class SimulationRunner {
  private static final Logger log = LoggerFactory.getLogger(SimulationRunner.class);

  public void simulate() {
    Phaser phaser = new Phaser(1);

    Factory factory = new Factory(phaser);
    Faction world = new Faction(factory, phaser);
    Faction wednesday = new Faction(factory, phaser);

    try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
      for (int i = 0; i < 100; ++i) {
        executorService.submit(factory);
        phaser.arriveAndAwaitAdvance();

        executorService.submit(world);
        executorService.submit(wednesday);
        phaser.arriveAndAwaitAdvance();
      }

      int worldRobots = world.getRobotCount();
      int wednesdayRobots = wednesday.getRobotCount();

      if (worldRobots > wednesdayRobots) {
        log.info("World has won. {} vs {}", worldRobots, wednesdayRobots);
      } else if (worldRobots < wednesdayRobots) {
        log.info("Wednesday has won. {} vs {}", wednesdayRobots, worldRobots);
      } else {
        log.info("Tie. {} vs {}", worldRobots, wednesdayRobots);
      }
    }
  }
}
