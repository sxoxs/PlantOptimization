package logic.parser;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import java.io.IOException;

public class XmlWriter {

    public void write(String fileName) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse(fileName);

            // Вызываем метод для добавления новой книги
            addNewBook(document);

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private static void addNewBook(Document document) throws TransformerFactoryConfigurationError, DOMException {
        // Получаем корневой элемент
        Node root = document.getDocumentElement();

        Element parametrs = document.createElement("AntParametrs");





        // Создаем новую книгу по элементам
        // Сама книга <Book>
        Element book = document.createElement("AntParameters");
        // <Title>
        Element title = document.createElement("Title");
        // Устанавливаем значение текста внутри тега
        title.setTextContent("Incredible book about Java");


        // Добавляем внутренние элементы книги в элемент <Book>
//        book.appendChild(title);
//        book.appendChild(author);
//        book.appendChild(date);
//        book.appendChild(isbn);
//        book.appendChild(publisher);
//        book.appendChild(cost);
//        // Добавляем книгу в корневой элемент
//        root.appendChild(book);
//
//        // Записываем XML в файл
//        writeDocument(document);
    }
}
