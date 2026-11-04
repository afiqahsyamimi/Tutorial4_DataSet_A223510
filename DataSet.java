/*
TUTORIAL 4: DataSet
AFIQAH SYAMIMI BINTI MOHD SOUFI
A223510
*/

public class DataSet
{
    private double sum;
    private Measurable max;
    private int count;

    public DataSet()
    {
        sum = 0;
        count = 0;
        max = null;
    }

    public void add(Measurable x)
    {
        sum += x.getMeasure();
        count++;

        if(max == null || x.getMeasure() > max.getMeasure())
        {
            max = x;
        }
    }

    public Measurable getMax()
    {
        return max;
    }

    public double getAverage()
    {
        if(count == 0)
            return 0;

        return sum / count;
    }
}

//Reference: Copilot