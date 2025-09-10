package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Phaser;

public class Faction implements Runnable {

  private static final Logger log = LoggerFactory.getLogger(Faction.class);

  private final Factory factory;
  private final Phaser phaser;
  private final Map<RobotPart, Integer> partMap = new ConcurrentHashMap<>(RobotPart.class.getEnumConstants().length);
  private int robotCount = 0;

  public Faction(Factory factory, Phaser phaser) {
    this.factory = factory;
    this.phaser = phaser;
  }

  @Override
  public void run() {
    phaser.register();
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

  public int getRobotCount() {
    return robotCount;
  }
}
