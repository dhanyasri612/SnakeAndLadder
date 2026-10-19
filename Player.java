public class Player{
    private int id;
    private String name;
    private int position;

    public Player(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    } 

    public int getPosition(){
        return position;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public void move(int steps){
        setPosition(position + steps);
    }
}