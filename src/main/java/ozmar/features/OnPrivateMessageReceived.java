package ozmar.features;

import com.github.philippheuer.events4j.annotation.EventSubscriber;
import ozmar.pokemonBattle.pokemonBattleHelpers.PokeController;
import twitch4j_packages.common.events.user.PrivateMessageEvent;

public class OnPrivateMessageReceived {

    private final PokeController controller;

    public OnPrivateMessageReceived(PokeController controller) {
        this.controller = controller;
    }

    @EventSubscriber
    public void onPrivateMessage(PrivateMessageEvent event) {
        System.out.println("HELLO: " + event.toString());

//         BASIC TEST
//        if (event.getUser().getName().toLowerCase().equals("namedauto")) {
//            if (event.getMessage().equals("!start")) {
//                Map<Long, String> userMap = new HashMap<>();
//                userMap.put(1L, "RED");
//                userMap.put(2L, "BLUE");
//                PokeBattleViewInterface view = new PokeBattleView(event.getTwitchChat(), "namedauto", userMap);
//                controller.setView(view);
//                controller.setUsers(1, 2);
//
//            } else if (event.getMessage().startsWith("!move ")) {
//                String input = event.getMessage().substring(6);
//                String[] positions = input.split("\\s+");
//                int[] numArray = new int[3];
//                for (int i = 0; i < 3; i++) {
//                    numArray[i] = Integer.parseInt(positions[i]);
//                }
//                controller.setMoveToUse(numArray[0], numArray[1], numArray[2]);
//
//            } else if (event.getMessage().startsWith("!switch ")) {
//                String input = event.getMessage().substring(8);
//                String[] positions = input.split("\\s+");
//                int[] numArray = new int[3];
//                for (int i = 0; i < 3; i++) {
//                    numArray[i] = Integer.parseInt(positions[i]);
//                }
//                controller.setPokeToSwitchIn(numArray[0], numArray[1], numArray[2]);
//            }
//        }
//
//        System.out.println();
//        event.getTwitchChat().sendMessage(event.getUser().getName(), "namedauto");

    }
}
