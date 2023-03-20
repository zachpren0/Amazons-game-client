package ActionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import GameState.GameBoardState;
import Search.TreeSearch.node;


public class Node extends GameBoardState  {
	
		

		int id = 1;
		static int count = 1;
		
		private int mini =0;
		private double M =0; //monte-carlo value
		private double h =0; //hueristic
		private double g =0; //g-val
		public double C = 1; //carlo constant
		
		private int wins = 0;
		private int visits = 1; 
		private boolean expanded = false;
		private double ucb;
		
		Node parent;
		ArrayList<Node> children = new ArrayList<>();
		
		
		// F(n) = Evaluation(node) + C * root(ln(Visits(parent(node))/Visits(node)
		public double getUCB() {return Math.abs( wins/visits + C*Math.sqrt(Math.log(parent.visits)/visits));} //UCBI Score
		public void setHueristicScore(double newVal) {h = newVal;}
		public void setGval(double newVal){g=newVal;}

		
		//-----CONSTRUCTORS-----------

		public Node(ArrayList<Integer> gameBoard) {
			super(gameBoard);			
			id= count++;
		}
		public Node(int[][] gameBoard) {
			super(gameBoard);
			id= count++;
		}
		public Node(int[][] gameBoard, Node parent) {
			super(gameBoard);
			this.setParent(parent);
			id= count++;
		}
		/**
		 * @deprecated
		 * @param node
		 */
		public Node(Node node) {
			super(node.getCurBoard());
			g =node.g;
			M = node.M;
			h = node.h;
			C = node.C;
			id = node.id;
			wins = node.wins;
			visits = node.visits;
			
			parent = node.parent;
			for(Node n : node.children)
				children.add(n);
			
			moveInfo = node.moveInfo;
			
		}
		
		//---HELP ME--------------	
		//meant to be called through backprpagation to update weather the node that it has been visited and weather it won.
		public void updateNode(boolean didWin) {
			if(didWin)
				wins++;
			visits++;
		}
		public int getvisits() {return visits;}
//		public String toString() {
//			return "" + id;
//		}
//		
		public void setParent(Node node) {
			parent = node;
		}
		public Node getParent() {
			return this.parent;
		}
		public void addChild(Node node) {
			children.add(node);
		}		
		public void setChildren(ArrayList<Node> children) {
			this.children = children;
		}	
		public ArrayList<Node> getChildren() {
			return this.children;
		}	
		public boolean isExpanded() {
			return this.expanded;
		}
		public void setExpanded(boolean expanded) {
			this.expanded = expanded;
		}
		public int getVisits() {
			return this.visits;
		}
		public void incrVisits() {
			this.visits++;
		}
		public int getWins() {
			return this.wins;
		}
		public void incrWins(int simResult) {
			this.wins = this.wins + simResult;
		}
		public void RemoveChild(Node node) {
			children.remove(node);
		}
		public int childCount() {return children.size();}

	
}
