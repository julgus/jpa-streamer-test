module jpastreamer.test {

    opens com.speedment.jpastreamer.test.model to org.hibernate.orm.core;

    requires jpastreamer.application.standard;
    requires jpastreamer.field;

    requires java.persistence;

    requires org.hibernate.orm.core;
    requires net.bytebuddy;
    requires com.fasterxml.classmate;
    requires java.xml.bind;

}