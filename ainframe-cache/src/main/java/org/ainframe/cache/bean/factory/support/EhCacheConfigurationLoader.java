package org.ainframe.cache.bean.factory.support;

import lombok.extern.slf4j.Slf4j;
import org.ainframe.core.io.PathsMatchingResourceResolver;
import org.ainframe.core.util.Label;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 13.
 */
@Slf4j
public class EhCacheConfigurationLoader {
    private PathMatchingResourcePatternResolver pathMatching = new PathMatchingResourcePatternResolver();
    private PathsMatchingResourceResolver pathsMatching
            = new PathsMatchingResourceResolver();

    private final String ehcacheLocation;
    private final String[] cacheLocation;
    private String charset = "UTF-8";

    public EhCacheConfigurationLoader(String ehcacheLocation, String[] cacheLocation) {
        this.ehcacheLocation = ehcacheLocation;
        this.cacheLocation = cacheLocation;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    private Document getEhcacheXmlLoader() throws EhCacheConfigurationException {
        try {
            Label label = new Label().title("EHCACHE - Load configuration XML for Ehcache.");

            Resource resource = pathMatching.getResource(ehcacheLocation);

            if (resource.exists()) {
                label.first(resource.getURI());
            }
            label.debug();
            Assert.isTrue(resource.exists(), "The ehcache configuration XML could not be found.");
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(resource.getURI().toString());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new EhCacheConfigurationException(e);
        }
    }

    private List<Node> getCacheXmlLoader() throws EhCacheConfigurationException {
        try {
            Label label = new Label().title("EHCACHE - Load configuration XML for cache.");

            List<Node> result = new ArrayList<>();
            Resource[] resources = pathsMatching.getResources(cacheLocation);

            for (Resource resource : resources) {
                if (resource.exists()) {
                    label.first("Config XML Loader: ").add(resource.getURI());
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    Document document = dbf.newDocumentBuilder().parse(resource.getURI().toString());

                    Element rootElement = document.getDocumentElement();

                    NodeList list = rootElement.getChildNodes();
                    int length = list.getLength();

                    for (int i = 0; i < length; i++) {
                        Node node = list.item(i);
                        if ("cache".equals(node.getNodeName())) {
                            result.add(node);
                        }
                    }
                }
            }

            label.debug();

            return result;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.error(e.getMessage(), e);
            throw new EhCacheConfigurationException(e);
        }
    }

    public Resource getResource() throws EhCacheConfigurationException {
        Label label = new Label()
            .title("EHCACHE - Create configuration XML for Ehcache and create the resource as a result.")
            .first("ehcacheLocation: ").add(ehcacheLocation)
            .first("cacheLocation: ").add(StringUtils.join(cacheLocation))
            .first("charset: ").add(charset);
        try {
            Document document = this.getEhcacheXmlLoader();
            Element rootElement = document.getDocumentElement();

            List<Node> nodes = this.getCacheXmlLoader();

            for (Node node : nodes) {
                Node newNode = document.importNode(node, true);
                rootElement.appendChild(newNode);
            }

            DOMSource source = new DOMSource(document);

            StringWriter xmlAsWriter = new StringWriter();
            StreamResult result = new StreamResult(xmlAsWriter);

            TransformerFactory.newInstance().newTransformer().transform(source, result);
            String xml = xmlAsWriter.toString();
            InputStream inputStream = new ByteArrayInputStream(xml.getBytes(charset));

            label.first("ehcache.xml >>>").br().add(xml);

            return new InputStreamResource(inputStream);
        } catch (IOException | TransformerException e) {
            log.error(e.getMessage(), e);
            throw new EhCacheConfigurationException(e);
        } finally {
            label.debug();
        }
    }
}
