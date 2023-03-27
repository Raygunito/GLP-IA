package test;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import process.astar.AStarCore;

public class TestTxTAstar {

  private AStarCore aStarCore;

  public static void main(String[] args) {
    AStarCore core = new AStarCore();
    core.process();
    while (!core.isEnded() && !core.getOpenList().getQueue().isEmpty()) {
      core.process();
    }
    System.out.println(core.showPath(core.getGrid().getCell(core.getGrid().getDIM() - 1, core.getGrid().getDIM() - 1)));
  }

  @Before
  public void setUp() {
    aStarCore = new AStarCore();
  }

  @Test
  public void testProcess() {
    aStarCore.process();
    assertEquals(1, aStarCore.getClosedListSize());
  }

  @Test
  public void testIsEnded() {
    assertFalse(aStarCore.isEnded());
    while (!aStarCore.workFinished()) {
      aStarCore.process();
    }
    assertTrue(aStarCore.isEnded());
  }

  @Test
  public void testWorkFinished() {
    assertFalse(aStarCore.workFinished());
    while (!  aStarCore.isEnded()) {
      aStarCore.process();
    }
    assertTrue(aStarCore.workFinished());
  }

  @Test
  public void testGetClosedListSize() {
    AStarCore aStarCore = new AStarCore();
    aStarCore.process();
    int expectedSize = 1;
    int actualSize = aStarCore.getClosedListSize();
    assertEquals(expectedSize, actualSize);
  }

  @Test
  public void testGetCurrentPathSize() {
    AStarCore aStarCore = new AStarCore();
    aStarCore.process();
    int expectedSize = 1;
    int actualSize = aStarCore.getCurrentPathSize();
    assertEquals(expectedSize, actualSize);
  }

}
