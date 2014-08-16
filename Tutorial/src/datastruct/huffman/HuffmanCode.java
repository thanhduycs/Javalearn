/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datastruct.huffman;

/**
 *
 * @author SONY VAIO
 */
public class HuffmanCode
{
    public final int code;
    public final int len;
    public String binary = "";

    public HuffmanCode(int code, int len)
    {
        this.code = code;
        this.len = len;

        for(int i=len-1; i>=0; i--)
        {
            binary += (code>>i) & 1;
        }
    }
}
