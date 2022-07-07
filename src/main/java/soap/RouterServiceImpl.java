
package soap;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.2
 * 
 */
@WebService(name = "RouterServiceImpl", targetNamespace = "http://router/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface RouterServiceImpl {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sendNotification", targetNamespace = "http://router/", className = "soap.SendNotification")
    @ResponseWrapper(localName = "sendNotificationResponse", targetNamespace = "http://router/", className = "soap.SendNotificationResponse")
    @Action(input = "http://router/RouterServiceImpl/sendNotificationRequest", output = "http://router/RouterServiceImpl/sendNotificationResponse")
    public String sendNotification(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sendReport", targetNamespace = "http://router/", className = "soap.SendReport")
    @ResponseWrapper(localName = "sendReportResponse", targetNamespace = "http://router/", className = "soap.SendReportResponse")
    @Action(input = "http://router/RouterServiceImpl/sendReportRequest", output = "http://router/RouterServiceImpl/sendReportResponse")
    public String sendReport(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
