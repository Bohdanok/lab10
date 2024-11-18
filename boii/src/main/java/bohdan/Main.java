package bohdan;

public class Main {
    public static void main(String[] args) {
        Document document = new NewDocument();
        document = new CachedDocument(new TImeDocument(document));
        document.parse("NOhdan");

    }
}


