package com.sacak.forumcivillian.utility.data;

import com.sacak.forumcivillian.entity.Comment;

import java.util.List;

public class CommentGenerator {

    public static List<Comment> generateComments() {
        // Post 1 comments
        Comment comment1 = Comment.builder().userId(1L).postId(1L).content("Planting trees is a great way to combat climate change!").build();
        Comment comment2 = Comment.builder().userId(2L).postId(1L).content("Totally agree! Trees also provide shade and improve air quality.").build();
        Comment comment3 = Comment.builder().userId(3L).postId(1L).content("We should organize a community tree-planting event.").build();
        Comment comment4 = Comment.builder().userId(1L).postId(1L).content("I’ve heard that tree planting helps preserve biodiversity as well.").build();
        Comment comment5 = Comment.builder().userId(2L).postId(1L).content("Yes, it’s also great for mental health to be surrounded by nature.").build();
        Comment comment6 = Comment.builder().userId(3L).postId(1L).content("Do you think we should focus on native trees for planting?").build();
        Comment comment7 = Comment.builder().userId(1L).postId(1L).content("Yes, native trees are better for the local ecosystem.").build();
        Comment comment8 = Comment.builder().userId(2L).postId(1L).content("What tree species would you recommend planting around here?").build();
        Comment comment9 = Comment.builder().userId(3L).postId(1L).content("I recommend oak and maple trees. They’re sturdy and beneficial.").build();
        Comment comment10 = Comment.builder().userId(1L).postId(1L).content("I think planting trees in urban areas is also very important.").build();

        // Post 2 comments
        Comment comment11 = Comment.builder().userId(1L).postId(2L).content("My favorite rainy-day movie is 'The Shawshank Redemption'.").build();
        Comment comment12 = Comment.builder().userId(2L).postId(2L).content("Great choice! I’d add 'The Godfather' to the list.").build();
        Comment comment13 = Comment.builder().userId(3L).postId(2L).content("Don’t forget about 'Inception'—a perfect mind-bender for rainy days.").build();
        Comment comment14 = Comment.builder().userId(1L).postId(2L).content("Another great rainy-day movie is 'Forrest Gump'.").build();
        Comment comment15 = Comment.builder().userId(2L).postId(2L).content("Good one! Also, 'The Dark Knight' is perfect for the mood.").build();
        Comment comment16 = Comment.builder().userId(3L).postId(2L).content("Definitely! 'Interstellar' is also a fantastic choice.") .build();
        Comment comment17 = Comment.builder().userId(1L).postId(2L).content("I enjoy 'The Green Mile' too, it's such a heartwarming story.").build();
        Comment comment18 = Comment.builder().userId(2L).postId(2L).content("I love rewatching 'The Matrix' on rainy days!").build();
        Comment comment19 = Comment.builder().userId(3L).postId(2L).content("How about 'The Pursuit of Happyness'? Perfect for a rainy afternoon.").build();
        Comment comment20 = Comment.builder().userId(1L).postId(2L).content("A classic rainy-day movie is 'The Breakfast Club'.").build();

        // Post 3 comments
        Comment comment21 = Comment.builder().userId(1L).postId(3L).content("I love using warm lighting and cozy blankets in winter decor.").build();
        Comment comment22 = Comment.builder().userId(2L).postId(3L).content("Adding some scented candles can make the room feel even cozier!").build();
        Comment comment23 = Comment.builder().userId(3L).postId(3L).content("I like using neutral tones with a splash of festive red for the season.").build();
        Comment comment24 = Comment.builder().userId(1L).postId(3L).content("Fairy lights and a hot cup of cocoa complete the winter atmosphere.").build();
        Comment comment25 = Comment.builder().userId(2L).postId(3L).content("A big comfy couch with soft throws is a must-have in winter.").build();
        Comment comment26 = Comment.builder().userId(3L).postId(3L).content("I love decorating with pinecones and rustic wooden pieces.").build();
        Comment comment27 = Comment.builder().userId(1L).postId(3L).content("I usually go for a minimalist style with some vintage decorations.").build();
        Comment comment28 = Comment.builder().userId(2L).postId(3L).content("A mix of modern and traditional styles can really make the room pop.").build();
        Comment comment29 = Comment.builder().userId(3L).postId(3L).content("I like to add a winter-themed rug to make the room feel more festive.").build();
        Comment comment30 = Comment.builder().userId(1L).postId(3L).content("What do you think about using faux fur for winter decorations?").build();

        // Post 4 comments
        Comment comment31 = Comment.builder().userId(1L).postId(4L).content("Rock and roll in the '60s was such a transformative era.").build();
        Comment comment32 = Comment.builder().userId(2L).postId(4L).content("Agreed! The transition to electronic music in the '80s was also groundbreaking.").build();
        Comment comment33 = Comment.builder().userId(3L).postId(4L).content("Each decade brought something unique to music—it’s fascinating.").build();
        Comment comment34 = Comment.builder().userId(1L).postId(4L).content("The '90s grunge movement really changed the musical landscape.") .build();
        Comment comment35 = Comment.builder().userId(2L).postId(4L).content("I think the rise of hip-hop in the '80s had a major impact on culture.").build();
        Comment comment36 = Comment.builder().userId(3L).postId(4L).content("The '70s disco era also had a huge cultural influence.") .build();
        Comment comment37 = Comment.builder().userId(1L).postId(4L).content("Jazz in the '50s was an incredible movement for musical expression.") .build();
        Comment comment38 = Comment.builder().userId(2L).postId(4L).content("The '00s pop scene was so diverse, with artists like Britney Spears and NSYNC.") .build();
        Comment comment39 = Comment.builder().userId(3L).postId(4L).content("Every era has its defining genres. I love how music evolved over time.") .build();
        Comment comment40 = Comment.builder().userId(1L).postId(4L).content("I think the '60s folk movement had a huge cultural impact on society.") .build();

        // Distribute the remaining comments evenly across the posts
        Comment comment41 = Comment.builder().userId(2L).postId(1L).content("What’s your opinion on tree maintenance after planting?").build();
        Comment comment42 = Comment.builder().userId(3L).postId(1L).content("We should focus on maintaining the trees long-term, not just planting them.") .build();
        Comment comment43 = Comment.builder().userId(1L).postId(2L).content("Music is such a powerful way to bring people together, especially during tough times.") .build();
        Comment comment44 = Comment.builder().userId(3L).postId(2L).content("I agree! Music really connects people across cultures and generations.") .build();
        Comment comment45 = Comment.builder().userId(2L).postId(3L).content("Winter decorations are a fun way to reflect the season's spirit.") .build();
        Comment comment46 = Comment.builder().userId(1L).postId(3L).content("I think the combination of nature and winter decor is perfect.") .build();
        Comment comment47 = Comment.builder().userId(3L).postId(4L).content("Do you think digital music platforms are changing how we consume music?") .build();
        Comment comment48 = Comment.builder().userId(1L).postId(4L).content("Streaming services have definitely altered how we listen to music.") .build();
        Comment comment49 = Comment.builder().userId(2L).postId(4L).content("I think vinyl records are making a comeback! They're a great way to appreciate music.") .build();
        Comment comment50 = Comment.builder().userId(3L).postId(4L).content("The way music is consumed has definitely evolved, but live performances are still key.") .build();


        //sports comments

        Comment comment51 = Comment.builder().userId(1L).postId(5L).content("Esports is growing faster than traditional sports. Exciting times ahead!").build();
        Comment comment52 = Comment.builder().userId(2L).postId(5L).content("Definitely! Mobile gaming is a huge factor in this growth.").build();
        Comment comment53 = Comment.builder().userId(3L).postId(5L).content("Esports could be the future of sports in general!").build();
        // Add more comments for post1

        // Post 2 comments
        Comment comment54 = Comment.builder().userId(1L).postId(6L).content("Can't wait to watch these players! Who’s your top pick for 2024?").build();
        Comment comment55 = Comment.builder().userId(2L).postId(6L).content("I'm watching out for player X. They’ve been killing it this season.").build();
        Comment comment56 = Comment.builder().userId(3L).postId(6L).content("Football in 2024 is going to be wild! Can’t wait to see the new talent.").build();
        // Add more comments for post2

        // Post 3 comments
        Comment comment57 = Comment.builder().userId(1L).postId(7L).content("Basketball in Europe has so much potential! Teams are getting stronger each year.").build();
        Comment comment58 = Comment.builder().userId(2L).postId(7L).content("I agree. The level of competition in Europe is rising.").build();
        // Add more comments for post3

        // Post 4 comments
        Comment comment59 = Comment.builder().userId(1L).postId(8L).content("Track and field athletes truly deserve more recognition. Their discipline is unmatched.").build();
        Comment comment60 = Comment.builder().userId(2L).postId(8L).content("Absolutely! The amount of work that goes into training is insane.").build();
        // Add more comments for post4

        // Distribute the remaining comments evenly across the posts
        Comment comment61 = Comment.builder().userId(3L).postId(5L).content("What’s your favorite esports title to watch?").build();
        Comment comment62 = Comment.builder().userId(1L).postId(6L).content("I think football in 2024 is going to be all about the next-gen players.").build();
        // More comments up to comment 50
        return List.of(comment1, comment2, comment3, comment4, comment5, comment6, comment7, comment8, comment9, comment10,
                comment11, comment12, comment13, comment14, comment15, comment16, comment17, comment18, comment19, comment20,
                comment21, comment22, comment23, comment24, comment25, comment26, comment27, comment28, comment29, comment30,
                comment31, comment32, comment33, comment34, comment35, comment36, comment37, comment38, comment39, comment40,
                comment41, comment42, comment43, comment44, comment45, comment46, comment47, comment48, comment49, comment50,
                comment51,comment52,comment53,comment54,comment55,comment56,comment57,comment58,comment59,comment60,comment61,comment62
        );
    }
}
