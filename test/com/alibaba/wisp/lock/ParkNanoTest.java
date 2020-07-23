/*
 * Copyright (c) 2020 Alibaba Group Holding Limited. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation. Alibaba designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */

/*
 * @test
 * @summary Test park nanos
 * @requires os.family == "linux"
 * @run main/othervm -XX:+EnableCoroutine -Dcom.alibaba.wisp.transparentWispSwitch=true ParkNanoTest
 */


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ParkNanoTest {
    public static void main(String[] args) throws Exception {
        doTest(400_000, 300_000);
        doTest(800_000, 500_000);
    }

    private static void doTest(long wait, long expected) throws Exception {
        CountDownLatch l = new CountDownLatch(1);
        long start = System.nanoTime();
        l.await(wait, TimeUnit.NANOSECONDS);
        long diff = System.nanoTime() - start;

        if (diff < expected)
            throw new Error("wake up too early!");
    }
}
