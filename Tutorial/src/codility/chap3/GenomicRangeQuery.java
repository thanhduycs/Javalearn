package codility.chap3;

//https://codility.com/demo/results/demoE4QW2J-UT5/

public class GenomicRangeQuery {
	public int[] solution(String S, int[] P, int[] Q) {
        int [] impact_factors = new int[S.length()];
        
        final char[] LETTERS = {'A', 'C', 'G', 'T'}; //--> 0, 1, 2, 3
        
        for (int i=0; i<impact_factors.length; i++)
        {
            int letter = S.charAt(i);
            for(int k=0; k<LETTERS.length; k++)
            {
                if (letter == LETTERS[k])
                    impact_factors[i] = k;
            }
        }
        
        int[][] final_counters = new int[impact_factors.length+1][4];
        int[] current_counters = new int[4]; 
        
        for (int i=1; i<=impact_factors.length; i++)
        {
            int imp_factor = impact_factors[i-1]; //
            current_counters[imp_factor]++;
            
            for (int k=0; k<current_counters.length; k++)
                final_counters[i][k] = current_counters[k];
        }
        
        //main
        int[] results = new int[P.length];
        for(int i=0; i<P.length; i++)
        {
            int p = P[i];
            int q = Q[i]+1;
            
            for (int k=0; k<4; k++)
            {
                if (final_counters[q][k] - final_counters[p][k] > 0)
                {
                    results[i] = k+1;
                    break;
                }
            }
        }
        
        
        return results;
    }
}
