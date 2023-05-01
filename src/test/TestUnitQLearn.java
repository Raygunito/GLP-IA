package test;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import process.qlearn.QLearnCore;

public class TestUnitQLearn {
    private QLearnCore qLearnCore;

    @Before
    public void setUp() {
        qLearnCore = new QLearnCore(4, 1000, 0.5f, 0.5f, 0.5f);
    }

    @Test
    public void testDoOneIteration() {
        int initialNbIte = qLearnCore.getNbIte();
        qLearnCore.doOneIteration();
        assertNotEquals(initialNbIte, qLearnCore.getNbIte());
        assertNotEquals(0, qLearnCore.path.size());
        assertEquals(0, qLearnCore.success);
    }


    @Test
    public void testQLearnCore() {
        assertNotNull(qLearnCore.getGrid());
        assertNotNull(qLearnCore.getqTable());
        assertNull(qLearnCore.path);
        assertEquals(qLearnCore.getNbTot(), qLearnCore.getNbIte());
        assertTrue(qLearnCore.getLearningRate() >= 0.01f);
        assertTrue(qLearnCore.getExplorationRate() >= 0.01f);
    }

    @Test
    public void testQLearnFormula() {
        float oldQValue = 0.5f;
        float reward = 1.0f;
        float nextCellMaxQValue = 0.3f;
        float discountFactor = 0.8f;
        float newQValue = qLearnCore.qLearnFormula(oldQValue, reward, nextCellMaxQValue, discountFactor);
        float expectedNewQValue = 0.87f;
        assertEquals(expectedNewQValue, newQValue, 0.01f);
    }
}
