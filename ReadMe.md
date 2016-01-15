Author: Clement Venard                   Tsinghua University                                        14-01-2016



                                    Combinatorics and Algorithms Project


HOW TO RUN THIS SOFTWARE.
-------------------------

Open terminal, locate the file and compile the  SeamCarver Java file.

Then run the SeamCarver file.

Now type in a value from 1 to 5 to test the arrays specified, you can add additional matrices by creating a file with
the name "X-in.txt", X must be an integer number > 5



WHAT THIS SOFTWARE DOES.
-------------------------

This software reads in a txt file and converts this to an array. It then determines the seams with the lowest values,
it then removes the number of seams as specified in the initial txt file.

Returning an array with the row length decreased by the number of seams required. The array is returned to the command
line.

FUTURE WORK.
-------------------------

Initial seam carving algorithm is set up to find minimum path through an array. Now need to use this on an image to be 
able to reduce the image size using seam carving.
