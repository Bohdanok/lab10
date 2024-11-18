package bohdan;

public class TImeDocument extends DocumentDecorator{
    public TImeDocument(Document document) {
        super(document);
    }

    public String parse(String path) {
        long startTime = System.nanoTime();

        String result =  super.parse(path);

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println("Elapsed time in nanoseconds: " + elapsedTime);

        return result;
    }
    
}
