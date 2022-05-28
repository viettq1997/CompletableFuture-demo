package vn.com.viettq.async;

import java.util.concurrent.CompletableFuture;

/***
 * @author Tran Quoc Viet
 * @version 1.0
 * @since 5/28/2022
 */
public class ExceptionHandleExample {
    public static void main(String[] args) {
        boolean error = true;
//        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
//            if (error) throw new RuntimeException("Error@Future");
//            return "success";
//        }).handle((s, e) -> {
//            if (e != null) {
//                System.out.println(e.getMessage());
//                return "null";
//            }
//            return s;
//        });
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            if (error) throw new RuntimeException("Error@Future");
            return "success";
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return "null";
        });

        System.out.println(future.join());
    }
}
