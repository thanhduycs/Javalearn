/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastruct.huffman;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author SONY VAIO
 */
public class BitInputStream {

    private BufferedInputStream ins = null;
    private byte[] stream = null;
    private int index;
    private int Buffer = 0;
    private int iBitCount = -1;

    public BitInputStream(File file) throws IOException {
        ins = new BufferedInputStream(new FileInputStream(file));
        stream = new byte[1024];
        index = 1024;
    }

    public int read() throws IOException {
        if (index == 1024) {
            int result = ins.read(stream, 0, 1024);
            if (result == 1) {
                return -1;
            }
            index = 0;
        }
        return stream[index++] & 0xFF;
    }

    public int readBit() throws IOException {
        if (iBitCount < 0) {
            Buffer = read();
            iBitCount = 7;
            if (Buffer == -1) {
                return -1;
            }
        }
        int bit = Buffer & (1 << iBitCount--);
        bit = (bit == 0) ? 0 : 1;
        return bit;
    }

    // maximum length is 32
    public int readBits(int len) throws IOException {
        int bits = 0;
        for (int i = 0; i < len; i++) {
            bits = (bits << 1) | readBit();
        }
        return bits;
    }

    public void clear() {
        Buffer = 0;
        iBitCount = -1;
    }

    public void close() throws IOException {
        ins.close();
    }
}
