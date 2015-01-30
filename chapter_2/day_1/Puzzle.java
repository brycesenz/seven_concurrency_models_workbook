public class Puzzle {
  static boolean answerReady = false;
  static int answer = 0;
  static Thread t1 = new Thread() {
    public void run() {
      answer = 42;
      answerReady = true;
    }
  };

  static Thread t2 = new Thread() {
    public void run() {
      if (answerReady)
        System.out.println("The meaning of life is: " + answer);
      else
        System.out.println("I don't know the answer");
    }
  };

  public static void main(String[] args) throws InterruptedException {
    t1.start(); t2.start();
    t1.join(); t2.join();
  }
}

// There's an obvious race conditions here.
// But that’s not all—there’s one other result we might see:
//   The meaning of life is: 0

// What?! How can answer possibly be zero if answerReady is true? It’s almost as if
// something switched lines 6 and 7 around underneath our feet.

// Well, it turns out that it’s entirely possible for something to do exactly that.
// Several somethings, in fact:
// • The compiler is allowed to statically optimize your code by reordering
// things.
// • The JVM is allowed to dynamically optimize your code by reordering things.
// • The hardware you’re running on is allowed to optimize performance by
// reordering things.
