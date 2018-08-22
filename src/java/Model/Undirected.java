package Model;

public class Undirected {
    private String name;
    private long picturepath;
    private long id;
    private String path;
    
    
    public Undirected(String na, long pic,long ID)
    {
        name=na;
        picturepath=pic;
        id=ID;
    }
    
    public String getName()
    {
        return name;
    }
    public long getPicturePath()
    {
        return picturepath;
    }
    public long getID()
    {
        return id;
    }
    public void setPath(String x)
    {
        path=x;
    }
    public String getPath()
    {
        return path;
    }
    
}
