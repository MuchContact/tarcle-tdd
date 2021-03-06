package com.tarcle.moment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tarcle.moment.R;
import com.tarcle.moment.model.Comment;
import com.tarcle.moment.model.Role;
import com.tarcle.moment.model.Topic;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TopicCardView extends RelativeLayout {

    @Bind(R.id.user_avatar)
    protected ImageView userAvatar;

    @Bind(R.id.role_name)
    protected TextView roleName;

    @Bind(R.id.content)
    protected TextView topicContent;

    @Bind(R.id.images_container)
    protected GridView imageContainer;

    @Bind(R.id.formatted_time)
    protected TextView formattedTime;

    @Bind(R.id.like)
    protected TextView likeButton;

    @Bind(R.id.remark)
    protected TextView remarkButton;

    @Bind(R.id.social_container)
    protected View socialContainer;

    @Bind(R.id.followers)
    protected TextView followersText;

    public TopicCardView(Context context) {
        super(context);
    }

    public TopicCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TopicCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);
    }

    public void showTopic(Topic topic) {
        showAuthorInfo(topic.getAuthor());
        showImages(topic.getImages());
        showSocialContainer(topic.getFollowers(), topic.getComments());
        topicContent.setText(topic.getContent());
        formattedTime.setText(topic.getTime());
    }

    private void showSocialContainer(List<Role> followers, List<Comment> comments) {
        if (followers == null && comments == null) {
            socialContainer.setVisibility(View.GONE);
        } else {
            socialContainer.setVisibility(View.VISIBLE);
            followersText.setText(formattedFollowers(followers));
            // TODO show comments
        }
    }

    private String formattedFollowers(List<Role> followers) {
        if (followers == null) return "";

        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < followers.size(); index++) {
            Role role = followers.get(index);
            builder.append(role.getName());
            if (index + 1 < followers.size()) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }

    private void showImages(String[] images) {
        if (images == null || images.length == 0) {
            imageContainer.setVisibility(View.GONE);
        } else {
            imageContainer.setVisibility(View.VISIBLE);
            //TODO load the image to container
        }
    }

    private void showAuthorInfo(Role author) {
        Picasso.with(getContext()).load(author.getAvatar()).into(userAvatar);
        roleName.setText(author.getName());
    }

}
