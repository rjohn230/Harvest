package Model;

import java.util.ArrayList;
import java.util.List;

public final class Professional_Bundle {
    
    private final Professional prof;
    
    private final Locale location;
    
    private final Subject subjects;
    
    private final Member   member;
    private List<String> list ;
    
    
    public Professional_Bundle(Professional p, Locale l, Subject s, Member m)
    {
        prof=p;
        location=l;
        subjects=s;
        member=m;
        
        list =new ArrayList<>();
        setList(s);
    }
    
    

    public Locale getLocation() {
        return location;
    }

    public Member getMember() {
        return member;
    }
    
    public Professional getProfessional() {
        return prof;
    }
    

    public Subject getSubjects() {
        return subjects;
    }

    public void setList(Subject s)
    {
      
        List<String> foo =  new ArrayList<>();
        
        if(s.getCS()!=null)
        {
            foo.add("Computer Science");
        }
        if(s.getSci()!=null)
        {
            foo.add("Science");
        }
        if(s.getMed()!=null)
        {
            foo.add("Medical");
        }
        if(s.getEgr()!=null)
        {
            foo.add("Engineering");
        }
        if(s.getLaw()!=null)
        {
            foo.add("Law");
        }
        if(s.getBus()!=null)
        {
            foo.add("Business");
        }
        
   list = foo;
   
   
    
    }
    
    public List<String> getList()
    {
        return list;
    }


}
