package main.Giphy;

import java.util.List;

/**
 * Класс, на который происходить мэппинг json файла с данными gif.
 */
public class GiphyBean {
    List<Data> data;

    public GiphyBean() {
    }

    public GiphyBean(List<Data> data) {
        this.data = data;
    }

    public List<Data> getData() {
        return data;
    }
}


