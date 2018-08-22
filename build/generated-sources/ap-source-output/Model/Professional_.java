package Model;

import Model.Member;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-20T13:35:17")
@StaticMetamodel(Professional.class)
public class Professional_ { 

    public static volatile SingularAttribute<Professional, String> profession;
    public static volatile SingularAttribute<Professional, String> firstName;
    public static volatile SingularAttribute<Professional, String> lastName;
    public static volatile SingularAttribute<Professional, String> education;
    public static volatile SingularAttribute<Professional, String> phone;
    public static volatile SingularAttribute<Professional, String> about;
    public static volatile SingularAttribute<Professional, Member> member;
    public static volatile SingularAttribute<Professional, String> email;

}