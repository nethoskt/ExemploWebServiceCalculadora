package br.senac.pi.exemplowebservicecalculadora;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by Aluno on 30/11/2015.
 */
public class CalculadoraWS {
    public  CalculadoraWS() {}
    public int add(int i, int j)throws IOException, XmlPullParserException  {
        SoapObject soapObject =  new SoapObject("http://calculator.me.org/","add");
        soapObject.addProperty("i", i);
        soapObject.addProperty("j", j);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);
        HttpTransportSE transportSE = new HttpTransportSE("http://10.19.14.15:8080/CalculatorApp/CalculatorWSService?wsdl");
        transportSE.call("add", envelope);
        Object resultado = envelope.getResponse();
        return Integer.parseInt(resultado.toString());
    }
}
