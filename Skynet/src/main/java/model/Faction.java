package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

public class Faction implements Runnable {

  private static final Logger log = LoggerFactory.getLogger(Faction.class);

  private final Factory factory;
  private final Phaser phaser;
  private final Map<RobotPart, Integer> partMap =
      new ConcurrentHashMap<>(RobotPart.class.getEnumConstants().length);
  private int robotCount = 0;

  public Faction(Factory factory, Phaser phaser) {
    this.factory = factory;
    this.phaser = phaser;
  }

  @Override
  public void run() {
    phaser.register();
    List<RobotPart> parts = new ArrayList<>();
    factory.getStorage().drainTo(parts, 5);

    for (RobotPart part : parts) {
      partMap.merge(part, 1, Integer::sum);
    }
    if (canBuildRobots()) {
      buildRobots();
    }

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

  private boolean canBuildRobots() {
    return IntStream.of(
            partMap.getOrDefault(RobotPart.HEAD, 0),
            partMap.getOrDefault(RobotPart.FEET, 0),
            partMap.getOrDefault(RobotPart.TORSO, 0),
            partMap.getOrDefault(RobotPart.HANDS, 0))
        .allMatch(count -> count > 0);
  }

  public int getRobotCount() {
    return robotCount;
  }
}
