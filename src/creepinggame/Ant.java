package creepinggame;

public class Ant {

	private int Direction;
	private int Position ;
	private int Velocity ;
	private boolean isOut = false;
	
	public Ant() {
		// TODO Auto-generated constructor stub
	}

	public void setIsOut(boolean status) {
		this.isOut=status;
	}
	
	public boolean getIsOut() {
		return isOut;
	}

	public void setDirection(int direction) {
		Direction = direction;
	}

	public int getDirection() {
		return Direction;
	}
	
	public int getPosition() {
		return Position;
	}

	public void setPosition(int position) {
		Position = position;
	}

	public void setVelocity(int velocity) {
		Velocity = velocity;
	}

	public int getVelocity(){return Velocity;}

    public void changeDirection() {
    	this.setDirection(0-Direction);
    }
    
    public void creeping() {
    	int position=this.getPosition();
    	int newPosition=position+this.Direction*this.Velocity;
        this.setPosition(newPosition);
    }
}

