package com.study.code.juc.atomics;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

/**
 * @ClassName: LongAdderApiDemo
 * @Description:
 * @Author: jay
 * @Date: 2022/11/3 15:26
 **/
public class LongAdderApiDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.longValue());


        // LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);
        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        }, 0);

        // 1+3
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(3);

        System.out.println(longAccumulator.get());
    }
}
