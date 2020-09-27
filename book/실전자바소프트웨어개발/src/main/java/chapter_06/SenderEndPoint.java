package chapter_06;

import java.util.Objects;

public class SenderEndPoint {

  private final User user;
  private final Twootr twootr;

  public SenderEndPoint(User user, Twootr twootr) {
    Objects.requireNonNull(user, "user");
    Objects.requireNonNull(twootr, "twootr");

    this.user = user;
    this.twootr = twootr;
  }

  public FollowStatus onFollow(final String userIdToFollow) {
    Objects.requireNonNull(userIdToFollow, "userIdToFollow");

    return twootr.onFollow(user, userIdToFollow);
  }

  public void onSendTwoot(final String id, final User user, final String content) {
    final String userId = user.getId();
    final Twoot twoot = new Twoot(id, userId, content);

    user.followers()
      .filter(User::isLoggedOn)
      .forEach(follower -> follower.receiveTwoot(twoot));
  }

}
