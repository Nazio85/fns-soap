
package pro.x_way.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebService(name = "FNSNDSCAWS2_Port", targetNamespace = "http://ws.unisoft")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
        ObjectFactory.class
})
public interface FNSNDSCAWS2Port {


    @WebMethod(operationName = "NdsRequest2", action = "NdsRequest2")
    @WebResult(name = "NdsResponse2", targetNamespace = "http://ws.unisoft/FNSNDSCAWS2/Response",
            partName = "np_out")
    public NdsResponse2 ndsRequest2(
            @WebParam(name = "NdsRequest2", targetNamespace = "http://ws.unisoft/FNSNDSCAWS2/Request", partName = "np_in")
                    NdsRequest2 npIn);

}