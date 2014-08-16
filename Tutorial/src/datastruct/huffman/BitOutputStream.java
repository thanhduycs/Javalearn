/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastruct.huffman;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author SONY VAIO
 */
public class BitOutputStream {

    private BufferedOutputStream outs = null;
    private byte[] stream = null;
    private int index = 0;
    private int Buffer = 0;
    private int iBitCount = 7;

    public BitOutputStream(File file) throws IOException {
        this.outs = new BufferedOutputStream(new FileOutputStream(file));
        stream = new byte[1024];
        index = 0;
    }

    public void write(int buffer) throws IOException {
        stream[index++] = (byte) buffer;
        if (index == 1024) {
            outs.write(stream, 0, 1024);
            index = 0;
        }
    }

    public void write(byte[] buffer) throws IOException {
        for (int i = 0; i < buffer.length; i++) {
            stream[index++] = buffer[i];
            if (index == 1024) {
                outs.write(stream, 0, 1024);
                index = 0;
            }
        }
    }

    public void writeBit(int bit) throws IOException {
        if (bit != 0 && bit != 1) {
            throw new IOException("..is not a bit");
        }
        Buffer = (Buffer << 1) | bit;
        iBitCount--;
        if (iBitCount < 0) {
            flush();
        }
    }

    public void writeBits(int bits, int len) throws IOException {
        for (int i = len - 1; i >= 0; i--) {
            writeBit((bits >> i) & 1);
        }
    }

    public void flush() throws IOException {
        flush(false);
    }

    public void flush(boolean force) throws IOException {
        if (iBitCount < 7 || force) {
            Buffer = Buffer << (iBitCount + 1);
            write(Buffer);
            Buffer = 0;
            iBitCount = 7;
        }
    }

    public void close() throws IOException {
        flush();
        if (index != 0) {
            outs.write(stream);
            index = 0;
        }
        outs.close();
    }
}
