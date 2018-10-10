package creepinggame;

import java.util.Vector;

public class PlayRoom {
	private boolean AntDirection;
	private int[] AntPosition;
	private int AntVelocity;
	private double ince;
	private int PoleLength;
	private Ant[] ants;
	private Pole pole;
	private Vector[] possibilities=new Vector[32];
	
	public PlayRoom() {
		// TODO Auto-generated constructor stub
		ants=new Ant[5];
		for(int i=0;i<5;i++)
			ants[i]=new Ant();
		pole=new Pole(900);
		for(int i=0;i<32;i++)
			possibilities[i]=new Vector();	
	}
	
	public void savepossibilitiesofantsdirection() {	
		int a,b,c,d,e;
		int cnt=0;
		for(a=1;a>=-1;a=a-2) {
			ants[0].setDirection(a);
			for (b=1;b>=-1;b=b-2) {
				ants[1].setDirection(b);
				for (c=1;c>=-1;c=c-2) {
					ants[2].setDirection(c);
					for (d=1;d>=-1;d=d-2) {
						ants[3].setDirection(d);
						for (e=1;e>=-1;e=e-2) {
							ants[4].setDirection(e);
				            for(int i=0;i<5;i++)
				            {
				            	possibilities[cnt].addElement(ants[i].getDirection());
				            }
				            cnt++;
				        }
					} 
				}
			}
		}
	}
	
	public void buildantsinitialstatus()
	{
		  for(int i=0;i<5;i++)
			  ants[i].setDirection(1);
		  for(int i=0;i<5;i++)
			{
		        ants[i].setVelocity(15);
			}
			ants[0].setPosition(90);
			ants[1].setPosition(240);
			ants[2].setPosition(330);
			ants[3].setPosition(480);
			ants[4].setPosition(750);
		  for(int i=0;i<5;i++)
				ants[i].setIsOut(false);
	}
	
	public void playGame() {
	    CreepingGame gb=new CreepingGame(ants,pole,possibilities);
	}

	public  static void main(String[] args) {
		// TODO Auto-generated method stub
	  PlayRoom playroom =new PlayRoom();
	  playroom.savepossibilitiesofantsdirection();;
	  playroom.buildantsinitialstatus();;
      playroom.playGame();
	}
}
