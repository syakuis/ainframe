package org.ainframe.cache.bean.factory.support;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.ainframe.core.io.PathsMatchingResourceResolver;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import lombok.extern.slf4j.Slf4j;

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
            Resource resource = pathMatching.getResource(ehcacheLocation);
            Assert.isTrue(resource.exists(), "ehcache xml not found.");
            log.debug("><>< Ehcache Loader: {}", resource.getURI());
            return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(resource.getURI().toString());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new EhCacheConfigurationException(e);
        }
    }

    private List<Node> getCacheXmlLoader() throws EhCacheConfigurationException {
        try {
            List<Node> result = new ArrayList<>();
            Resource[] resources = pathsMatching.getResources(cacheLocation);

            for (Resource resource : resources) {
                if (resource.exists()) {
                    log.debug("><>< Ehcache Loader: {}", resource.getURI());
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

            return result;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.error(e.getMessage(), e);
            throw new EhCacheConfigurationException(e);
        }
    }

    public Resource getResource() throws EhCacheConfigurationException {
        log.info("><>< Ehcache Setting Create XML");
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

            InputStream inputStream = new ByteArrayInputStream(xmlAsWriter.toString().getBytes(charset));
            return new InputStreamResource(inputStream);
        } catch (TransformerException | UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            throw new EhCacheConfigurationException(e);
        }
    }
}
