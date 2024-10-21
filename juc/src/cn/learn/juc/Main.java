package cn.learn.juc;

public class Main {
    public static void main(String[] args) {
        String message = "La compra realizada no otorga kWh, Intente con un importe mayor a B/. &";
        String[] split = message.split("&");

        StringBuffer result = new StringBuffer(message);
        int count = 0;
        while (result.indexOf("&") != -1 && count < 4) {
            int index = result.indexOf("&");
            switch (count) {
                case 0:
                    result.replace(index, index + 1, "         17.00");
                    break;
                case 1:
                    result.replace(index, index + 1, "messageV2");
                    break;
                case 2:
                    result.replace(index, index + 1, "messageV3");
                    break;
                case 3:
                    result.replace(index, index + 1, "messageV4");
                    break;
            }
            count++;
        }
        System.out.println(result);
        System.out.println("Hello Juc!");
    }
}