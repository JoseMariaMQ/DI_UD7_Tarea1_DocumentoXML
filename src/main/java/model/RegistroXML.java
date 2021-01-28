package model;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class RegistroXML {

    public void documentoXML(Registro nuevoRegistro) {
        try {
            //Definimos el objeto que permite producir árboles de objetos DOM a partir de XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Definimos el objeto para obtener una instancia para poder analizar XML
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Interfaz que representa el documento XML
            Document documento;

            //Comprobamos si existe el fichero XML, si no existe lo creamos, si existe añadimos.
            File archivo = new File("RegistrosEntregas.xml");
            if (!archivo.exists()) { //Si no existe creamos
                DOMImplementation implementation = builder.getDOMImplementation();

                documento = implementation.createDocument(null, "registros", null);
                documento.setXmlVersion("1.0");
            } else { //Si existe lo referenciamos para modificarlo
                documento = builder.parse(new File("RegistrosEntregas.xml"));
            }

            //Interfaz que representa los elementos dentro de un XML
            Element registro = documento.createElement("registro");

            //Creamos la etiqueta fechaEntrega
            Element fecha = documento.createElement("fechaEntrega");
            //Le creamos un "hijo" de tipo texto con los datos introducidos
            Text textFecha = documento.createTextNode(nuevoRegistro.getFecha().toString());
            //Se lo agregamos a la etiqueta fechaEntrega
            fecha.appendChild(textFecha);
            //Agregamos la etiqueta fechaEntrega con su nodo hijo de tipo texto a la etiqueta registro
            registro.appendChild(fecha);

            //Creamos la etiqueta codigoPostal
            Element codPostal = documento.createElement("codigoPostal");
            //Creamos un nodo de tipo texto con los datos introducidos
            Text textCP = documento.createTextNode(String.valueOf(nuevoRegistro.getCodPostal()));
            //Se lo agregamos a la etiqueta codigoPostal
            codPostal.appendChild(textCP);
            //Agregamos la etiqueta codigoPostal con su nodo hijo de tipo texto a la etiqueta registro
            registro.appendChild(codPostal);

            //Creamos la etiqueta clienteDNI
            Element dniCliente = documento.createElement("clienteDNI");
            //Creamos nodo de tipo texto con los datos introducidos
            Text textDNI = documento.createTextNode(nuevoRegistro.getDniCliente());
            //Se lo agregamos a la etiqueta clienteDNI
            dniCliente.appendChild(textDNI);
            //Agregamos la etiqueta clienteDNI con su nodo hijo de tipo texto a la etiqueta registro
            registro.appendChild(dniCliente);

            //Creamos la etiqueta repartidorId
            Element idRepartidor = documento.createElement("repartidorId");
            //Creamos nodo de tipo texto con los datos introducidos
            Text textId = documento.createTextNode(String.valueOf(nuevoRegistro.getIdRepartidor()));
            //Se lo agregamos a la etiqueta repartidorId
            idRepartidor.appendChild(textId);
            //Agregamos la etiqueta repartidorId con su nodo hijo de tipo texto a la etiqueta registro
            registro.appendChild(idRepartidor);

            //Agregamos al documento todas las etiquetas dentro de la etiqueta base registros
            documento.getDocumentElement().appendChild(registro);

            //Creamos la fuente de entrada para el documento XML
            Source source = new DOMSource(documento);
            //Le introducimos la información para construir el árbol
            Result result = new StreamResult(new File("RegistrosEntregas.xml"));

            //Procesamos los datos y los escribimos
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);

        } catch (ParserConfigurationException | TransformerException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
