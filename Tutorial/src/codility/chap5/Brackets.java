package codility.chap5;

//https://codility.com/demo/results/demo2RWTZB-D6P/
public class Brackets {
	public int solution(String S) {
        char [] stacks = new char[S.length()];
        int stack_length = 0;
        for(int i =0 ; i<S.length(); i++)
        {
            char chr = S.charAt(i);
            if (chr == '{' || chr == '[' || chr == '(')
            {
                stacks[stack_length++] = chr; //push open closet into stacks
            } else if (stack_length == 0)
            {
                return 0;
            } else
            {
                char open = stacks[--stack_length]; //pop from stack
                if (chr == '}' && open != '{')
                    return 0;
                if (chr == ']' && open != '[')
                    return 0;
                if (chr == ')' && open != '(')
                    return 0;
            }
        }
        
        if(stack_length != 0)
        	return 0;
        return 1;
    }
}
