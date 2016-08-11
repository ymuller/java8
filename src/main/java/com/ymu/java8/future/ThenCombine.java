package com.ymu.java8.future;

import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Some experiments with the {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
 *
 * Created by Yannick Muller on 11/08/2016.
 */
public class ThenCombine {

    private static int TIME_TO_WAIT_IN_MILLISECONDS = 2000;
    private static int TIME_TO_WAIT_IN_MILLISECONDS_2 = 4000;
    private CompletableFuture<String> cf1;
    private CompletableFuture<String> cf2;
    private ExecutorService executor = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws Exception
    {
        ThenCombine f = new ThenCombine();
        f.doThenCombine();
    }


    public void doThenCombine() throws Exception
    {
        cf1 = CompletableFuture.supplyAsync(() -> doCf1(), executor);
        cf2 = CompletableFuture.supplyAsync(() -> doCf2(), executor);

        System.out.println(MessageFormat.format("Thread : {0} | cf1.isDone() : {1} | cf2.isDone() : {2}", Thread.currentThread().getName(), cf1.isDone(), cf2.isDone()));
        cf1.thenCombine(cf2, (res1, res2) -> doCombine());
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

    private String doCf2()
    {
        try
        {
            Thread.sleep(TIME_TO_WAIT_IN_MILLISECONDS_2);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("doCf2 is finished");
        return "doCf2 is finished";
    }


    private String doCombine()
    {
        System.out.println(MessageFormat.format("Thread : {0} | cf1.isDone() : {1} | cf2.isDone() : {2}", Thread.currentThread().getName(), cf1.isDone(), cf2.isDone()));

        return null;
    }
}
