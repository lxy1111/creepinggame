package test;

import creepinggame.CreepingGame;
import creepinggame.Ant;
import creepinggame.CreepingGame;
import creepinggame.Pole;
import org.junit.Before;
import org.junit.Test;

import static creepinggame.CreepingGame.*;
import static org.junit.Assert.assertEquals;

public class CreepingGameTest {
    private Ant ants[]=new Ant[5];
    private Pole pole;
    @Before
    public void initialize()
    {
        for(int i=0;i<5;i++)
        {
          ants[i]=new Ant();
        }
        pole=new Pole(300);
    }
    @Test
    public void the_iscollisoin_should_return_true_if_two_ants_is_in_collision()
    {
        ants[0].setPosition(100);
        ants[1].setPosition(100);
        assertEquals(true,isCollision(ants,0));
        assertEquals(true,isCollision(ants,1));
        ants[1].setPosition(200);
        ants[2].setPosition(200);
        assertEquals(true,isCollision(ants,1));
        assertEquals(true,isCollision(ants,2));
        ants[2].setPosition(300);
        ants[3].setPosition(300);
        assertEquals(true,isCollision(ants,2));
        assertEquals(true,isCollision(ants,3));
        ants[3].setPosition(400);
        ants[4].setPosition(400);
        assertEquals(true,isCollision(ants,3));
        assertEquals(true,isCollision(ants,4));
    }


    @Test
    public void the_ant_should_be_set_is_Out_if_the_ant_is_out_of_the_pole()
    {
        ants[0].setPosition(400);
        checkantsstatus(ants,pole);
        assertEquals(true,ants[0].getIsOut());
    }

    @Test
    public void the_game_is_over_if_all_the_ants_are_out_of_the_pole()
    {
        for(int i=0;i<5;i++) {
            ants[i].setIsOut(true);
        }
        assertEquals(true,gameOver(ants,pole));
    }

}
