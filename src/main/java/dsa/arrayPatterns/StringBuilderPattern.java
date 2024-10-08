package dsa.arrayPatterns;
//· String += c → linear
//· StringBuilder.append(c) → constant
public class StringBuilderPattern
{
    public String appendNtimesUsingStringConcat(char c, int n)
    {
        String str = "";
        for (int i = 0; i < n; i++)
        {
            str += c;    // O(s) where s = length(str)
        }
        return str;
    }

    // O(n)
    public String appendNtimesUsingStringBuilder(char c, int n)
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
        {
            sb.append(c);   // O(1)
        }
        return sb.toString();
    }
}