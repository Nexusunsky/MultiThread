package practice.content;

import java.io.DataInputStream;
import java.io.EOFException;
import java.net.URL;

/**
 * 同步获取网页数据内容
 *
 * @author haoliu
 */
public class SyncContentImpl implements Content {
    private byte[] contentbytes;

    public SyncContentImpl(String urlStr) {
        System.out.println(Thread.currentThread().getName() + ": Getting " + urlStr);
        try {
            URL url = new URL(urlStr);
            DataInputStream in = new DataInputStream(url.openStream());
            byte[] buffer = new byte[1];
            int index = 0;
            try {
                while (true) {
                    int c = in.readUnsignedByte();
                    //实现两倍扩容buffer时，数据拷贝
                    if (buffer.length <= index) {
                        byte[] largerbuffer = new byte[buffer.length * 2];
                        System.arraycopy(buffer, 0, largerbuffer, 0, index);
                        buffer = largerbuffer;
                        //                        System.out.println("Enlarging buffer to " + buffer.length);
                    }
                    buffer[index++] = (byte) c;
                    //                    System.out.print("Getting " + index + " bytes from " + urlStr);
                }
            } catch (EOFException e) {
                System.out.println("Get Bytes " + e);
            } finally {
                in.close();
            }
            contentbytes = new byte[index];
            System.arraycopy(buffer, 0, contentbytes, 0, index);
        } catch (Exception e) {
            System.out.println("ArrayCopy EOFException" + e.getMessage());
        }
    }

    @Override
    public byte[] getBytes() {
        return contentbytes;
    }
}