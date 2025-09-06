package model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Phaser;

public class Factory implements Runnable {

  private static final Logger log = LoggerFactory.getLogger(Factory.class);
  private final BlockingQueue<RobotPart> storage = new LinkedBlockingQueue<>();
  private final Random random = new Random();
  private final Phaser phaser;

  public Factory(Phaser phaser) {
    this.phaser = phaser;
  }

  @Override
  public void run() {
    phaser.register();
    int amount = random.nextInt(11);
    try {
      for (int i = 0; i < amount; ++i) {
        storage.put(RobotPart.getPart());
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.warn("Thread was interrupted: {}", e.getMessage());
    } finally {
      phaser.arriveAndDeregister();
    }
  }

  public BlockingQueue<RobotPart> getStorage() {
    return storage;
  }
}
