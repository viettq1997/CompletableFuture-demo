package vn.com.viettq.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/***
 * @author Tran Quoc Viet
 * @version 1.0
 * @since 5/27/2022
 */
public class CallbackExample {
    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(
                () -> {
//                    delay(2);
                    return "Viettq";
                }
        )
                .thenApplyAsync(s -> "abcd")
                .thenApplyAsync(s -> "ttttt")
                .thenAcceptAsync(s -> {
                    delay(2);
                            System.out.println("Result: " + s);
                        }
                ).thenAcceptAsync(s -> {
                    delay(1);
                    System.out.println("second");
                });

        completableFuture.join();
    }
}
