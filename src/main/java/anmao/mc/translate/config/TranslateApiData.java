package anmao.mc.translate.config;

public class TranslateApiData {
    private String url;
    private String type;
    private String head;
    private String data;
    private String result;

    public String getUrl() {
        return url;
    }
    public String getUrl(String source){
        return getUrl().replace("<source>",source);
    }

    public String getType() {
        return type;
    }

    public String getHead() {
        return head;
    }

    public String getData() {
        return data;
    }
    public String getResult(){return result;}

    @Override
    public String toString() {
        return "TranslateApiData{" +
                "url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", head='" + head + '\'' +
                ", data='" + data + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
