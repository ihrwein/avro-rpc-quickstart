package example.message;

public class JsonMessage {

    private String to;
    private String from;
    private String body;
    private String repr;

    public JsonMessage(String to, String from, String body) {
        this.to = to;
        this.from = from;
        this.body = body;
        this.repr = null;
    }

    @Override
    public String toString() {
        if (repr == null) {
            StringBuilder builder = new StringBuilder( to.length() + from.length() + body.length() + 10);
            builder.append("{ 'to': '").append(to).append("', 'from' : '").append(from).append("', 'body' : '").append(body).append("' }");
            repr = builder.toString();
        }

        return repr;
    }
}
