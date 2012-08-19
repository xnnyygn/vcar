package in.xnnyygn.vcar.servlet;

public class Rect {

	private int left;
	private int top;
	private int right;
	private int bottom;
	
	public Rect(int left, int top, int right, int bottom) {
		super();
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}
	
	/**
	 * @return the left
	 */
	public int getLeft() {
		return left;
	}
	
	/**
	 * @return the top
	 */
	public int getTop() {
		return top;
	}
	
	/**
	 * @return the right
	 */
	public int getRight() {
		return right;
	}
	
	/**
	 * @return the bottom
	 */
	public int getBottom() {
		return bottom;
	}
	
}
