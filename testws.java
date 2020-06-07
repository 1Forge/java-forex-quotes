// import java.util.concurrent.CountDownLatch;

import com._1forge.java.ForexDataClient;
import com._1forge.java.SocketClientListener;

public class testws {
    static ForexDataClient client;

    public static void main(final String args[]) {
        // Initialize the client
        // final CountDownLatch latch = new CountDownLatch(1);

        SocketClientListener listener = new SocketClientListener() {

            @Override
            public void onUpdate(String update) {
                System.out.println("UPDATE: " + update);
            }

            @Override
            public void onLoginSuccessful() {
                try {
                    // client.subscribeToAll();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };

        client = new ForexDataClient("0JHZqgJf7V3tvd7BA3MGThQB3NqVX7F9", listener);

        try {
            client.Connect();
            // client.subscribeTo("EUR/USD");
        } catch (final Exception e) {
            System.out.println(e.toString());
        }

        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // try {
        // latch.await();
        // } catch (final InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }
}