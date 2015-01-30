public class HelloWorld {
  public static void main(String[] args) throws InterruptedException {
    Thread myThread = new Thread() {
      public void run() {
        System.out.println("Hello from new thread");
      }
    };

    myThread.start();
    Thread.yield();
    System.out.println("Hello from main thread");
    myThread.join();
  }
}

// Because this doesn't force any sort of serialization, the order in which
// the system prints "Hellow from new thread" and "Hello from main thread"
// are not guaranteed.