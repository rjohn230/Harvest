package Model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-20T13:35:17")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, Date> init;
    public static volatile SingularAttribute<Message, Boolean> receiverDel;
    public static volatile SingularAttribute<Message, Long> senderid;
    public static volatile SingularAttribute<Message, Long> receiverid;
    public static volatile SingularAttribute<Message, Long> messageid;
    public static volatile SingularAttribute<Message, String> message;
    public static volatile SingularAttribute<Message, Boolean> senderDel;

}