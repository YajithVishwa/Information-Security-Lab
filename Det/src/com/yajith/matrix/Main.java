package com.yajith.matrix;

import java.math.BigInteger;
import java.util.*;

public class Main {
    static List<Integer> l=new ArrayList<>();
    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        if (a == b)
            return a;
        if (a > b)
            return gcd(a-b, b);
        return gcd(a, b-a);
    }
    static int countPrimitiveRoots(int p)
    {
        int result = 1;
        for (int i = 2; i < p; i++)
            if (gcd(i, p) == 1)
                result++;

        return result;
    }
    public static void main (String[] args) {
        int p = 20;
        int q = 50;
        for(int i=p;i<q;i++)
        {
            l.add(countPrimitiveRoots(i-1));
        }
        Set<Integer> s=new LinkedHashSet<>(l);
        l.clear();
        for(Integer a:s)
        {
            l.add(a);
        }
        Collections.sort(l);
        for(Integer b:l)
        {
            if(b>=20) {
                System.out.println(b);
            }
        }

    }
}
