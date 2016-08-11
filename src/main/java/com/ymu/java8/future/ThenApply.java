package com.ymu.java8.future;

import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * Some experiments with the {@link CompletableFuture#thenApply(Function)}
 * <p>
 * Created by Yannick Muller on 11/08/2016.
 */
public class ThenApply {

    private static int TIME_TO_WAIT_IN_MILLISECONDS = 2000;
    private CompletableFuture<String> cf1;
    private ExecutorService executor = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws Exception
    {
        ThenApply f = new ThenApply();
        f.doThenApply();
    }


    public void doThenApply() throws Exception
    {
        cf1 = CompletableFuture.supplyAsync(() -> doCf1(), executor);

        System.out.println(MessageFormat.format("Thread : {0} | cf1.isDone() : {1} |", Thread.currentThread().getName(), cf1.isDone()));
        cf1.thenApply((res) -> doApply(res));
    }

    private String doApply(String res)
    {
        System.out.println(MessageFormat.format("Thread : {0} | cf1.isDone() : {1} |", Thread.currentThread().getName(), cf1.isDone()));
        System.out.println(MessageFormat.format("Result from cf1 : {0}", res));
        return null;
    }

    private String doCf1()
    {
        try
        {
            Thread.sleep(TIME_TO_WAIT_IN_MILLISECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("doCf1 is finished");
        return "doCf1 is finished";
    }
}
