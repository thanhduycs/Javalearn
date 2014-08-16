/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datastruct.huffman;

/**
 *
 * @author SONY VAIO
 */
public class Node implements Comparable<Node>
{
    public char chr;
    public final int freg;
    public Node left;
    public Node right;

    public Node()
    {
        chr = (char) 0;
        freg = 0;
        left = null;
        right = null;
    }

    public Node(char chr, int freg, Node left, Node right)
    {
        this.chr = chr;
        this.freg = freg;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf()
    {
        return (this.left == null && this.right == null);
    }

    public int compareTo(Node that) {
        return this.freg  - that.freg;
    }
}
