public class Counting {
	public static void main(String[] args) throws InterruptedException {
    class Counter {
      private int count = 0;
      public synchronized void increment() { ++count; }
      public synchronized int getCount() { return count; }
    }

    final Counter counter = new Counter();
    class CountingThread extends Thread {
      public void run() {
        for(int x = 0; x < 10000; ++x)
          counter.increment();
      }
    }

    CountingThread t1 = new CountingThread();
    CountingThread t2 = new CountingThread();
    t1.start(); t2.start();
    t1.join(); t2.join();
    System.out.println(counter.getCount());
  }
}

// Without the 'synchronized' keyword on increment() and, to a lesser 
// degree, on getCount(), the output will potentially be incorrect.