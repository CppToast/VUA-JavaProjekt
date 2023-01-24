/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.parsers.rss;

import cfg.Properties;
import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import java.util.List;
import hr.algebra.model.Article;
import hr.algebra.model.Category;
import hr.algebra.utils.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Fishie
 */
public class ArticleParser {

    private static final String RSS_URL = "https://www.indieretronews.com/feeds/posts/default?alt=rss";
    private static final String ATT_URL = "url";

    public static List<Article> parse() throws IOException, XMLStreamException {
        List<Article> articles = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);
        InputStream is = con.getInputStream();
        XMLEventReader reader = ParserFactory.createStaxParser(is);

        Optional<TagType> tagType = Optional.empty();
        Article article = null;
        StartElement startElement = null;

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    
                    tagType = TagType.from(qName);

                    if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                        article = new Article();
                        articles.add(article);
                    }

                    if(tagType.isPresent() && tagType.get().equals(TagType.IMAGE)){
                        if(startElement != null && article.getImagePath().isEmpty()) {
                            Attribute att = startElement.getAttributeByName(new QName(ATT_URL));
                            if(att != null){
                                handlePicture(article, att.getValue());
                            }
                        }
                    }
                    
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (tagType.isPresent() && article != null) {
                        Characters characters = event.asCharacters();
                        String data = characters.getData();
                        
                        switch (tagType.get()) {
                            case TITLE:
                                if(!data.isEmpty()) {
                                    if(article.getTitle().isEmpty()){
                                        article.setTitle(data);
                                    } else {
                                        article.setTitle(article.getTitle() + data);
                                    }
                                    
                                }
                                break;
                            case PUB_DATE:
                                if(!data.isEmpty()) {
                                    LocalDateTime date = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                    article.setPublishedDate(date.toLocalDate());
                                }
                                break;
                            case BODY:
                                if(!data.isEmpty()) {
                                    if(article.getBody().isEmpty()){
                                        article.setBody(data);
                                    } else {
                                        article.setBody(article.getBody() + data);
                                    }
                                }
                                break;
                            case CATEGORY:
                                if(!data.isEmpty()) {
                                    article.addCategory(new Category(-1, data));
                                }
                                break;
                            
                        }
                    }
                    break;

            }
        }
        return articles;
    }

    private static void handlePicture(Article article, String url) {
        String name = UUID.randomUUID().toString();
        String localPath = System.getProperty("user.dir") + Properties.IMAGE_DIR;
                
        try {
            FileUtils.copyFromUrl(url, localPath + name);
            article.setImagePath(name);
        } catch (IOException ex) {
            Logger.getLogger(ArticleParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        PUB_DATE("pubDate"),
        BODY("summary"),
        IMAGE("thumbnail"),
        CATEGORY("category");

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }

            return Optional.empty();
        }
    }

}
