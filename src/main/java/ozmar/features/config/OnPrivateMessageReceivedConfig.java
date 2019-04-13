package ozmar.features.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ozmar.features.OnPrivateMessageReceived;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeController;

import javax.annotation.Nonnull;

@Configuration
public class OnPrivateMessageReceivedConfig {

    @Bean
    public OnPrivateMessageReceived onPrivateMessageReceivedBean(@Nonnull PokeController pokeController) {
        return new OnPrivateMessageReceived(pokeController);
    }

}
