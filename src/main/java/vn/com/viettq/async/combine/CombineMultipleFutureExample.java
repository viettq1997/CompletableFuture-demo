package vn.com.viettq.async.combine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/***
 * @author Tran Quoc Viet
 * @version 1.0
 * @since 5/28/2022
 */
public class CombineMultipleFutureExample {
    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static CompletableFuture<String> future1() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 - " + Thread.currentThread().getName());
            delay(2);
            return "1";
        });
    }

    public static CompletableFuture<String> future2() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 - " + Thread.currentThread().getName());
            delay(4);
            return "2";
        });
    }

    public static CompletableFuture<String> future3() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("future3 - " + Thread.currentThread().getName());
            delay(1);
            return "3";
        });
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        CompletableFuture<String> f1 = future1();
        CompletableFuture<String> f2 = future2();
        CompletableFuture<String> f3 = future3();
        String s1 = f1.join();
        String s2 = f2.join();
        String s3 = f3.join();
        System.out.println(s1 + s2 + s3);
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) / 1000);
    }
}
