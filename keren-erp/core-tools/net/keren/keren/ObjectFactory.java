//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.02.17 at 08:11:24 AM GMT+01:00 
//


package net.keren.keren;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the net.keren.keren package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Template_QNAME = new QName("http://www.keren.net/keren", "template");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: net.keren.keren
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReportRecord }
     * 
     */
    public ReportRecord createReportRecord() {
        return new ReportRecord();
    }

    /**
     * Create an instance of {@link Search }
     * 
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link Field }
     * 
     */
    public Field createField() {
        return new Field();
    }

    /**
     * Create an instance of {@link Menu }
     * 
     */
    public Menu createMenu() {
        return new Menu();
    }

    /**
     * Create an instance of {@link Menuitem }
     * 
     */
    public Menuitem createMenuitem() {
        return new Menuitem();
    }

    /**
     * Create an instance of {@link Action }
     * 
     */
    public Action createAction() {
        return new Action();
    }

    /**
     * Create an instance of {@link Dashboardentry }
     * 
     */
    public Dashboardentry createDashboardentry() {
        return new Dashboardentry();
    }

    /**
     * Create an instance of {@link Header }
     * 
     */
    public Header createHeader() {
        return new Header();
    }

    /**
     * Create an instance of {@link Button }
     * 
     */
    public Button createButton() {
        return new Button();
    }

    /**
     * Create an instance of {@link DashboardRecord }
     * 
     */
    public DashboardRecord createDashboardRecord() {
        return new DashboardRecord();
    }

    /**
     * Create an instance of {@link Dashboard }
     * 
     */
    public Dashboard createDashboard() {
        return new Dashboard();
    }

    /**
     * Create an instance of {@link TreeRecord }
     * 
     */
    public TreeRecord createTreeRecord() {
        return new TreeRecord();
    }

    /**
     * Create an instance of {@link CalendarRecord }
     * 
     */
    public CalendarRecord createCalendarRecord() {
        return new CalendarRecord();
    }

    /**
     * Create an instance of {@link Keren }
     * 
     */
    public Keren createKeren() {
        return new Keren();
    }

    /**
     * Create an instance of {@link FormRecord }
     * 
     */
    public FormRecord createFormRecord() {
        return new FormRecord();
    }

    /**
     * Create an instance of {@link Sheet }
     * 
     */
    public Sheet createSheet() {
        return new Sheet();
    }

    /**
     * Create an instance of {@link Group }
     * 
     */
    public Group createGroup() {
        return new Group();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.keren.net/keren", name = "template")
    public JAXBElement<String> createTemplate(String value) {
        return new JAXBElement<String>(_Template_QNAME, String.class, null, value);
    }

}
