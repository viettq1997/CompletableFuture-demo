package vn.com.viettq.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/***
 * @author Tran Quoc Viet
 * @version 1.0
 * @since 5/27/2022
 */
public class SupplyAsync {
    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Supplier<String> supplier = () -> {
            delay(1);
            System.out.println("I am in supplier - " + Thread.currentThread().getName());
            return "Hello from Supplire";
        };

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(supplier, executorService);
        System.out.println("I am in main - " + Thread.currentThread().getName());

        String value = completableFuture.join();
        System.out.println("Value - " + value);
    }
}
