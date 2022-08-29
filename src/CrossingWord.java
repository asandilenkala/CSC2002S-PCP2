public class CrossingWord {

    public static WordDictionary dict;

    private String word; // the word
    private int x; // x postition
    private int y; // y posistion
    private int maxX; // max getWidth
    private boolean crossed; //has the word crossed the screen
    private int crossingSpeed; //
    private static int maxWait = 1000;
    private static int minWait = 100;

    CrossingWord() { //constructor with defaults
		word = "computer"; // a default - not used
		x = 0;
		y = 0;	
		maxX = 300;
		crossed = false;
		crossingSpeed = (int)(Math.random() * (maxWait-minWait)+minWait); 
	}
	
	CrossingWord(String text) { 
		this();
		this.word=text;
	}

    CrossingWord(String text, int maxX, int y){
        this(text);
        this.maxX = maxX;
        this.y = y;
    }

    public static void increaseSpeed( ) {
		minWait+=50;
		maxWait+=50;
	}
	
	public static void resetSpeed( ) {
		maxWait=1000;
		minWait=100;
	}
    public synchronized  void setX(int x) {
		if (x > maxX) {
			x = maxX;
			crossed = true; //user did not manage to catch this word
		}
		this.x = x;
	}

    public synchronized  void setY(int y) {
		this.y = y;
	}

    public synchronized  void setWord(String text) {
		this.word=text;
	}

	public synchronized  String getWord() {
		return word;
	}
	
	public synchronized  int getX() {
		return x;
	}	
	
	public synchronized  int getY() {
		return y;
	}

    public synchronized  int getSpeed() {
		return crossingSpeed;
	}

	public synchronized void setPos(int x, int y) {
        setX(x);
		setY(y);
	}

	public synchronized void resetPos() {
		setX(0);
	}

    public synchronized void resetWord() {
		resetPos();
		word = dict.getNewWord();
		crossed = false;
		crossingSpeed = (int)(Math.random() * (maxWait-minWait)+minWait); 
		//System.out.println(getWord() + " falling speed = " + getSpeed());
	}

    public synchronized boolean matchWord(String typedText) {
		if (typedText.equals(this.word)) {
			resetWord();
			return true;
		}
		else
			return false;
	}

    public synchronized  void moveRight(int incr) {
        setX(x + incr);
    }

    public boolean crossed(){ return crossed; }

}