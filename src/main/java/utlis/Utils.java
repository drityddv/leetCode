package utlis;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import zuo.constant.SortEnum;

public class Utils {

    private static final String SALT = "ddv";

    public static int[][] array2;

    static {
        array2 = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                array2[i][j] = 1;
            }
        }
    }

    public static void swapElement(int[] array, int a, int b) {
        // System.out.print(a + " " + b + " ");
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;

        // logArray(array);
    }

    public static void logArray2(int[][] mapData) {
        for (int[] mapDatum : mapData) {
            logArray(mapDatum);
        }
    }

    public void swap(char[] chars, int start, int end) {
        int n = (start + end) / 2;
        int index = 0;
        while (start <= n) {
            char temp = chars[end - index];
            chars[end - index] = chars[start];
            chars[start] = temp;
            start++;
            index++;
        }
    }

    public static int[] getRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }

        return array;
    }

    public static void classSort(int[] array) {
        Arrays.sort(array);
    }

    public static boolean isEqual(int[] a, int[] b) {
        if (a.length != b.length)
            return false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                System.out.printf("下标 %d号位不相等!\n", i);
                logArray(a);
                logArray(b);
                return false;
            }
        }
        return true;
    }

    public static void jujgeArray(int times, int size, int id) {
        long startTime = System.currentTimeMillis();
        int rightTimes = 0;
        boolean sign;
        for (int i = 0; i < times; i++) {
            sign = false;

            int[] array = getRandomArray(size);
            int[] rightArray = new int[size];

            for (int j = 0; j < rightArray.length; j++)
                rightArray[j] = array[j];
            System.out.print("样本数据 :");
            logArrayWithMaoHao(array);
            classSort(rightArray);
            SortEnum.getById(id).sort(array);

            sign = isEqual(array, rightArray);
            if (sign) {
                rightTimes++;
            } else {
                break;
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.printf("测试完毕!:样本数量%d,通过次数:%d,正确率:%%%.2f 耗时%d秒", times, rightTimes, (double)rightTimes / times * 100,
            (endTime - startTime) / 1000);
    }

    public static void logArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.printf("%d ", a[i]);
        }
        System.out.println();
    }

    public static void logArrayWithMaoHao(int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (i == a.length - 1) {
                System.out.printf("%d", a[i]);
            } else {
                System.out.printf("%d, ", a[i]);
            }
        }
        System.out.println();
    }

    public static void logArrayWithTwo(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%d\t", a[i][j]);
            }
            System.out.println();
        }
    }

    /** 生成min,max闭区间随机数 */
    public static int getRandom(int min, int max) {
        Random random = new Random();
        return (random.nextInt(max - min + 1)) + min;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static String encode(String password) {
        password = password + SALT;
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte)charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int)md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    // public static void main(String[] args) {
    //// // 运行100W次
    //// int size = 10000 * 100;
    //// Random random = new Random();
    //// Map<Integer, Integer> result = new HashMap<>();
    //// for (int i = 0; i < size; i++) {
    //// int num = random.nextInt(9) + 1;
    //// result.putIfAbsent(num, 0);
    //// int times = result.get(num);
    //// result.put(num, times + 1);
    //// }
    ////
    //// System.out.println("样本数量:" + size);
    //// result.forEach((num, times) -> System.out.println("num :" + num + " times:" + times));
    // }
}
