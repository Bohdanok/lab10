package bohdan;
public class TimedDocument extends DocumentDecorator {
    static final double CONVERT_TO_MS = 1_000_000_000.0;

    public TimedDocument(Document document) {
        super(document);
    }

    public String parse(String path) {
        long startTime = System.nanoTime();

        String result =  super.parse(path);

        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        System.out.println(
            "Elapsed time in nanoseconds: " + elapsedTime/CONVERT_TO_MS
        );

        return result;
    }
}