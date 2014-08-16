/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datastruct.huffman;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author SONY VAIO
 */
public class QueueNode extends LinkedList<Node>
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3833622131400119355L;

	public Node deleteMin()
    {
        Node min = peek();
        int imin = 0;
        int i = 0;
        for(Iterator<Node> it = this.iterator(); it.hasNext() ; i++)
        {
            Node next = it.next();
            if(min.compareTo(next) > 0) { min = next; imin = i; }
        }
        remove(imin);
        return min;
    }
}
