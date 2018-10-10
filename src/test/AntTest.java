package test;

import creepinggame.Ant;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

public class AntTest {
    private Ant ant;

    @Before
    public void initialize() {
        ant=new Ant();
    }

    @Test
    public void the_ant_should_be_out_after_the_status_of_the_ant_is_set_to_be_true() {
       ant.setIsOut(true);
       assertEquals(true,ant.getIsOut());
    }

    @Test
    public void the_direction_of_the_ant_should_be_1_after_the_direction_of_the_ant_is_set_to_be_1() {
        ant.setDirection(1);
        assertEquals(1,ant.getDirection());
    }

    @Test
    public void the_direction_of_the_ant_should_be_changed_as_the_opposite_after_the_direction_of_the_ant_is_set()
    {
        ant.setDirection(1);
        ant.changeDirection();
        assertEquals(-1,ant.getDirection());
    }


    @Test
    public void the_position_of_the_ant_should_be_200_after_the_position_of_the_ant_is_set_to_be_200()
    {
        ant.setPosition(200);
        assertEquals(200,ant.getPosition());
    }

    @Test
    public void the_velocity_of_the_ant_should_be_5_after_the_velocity_of_the_ant_is_set_to_be_15()
    {
        ant.setVelocity(5);
        assertEquals(5,ant.getVelocity());
    }

    @Test
    public void the_new_position_of_the_ant_should_be_velocity_multiple_direction_plus_original_position_after_the_method_is_invoked()
    {
        ant.setVelocity(5);
        ant.setDirection(1);
        ant.setPosition(100);
        ant.creeping();
        assertEquals(105,ant.getPosition());
    }
}
