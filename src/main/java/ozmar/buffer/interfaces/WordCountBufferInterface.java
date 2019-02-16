package ozmar.buffer.interfaces;

import javax.annotation.Nonnull;
import java.util.Map;

public interface WordCountBufferInterface {

    @Nonnull
    Map<String, Integer> getMap();

    void clearMap();

    void updateWordCount(@Nonnull String word);
}
