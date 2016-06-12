package chapter04;

/**
 * @author NikoBelic
 * @create 16/6/7 14:21
 */
public class MutablePoint
{
    public int x, y;

    public MutablePoint()
    {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p)
    {
        this.x = p.x;
        this.y = p.y;
    }
}
