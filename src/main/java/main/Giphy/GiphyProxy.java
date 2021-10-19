package main.Giphy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "giphy", url = "${giphy.url}")
public interface GiphyProxy {
    @GetMapping("?api_key=${giphy.api_id}&q=rich&limit=1&offset={random}")
    public GiphyBean richGiphy(@PathVariable(name = "random") int random);

    @GetMapping("?api_key=${giphy.api_id}&q=broke&limit=1&offset={random}")
    public GiphyBean brokeGiphy(@PathVariable(name = "random") int random);
}
