package io.improbable.science.test;

import io.improbable.science.*;
import io.improbable.science.test.gameoflife.Simulation;
import junit.framework.TestCase;

public class TestGameOfLife extends TestCase {

    public Conductor conductor;

    public void setUp() {
        this.conductor = new Conductor(new Simulation());
    }

    public void testConductorRuns() {
        this.conductor.run();
    }

}
