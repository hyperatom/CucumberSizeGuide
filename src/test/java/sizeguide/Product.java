package sizeguide;

public class Product {

    private String url;
    private String code;

    public Product(String url, String code) {
        this.setUrl(url);
        this.setCode(code);
    }

    public String getUrl () {
        return url;
    }

    public void setUrl (String url) {
        this.url = url;
    }

    public String gettCode () {
        return code;
    }

    public void setCode (String tCode) {
        this.code = tCode;
    }
}
