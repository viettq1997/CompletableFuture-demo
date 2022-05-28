package vn.com.viettq.async.combine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/***
 * @author Tran Quoc Viet
 * @version 1.0
 * @since 5/28/2022
 */
public class ComposeExample {
    public static void delay(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static CompletableFuture<String> getUserDetails() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("GetuserDetails() " + Thread.currentThread().getName());
            delay(2);
            return "UserDtails";
        });
    }

    public static CompletableFuture<String> getWishList(String user) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("getWishList() - " + user + " - " + Thread.currentThread().getName());
            delay(3);
            return "User's WishList";
        });
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> future = getUserDetails().thenCompose(ComposeExample::getWishList);
        System.out.println("Doing something");
        delay(4);
        System.out.println(future.join());
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime)/1000);
    }
}
