package cn.learn.stack.abuse;

/**
 * Convert Int to a Byte
 * 将 Int 转换为 Byte
 *
 * @author zengxuebin
 * @since 2024/11/21 11:17
 */
public class IntToByteConverter {

    /**
     * myInt的值 (128) 大于一个字节中可以存储的最大值 (127)，因此该值将被截断以适合 8 位范围。 myByte的结果值为 -128
     * the value of myInt (128) is larger than the maximum value that can be stored in a byte (127),
     * so the value will be truncated to fit in the 8-bit range. The resulting value of myByte will be -128.
     */
    public static void main(String[] args) {
        // 最简单方法之一是使用类型转换
        int myInt = 128;
        byte myByte1 = (byte) myInt;
        System.out.println(myByte1);

        // 将 int 转换为 byte 的另一种方法是使用位移位和位掩码
        byte myByte2 = (byte) (myInt & 0xff);
        System.out.println(myByte2);

        // 在 Java 中将 int 转换为 byte（或反之亦然）的另一种方法是使用Byte和Integer类。
        // 例如，Byte类提供了一个名为toUnsignedInt的静态方法，可用于转换，
        // 而Integer类提供了一个名为byteValue的方法，可用于将 int 转换为 byte。
        byte myByte = -128;
        // myByte的值为 -128，超出了有符号字节的范围
        // 但由于使用了toUnsignedInt方法，它会将字节转换为无符号 int，结果将为 128。
        myInt = Byte.toUnsignedInt(myByte);
        System.out.println(myInt);

        // 可以使用Integer类将 int 转换为 byte。
        // 下面是使用Integer类及其辅助方法将 int 转换为 byte 的示例：
        Integer myInteger = new Integer(200);
        myByte = myInteger.byteValue();
        System.out.println(myByte);
    }
}
