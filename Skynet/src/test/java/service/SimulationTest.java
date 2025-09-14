package service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SimulationTest {
  private final SimulationRunner sr = new SimulationRunner();

  @Test
  public void testSimulation() {
    assertTimeoutPreemptively(Duration.ofSeconds(1), sr::simulate);
    assertDoesNotThrow(sr::simulate);
  }
}
