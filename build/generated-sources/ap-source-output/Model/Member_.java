package Model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-20T13:35:17")
@StaticMetamodel(Member.class)
public class Member_ { 

    public static volatile SingularAttribute<Member, String> salt;
    public static volatile SingularAttribute<Member, String> role;
    public static volatile SingularAttribute<Member, Long> pictureid;
    public static volatile SingularAttribute<Member, String> pass_salt;
    public static volatile SingularAttribute<Member, Boolean> senderDelete;
    public static volatile SingularAttribute<Member, Long> id;
    public static volatile SingularAttribute<Member, Boolean> receiverDelete;

}