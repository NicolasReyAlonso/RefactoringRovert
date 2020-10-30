package refactoring;

import refactoring.Rover.Action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Rover {
	private Heading heading;
	private Position position;
	Map<Order, Action> actions = new HashMap<>();
	private Map <Position, Boolean> mapa = new HashMap<>();


	//Static initializer for hash map:
	{
		this.actions.put(Order.Forward, () -> this.position = this.position.forward(this.heading));
		this.actions.put(Order.Backward, () -> this.position = this.position.backward(this.heading));
		this.actions.put(Order.Left, () -> this.heading = this.heading.turnLeft());
		this.actions.put(Order.Right, () -> this.heading = this.heading.turnRight());
	}

	public Rover(String facing, int x, int y) {
		this.heading=Heading.of(facing);
		this.position = new Position(x,y);
		updateMap();
	}

	public Rover(Heading heading, Position position) {
		this.heading=heading;
		this.position = position;
		updateMap();
	}

	public void go(Order... orders){
		for (Order order:orders) {
				this.actions.get(order);
		}
	}
	public void go(String a){
		Arrays.stream(a.split(""));
	}

	public static class Position {
		private final int x;
		private final int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		public Position forward(Heading heading) {
			if (heading == Heading.North || heading == Heading.South){return horizontalMovement(heading, 1);}
			return verticalMovement(heading, 1);
		}
		public Position backward(Heading heading) {
			if (heading == Heading.North || heading == Heading.South){return horizontalMovement(heading, -1);}
			return verticalMovement(heading, -1);
		}

		private Position horizontalMovement(Heading heading, int sign){
			if (heading == Heading.North){return new Position(x, y+(1*sign)); }
			else {return new Position(x, y-(1*sign));}
		}
		private Position verticalMovement(Heading heading, int sign){
			if (heading == Heading.East){return new Position(x+(1*sign), y); }
			else {return new Position(x-(1*sign), y);}
		}

		@Override
		public boolean equals(Object object) {
			return isSameClass(object) && equals((Position) object);
		}

		private boolean equals(Position position) {
			return position == this || (x == position.x && y == position.y);
		}

		private boolean isSameClass(Object object) {
			return object != null && object.getClass() == Position.class;
		}

	}
		private boolean BlockControl(Position xy){
			updateMap();
			return this.mapa.get(xy);
		}
		private boolean BlockControl(Map<Position,Boolean> mapa, Position xy){
			return mapa.containsValue(xy);
		}

		private void updateMap(){
			mapa.clear();
			mapa.put(new Position(this.position.getX()+1,this.position.getY()), generateRanBool());
			mapa.put(new Position(this.position.getX()-1,this.position.getY()), generateRanBool());
			mapa.put(new Position(this.position.getX(),this.position.getY()-1), generateRanBool());
			mapa.put(new Position(this.position.getX(),this.position.getY()+1), generateRanBool());
		}


		//metodo que genera booleanos aleatorios para probar "updateMap"
		private boolean generateRanBool(){
			if (Math.random() < 0.5){
				return  false;
			}return true;
		}

	public enum Heading {
		North, East, South, West;

		public static Heading of(String label) {
			return of(label.charAt(0));
		}

		public static Heading of(char label) {
			if (label == 'N') return North;
			if (label == 'S') return South;
			if (label == 'W') return West;
			if (label == 'E') return East;
			return null;
		}


		public Heading turnRight() {
			return values()[add(+1)];
		}

		public Heading turnLeft() {
			return values()[add(-1)];
		}

		private int add(int offset) {
			return (this.ordinal() + offset + values().length) % values().length;
		}

	}
	public enum Order { Forward, Backward, Left, Right; }



	public interface Action {
		void execute();
	}


}

