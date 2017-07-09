# Algorithms - Coursera
## Week1 - Percolation
### Percolation Class
In order to judge if a system percolates, we created a virtual top and a virtual bottom. However, due to the existence of the virtual bottom, grids in the bottom line will be viewed as being connected with the virtual top, so that they will be wronly considered as "full sites". Therefore, we need to build another n by n grid without a virtual bottom to help us check if grids in the bottom line are "full sites".
### PercolationStats Class
MCMC simulation to find the threshold P. Everytime we simulate a pair of numbers(row and column), we need check if the site at this position has been open, otherwise it will waste a a lot of time implementing open operations on open sites. 
## Week2 - Dequeue and Randomized Queues
### Dequeue Class
Must have first node and last node to implement add/remove items at first or at last.
### RandomizedQueue Class
Used resized array to implement this class. The initialization of an array is implemented element by element and you cannot use itr_a = a to assign all a values to itr_a.
## Week3 - Collinear Points
### Point Class
### BruteForceSearch
### FastSearch
