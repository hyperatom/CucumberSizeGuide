package sizeguide;

public class UrlConstructor {

    private static final String QUERY_URL_1 = "/?sortBy=product.best_selling|1&display=product&resultsPerPage=";
    private static final String QUERY_URL_2 = "&pageChoice=1&cachedFilters=%20generic-1090+product+";
    private static final String QUERY_URL_3 = "+0+480+0+480";

    /**
     * Constructs a full PLP url.
     *
     * @param urlTag Tag portion of the url, e.g. l/kids/all-boys
     * @param topBestSellers Number of bestsellers to display on the PLP.
     * @return Full url of PLP page.
     */
    public static String constructPlpUrl(String urlTag, int topBestSellers) {
        String fullQueryUrl = QUERY_URL_1 + topBestSellers + QUERY_URL_2 + topBestSellers + QUERY_URL_3;
        return Config.BASE_URL + urlTag + fullQueryUrl;
    }
}
