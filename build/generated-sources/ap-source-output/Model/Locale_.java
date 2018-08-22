package Model;

import Model.Member;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-20T13:35:17")
@StaticMetamodel(Locale.class)
public class Locale_ { 

    public static volatile SingularAttribute<Locale, String> country;
    public static volatile SingularAttribute<Locale, String> city;
    public static volatile SingularAttribute<Locale, Member> member;
    public static volatile SingularAttribute<Locale, String> state;

}