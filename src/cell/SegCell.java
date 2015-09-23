package cell;

import location.Location;
import state.SegState;
import state.State;
import gui.CellSocietyGUI;
import gui.SquareCellGUI;

public class SegCell extends Cell {
	public static final double CELL_SIZE = 70;

	private static final int EMPTY_STATE = 0;
	private static final int BLUE_STATE = 1;
	private static final int YELLOW_STATE = 2;
	
	private double mySimilarity = 0.50;

	public SegCell(State s, Location l, CellSocietyGUI CSGUI) {
		super(s, l, CSGUI);

		myCellGUI = new SquareCellGUI(CSGUI, l);
		myCellGUI.updateState(s);
	}
	
	public void setSimilarity(double similarity) {
		mySimilarity = similarity;
	}

	@Override
	public void determineNextState() {
	}
	
	public void determineNextState(State s){
		myState.setNextState(s.getStateInt());
	}

	public boolean isSatisfied() {
		if (getState().getStateInt() == EMPTY_STATE)
			return false;
		int otherState = BLUE_STATE + YELLOW_STATE - getState().getStateInt();
		int sameNeighborSize = getNeighborsInState(myState).size();
		int otherNeighborSize = getNeighborsInState(new SegState(otherState)).size();
		return ((double)sameNeighborSize)/(sameNeighborSize+otherNeighborSize)>=mySimilarity;
	}
	
	public boolean isEmpty() {
		return getState().getStateInt() == EMPTY_STATE;
	}

	public void remove() {
		myCellGUI.remove();
	}

	@Override
	public void goToNextState() {
		super.goToNextState();
		myCellGUI.updateState(myState);
	}

}
