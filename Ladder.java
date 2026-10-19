public class Ladder{
    private int bottom;
    private int top;

    public Ladder(int bottom,int top){
        if (bottom >= top) {
            throw new IllegalArgumentException(
                "Ladder top must be above its bottom"
            );
        }
        this.bottom = bottom;
        this.top = top;
    }

    public int getBottom(){
        return bottom;
    }

    public int getTop(){
        return top;
    }
}