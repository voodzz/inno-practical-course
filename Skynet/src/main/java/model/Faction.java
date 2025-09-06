package model;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Phaser;

public class Faction implements Runnable {

  private final String name;
  private final Factory factory;
  private final Phaser phaser;
  private final Map<RobotPart, Integer> partMap = new EnumMap<>(RobotPart.class);
  private int robotCount = 0;

  public Faction(String name, Factory factory, Phaser phaser) {
    this.name = name;
    this.factory = factory;
    this.phaser = phaser;
    phaser.register();
  }

  @Override
  public void run() {
    phaser.arriveAndAwaitAdvance();

    for (int i = 0; i < 5; ++i) {
      RobotPart part = factory.getStorage().poll();
      if (part != null) {
        partMap.put(part, partMap.getOrDefault(part, 0) + 1);
      }
    }
    buildRobots();

    phaser.arriveAndDeregister();
  }

  private void buildRobots() {
    Optional<Integer> robots = partMap.values().stream().min(Comparator.naturalOrder());
    robots.ifPresent(
        integer -> {
          robotCount += integer;
          partMap.forEach((k, v) -> partMap.merge(k, -1 * integer, Integer::sum));
        });
  }
}
