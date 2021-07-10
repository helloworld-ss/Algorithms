package com.yitu._15排序;

import java.text.DecimalFormat;

@SuppressWarnings("unchecked")
public abstract class Sort<T extends Comparable<T>> implements Comparable<Sort<T>>{
    protected T[] array;
    /**
     * 比较次数
     */
    private int cmpCount;
    /**
     * 交换次数
     */
    private int swapCount;
    /**
     * 消耗时间 ms 毫秒
     */
    private long time;
    private DecimalFormat fmt=new DecimalFormat("#.00");

    public void sort(T[] array){
        if(array==null||array.length<2) return;
        this.array=array;

        long begin=System.currentTimeMillis();
        sort();
        time=System.currentTimeMillis()-begin;
    }

    @Override
    public int compareTo(Sort<T> o) {
        int result= (int) (time-o.time);
        if(result!=0) return result;

        result=cmpCount-o.cmpCount;
        if(result!=0) return result;

        return swapCount-o.swapCount;
    }

    /**
     * 返回值等于0，代表 array[i1] == array[i2]
     * 返回值小于0，代表 array[i1] < array[i2]
     * 返回值大于0，代表 array[i1] > array[i2]
     */
    protected int cmp(int i1,int i2){
        cmpCount++;
        return array[i1].compareTo(array[i2]);
    }

    protected int cmp(T v1,T v2){
        cmpCount++;
        return v1.compareTo(v2);
    }

    protected void swap(int i1,int i2){
        swapCount++;
        T tmp=array[i1];
        array[i1]=array[i2];
        array[i2]=tmp;
    }

    private String numberString(int number) {
        if (number < 10000) return "" + number;

        if (number < 100000000) return fmt.format(number / 10000.0) + "万";
        return fmt.format(number / 100000000.0) + "亿";
    }

    @Override
    public String toString() {
        String timeStr = "耗时：" + (time / 1000.0) + "s(" + time + "ms)";
        String compareCountStr = "比较：" + numberString(cmpCount);
        String swapCountStr = "交换：" + numberString(swapCount);
//        String stableStr = "稳定性：" + isStable();
        return "【" + getClass().getSimpleName() + "】\n"
//                + stableStr + " \t"
                + timeStr + " \t"
                + compareCountStr + "\t "
                + swapCountStr + "\n"
                + "------------------------------------------------------------------";
    }

    protected abstract void sort();
}
