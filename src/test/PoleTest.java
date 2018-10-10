package test;

import creepinggame.Ant;
import creepinggame.Pole;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PoleTest {
    private Pole pole;

    @Before
    public void initialize()
    {
        pole=new Pole(100);
    }

    @Test
    public void the_isoverflow_should_be_false_if_ant_is_in_the_range_of_pole_after_isOverflow_is_invoked_else_should_be_true()
    {
        Ant ant=new Ant();
        for(int i=1;i<=99;i++) {
            ant.setPosition(i);
            assertEquals(false,pole.isOverflow(ant));
        }
        for(int i=-100;i<=0;i++)
        {
            ant.setPosition(i);
            assertEquals(true,pole.isOverflow(ant));
        }
        for(int i=100;i<200;i++) {
            ant.setPosition(i);
            assertEquals(true, pole.isOverflow(ant));
        }
    }

}
