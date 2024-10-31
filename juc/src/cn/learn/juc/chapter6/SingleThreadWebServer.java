package cn.learn.juc.chapter6;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zengxuebin
 * @since 2024/10/30 14:07
 */
public class SingleThreadWebServer {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(80)) {
            System.out.println("服务器启动成功");
            while (true) {
                Socket connection = serverSocket.accept();
                System.out.println(Thread.currentThread().getName() + "连接成功=>" + connection);
                handleRequest(connection);
            }
        } catch (IOException e) {
            System.out.println("服务器启动失败");
        }
    }

    private static void handleRequest(Socket connection) {
        try {
            InputStream inputStream = connection.getInputStream();;
            byte[] buff = new byte[1024];
            int len;
            while ((len = inputStream.read(buff)) != -1) {
                String msg = new String(buff, 0, len);
                System.out.println("客户端发送来的消息为=>" + msg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
