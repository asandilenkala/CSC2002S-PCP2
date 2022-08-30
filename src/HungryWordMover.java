import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class HungryWordMover extends Thread {
	private CrossingWord myWord;
	private AtomicBoolean done;
	private AtomicBoolean pause;
	private Score score;
	CountDownLatch startLatch; // so all can start at once

	HungryWordMover(CrossingWord word) {
		myWord = word;
	}

	HungryWordMover(CrossingWord myWord, WordDictionary dict, Score score,
			CountDownLatch startLatch, AtomicBoolean d, AtomicBoolean p) {
		this.myWord = myWord;
		this.startLatch = startLatch;
		this.score = score;
		this.done = d;
		this.pause = p;
	}

	public void run() {

		// System.out.println(myWord.getWord() + " falling speed = " +
		// myWord.getSpeed());
		try {
			System.out.println(myWord.getWord() + " hungry waiting to start ");
			startLatch.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // wait for other threads to start
		System.out.println(myWord.getWord() + " started");
		while (!done.get()) {
			// animate the word
			while (!myWord.crossed() && !done.get()) { // *******************************
				myWord.moveRight(20);
				System.out.println(myWord.getWord() + " " + myWord.getX() + " y : " + myWord.getY());
				try {
					sleep(myWord.getSpeed());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				;
				while (pause.get() && !done.get()) {
				}
				;
			}
			if (!done.get() && myWord.crossed()) { // *******************************************
				score.missedWord();
				myWord.resetWord();
			}
			myWord.resetWord();
		}
	}

}
