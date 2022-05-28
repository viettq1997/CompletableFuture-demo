package vn.com.viettq.async.combine;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/***
 * @author Tran Quoc Viet
 * @version 1.0
 * @since 5/28/2022
 */
public class AnyAllOfExample {
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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
//        String f1 = future1().join();
//        String f2 = future2().join();
//        String f3 = future3().join();
//        System.out.println(f1 + f2 + f3);
        CompletableFuture<String> future = CompletableFuture.allOf(future1(), future2(), future3()).thenApply(unused -> {
            String f1 = future1().join();
            String f2 = future2().join();
            String f3 = future3().join();
            String result = f1 + f2 + f3;
            System.out.println(result);
            return result;
        });
        System.out.println(future.get());
        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) / 1000);

//        long start = System.currentTimeMillis();
//        CompletableFuture<Object> future = CompletableFuture.anyOf(future1(), future2(), future3());
//        System.out.println(future.join());
//        long end = System.currentTimeMillis();
//        System.out.println("Time taken: " + (end - start)/1000);
    }
}
