//package twitch4jTemp.graphql.command;
//
//import com.apollographql.apollo.ApolloCall;
//import com.apollographql.apollo.ApolloClient;
//import twitch4jTemp.graphql.internal.FollowMutation;
//import twitch4jTemp.graphql.internal.type.FollowUserInput;
//
///**
// * Follow User
// */
//public class CommandFollowUser extends BaseCommand<FollowMutation.Data> {
//
//    private final Long targetUserId;
//
//    private final Boolean goLiveNotification;
//
//    public CommandFollowUser(ApolloClient apolloClient, Long targetUserId, Boolean goLiveNotification) {
//        super(apolloClient);
//        this.targetUserId = targetUserId;
//        this.goLiveNotification = goLiveNotification;
//    }
//
//    @Override
//    protected ApolloCall getGraphQLCall() {
//        ApolloCall apolloCall = apolloClient.mutate(
//            FollowMutation.builder()
//                .followUserInput(
//                    FollowUserInput.builder()
//                        .targetID(targetUserId.toString())
//                        .disableNotifications(goLiveNotification ? false : true)
//                        .build()
//                )
//                .build()
//        );
//
//        return apolloCall;
//    }
//
//}
