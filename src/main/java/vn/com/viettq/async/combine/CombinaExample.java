package vn.com.viettq.async.combine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/***
 * @author Tran Quoc Viet
 * @version 1.0
 * @since 5/28/2022
 */
public class CombinaExample {
    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static CompletableFuture<String> getUserEmail() {
        return CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("getUserEmail() - " + Thread.currentThread().getName());
                    delay(3);
                    return "viettq";
                }
        );
    }

    public static CompletableFuture<String> getWeatherReport() {
        return CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("getWeatherReport() - " + Thread.currentThread().getName());
                    delay(3);
                    return "Weather report of city is - rainy";
                }
        );
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> future = getUserEmail()
                .thenCombine(getWeatherReport(), (e, w) -> {
                    System.out.println("Sending email to " + e + "with report" + w);
                    delay(1);
                    return e + w;
        }).thenCombine(getWeatherReport(), (e, w) -> {
            return e + w;
                });
        System.out.println("Do something!");
        delay(3);
        System.out.println(future.join());
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime)/1000);
    }
}
