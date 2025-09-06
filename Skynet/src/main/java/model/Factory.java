package model;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Phaser;

public class Factory implements Runnable {

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
      throw new RuntimeException(e);
    } finally {
      phaser.arriveAndDeregister();
    }
  }

  public BlockingQueue<RobotPart> getStorage() {
    return storage;
  }
}
