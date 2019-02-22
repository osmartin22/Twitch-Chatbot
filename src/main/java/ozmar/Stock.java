package ozmar;

public class Stock {
    String stockName;
    String stockSymbol;
    String price;
    long lastModified;

    public Stock() {

    }

    public Stock(String stockName, String stockSymbol, String price, long lastModified) {
        this.stockName = stockName;
        this.stockSymbol = stockSymbol;
        this.price = price;
        this.lastModified = lastModified;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}