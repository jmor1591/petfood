/*
 * Problem 2 Sell My Pet Food
 */
public class TargetedAd {

  public static void main(String[] args)
  {
    /*  
     * TODO:
     * PREPARATION WORK
     * (1) Create a file called targetWords.txt. Populate this file with words on each line that
     *     you think would determine if a user is a dog or cat owner.
     * 
     * PROGRAMMING
     * (2) Create a new DataCollector object and set the data to "socialMediaPostsSmall.txt" and "targetWords.txt"
     *     Important: Use the socialMedialPostsSmall to create your algorithm. Using a small file will help you 
     *     generate your solution quicker and give you the ability to double check your work.
     * (3) Create a String variable to hold the names of all the user. (The first word of every post is 
     *     a person's username)
     * (4) Compare each user's post to each target word. If a user mentions a target word, add their username to 
     *     the String of users. Separate usernames with a space. 
     *         Hint: You can use loops to look through each word. 
     *         Hint2: You can use indexOf to check if a word is in a user post. 
     * (5) Once you have all the users, use your DataCollector's prepareAdvertisement method to prepare a file 
     *     with all users and the advertisement you will send them.
     *         Additional Info: The prepareAdvertisement creates a new file on your computer. Check the posts of
     *         some of the usernames to make sure your algorithm worked.
     * 
     * THE FINAL SOLUTION
     * (6) Your solution should work with the socialMedialPostsSmall.txt. Modify your DataCollector initialization
     *    so you use the socialMediaPosts.txt. You should now have a larger file of users to target.
     */


    /* your code here */
    //creates new collector
    DataCollector collector = new DataCollector();
    //the collector is given "access"/takes from the social media posts and target words
    collector.setData("socialMediaPosts.txt", "targetWords.txt");

    //a place for the usernames to go is created
    String usernames = "";
    //initialize curPost to get the first post of the list
    String curPost = collector.getNextPost();
    //initializaes a target word so that each post analyzes every target word from the beginning of the targetWords.txt
    String target = "";

    //while there is at least 1 valid post left (is there a post to look through)
    while (curPost.indexOf(" ") != -1) {
      //collects the username for future use (better to grab now than later)
      String curName = curPost.substring(0, curPost.indexOf(" "));
      //gets the first target word of the list of target words
      target = collector.getNextTargetWord();
      //boolean to be used to determine if the username of the post being looked at has already been added to be
      //advertised to
      boolean present = false;
      //while we have not run out of target words
      while (!target.equals("NONE")) {
        //if a target word's letters are in a post (in the correct order) and the username
        //has not been added yet, then the username is added to 
        if (curPost.toLowerCase().indexOf(target) != -1 && !present) {
          usernames += curName + " ";
          present = true;
        }
        //gets the next target word for the current post
        target = collector.getNextTargetWord();
      }
      //at the end of looking at one post, the next post is cycled to be checked
      curPost = collector.getNextPost();
    }
    //prepares advertisement by creating a file of possible usernames that the ad should be targeted to
    collector.prepareAdvertisement("toSend.txt", usernames, "Check out this new, extremely nutritious pet food!");
  }
}
