package bohdan;

public class DocumentDecorator implements Document{
    private Document document;
    // public DocumentDecorator(Document document2) {
    //     //TODO Auto-generated constructor stub
    // }
    public DocumentDecorator(Document document) {
        this.document = document;
    }
    @Override
    public String parse(String path) {
        return this.document.parse(path);
    }
}
