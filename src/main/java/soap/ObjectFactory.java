
package soap;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap package. 
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

    private final static QName _SendNotification_QNAME = new QName("http://router/", "sendNotification");
    private final static QName _SendNotificationResponse_QNAME = new QName("http://router/", "sendNotificationResponse");
    private final static QName _SendReport_QNAME = new QName("http://router/", "sendReport");
    private final static QName _SendReportResponse_QNAME = new QName("http://router/", "sendReportResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendNotification }
     * 
     */
    public SendNotification createSendNotification() {
        return new SendNotification();
    }

    /**
     * Create an instance of {@link SendNotificationResponse }
     * 
     */
    public SendNotificationResponse createSendNotificationResponse() {
        return new SendNotificationResponse();
    }

    /**
     * Create an instance of {@link SendReport }
     * 
     */
    public SendReport createSendReport() {
        return new SendReport();
    }

    /**
     * Create an instance of {@link SendReportResponse }
     * 
     */
    public SendReportResponse createSendReportResponse() {
        return new SendReportResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendNotification }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SendNotification }{@code >}
     */
    @XmlElementDecl(namespace = "http://router/", name = "sendNotification")
    public JAXBElement<SendNotification> createSendNotification(SendNotification value) {
        return new JAXBElement<SendNotification>(_SendNotification_QNAME, SendNotification.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendNotificationResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SendNotificationResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://router/", name = "sendNotificationResponse")
    public JAXBElement<SendNotificationResponse> createSendNotificationResponse(SendNotificationResponse value) {
        return new JAXBElement<SendNotificationResponse>(_SendNotificationResponse_QNAME, SendNotificationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendReport }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SendReport }{@code >}
     */
    @XmlElementDecl(namespace = "http://router/", name = "sendReport")
    public JAXBElement<SendReport> createSendReport(SendReport value) {
        return new JAXBElement<SendReport>(_SendReport_QNAME, SendReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendReportResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SendReportResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://router/", name = "sendReportResponse")
    public JAXBElement<SendReportResponse> createSendReportResponse(SendReportResponse value) {
        return new JAXBElement<SendReportResponse>(_SendReportResponse_QNAME, SendReportResponse.class, null, value);
    }

}
