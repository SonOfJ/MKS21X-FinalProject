# MKS21X-FinalProject
INSTRUCTIONS: Move around with the arrow keys.
Compile: javac -cp lanterna.jar:. World.java
Run: java -cp lanterna.jar:. World (World #)
NOTE: The only available world at the moment is World 1.

1/4/19
Jason: Had to experiment with PuTTY because GitBASH for window does not support special characters. Downloaded PuTTY and learned how to SSH to the school server. Uploaded lanterna.jar and TerminalDemo.java to GitHub.
1/6/19
Jason: Tried to use atom on PuTTY. It did not work. Tried to find other text editors that might work with PuTTY. Really don't want to have to transfer files from school server to computer all the time.
1/7/19
Jason: Downloaded and experimented with FileZilla. Learned how to move files from bart.stuy.edu to PC. Took away some of the features in the provided TerminalDemo.java that won't be needed.
Eliza: Testing functionality of lanterna. Looking for the code of characters to print:
http://www.columbia.edu/kermit/ascii.html
1/8/19
Eliza: Research and familiarize the coding for 2D game: https://www.youtube.com/watch?v=dEKs-3GhVKQ&index=1&list=PLah6faXAgguMnTBs3JnEJY0shAc18XYQZ
1/9/19
Jason: Did some failed experiments with JFrames.
Eliza: Started on creating things/player/avatar.
1/11/19
Eliza: Research other rogue-like game using lanterna:
https://github.com/mgiaramita/Lantern-Adventures
1/12/19
Jason: Finalized the boundaries. Added code to test argument inputs. Changed names of some of the classes, finished up with launcher class, and started work on maps.
1/13/19
Jason & Eliza: Finalized map and print function. Created working walls as well.
<<<<<<< HEAD
1/16/19
Jason: Experimented with BufferedReader in order to find a way to read maps in text files.
1/17/19
Jason: Tried to adjust blocks of code so that a map with a size that is not 50x50 can be printed. Worked on storing maps in text files in 2D arrays.
1/18/19
Jason: Finally made it so that maps can be stored in text files and read with a scanner.





THOUGHTS:
1. create map files to read from
2. remind user to change terminal size
3. create monster on tile
4. battle screen change
=======
1/14/19
Jason & Eliza: Enlightened by Mr. K regarding the arts of resizing the terminal and adjusting map size. Received recommendation to move maps into text files.
>>>>>>> 3b3b683e3be766be60f1aa853783aad0ce228e45
