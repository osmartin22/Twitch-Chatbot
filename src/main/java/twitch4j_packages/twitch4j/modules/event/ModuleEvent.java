package twitch4j_packages.twitch4j.modules.event;

import com.github.philippheuer.events4j.domain.Event;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import twitch4j_packages.twitch4j.modules.IModule;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
abstract class ModuleEvent extends Event {
    private final IModule module;
}
