package creepinggame;

public class Pole {
	private int Length = 900;
	private int MinPosition = 0;
	private int MaxPosition = MinPosition+Length;
	
	public Pole(int Length) {
		// TODO Auto-generated constructor stub
		this.Length=Length;
		this.MinPosition=0;
		this.MaxPosition=MinPosition+Length;
	}

	public boolean isOverflow(Ant ant) {
		int position=ant.getPosition();
		if (position>=MaxPosition||position<=MinPosition) {
			ant.setIsOut(true);
			return true;
		}
		return false;
	}
}