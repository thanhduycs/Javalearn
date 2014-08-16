/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datastruct.huffman;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

/**
 *
 * @author SONY VAIO
 */

@SuppressWarnings("unused")
public class Huffman {

    public final static int program = 1321;
    public final static int version = 0;

    public void compress(byte[] data, BitOutputStream outs) throws IOException {
        int freg[] = new int[256];
        int length = data.length;
        for (int i = 0; i < length; i++) {
            freg[data[i] & 0xFF]++;
        }

        Node root = buildTree(freg);
        HuffmanCode Table[] = buildCodeTable(root);
        outs.writeBits(program, 16);
        outs.writeBits(version, 8);
        outs.writeBits(length, 32);
        writeTree(root, outs);
        for (int i = 0; i < length; i++) {
            int chr = data[i] & 0xFF;
            outs.writeBits(Table[chr].code, Table[chr].len);
        }
        outs.flush(true);
    }

    
	@SuppressWarnings({ "static-access" })
	public byte[] decompress(BitInputStream ins) throws IOException {
        int program = ins.readBits(16);
        int version = ins.readBits(8);
        if (this.program != program) {
            JOptionPane.showMessageDialog(null, "File cáº§n Ä‘Æ°á»£c giáº£i nÃ©n khÃ´ng Ä‘Æ°á»£c nÃ©n báº±ng chÆ°Æ¡ng trÃ¬nh nÃ y. Do Ä‘Ã³ khÃ´ng thá»ƒ gá»�i hÃ m nÃ y " +
                    "Ä‘á»ƒ giáº£i nÃ©n.\nVui lÃ²ng kiá»ƒm tra láº¡i !!! ChÆ°Æ¡ng trÃ¬nh sáº½ thoÃ¡t ngay bÃ¢y giá»� !!!",
                    "Lá»—i CÆ¡ Sá»Ÿ Dá»¯ Liá»‡u", JOptionPane.ERROR_MESSAGE);
            System.exit(1321);
        }

        int length = ins.readBits(32);
        Node root = readTree(ins);
        Node node = root;
        int chr;

        byte[] buffer = new byte[length];
        int offset = 0;

        while ((chr = ins.readBit()) != -1 && length > 0) {
            if (chr == 0) {
                node = node.left;
            } else if (chr == 1) {
                node = node.right;
            }

            if (node.isLeaf()) {
                buffer[offset++] = (byte)node.chr;
                node = root;
                length--;
            }
        }
        ins.clear();
        return buffer;
    }

    public HuffmanCode[] buildCodeTable(Node root) {
        class Element {

            public final Node node;
            public final int code;
            public final int len;

            public Element(Node node, int code, int len) {
                this.node = node;
                this.code = code;
                this.len = len;
            }
        }
        HuffmanCode Table[] = new HuffmanCode[256];
        Stack<Element> stack = new Stack<Element>();
        stack.add(new Element(root, 0, 0));

        while (!stack.isEmpty()) {
            Element element = stack.pop();
            if (element.node.isLeaf()) {
                Table[element.node.chr] = new HuffmanCode(element.code, element.len);
            } else {
                stack.add(new Element(element.node.left, (element.code << 1), element.len + 1));
                stack.add(new Element(element.node.right, (element.code << 1) | 1, element.len + 1));
            }
        }
        return Table;
    }

    public Node buildTree(int freg[]) {
        QueueNode queue = new QueueNode();
        for (int i = 0; i < freg.length; i++) {
            if (freg[i] > 0) {
                queue.add(new Node((char) i, freg[i], null, null));
            }
        }
        while (queue.size() > 1) {
            Node left = queue.deleteMin();
            Node right = queue.deleteMin();
            queue.add(new Node((char) 0, left.freg + right.freg, left, right));
        }
        return queue.deleteMin();
    }

    public void writeTree(Node root, BitOutputStream outs) throws IOException {
        if (root.isLeaf()) {
            outs.writeBit(1);
            outs.writeBits(root.chr, 8);
            return;
        }
        outs.writeBit(0);
        writeTree(root.left, outs);
        writeTree(root.right, outs);
    }

    public Node readTree(BitInputStream ins) throws IOException {
        int ch = ins.readBit();
        if (ch == 1) {
            return new Node((char) ins.readBits(8), 0, null, null);
        } else {
            return new Node((char) 0, 0, readTree(ins), readTree(ins));
        }
    }
}
